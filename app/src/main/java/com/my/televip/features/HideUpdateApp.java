package com.my.televip.features;

import android.app.Activity;
import android.content.SharedPreferences;

import com.my.televip.Utils;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

public class HideUpdateApp {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            SharedPreferences preferences = loadClass.getApplicationContext().getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
            preferences.edit().remove("appUpdate").apply();
            preferences.edit().remove("appUpdateCheckTime").apply();
            preferences.edit().remove("appUpdateBuild").apply();

            if (loadClass.getSharedConfigClass() != null) {

                XposedHelpers.findAndHookMethod(
                        loadClass.getSharedConfigClass(),
                        AutomationResolver.resolve("SharedConfig", "setNewAppVersionAvailable", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("setNewAppVersionAvailable", new Class[]{loadClass.getTLRPC$TL_help_appUpdateClass()}), new XC_MethodReplacement() {
                            @Override
                            protected Object replaceHookedMethod(MethodHookParam param) {
                                return false;
                            }
                        }));

                XposedHelpers.findAndHookMethod(
                        loadClass.getSharedConfigClass(),
                        AutomationResolver.resolve("SharedConfig", "isAppUpdateAvailable", AutomationResolver.ResolverType.Method), new XC_MethodReplacement() {
                            @Override
                            protected Object replaceHookedMethod(MethodHookParam param) {
                                return false;
                            }
                        });
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }
}
