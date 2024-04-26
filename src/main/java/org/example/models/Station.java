package org.example.models;

public class Station {
    private String stopId;
    private String stopName;
    private double latitude;
    private double longitude;

    public Station(String stopId, String stopName, double latitude, double longitude) {
        this.stopId = stopId;
        this.stopName = stopName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getStopId() {
        return stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}