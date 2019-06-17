package com.ahtazaz.client_application_tech_exercise.landingview;

import android.content.Context;
import android.view.View;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingViewHandler {

    private final String BASE_URL = "https://staging.hellogold.com/api/v2/spot_price.json/";
    private LandingViewInterface listener = null;
    private Context mContext = null;
    private PriceNetworkInterface apiService = null;

    public LandingViewHandler(Context context)
    {
        mContext = context;
    }

    public void setEventListener(LandingViewInterface listener)
    {
        this.listener = listener;
    }

    public void getPrice(View view)
    {
        if(view != null && listener != null)
            listener.clean();

        if(apiService == null)
            apiService = getRegistrationService();

        if(apiService != null)
        {
            apiService.getPrice().enqueue(new Callback<PriceNetworkResponse>() {
                @Override
                public void onResponse(Call<PriceNetworkResponse> call, Response<PriceNetworkResponse> response)
                {
                    if(listener != null)
                        listener.onResult(((PriceNetworkResponse)response.body()).data);
                }

                @Override
                public void onFailure(Call<PriceNetworkResponse> call, Throwable t)
                {
                    getPrice(null);
                }
            });
        }
    }

    private PriceNetworkInterface getRegistrationService() {

        return getClient(BASE_URL).create(PriceNetworkInterface.class);
    }

    private Retrofit getClient(String baseUrl) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
