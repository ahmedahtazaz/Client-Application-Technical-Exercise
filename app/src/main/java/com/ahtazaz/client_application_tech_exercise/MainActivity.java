package com.ahtazaz.client_application_tech_exercise;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahtazaz.client_application_tech_exercise.landingview.LandingViewActivity;
import com.ahtazaz.client_application_tech_exercise.registration.RegistrationActivity;

public class MainActivity extends AppCompatActivity {

    boolean exit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(exit)
        {
            finish();
            return;
        }

        Utlities.mContext = this;

        if(Utlities.readString(Utlities.EMAIL_KEY, "na", Utlities.EMAIL_PREFERENCE).equalsIgnoreCase("na"))
        {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivityForResult(intent, Utlities.REQUEST_CODE_REGISTER);
        }
        else
        {
            Intent intent = new Intent(this, LandingViewActivity.class);
            startActivityForResult(intent, Utlities.REQUEST_CODE_LANDING);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Utlities.REQUEST_CODE_REGISTER)
        {
            if(resultCode == Utlities.RESULT_OK)
            {
                Intent intent = new Intent(this, LandingViewActivity.class);
                startActivityForResult(intent, Utlities.REQUEST_CODE_LANDING);
            }
        }
        else if(requestCode == Utlities.REQUEST_CODE_LANDING)
        {
            exit = true;
        }
    }
}
