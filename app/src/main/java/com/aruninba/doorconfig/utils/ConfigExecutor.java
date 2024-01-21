package com.aruninba.doorconfig.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Arun Inba on 20/01/24.
 */
public class ConfigExecutor implements Executor {

    private final Executor configExecutor;

    public ConfigExecutor() {
        this.configExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(Runnable command) {
        configExecutor.execute(command);
    }
}
