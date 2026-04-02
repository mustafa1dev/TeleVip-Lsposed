package com.my.televip.application;

import android.app.Application;
import android.content.Context;

import com.my.televip.Utils;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.io.File;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class ApplicationLoaderHook {
    private static boolean initialized = false;

    public static void init(ClassLoader loader) {
        // our minSdk is 21 so there is no need to wait for MultiDex to initialize
        if (initialized)
            return;


        if (loadClass.getApplicationLoaderClass() == null) {
            Utils.log("Not found ApplicationLoader, " + Utils.issue);
            return;
        }
        XposedHelpers.findAndHookMethod(loadClass.getApplicationLoaderClass(), AutomationResolver.resolve("ApplicationLoader", "onCreate", AutomationResolver.ResolverType.Method), new XC_MethodHook(51) {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
                Context app = (Application) param.thisObject;

                try {
                    app = app.getApplicationContext();
                } catch (Throwable ignored) {}

                if (app == null)
                {
                    Utils.log("ApplicationLoader is wrong, " + Utils.issue);
                    return;
                }

                File dir = new File(app.getFilesDir().getParentFile(), "TeleVip");
                if (!dir.exists())
                    if (!dir.mkdir())
                    {
                        Utils.log("Cannot create " + dir.getAbsolutePath() + " dir, please create by yourself!");
                        return;
                    }

                Utils.deletedMessagesDatabasePath = new File(dir.getAbsolutePath() + "/deletedMessages.db");

            }
        });
        initialized = true;
    }
}