package com.aruninba.doorconfig.data.model;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class Range {
    private double min;
    private int max;

    public Range(double min, int max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
