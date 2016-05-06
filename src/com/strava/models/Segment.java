package com.strava.models;

public class Segment {
    public final double distance;
    public final int climbingCategory;
    public final double startLong;
    public final double startLat;

    public Segment(double distance, int climbingCategory, double startLong, double startLat) {
        this.distance = distance;
        this.climbingCategory = climbingCategory;
        this.startLong = startLong;
        this.startLat = startLat;
    }

    public Segment(){
        distance = 0.0;
        climbingCategory = 0;
        startLat = 0.0;
        startLong = 0.0;
    }
}
