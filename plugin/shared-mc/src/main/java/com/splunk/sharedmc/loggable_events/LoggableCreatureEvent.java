package com.splunk.sharedmc.loggable_events;

import com.splunk.sharedmc.utilities.Entity;
import com.splunk.sharedmc.utilities.Point3d;

public class LoggableCreatureEvent extends AbstractLoggableEvent {


    private Entity entity;

    public LoggableCreatureEvent(long gameTime, String minecraft_server, String world, Point3d dest, EntityEventAction action) {
        super(gameTime, minecraft_server, world, dest, "creature", action.asString());
    }


    public Entity getEntity() {
        return this.entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setEntity(String type, String name) {
        Entity entity = new Entity(type, name);
        setEntity(entity);
    }

    public enum EntityEventAction {
        SPAWN("spawn");

        private final String action;

        EntityEventAction(String action) {
            this.action = action;
        }

        public String asString() {
            return action;
        }
    }
}
