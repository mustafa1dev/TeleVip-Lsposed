package com.my.televip.application;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import com.my.televip.Utils;
import com.my.televip.configs.ConfigManager;
import com.my.televip.language.Language;
import com.my.televip.obfuscate.AutomationResolver;

public class ApplicationLoaderHook extends Language {
    private static boolean initialized = false;

    public static void init(ClassLoader loader) {
        // our minSdk is 21 so there is no need to wait for MultiDex to initialize
        if (initialized)
            return;

        Class<?> applicationClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), loader);
        if (applicationClass == null) {
            Utils.log("Not found ApplicationLoader, " + Utils.issue);
            return;
        }
        XposedHelpers.findAndHookMethod(applicationClass, AutomationResolver.resolve("ApplicationLoader", "onCreate", AutomationResolver.ResolverType.Method), new XC_MethodHook(51) {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
                Context app = (Application) param.thisObject;

                try {
                    app = app.getApplicationContext();
                } catch (Throwable ignored) {
                }

                if (app == null)
                {
                    Utils.log("ApplicationLoader is wrong, " + Utils.issue);
                    return;
                }

                File dir = new File(app.getFilesDir().getParentFile(), strTelevip);
                if (!dir.exists())
                    if (!dir.mkdir())
                    {
                        Toast.makeText(app, "Cannot create " + dir.getAbsolutePath() + " dir, please create by yourself!", Toast.LENGTH_LONG).show();
                        Utils.log("Cannot create " + dir.getAbsolutePath() + " dir, please create by yourself!");
                        return;
                    }
                //Utils.deletedMessagesSavePath = new File(dir.getAbsolutePath() + "/deletedMessages.list");
                Utils.deletedMessagesDatabasePath = new File(dir.getAbsolutePath() + "/deletedMessages.db");
                ConfigManager.cfgPath = new File(dir.getAbsolutePath() + "/AntiRecall.cfg");
                try
                {
                    if (!ConfigManager.cfgPath.exists())
                    {
                        ConfigManager.cfgPath.createNewFile();
                        ConfigManager.save();
                    }

                    //Utils.readDeletedMessages();
                    ConfigManager.read();
                    ConfigManager.save();
                    HostApplicationInfo.setApplication(app);
                }
                catch (IOException e)
                {
                    Utils.log(e);
                }
            }
        });
        initialized = true;
    }
}