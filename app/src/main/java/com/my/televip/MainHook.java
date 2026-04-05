package com.my.televip;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.my.televip.base.AbstractMethodHook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class MainHook implements IXposedHookLoadPackage, IXposedHookZygoteInit {

    public boolean isStart;
    @SuppressLint("StaticFieldLeak")
    public static int id = 8353847;

    @Override
    public void initZygote(StartupParam startupParam){ Utils.modulePath = startupParam.modulePath; }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        if (!ClientChecker.ClientType.containsPackage(lpparam.packageName)) {
            return;
        }
        Utils.pkgName = lpparam.packageName;
        Utils.classLoader = lpparam.classLoader;


        XposedHelpers.findAndHookMethod(loadClass.getLaunchActivityClass(), "onCreate", Bundle.class, new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                Activity launchActivity = (Activity) param.thisObject;
                if (!isStart) {
                    TeleVip.startHook(lpparam, launchActivity);
                    isStart = true;
                }
            }
        });
    }


}

