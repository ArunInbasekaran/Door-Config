package com.aruninba.doorconfig.data.service;

import com.aruninba.doorconfig.data.api.DoorConfigApi;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class ConfigService {

    private static final String URL = "https://run.mocky.io/v3/d5f5d613-474b-49c4-a7b0-7730e8f8f486/";

    private final DoorConfigApi doorConfigApi;

    private static ConfigService mInstance;

    private ConfigService() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL).build();

        doorConfigApi = mRetrofit.create(DoorConfigApi.class);
    }

    public static ConfigService getInstance() {
        if (mInstance == null) {
            synchronized (ConfigService.class) {
                if (mInstance == null) {
                    mInstance = new ConfigService();
                }
            }
        }
        return mInstance;
    }

    public DoorConfigApi getDoorConfigApi() {
        return doorConfigApi;
    }
}
