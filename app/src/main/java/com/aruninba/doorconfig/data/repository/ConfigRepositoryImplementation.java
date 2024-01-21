package com.aruninba.doorconfig.data.repository;


import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;
import com.aruninba.doorconfig.data.model.DoorConfigResponse;

import java.util.List;

/**
 * Created by Arun Inba on 20/01/24.
 * Responsible for getting data from Local DB/Server
 */
public class ConfigRepositoryImplementation implements IConfigRepository{


    private final IConfigDataSource.Remote mRemoteDataSource;
    private final IConfigDataSource.Local mLocalDataSource;
    private static ConfigRepositoryImplementation mInstance;

    public ConfigRepositoryImplementation(ConfigRemoteDataSource remoteDataSource,
                                          ConfigLocalDataSource localDataSource) {
        this.mRemoteDataSource = remoteDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static ConfigRepositoryImplementation getInstance(ConfigRemoteDataSource remoteDataSource,
                                                              ConfigLocalDataSource localDataSource){
        if(mInstance  == null){
            mInstance = new ConfigRepositoryImplementation(remoteDataSource, localDataSource);
        }
        return mInstance;
    }

    @Override
    public void getConfig(LoadConfigCallback callback) {
        getMoviesFromLocalDataSource(callback);
    }

    @Override
    public void saveConfig(List<DoorConfigParameter> config) {
        mLocalDataSource.saveConfig(config);
    }

    @Override
    public void UpdateConfig(DoorConfigParameter mDoorConfigParameter) {
        mLocalDataSource.UpdateConfig(mDoorConfigParameter);
    }

    /**
     * Gets data from lacal db if available
     * calls api if no data present
     * @param callback
     */
    private void getMoviesFromLocalDataSource(final IConfigRepository.LoadConfigCallback callback) {
        mLocalDataSource.getConfig(new IConfigRepository.LoadConfigCallback() {
            @Override
            public void onConfigLoaded(List<DoorConfigParameter> configResponse) {
                callback.onConfigLoaded(configResponse);
            }

            @Override
            public void onDataNotAvailable() {
                getConfigFromRemoteDataSource(callback);
            }

            @Override
            public void onError() {
                //not implemented in local data source
            }
        });
    }

    /**
     * Api call made if no data is present in DB
     * Response is saved into local DB
     * @param callback
     */
    private void getConfigFromRemoteDataSource(final IConfigRepository.LoadConfigCallback callback) {
        mRemoteDataSource.getConfig(new IConfigRepository.LoadConfigCallback() {
            @Override
            public void onConfigLoaded(List<DoorConfigParameter> configResponse) {
                callback.onConfigLoaded(configResponse);
                saveConfig(configResponse);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }
}
