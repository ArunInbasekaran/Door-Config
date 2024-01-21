package com.aruninba.doorconfig.view.edit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;
import com.aruninba.doorconfig.data.repository.IConfigRepository;

import java.util.List;

/**
 * Created by Arun Inba on 21/01/24.
 */
public class EditConfigViewModel extends ViewModel {

    private final MutableLiveData<DoorConfigParameter> configParameterLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> showErrorMessageLiveData = new MutableLiveData<>();
    private final IConfigRepository configRepository;

    EditConfigViewModel(IConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    /**
     * Get the selected config from list
     * @param selectedParameter
     */
    public void getSelectedConfigModel(String selectedParameter) {
        configRepository.getConfig(new IConfigRepository.LoadConfigCallback() {
            @Override
            public void onConfigLoaded(List<DoorConfigParameter> config) {
                for(DoorConfigParameter parameter : config){
                    if(parameter.getTitle().equals(selectedParameter)){
                        configParameterLiveData.postValue(parameter);
                        return;
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {
                showErrorMessageLiveData.postValue("There is not items!");
            }

            @Override
            public void onError() {
                showErrorMessageLiveData.postValue("Something Went Wrong!");
            }
        });
    }

    /**
     * LiveData
     **/
    public LiveData<DoorConfigParameter> getConfigParameterLiveData(){
        return configParameterLiveData;
    }

    public LiveData<String> getErrorMessageLiveData(){
        return showErrorMessageLiveData;
    }

    /**
     * Update the edited config in local db
     * @param mDoorConfigParameter
     */
    public void UpdateConfig(DoorConfigParameter mDoorConfigParameter) {
        configRepository.UpdateConfig(mDoorConfigParameter);
    }
}
