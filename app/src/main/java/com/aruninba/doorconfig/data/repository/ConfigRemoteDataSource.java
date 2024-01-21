package com.aruninba.doorconfig.data.repository;

import android.util.Log;

import com.aruninba.doorconfig.data.api.DoorConfigApi;
import com.aruninba.doorconfig.data.mapper.DomainMapper;
import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;
import com.aruninba.doorconfig.data.model.DoorConfigResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arun Inba on 20/01/24.
 */
public class ConfigRemoteDataSource implements IConfigDataSource.Remote{

    private static ConfigRemoteDataSource mInstance;

    private final DoorConfigApi mDoorConfigApi;

    public ConfigRemoteDataSource(DoorConfigApi doorConfigApi) {
        this.mDoorConfigApi = doorConfigApi;
    }

    public static ConfigRemoteDataSource getInstance(DoorConfigApi doorConfigApi){
        if(mInstance == null){
            mInstance = new ConfigRemoteDataSource(doorConfigApi);
        }
        return mInstance;
    }

    /**
     * Network call to get the response from API
     * Nested Object class is converted into list of objects
     * @param callback
     */
    @Override
    public void getConfig(IConfigRepository.LoadConfigCallback callback) {
        mDoorConfigApi.getDoorConfig().enqueue(new Callback<DoorConfigResponse>() {
            @Override
            public void onResponse(Call<DoorConfigResponse> call, Response<DoorConfigResponse> response) {
                DoorConfigResponse doorConfigResponse = response.body() != null ? response.body() : null;
                if (doorConfigResponse != null) {
                    callback.onConfigLoaded(DomainMapper.toDomain(doorConfigResponse));
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<DoorConfigResponse> call, Throwable t) {
                callback.onError();
            }
        });
    }
}
