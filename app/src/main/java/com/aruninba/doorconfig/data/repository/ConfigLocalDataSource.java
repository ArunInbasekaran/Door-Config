package com.aruninba.doorconfig.data.repository;

import com.aruninba.doorconfig.data.database.DoorConfigDao;
import com.aruninba.doorconfig.data.database.DoorConfigDatabase;
import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;
import com.aruninba.doorconfig.data.model.DoorConfigResponse;
import com.aruninba.doorconfig.utils.ConfigExecutor;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Arun Inba on 20/01/24.
 * Class to Preserve/fetch data from DB
 */
public class ConfigLocalDataSource implements IConfigDataSource.Local{

    private final Executor executor;
    private final DoorConfigDao doorConfigDao;
    private static ConfigLocalDataSource instance;
    private ConfigLocalDataSource(Executor executor, DoorConfigDao configDao) {
        this.executor = executor;
        this.doorConfigDao = configDao;
    }

    public static ConfigLocalDataSource getInstance(DoorConfigDao configDao) {
        if (instance == null) {
            instance = new ConfigLocalDataSource(new ConfigExecutor(), configDao);
        }
        return instance;
    }

    /**
     * Get config data from DB
     * @param callback
     */
    @Override
    public void getConfig(IConfigRepository.LoadConfigCallback callback) {
        Runnable runnable = () -> {
            List<DoorConfigParameter> response = doorConfigDao.getDoorConfig();
            if (response != null && !response.isEmpty()) {
                callback.onConfigLoaded(response);
            } else {
                callback.onDataNotAvailable();
            }
        };
        executor.execute(runnable);
    }

    /**
     * To save config data
     * @param config
     */
    @Override
    public void saveConfig(List<DoorConfigParameter> config) {
        Runnable runnable = () -> doorConfigDao.saveConfig(config);
        executor.execute(runnable);
    }

    /**
     * To update edited record
     * @param doorConfigParameter
     */
    @Override
    public void UpdateConfig(DoorConfigParameter doorConfigParameter) {
        Runnable runnable = () -> doorConfigDao.updateConfig(doorConfigParameter);
        executor.execute(runnable);
    }
}
