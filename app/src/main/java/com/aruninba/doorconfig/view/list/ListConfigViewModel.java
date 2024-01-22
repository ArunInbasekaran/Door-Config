package com.aruninba.doorconfig.view.list;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;
import com.aruninba.doorconfig.data.repository.IConfigRepository;
import com.aruninba.doorconfig.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class ListConfigViewModel extends ViewModel {

    private final MutableLiveData<List<DoorConfigParameter>> configLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DoorConfigParameter>> filteredConfigLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> showErrorMessageLiveData = new MutableLiveData<>();
    private final MutableLiveData<Void> showLoadingLiveData = new MutableLiveData<>();
    private final MutableLiveData<Void> hideLoadingLiveData = new MutableLiveData<>();
    private final MutableLiveData<DoorConfigParameter> navigateToDetailsLiveData = new MutableLiveData<>();

    private final ConfigCallback configCallback = new ConfigCallback();
    private final IConfigRepository configRepository;

    private List<DoorConfigParameter> mDoorConfigResponse;

    ListConfigViewModel(IConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    /**
     * Initial call to load the data
     */
    public void loadConfig() {
        setIsLoading(true);
        configRepository.getConfig(configCallback);
    }

    private void setIsLoading(boolean loading) {
        if (loading) {
            showLoadingLiveData.postValue(null);
        } else {
            hideLoadingLiveData.postValue(null);
        }
    }

    private void setConfigLiveData(List<DoorConfigParameter> doorConfigResponse) {
        setIsLoading(false);
        this.configLiveData.postValue(doorConfigResponse);
    }

    private void setFilteredConfigLiveData(List<DoorConfigParameter> doorConfigResponse) {
        this.filteredConfigLiveData.postValue(doorConfigResponse);
    }

    public void onConfigClicked(DoorConfigParameter doorConfig) {
        navigateToDetailsLiveData.postValue(doorConfig);
    }

    /**
     * Get results based on the search query
     * @param query searchText
     */
    public void getFilteredData(String query) {
        List<DoorConfigParameter> filteredList = new ArrayList<>();

        if(Constants.isEmpty(query)){
            setFilteredConfigLiveData(mDoorConfigResponse);
            return;
        }

        for (DoorConfigParameter doorConfigResponse : mDoorConfigResponse) {
            if (doorConfigResponse.getTitle().toLowerCase().contains(query)) {
                filteredList.add(doorConfigResponse);
            }
        }
        setFilteredConfigLiveData(filteredList);
    }

    private class ConfigCallback implements IConfigRepository.LoadConfigCallback {
        @Override
        public void onConfigLoaded(List<DoorConfigParameter> config) {
            mDoorConfigResponse = config;
            setConfigLiveData(config);
        }

        @Override
        public void onDataNotAvailable() {
            setIsLoading(false);
            showErrorMessageLiveData.postValue("There is not items!");
        }

        @Override
        public void onError() {
            setIsLoading(false);
            showErrorMessageLiveData.postValue("Something Went Wrong!");
        }
    }

    /**
     * LiveData
     **/
    public LiveData<List<DoorConfigParameter>> getConfigLiveData() {
        return configLiveData;
    }
    public LiveData<List<DoorConfigParameter>> getFilteredConfigLiveData() {
        return filteredConfigLiveData;
    }

    public LiveData<Void> getShowLoadingLiveData() {
        return showLoadingLiveData;
    }

    public LiveData<String> getShowErrorMessageLiveData() {
        return showErrorMessageLiveData;
    }

    public LiveData<Void> getHideLoadingLiveData() {
        return hideLoadingLiveData;
    }

    public LiveData<DoorConfigParameter> getNavigateToDetailsLiveData() {
        return navigateToDetailsLiveData;
    }


}


