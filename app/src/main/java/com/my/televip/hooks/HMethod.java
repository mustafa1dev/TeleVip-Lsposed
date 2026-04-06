package com.my.televip.hooks;

import com.my.televip.logging.Logger;

import de.robv.android.xposed.XposedHelpers;

public class HMethod {

    public static void hookMethod(Class<?> cls, String name, Object... args) {
        try {
            if (cls != null) {
                XposedHelpers.findAndHookMethod(cls, name, args);
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }

    public static void hookMethod(Class<?> cls, String[] names, Object... args) {
        try {
            if (cls != null) {
                for (String name : names) {
                    XposedHelpers.findAndHookMethod(cls, name, args);
                }
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }

}
