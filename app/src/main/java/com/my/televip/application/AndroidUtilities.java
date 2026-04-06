package com.my.televip.application;

import android.content.Context;
import android.os.Handler;

import com.my.televip.Class.ClassLoad;
import com.my.televip.logging.Logger;

public class AndroidUtilities {

    private static float density = 1;

    public static volatile Handler applicationHandler;

    public static void runOnUIThread(Runnable runnable) {
        if (applicationHandler == null) {
            return;
        }
        applicationHandler.post(runnable);

    }

    public static void init(Context context) {
        try {
            applicationHandler = new Handler(ClassLoad.getApplicationContext().getMainLooper());
            density = context.getResources().getDisplayMetrics().density;
        } catch (Throwable e) {
            Logger.e(e);
        }
    }

    public static int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

}
