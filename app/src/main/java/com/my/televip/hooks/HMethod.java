package com.my.televip.hooks;

import com.my.televip.Utils;

import de.robv.android.xposed.XposedHelpers;

public class HMethod {

    public static void hookMethod(Class<?> classLoader, String name, Object... args){
        try {
            XposedHelpers.findAndHookMethod(classLoader, name, args);
        } catch (Exception e){
            Utils.log(e);
        }
    }

    public static void hookMethod(Class<?> classLoader, String[] names, Object... args) {
        try {
            for (String name : names) {
                XposedHelpers.findAndHookMethod(classLoader, name, args);
            }
        } catch (Exception e) {
            Utils.log(e);
        }
    }

}
