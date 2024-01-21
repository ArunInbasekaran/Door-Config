package com.aruninba.doorconfig.data.api;
import com.aruninba.doorconfig.data.model.DoorConfigResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Arun Inba on 20/01/24.
 * Api class to get the door configs
 */
public interface DoorConfigApi {

    @GET(".")
    Call<DoorConfigResponse> getDoorConfig();
}
