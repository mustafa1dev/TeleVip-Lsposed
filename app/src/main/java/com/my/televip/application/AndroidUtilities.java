package com.my.televip.application;

import com.my.televip.MainHook;

public class AndroidUtilities {

    public static void runOnUIThread(Runnable runnable) {
        if (MainHook.applicationHandler == null) {
            return;
        }
        MainHook.applicationHandler.post(runnable);

    }

}
