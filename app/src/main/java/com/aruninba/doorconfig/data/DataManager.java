package com.aruninba.doorconfig.data;

import com.aruninba.doorconfig.data.api.DoorConfigApi;
import com.aruninba.doorconfig.data.database.DoorConfigDao;
import com.aruninba.doorconfig.data.database.DoorConfigDatabase;
import com.aruninba.doorconfig.data.repository.ConfigLocalDataSource;
import com.aruninba.doorconfig.data.repository.ConfigRemoteDataSource;
import com.aruninba.doorconfig.data.repository.ConfigRepositoryImplementation;
import com.aruninba.doorconfig.data.repository.IConfigRepository;
import com.aruninba.doorconfig.data.service.ConfigService;

/**
 * Created by Arun Inba on 20/01/24.
 */
public class DataManager {
    private static DataManager mInstance;

    private  DataManager(){
       // This class is not publicly instantiable
    }

    public static DataManager getInstance(){
        if(mInstance == null){
            mInstance = new DataManager();
        }

        return mInstance;
    }

    public IConfigRepository getConfigRepository() {

        DoorConfigApi configApi = ConfigService.getInstance().getDoorConfigApi();
        ConfigRemoteDataSource configRemoteDataSource = ConfigRemoteDataSource.getInstance(configApi);

        DoorConfigDao configDao = DoorConfigDatabase.getInstance().configDao();
        ConfigLocalDataSource configLocalDataSource = ConfigLocalDataSource.getInstance(configDao);

        return ConfigRepositoryImplementation.getInstance(configRemoteDataSource, configLocalDataSource);
    }
}
