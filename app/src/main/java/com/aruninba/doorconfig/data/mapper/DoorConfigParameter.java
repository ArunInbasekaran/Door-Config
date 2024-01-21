package com.aruninba.doorconfig.data.mapper;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.aruninba.doorconfig.data.model.Range;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Arun Inba on 19/01/24.
 */
@Entity(tableName = "doorConfig")
public class DoorConfigParameter {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "primaryDoorConfig")
    private String primaryDoorConfig;
    @ColumnInfo(name = "secondaryDoorConfig")
    private String secondaryDoorConfig;
    @ColumnInfo(name = "values")
    private ArrayList<String> values;

    @Embedded
    private Range range;
    @ColumnInfo(name = "unit")
    private String unit;
    @JsonProperty("default")
    @ColumnInfo(name = "doorDefault")
    private String myDefault;

    @ColumnInfo(name = "common")
    private boolean common;

    public DoorConfigParameter(int id, String title, String primaryDoorConfig, String secondaryDoorConfig,
                               ArrayList<String> values, Range range, String unit,
                               String myDefault, boolean common) {
        this.title = title;
        this.primaryDoorConfig = primaryDoorConfig;
        this.secondaryDoorConfig = secondaryDoorConfig;
        this.values = values;
        this.range = range;
        this.unit = unit;
        this.myDefault = myDefault;
        this.common = common;
        this.id = id;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
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

    public String getMyDefault() {
        return myDefault;
    }

    public void setMyDefault(String myDefault) {
        this.myDefault = myDefault;
    }

    public boolean isCommon() {
        return common;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryDoorConfig() {
        return primaryDoorConfig;
    }

    public void setPrimaryDoorConfig(String primaryDoorConfig) {
        this.primaryDoorConfig = primaryDoorConfig;
    }

    public String getSecondaryDoorConfig() {
        return secondaryDoorConfig;
    }

    public void setSecondaryDoorConfig(String secondaryDoorConfig) {
        this.secondaryDoorConfig = secondaryDoorConfig;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
