package com.bluecloude.pagotarjeta.Utilerias;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS = "APP_SETTINGS";
    private static SharedPreferences mSharedPref;


    private SharedPreferencesManager() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String getValor(String Key) {
        return mSharedPref.getString(Key , "");
    }

    public static void setValor(String Key, String newValue) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(Key, newValue);
        prefsEditor.commit();
    }
}
