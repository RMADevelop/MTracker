package com.example.roma.mtracker_v3.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Roma on 20.07.2017.
 */

public class SPreferenceManager {

    public static final String APP_PREFERENCE = "settings";
    public static final String PREF_DATE = "prefDate";
    public static final String PREF_USD = "1";
    public static final String PREF_EUR = "2";
    public static final String PREF_GBP = "3";

    public static final float PREF_FLOAT_MISSING = 0;
    public static final String PREF_STRING_MISSING = "missing";

    SharedPreferences shPr;

    public SPreferenceManager(Context context) {
        shPr = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
    }

    public void setInfoCurrencyUpdate(float usd, float eur, float gbp, String date) {
        SharedPreferences.Editor editor = shPr.edit();
        editor.putFloat(PREF_USD, usd);
        editor.putFloat(PREF_EUR, eur);
        editor.putFloat(PREF_GBP, gbp);
        editor.putString(PREF_DATE, date);

        editor.apply();
    }

    public float getCurrencyValue(String key) {
        return shPr.getFloat(key, PREF_FLOAT_MISSING);
    }

    public String getCurrencyDate(String key) {
        return shPr.getString(PREF_DATE, PREF_STRING_MISSING);
    }



}
