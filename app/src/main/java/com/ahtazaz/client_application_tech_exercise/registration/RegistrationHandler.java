package com.ahtazaz.client_application_tech_exercise.registration;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ahtazaz.client_application_tech_exercise.Utlities;

import java.util.LinkedHashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationHandler {

    private final String BASE_URL = "https://staging.hellogold.com/api/v3/users/register/";
    private RegistrationHandlerInterface listener = null;
    private Context mContext = null;

    public RegistrationHandler(Context context)
    {
        mContext = context;
    }

    public void setEventListsner(RegistrationHandlerInterface listener)
    {
        this.listener = listener;
    }

    public void register(View view, final RegistrationViewModel registerViewModel)
    {
        if(view != null)
        {
            if(registerViewModel != null)
            {
                RegisterNetworkInterface mAPIService = getRegistrationService();

                final String email = registerViewModel.getEmailId();
                String uuid = Utlities.getUuid();
                String data = Utlities.getData();
                boolean tnc = registerViewModel.isTnc();

                RequestRegisterParams params = new RequestRegisterParams();
                LinkedHashMap<String, Object> paramsMap = params.getParams(email, uuid, data, tnc);

                if(mAPIService != null)
                {
                    mAPIService.register(paramsMap).enqueue(new Callback<RegisterNetworkResponse>() {
                        @Override
                        public void onResponse(Call<RegisterNetworkResponse> call, Response<RegisterNetworkResponse> response)
                        {
                            Utlities.writeString(Utlities.EMAIL_KEY, email, Utlities.EMAIL_PREFERENCE);

                            if(listener != null)
                                listener.onResult(Utlities.RESULT_OK);
                        }

                        @Override
                        public void onFailure(Call<RegisterNetworkResponse> call, Throwable t)
                        {
                            Toast.makeText(mContext, "Retry", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }
    }

    private RegisterNetworkInterface getRegistrationService() {

        return getClient(BASE_URL).create(RegisterNetworkInterface.class);
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
