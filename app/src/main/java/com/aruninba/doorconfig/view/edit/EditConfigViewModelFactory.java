package com.aruninba.doorconfig.view.edit;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aruninba.doorconfig.data.repository.IConfigRepository;

/**
 * Created by Arun Inba on 21/01/24.
 */
public class EditConfigViewModelFactory implements ViewModelProvider.Factory {

    private final IConfigRepository configRepository;

    public EditConfigViewModelFactory(IConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EditConfigViewModel.class)) {
            return (T) new EditConfigViewModel(configRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

