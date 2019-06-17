package com.ahtazaz.client_application_tech_exercise.landingview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

import com.ahtazaz.client_application_tech_exercise.BR;

public class LandingViewModel extends BaseObservable {

    public final ObservableBoolean firstLoad = new ObservableBoolean(true);

    public ObservableBoolean getFirstLoad()
    {
        return firstLoad;
    }

    private String emailId;

    @Bindable
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
        this.notifyPropertyChanged(BR.emailId);
    }
}
