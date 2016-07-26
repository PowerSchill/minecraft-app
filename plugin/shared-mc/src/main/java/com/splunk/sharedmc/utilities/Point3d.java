package com.splunk.sharedmc.utilities;

public class Point3d {
    public final double x;
    public final double y;
    public final double z;

    /**
     * Constructs a new point.
     */
    public Point3d(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;

        //Noticed performance issues on using Math.Round, apparently slower than this in Java 6 according to:
        // http://stackoverflow.com/questions/12091014/what-is-the-most-efficient-way-to-round-a-float-value-to-the
        // -nearest-integer-in , maybe add in http://labs.carrotsearch.com/junit-benchmarks.html to investigate
//
//        this.x = (long) (x + 0.5);
//        this.y = (long) (y + 0.5);
//        this.z = (long) (z + 0.5);
    }


}
