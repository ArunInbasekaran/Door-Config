package com.aruninba.doorconfig.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

/**
 * Created by Arun Inba on 19/01/24.
 */
public abstract class BaseActivity <BINDING extends ViewBinding, VM extends ViewModel> extends AppCompatActivity {

    protected VM viewModel;
    protected BINDING binding;

    @NonNull
    protected abstract VM createViewModel();

    @NonNull
    protected abstract BINDING createViewBinding(LayoutInflater layoutInflater);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = createViewBinding(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        viewModel = createViewModel();
    }

}