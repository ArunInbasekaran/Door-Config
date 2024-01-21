package com.aruninba.doorconfig.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class LockAngle {
    private Range range;
    private String unit;

    @SerializedName("default")
    private int myDefault;
    private boolean common;

    public LockAngle(Range range, String unit, int myDefault, boolean common) {
        this.range = range;
        this.unit = unit;
        this.myDefault = myDefault;
        this.common = common;
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

    public int getMyDefault() {
        return myDefault;
    }

    public void setMyDefault(int myDefault) {
        this.myDefault = myDefault;
    }

    public boolean isCommon() {
        return common;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }
}
