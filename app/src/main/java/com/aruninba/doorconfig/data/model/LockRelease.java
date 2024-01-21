package com.aruninba.doorconfig.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
/**
 * Created by Arun Inba on 19/01/24.
 */
public class LockRelease{

    private ArrayList<String> values;

    @SerializedName("default")
    private String myDefault;
    private boolean common;

    public LockRelease(ArrayList<String> values, String myDefault, boolean common) {
        this.values = values;
        this.myDefault = myDefault;
        this.common = common;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
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
}
