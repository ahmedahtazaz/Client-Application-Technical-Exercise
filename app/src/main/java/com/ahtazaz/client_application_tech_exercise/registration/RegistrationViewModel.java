package com.ahtazaz.client_application_tech_exercise.registration;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ahtazaz.client_application_tech_exercise.BR;

public class RegistrationViewModel extends BaseObservable {

    private String emailId;
    private String data;
    private boolean tnc;


    @Bindable
    public boolean isTnc() {
        return tnc;
    }

    public void setTnc(boolean tnc) {
        this.tnc = tnc;
        this.notifyPropertyChanged(BR.tnc);
    }

    @Bindable
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
        this.notifyPropertyChanged(BR.emailId);
    }

    @Bindable
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        notifyPropertyChanged(BR.data);
    }
}

