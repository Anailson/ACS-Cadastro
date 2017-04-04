package com.avisosms.iuri.avisasms.suporte;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by iuri on 6/7/2016.
 */
public class Funcoes {

    ///Prefs Padrão

    public static SharedPreferences getPreferences(Context context) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences;
    }

    public static void setPreference(Context context, String prefsKey, String prefsValue) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putString(prefsKey, prefsValue);
        editor.commit();
    }

    public static void setPreference(Context context, String prefsKey, int prefsValue) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putInt(prefsKey, prefsValue);
        editor.commit();
    }

    public static void setPreference(Context context, String prefsKey, boolean prefsValue) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putBoolean(prefsKey, prefsValue);
        editor.commit();
    }

    private static SharedPreferences.Editor getPreferencesEditor(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.edit();
    }

    public static Calendar dataHoje(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;

    }

    public static Date dataBanco(Date date){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();

    }


}
