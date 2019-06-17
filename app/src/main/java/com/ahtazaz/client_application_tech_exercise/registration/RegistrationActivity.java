package com.ahtazaz.client_application_tech_exercise.registration;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ahtazaz.client_application_tech_exercise.R;
import com.ahtazaz.client_application_tech_exercise.databinding.SignupScreenBinding;

import static com.ahtazaz.client_application_tech_exercise.Utlities.fullScreenActivity;

public class RegistrationActivity extends AppCompatActivity {

    private RegistrationViewModel regViewModel;
    private SignupScreenBinding binding;
    private RegistrationHandlerInterface registerHandlerListener = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullScreenActivity(this);

        regViewModel = new RegistrationViewModel();
        binding = DataBindingUtil.setContentView(this, R.layout.signup_screen);

        setViewModel();
        setHandler();
    }

    private void setViewModel()
    {
        if(binding != null && regViewModel != null)
            binding.setViewModel(regViewModel);
    }

    private void setHandler()
    {
        if(binding != null)
        {
            RegistrationHandler handler = new RegistrationHandler(this);
            handler.setEventListsner(new RegistrationHandlerInterface() {
                @Override
                public void onResult(int code)
                {
                    setResult(code);
                    finish();
                }
            });

            binding.setHandler(handler);
        }
    }
}
