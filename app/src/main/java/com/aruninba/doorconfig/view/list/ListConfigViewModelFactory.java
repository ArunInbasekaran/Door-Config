package com.aruninba.doorconfig.view.list;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aruninba.doorconfig.data.repository.IConfigRepository;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class ListConfigViewModelFactory implements ViewModelProvider.Factory {

    private final IConfigRepository configRepository;

    public ListConfigViewModelFactory(IConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListConfigViewModel.class)) {
            return (T) new ListConfigViewModel(configRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
