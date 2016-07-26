package com.splunk.sharedmc.loggable_events;

import com.splunk.sharedmc.utilities.Entity;
import com.splunk.sharedmc.utilities.Instrument;
import com.splunk.sharedmc.utilities.Point3d;

/**
 * Created by powerschill on 7/25/16.
 */
public class LoggableDeathEvent extends AbstractLoggableEvent {

    private Instrument weapon;
    private String cause;
    private Entity killer;
    private Entity victim;

    public LoggableDeathEvent(long gameTime, String minecraft_server, String world, Point3d dest, DeathEventAction action) {
        super(gameTime, minecraft_server, world, dest, "death", action.asString());
    }

    public Instrument getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Instrument weapon) {
        this.weapon = weapon;
    }

    public String getCause() {
        return this.cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Entity getKiller() {
        return this.killer;
    }

    public void setKiller(Entity entity) {
        this.killer = entity;
    }

    public void setKiller(String type, String name) {
        Entity entity = new Entity(type, name);
        setKiller(entity);
    }

    public Entity getVictim() {
        return this.victim;
    }

    public void setVictim(Entity entity) {
        this.victim = entity;
    }

    public void setVictim(String type, String name) {
        Entity entity = new Entity(type, name);
        setVictim(entity);
    }

    public enum DeathEventAction {
        CREATURE("creature_death"),
        PLAYER("player_death");

        private final String action;

        DeathEventAction(String action) {
            this.action = action;
        }

        public String asString() {
            return action;
        }
    }
}
