package com.aruninba.doorconfig.data.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class StringListConverter {
    @TypeConverter
    public static ArrayList<String> restoreList(String listOfString) {
        return new Gson().fromJson(listOfString, new TypeToken<ArrayList<String>>() {
        }.getType());
    }

    @TypeConverter
    public static String saveList(ArrayList<String> listOfString) {
        return new Gson().toJson(listOfString);
    }
}
