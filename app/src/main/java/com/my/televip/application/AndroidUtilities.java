package com.my.televip.application;

import android.os.Handler;

import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.loadClass;

public class AndroidUtilities {

    private static float density = 1;

    public static volatile Handler applicationHandler;

    public static void runOnUIThread(Runnable runnable) {
        if (applicationHandler == null) {
            return;
        }
        applicationHandler.post(runnable);

    }

    public static void init() {
        try {
            applicationHandler = new Handler(loadClass.getApplicationContext().getMainLooper());
            density = MainHook.launchActivity.getResources().getDisplayMetrics().density;
        } catch (Exception e) {
            Utils.log(e);
        }
    }

    public static int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

}
