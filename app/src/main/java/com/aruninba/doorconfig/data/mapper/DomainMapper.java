package com.aruninba.doorconfig.data.mapper;

import com.aruninba.doorconfig.data.model.DoorConfigResponse;
import com.aruninba.doorconfig.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arun Inba on 20/01/24.
 * Class used for mapping data model to domain/UI model class
 * Helps to reorganise the data to suit the ui
 */
public class DomainMapper {

    public static List<DoorConfigParameter> toDomain(DoorConfigResponse configResponse) {
         List<DoorConfigParameter> doorConfigs = new ArrayList<>();
         doorConfigs.add(new DoorConfigParameter(0, Constants.LOCK_VOLTAGE, "", "",
                 configResponse.getLockVoltage().getValues(), null, "",configResponse.getLockVoltage().getMyDefault(),
                 false));

        doorConfigs.add(new DoorConfigParameter(1,Constants.LOCK_TYPE, "", "",
                configResponse.getLockType().getValues(), null, "",configResponse.getLockType().getMyDefault(),
                false));

        doorConfigs.add(new DoorConfigParameter(2,Constants.LOCK_KICK, "", "",
                configResponse.getLockKick().getValues(), null, "",configResponse.getLockKick().getMyDefault(),
                false));

        doorConfigs.add(new DoorConfigParameter(3,Constants.LOCK_RELEASE, "", "",
                configResponse.getLockRelease().getValues(), null, "",configResponse.getLockRelease().getMyDefault(),
                configResponse.getLockRelease().isCommon()));

        doorConfigs.add(new DoorConfigParameter(4,Constants.LOCK_RELEASE_TIME, "", "",
                null, configResponse.getLockReleaseTime().getRange(), "",
                String.valueOf(configResponse.getLockReleaseTime().getMyDefault()),
                false));

        doorConfigs.add(new DoorConfigParameter(5,Constants.LOCK_ANGLE, "", "",
                null, configResponse.getLockAngle().getRange(), "",
                String.valueOf(configResponse.getLockAngle().getMyDefault()),
                configResponse.getLockAngle().isCommon()));

        return doorConfigs;
    }
}
