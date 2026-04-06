package com.my.televip;


import android.app.Activity;
import android.os.Bundle;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.base.AbstractMethodHook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class MainHook implements IXposedHookLoadPackage, IXposedHookZygoteInit {

    public boolean isStart;

    @Override
    public void initZygote(StartupParam startupParam){ Utils.modulePath = startupParam.modulePath; }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        if (!ClientChecker.ClientType.containsPackage(lpparam.packageName)) {
            return;
        }
        Utils.pkgName = lpparam.packageName;
        Utils.classLoader = lpparam.classLoader;


        XposedHelpers.findAndHookMethod(ClassLoad.getClass(ClassNames.LAUNCH_ACTIVITY), "onCreate", Bundle.class, new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                Activity launchActivity = (Activity) param.thisObject;
                if (!isStart) {
                    TeleVip.startHook(launchActivity);
                    isStart = true;
                }
            }
        });
    }


}

