package com.aruninba.doorconfig.data.model;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class LockReleaseTime {
    private Range range;
    private String unit;

    @SerializedName("default")
    private double myDefault;

    public LockReleaseTime(Range range, String unit, double myDefault) {
        this.range = range;
        this.unit = unit;
        this.myDefault = myDefault;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getMyDefault() {
        return myDefault;
    }

    public void setMyDefault(double myDefault) {
        this.myDefault = myDefault;
    }
}
