package com.aruninba.doorconfig;

import android.app.Application;
/**
 * Created by Arun Inba on 19/01/24.
 */
public class MainApplication extends Application {

    private static MainApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MainApplication getInstance() {
        return sInstance;
    }
}
