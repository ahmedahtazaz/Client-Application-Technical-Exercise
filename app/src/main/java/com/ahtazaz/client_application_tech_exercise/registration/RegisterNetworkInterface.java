package com.ahtazaz.client_application_tech_exercise.registration;

import java.util.HashMap;
import java.util.LinkedHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterNetworkInterface {

    @POST(".")
    Call<RegisterNetworkResponse> register(@Body LinkedHashMap<String, Object> params);
}
