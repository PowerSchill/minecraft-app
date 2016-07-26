package com.splunk.spigot.eventloggers;

import com.splunk.sharedmc.event_loggers.AbstractEventLogger;
import com.splunk.sharedmc.loggable_events.LoggableDeathEvent;
import com.splunk.sharedmc.loggable_events.LoggableDeathEvent.DeathEventAction;
import com.splunk.sharedmc.utilities.Instrument;
import com.splunk.sharedmc.utilities.Point3d;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by powerschill on 7/25/16.
 */
public class DeathEventLogger extends AbstractEventLogger implements Listener {

    public DeathEventLogger(Properties properties) {
        super(properties);
    }

    @EventHandler
    public void captureDeathEvent(EntityDeathEvent event) {

        // Default to creature death. Will override if actual player death.
        logAndSend(getLoggableDeathEvent(DeathEventAction.CREATURE, event));
    }

    private LoggableDeathEvent getLoggableDeathEvent(DeathEventAction action, EntityDeathEvent event) {

        final Entity victim = event.getEntity();
        final Location location = victim.getLocation();
        final World world = victim.getWorld();


        Point3d coordinates = new Point3d(location.getX(), location.getY(), location.getZ());

        LoggableDeathEvent deathEvent = new LoggableDeathEvent(world.getFullTime(), minecraft_server, world.getName(), coordinates, action);

        if (event instanceof PlayerDeathEvent) {
            // TODO switch this to use the enum.
            deathEvent.setAction("player_death");
            deathEvent.setVictim("player", victim.getName());


        } else {
            if (event.getEntityType() == EntityType.SKELETON) {
                Skeleton skeleton = (org.bukkit.entity.Skeleton) event.getEntity();
                deathEvent.setVictim("creature", skeleton.getSkeletonType() + "_SKELETON");

            } else {
                deathEvent.setVictim("creature", victim.getName());
            }
        }


        if (event.getEntity().getKiller() != null) {

            deathEvent.setKiller("player", event.getEntity().getKiller().getDisplayName());
            ItemStack instrument = event.getEntity().getKiller().getInventory().getItemInMainHand();
            Instrument tool = new Instrument(instrument.getType().toString());
            for (Enchantment key : instrument.getEnchantments().keySet()) {

                tool.addEnchantment(key.getName().toString(), instrument.getEnchantments().get(key));
            }


            deathEvent.setWeapon(tool);


        } else {
            // Creature did killing and we have to get killer from death message
            if (event instanceof PlayerDeathEvent) {
                // Only works if a player dies.

                Pattern regex = Pattern.compile("\\S* (was slain by|was shot by a|was blown up by) (?<killer>\\S*)");
                Matcher matcher = regex.matcher(((PlayerDeathEvent) event).getDeathMessage());

                if (matcher.matches()) {
                    deathEvent.setKiller("creature", matcher.group("killer"));
                }


            }
        }


        return deathEvent;
    }

}
