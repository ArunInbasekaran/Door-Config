package com.aruninba.doorconfig.data.database;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;

import java.util.List;


/**
 * Created by Arun Inba on 20/01/24.
 */
@Dao
public interface DoorConfigDao {

    /**
     * Select all config from the config table.
     *
     * @return all config.
     */
    @Query("SELECT * FROM doorConfig")
    List<DoorConfigParameter> getDoorConfig();

    /**
     * Insert all config.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveConfig(List<DoorConfigParameter> configResponse);

    /**
     * Update edited config
     * @param doorConfigParameter
     */
    @Update
    void updateConfig(DoorConfigParameter doorConfigParameter);
}
