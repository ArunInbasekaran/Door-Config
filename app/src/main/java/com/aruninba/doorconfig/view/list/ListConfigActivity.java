package com.aruninba.doorconfig.view.list;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.aruninba.doorconfig.data.DataManager;
import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;
import com.aruninba.doorconfig.data.repository.IConfigRepository;
import com.aruninba.doorconfig.databinding.ActivityListConfigBinding;
import com.aruninba.doorconfig.utils.Constants;
import com.aruninba.doorconfig.view.base.BaseActivity;
import com.aruninba.doorconfig.view.edit.EditConfigActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class ListConfigActivity extends BaseActivity<ActivityListConfigBinding,
        ListConfigViewModel> implements ListConfigAdapter.ConfigAdapterListener {

    private ListConfigAdapter mConfigAdapter;
    private final List<DoorConfigParameter> doorConfigResponseList = new ArrayList<>();

    @NonNull
    @Override
    protected ListConfigViewModel createViewModel() {
        IConfigRepository configRepository = DataManager.getInstance().getConfigRepository();
        ListConfigViewModelFactory factory = new ListConfigViewModelFactory(configRepository);
        return new ViewModelProvider(this, factory).get(ListConfigViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityListConfigBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityListConfigBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConfigAdapter = new ListConfigAdapter(this);
        binding.rvConfig.setAdapter(mConfigAdapter);

        observeViewModel();
        addTextChangedListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.etSearch.setText("");
        viewModel.loadConfig();
    }

    /**
     * Listener for search parameter text change
     * If text entered filter data based on input
     * on text remove, restore all list
     */
    private void addTextChangedListener() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {
                query = query.toString().toLowerCase();

                if(doorConfigResponseList.isEmpty()){
                    return;
                }

                if(Constants.isEmpty(query.toString())){
                    mConfigAdapter.setItems(doorConfigResponseList);
                    return;
                }
                List<DoorConfigParameter> filteredList = new ArrayList<>();

                for (int i = 0; i < doorConfigResponseList.size(); i++) {
                    if (doorConfigResponseList.get(i).getTitle().toLowerCase().contains(query)) {
                        filteredList.add(doorConfigResponseList.get(i));
                    }
                }
                binding.rvConfig.removeAllViews();
                mConfigAdapter.setItems(filteredList);
            }
        });
    }

    /**
     * Register observers for fetching data on change
     */
    private void observeViewModel() {

        viewModel.getShowLoadingLiveData().observe(this, aVoid -> binding.progressBar.setVisibility(View.VISIBLE));

        viewModel.getHideLoadingLiveData().observe(this, aVoid -> binding.progressBar.setVisibility(View.GONE));

        viewModel.getConfigLiveData().observe(this, doorConfigResponse -> {
            doorConfigResponseList.clear();
            doorConfigResponseList.addAll(doorConfigResponse);
            mConfigAdapter.setItems(doorConfigResponse);
        });

        viewModel.getNavigateToDetailsLiveData().observe(this, config -> GoToEdit(config.getTitle()));

        viewModel.getShowErrorMessageLiveData().observe(this, message -> Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show());
    }

    /**
     * Go to edit screen for editing the parameter
     * @param configTitle
     */
    private void GoToEdit(@Nullable String configTitle) {
        Intent intent = new Intent(this, EditConfigActivity.class);
        intent.putExtra(Constants.SELECTED_PARAMETER, configTitle);
        startActivity(intent);
    }

    @Override
    public void onConfigClicked(DoorConfigParameter configResponse) {
        viewModel.onConfigClicked(configResponse);
    }
}