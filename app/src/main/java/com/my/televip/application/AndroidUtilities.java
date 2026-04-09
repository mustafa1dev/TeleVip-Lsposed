package com.my.televip.application;

import android.annotation.SuppressLint;
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

    public static int getStatusBarHeight(Context context) {
        @SuppressLint({"InternalInsetResource", "DiscouragedApi"}) int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? context.getResources().getDimensionPixelSize(resourceId) : 0;
    }


}
