package com.aruninba.doorconfig.view.edit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.aruninba.doorconfig.R;
import com.aruninba.doorconfig.data.DataManager;
import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;
import com.aruninba.doorconfig.data.repository.IConfigRepository;
import com.aruninba.doorconfig.databinding.ActivityEditConfigBinding;
import com.aruninba.doorconfig.utils.Constants;
import com.aruninba.doorconfig.view.base.BaseActivity;

/**
 * Created by Arun Inba on 21/01/24.
 */
public class EditConfigActivity extends BaseActivity<ActivityEditConfigBinding, EditConfigViewModel>
        implements SeekBar.OnSeekBarChangeListener {

    private DoorConfigParameter mDoorConfigParameter;

    @NonNull
    @Override
    protected EditConfigViewModel createViewModel() {
        IConfigRepository configRepository = DataManager.getInstance().getConfigRepository();
        EditConfigViewModelFactory factory = new EditConfigViewModelFactory(configRepository);
        return new ViewModelProvider(this, factory).get(EditConfigViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityEditConfigBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityEditConfigBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String selectedParameter = getIntent().getStringExtra(Constants.SELECTED_PARAMETER);
        viewModel.getSelectedConfigModel(selectedParameter);

        setValueChangeListeners();
        observeViewModel();
    }

    /**
     * Method used to listen changes in the parameters
     */
    private void setValueChangeListeners() {
        binding.includePrimary.rgConfig.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            if (rb != null) {
                mDoorConfigParameter.setPrimaryDoorConfig(rb.getText().toString());
            }
        });

        binding.includeSecondary.rgConfig.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            if (rb != null) {
                mDoorConfigParameter.setSecondaryDoorConfig(rb.getText().toString());
            }
        });

        binding.btCancel.setOnClickListener(v -> onBackPressed());
        binding.btSave.setOnClickListener(v ->
        {
            viewModel.UpdateConfig(mDoorConfigParameter);
            onBackPressed();
        });
    }

    private void observeViewModel() {
        viewModel.getConfigParameterLiveData().observe(this, doorConfigParameter -> updateUi(doorConfigParameter));

        viewModel.getErrorMessageLiveData().observe(this, message ->
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show());
    }

    /**
     * Update the ui with selected parameter for editing
     * Manipulate ui with seek bar for range and radio button for values
     *
     * @param doorConfigParameter
     */
    private void updateUi(DoorConfigParameter doorConfigParameter) {
        mDoorConfigParameter = doorConfigParameter;
        //Hide secondary door view if common for both doors
        if (doorConfigParameter.isCommon()) {
            binding.includePrimary.tvDoorTitle.setText(getString(R.string.common_door));
            binding.includeSecondary.getRoot().setVisibility(View.GONE);
        }

        if (doorConfigParameter.getValues() != null && !doorConfigParameter.getValues().isEmpty()) {
            binding.includePrimary.rgConfig.setVisibility(View.VISIBLE);
            binding.includeSecondary.rgConfig.setVisibility(View.VISIBLE);
            UpdateRadioGroup(doorConfigParameter, Constants.DoorType.Primary);
            if (!doorConfigParameter.isCommon()) {
                UpdateRadioGroup(doorConfigParameter, Constants.DoorType.Secondary);
            }
        } else {
            binding.includePrimary.sbConfig.setVisibility(View.VISIBLE);
            binding.includeSecondary.sbConfig.setVisibility(View.VISIBLE);
            UpdateSeekBar(binding.includePrimary.sbConfig, doorConfigParameter, Constants.DoorType.Primary);
            if (!doorConfigParameter.isCommon()) {
                UpdateSeekBar(binding.includeSecondary.sbConfig, doorConfigParameter, Constants.DoorType.Secondary);
            }
        }
        UpdateContentText(doorConfigParameter);
    }

    /**
     * Update seek bar range value - Max and Min
     *
     * @param sbConfig
     * @param doorConfigParameter
     * @param doorType
     */
    private void UpdateSeekBar(SeekBar sbConfig, DoorConfigParameter doorConfigParameter, Constants.DoorType doorType) {
        sbConfig.setMax(doorConfigParameter.getRange().getMax());
        sbConfig.setMin((int) doorConfigParameter.getRange().getMin());

        int progress = getSelectedProgress(doorConfigParameter.getMyDefault(),
                Constants.DoorType.Primary == doorType ? doorConfigParameter.getPrimaryDoorConfig() :
                        doorConfigParameter.getSecondaryDoorConfig());
        sbConfig.setProgress(progress);
        if (Constants.DoorType.Primary == doorType) {
            binding.includePrimary.tvSeekBarProgress.setText(getString(R.string.selected_range, progress + ""));
        } else {
            binding.includeSecondary.tvSeekBarProgress.setText(getString(R.string.selected_range, progress + ""));
        }

        sbConfig.setOnSeekBarChangeListener(this);
    }

    /**
     * Get selected progress value to update the seek bar
     *
     * @param myDefault
     * @param selectedDoorConfig
     * @return int
     */
    private int getSelectedProgress(String myDefault, String selectedDoorConfig) {
        try {
            return Constants.isEmpty(selectedDoorConfig) ? (int) Double.parseDouble(myDefault) : (int) Double.parseDouble(selectedDoorConfig);
        } catch (NumberFormatException exception) {
            Log.d("NumberFormatException", exception.getMessage());
        }
        return 0;
    }

    /**
     * Update the radio group with dynamic radio button
     * Used for both Primary and secondary
     *
     * @param doorConfigParameter
     * @param doorType
     */
    private void UpdateRadioGroup(DoorConfigParameter doorConfigParameter, Constants.DoorType doorType) {
        for (String value : doorConfigParameter.getValues()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(value);
            radioButton.setTextColor(getColor(R.color.black));
            radioButton.setId(View.generateViewId());

            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);

            if (doorType == Constants.DoorType.Primary) {
                radioButton.setChecked(isSelectedValue(value, doorConfigParameter.getMyDefault(),
                        doorConfigParameter.getPrimaryDoorConfig()));
                binding.includePrimary.rgConfig.addView(radioButton, layoutParams);
            } else {
                radioButton.setChecked(isSelectedValue(value, doorConfigParameter.getMyDefault(),
                        doorConfigParameter.getSecondaryDoorConfig()));
                binding.includeSecondary.rgConfig.addView(radioButton, layoutParams);
            }
        }
    }

    /**
     * Check selected value to update radio button
     *
     * @param value
     * @param myDefault
     * @param selectedDoorConfig
     * @return true/false
     */
    private boolean isSelectedValue(String value, String myDefault, String selectedDoorConfig) {
        return value.equalsIgnoreCase(selectedDoorConfig) || (Constants.isEmpty(selectedDoorConfig) && value.equalsIgnoreCase(myDefault));
    }

    /**
     * To update the header and descriptions
     * @param doorConfigParameter
     */
    private void UpdateContentText(DoorConfigParameter doorConfigParameter) {
        binding.tvConfigType.setText(doorConfigParameter.getTitle());
        binding.tvConfigDescription.setText(getString(R.string.config_description, doorConfigParameter.getTitle().toLowerCase()));
        binding.includeSecondary.tvDoorTitle.setText(getString(R.string.Secondary_door));
        binding.includePrimary.tvDefaultConfig.setText(getString(R.string.default_config, doorConfigParameter.getMyDefault()));
        binding.includeSecondary.tvDefaultConfig.setText(getString(R.string.default_config, doorConfigParameter.getMyDefault()));
    }

    /**
     * @param seekBar  The SeekBar whose progress has changed
     * @param progress The current progress level. This will be in the range min..max where min
     *                 and max were set by {@link ProgressBar#setMin(int)} and
     *                 {@link ProgressBar#setMax(int)}, respectively. (The default values for
     *                 min is 0 and max is 100.)
     * @param fromUser True if the progress change was initiated by the user.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (seekBar.equals(binding.includePrimary.sbConfig)) {
            mDoorConfigParameter.setPrimaryDoorConfig(String.valueOf(progress));
            binding.includePrimary.tvSeekBarProgress.setText(getString(R.string.selected_range, progress + ""));
        } else {
            mDoorConfigParameter.setSecondaryDoorConfig(String.valueOf(progress));
            binding.includeSecondary.tvSeekBarProgress.setText(getString(R.string.selected_range, progress + ""));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Do nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Do nothing
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
