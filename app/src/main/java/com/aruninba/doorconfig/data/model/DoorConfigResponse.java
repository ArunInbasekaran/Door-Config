package com.aruninba.doorconfig.data.model;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class DoorConfigResponse{

    @PrimaryKey
    private int id;
    @Expose
    private LockVoltage lockVoltage;
    @Expose
    private LockType lockType;
    @Expose
    private LockKick lockKick;
    @Expose
    private LockRelease lockRelease;
    @Expose
    private LockReleaseTime lockReleaseTime;
    @Expose
    private LockAngle lockAngle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LockVoltage getLockVoltage() {
        return lockVoltage;
    }

    public void setLockVoltage(LockVoltage lockVoltage) {
        this.lockVoltage = lockVoltage;
    }

    public LockType getLockType() {
        return lockType;
    }

    public void setLockType(LockType lockType) {
        this.lockType = lockType;
    }

    public LockKick getLockKick() {
        return lockKick;
    }

    public void setLockKick(LockKick lockKick) {
        this.lockKick = lockKick;
    }

    public LockRelease getLockRelease() {
        return lockRelease;
    }

    public void setLockRelease(LockRelease lockRelease) {
        this.lockRelease = lockRelease;
    }

    public LockReleaseTime getLockReleaseTime() {
        return lockReleaseTime;
    }

    public void setLockReleaseTime(LockReleaseTime lockReleaseTime) {
        this.lockReleaseTime = lockReleaseTime;
    }

    public LockAngle getLockAngle() {
        return lockAngle;
    }

    public void setLockAngle(LockAngle lockAngle) {
        this.lockAngle = lockAngle;
    }
}

