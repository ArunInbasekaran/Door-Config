package com.aruninba.doorconfig.data.model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class LockType {


    private ArrayList<String> values;

    @SerializedName("default")
    private String myDefault;


    public LockType(ArrayList<String> values, String myDefault) {
        this.values = values;
        this.myDefault = myDefault;
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
}
