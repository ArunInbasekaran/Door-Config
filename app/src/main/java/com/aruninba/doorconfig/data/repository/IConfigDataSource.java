package com.aruninba.doorconfig.data.repository;


import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;
import com.aruninba.doorconfig.data.model.DoorConfigResponse;

import java.util.List;

/**
 * Created by Arun Inba on 20/01/24.
 */
public interface IConfigDataSource {

    interface Remote {
        void getConfig(IConfigRepository.LoadConfigCallback callback);
    }

    interface Local extends Remote {
        void saveConfig(List<DoorConfigParameter> config);

        void UpdateConfig(DoorConfigParameter doorConfigParameter);
    }
}
