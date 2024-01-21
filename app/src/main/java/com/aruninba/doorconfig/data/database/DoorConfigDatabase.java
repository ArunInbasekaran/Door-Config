package com.aruninba.doorconfig.data.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.aruninba.doorconfig.MainApplication;
import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;
import com.aruninba.doorconfig.data.model.DoorConfigResponse;
import com.aruninba.doorconfig.data.model.StringListConverter;

/**
 * Created by Arun Inba on 20/01/24.
 */
@Database(entities = {DoorConfigParameter.class}, version = 1, exportSchema = false)
@TypeConverters(StringListConverter.class)
public abstract class DoorConfigDatabase extends RoomDatabase {

    private static final String DATABASE_DOOR = "DoorConfig.db";
    public abstract DoorConfigDao configDao();

    private static DoorConfigDatabase sInstance;

    public static DoorConfigDatabase getInstance() {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(MainApplication.getInstance(), DoorConfigDatabase.class, DATABASE_DOOR).build();
        }
        return sInstance;
    }
}
