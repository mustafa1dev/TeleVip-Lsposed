package com.my.televip.Configs;

import android.app.Activity;
import android.content.SharedPreferences;

import com.my.televip.Class.ClassLoad;
import com.my.televip.logging.Logger;

public class ConfigPreferences {

    private static SharedPreferences sharedPreferences;


    public static void init(){
        sharedPreferences = ClassLoad.getApplicationContext().getSharedPreferences("TeleVip", Activity.MODE_PRIVATE);
    }

    public static boolean getBoolean(String key) {
        try {
            return sharedPreferences.getBoolean(key, false);
        } catch (ClassCastException e) {
            sharedPreferences.edit().remove(key).apply();
            return false;
        }
    }

    public static void putBoolean(String key, boolean b) {
        try {
            sharedPreferences.edit().putBoolean(key, b).apply();
        } catch (ClassCastException e) {
            sharedPreferences.edit().remove(key).apply();
        }
    }

    public static String getString(String key) {
        try {
            return sharedPreferences.getString(key, null);
        } catch (ClassCastException e) {

            sharedPreferences.edit().remove(key).apply();
            return null;
        }
    }

    public static void putString(String key, String v) {
        try {
            sharedPreferences.edit().putString(key, v).apply();
        } catch (ClassCastException e) {
            sharedPreferences.edit().remove(key).apply();
        }
    }

    public static void remove(String key){
        try {
            sharedPreferences.edit().remove(key).apply();
        } catch (Throwable t){
            Logger.e(t);
        }
    }

}
