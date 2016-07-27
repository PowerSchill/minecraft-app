package com.splunk.sharedmc.utilities;

public class LivingEntity {

    private String type;
    private String name;
    private Point3d location;

    public LivingEntity(String type, String name) {
        this.type = type;
        this.name = name.replaceAll("§\\S", ""); // Remove the formatting codes;


    }
    public LivingEntity(String type, String name, Point3d location) {
        this.type = type;
        this.name = name.replaceAll("§\\S", ""); // Remove the formatting codes;
        this.location = location;

    }
}
