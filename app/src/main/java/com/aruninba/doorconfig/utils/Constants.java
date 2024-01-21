package com.aruninba.doorconfig.utils;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class Constants {


    public static String SELECTED_PARAMETER = "SELECTED_PARAMETER";

    //Door parameters
    public static String LOCK_VOLTAGE = "Lock Voltage";
    public static String LOCK_TYPE = "Lock Type";
    public static String LOCK_KICK = "Lock Kick";
    public static String LOCK_RELEASE = "Lock Release";
    public static String LOCK_RELEASE_TIME = "Lock Release Time";
    public static String LOCK_ANGLE = "Lock Angle";

    /**
     * Method to check given string is empty
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public enum DoorType{
        Primary,
        Secondary
    }
}
