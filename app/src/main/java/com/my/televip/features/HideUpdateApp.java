package com.my.televip.features;

import android.app.Activity;
import android.content.SharedPreferences;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.hooks.HMethod;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XC_MethodReplacement;

public class HideUpdateApp {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;

                SharedPreferences preferences = ClassLoad.getApplicationContext().getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                preferences.edit().remove("appUpdate").apply();
                preferences.edit().remove("appUpdateCheckTime").apply();
                preferences.edit().remove("appUpdateBuild").apply();

                if (ClassLoad.getClass(ClassNames.SHARED_CONFIG) != null) {

                    HMethod.hookMethod(
                            ClassLoad.getClass(ClassNames.SHARED_CONFIG),
                            AutomationResolver.resolve("SharedConfig", "setNewAppVersionAvailable", AutomationResolver.ResolverType.Method),
                            AutomationResolver.merge(AutomationResolver.resolveObject("setNewAppVersionAvailable", new Class[]{ClassLoad.getClass(ClassNames.TL_HELP_APP_UPDATE)}), new XC_MethodReplacement() {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) {
                                    return false;
                                }
                            }));

                    HMethod.hookMethod(
                            ClassLoad.getClass(ClassNames.SHARED_CONFIG),
                            AutomationResolver.resolve("SharedConfig", "isAppUpdateAvailable", AutomationResolver.ResolverType.Method), new XC_MethodReplacement() {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) {
                                    return false;
                                }
                            });
                }
            }
        } catch (Throwable t){
            Logger.e(t);
        }
    }
}
