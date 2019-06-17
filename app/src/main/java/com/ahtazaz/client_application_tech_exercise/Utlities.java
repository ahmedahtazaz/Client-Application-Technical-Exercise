package com.ahtazaz.client_application_tech_exercise;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

import java.util.UUID;

public class Utlities {

    public static final int MODE = Context.MODE_PRIVATE;
    public static final int REQUEST_CODE_LANDING = 110;
    public static final int REQUEST_CODE_REGISTER = 111;
    public static final int RESULT_OK = 1;
    public static final String EMAIL_KEY = "email";
    public static final String EMAIL_PREFERENCE = "email_preference";

    public static Context mContext = null;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void fullScreenActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.colorBg));
        }
    }

    public static String getUuid()
    {
        return UUID.randomUUID().toString();
    }

    public static String getData()
    {
        // As UUID Represents 128 bit value

        return getUuid().concat(getUuid());
    }

    public static SharedPreferences getPreferences(String preferenceName)
    {
        return mContext.getSharedPreferences(preferenceName, MODE);
    }

    public static void writeString(String key, String value,String preferenceName)
    {
        getPreferences(preferenceName).edit().putString(key, value).commit();
    }

    public static String readString(String key, String defValue,String preferenceName)
    {
        return getPreferences(preferenceName).getString(key, defValue);
    }
}
