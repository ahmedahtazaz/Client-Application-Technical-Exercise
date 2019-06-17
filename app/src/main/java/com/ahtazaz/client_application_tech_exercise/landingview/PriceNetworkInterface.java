package com.ahtazaz.client_application_tech_exercise.landingview;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PriceNetworkInterface {

    @GET(".")
    Call<PriceNetworkResponse> getPrice();
}
