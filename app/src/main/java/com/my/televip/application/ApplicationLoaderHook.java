package com.my.televip.application;

import android.app.Application;
import android.content.Context;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.Utils;
import com.my.televip.hooks.HMethod;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;

import java.io.File;

import de.robv.android.xposed.XC_MethodHook;

public class ApplicationLoaderHook {
    private static boolean initialized = false;

    public static void init() {
        // our minSdk is 21 so there is no need to wait for MultiDex to initialize
        if (initialized)
            return;


        if (ClassLoad.getClass(ClassNames.APPLICATION_LOADER) == null) { return; }

        HMethod.hookMethod(ClassLoad.getClass(ClassNames.APPLICATION_LOADER), AutomationResolver.resolve("ApplicationLoader", "onCreate", AutomationResolver.ResolverType.Method), new XC_MethodHook(51) {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
                Context app = (Application) param.thisObject;

                try {
                    app = app.getApplicationContext();
                } catch (Throwable ignored) {}

                if (app == null)
                {
                    Logger.w("ApplicationLoader is wrong, " + Utils.issue);
                    return;
                }

                File dir = new File(app.getFilesDir().getParentFile(), "TeleVip");
                if (!dir.exists())
                    if (!dir.mkdir())
                    {
                        Logger.w("Cannot create " + dir.getAbsolutePath() + " dir, please create by yourself!");
                    }

            }
        });
        initialized = true;
    }
}