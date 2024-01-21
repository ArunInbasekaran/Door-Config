package com.aruninba.doorconfig.data.repository;

import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;
import com.aruninba.doorconfig.data.model.DoorConfigResponse;

import java.util.List;

/**
 * Created by Arun Inba on 20/01/24.
 */
public interface IConfigRepository {


    interface LoadConfigCallback {
        void onConfigLoaded(List<DoorConfigParameter> config);
        void onDataNotAvailable();
        void onError();
    }

    void getConfig(LoadConfigCallback callback);
    void saveConfig(List<DoorConfigParameter> config);

    void UpdateConfig(DoorConfigParameter mDoorConfigParameter);

}
