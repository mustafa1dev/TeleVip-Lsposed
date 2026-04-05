package com.my.televip.features;

import android.app.Activity;
import android.content.SharedPreferences;

import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;

import de.robv.android.xposed.XC_MethodReplacement;

public class HideUpdateApp {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;

                SharedPreferences preferences = loadClass.getApplicationContext().getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                preferences.edit().remove("appUpdate").apply();
                preferences.edit().remove("appUpdateCheckTime").apply();
                preferences.edit().remove("appUpdateBuild").apply();

                if (loadClass.getSharedConfigClass() != null) {

                    HMethod.hookMethod(
                            loadClass.getSharedConfigClass(),
                            AutomationResolver.resolve("SharedConfig", "setNewAppVersionAvailable", AutomationResolver.ResolverType.Method),
                            AutomationResolver.merge(AutomationResolver.resolveObject("setNewAppVersionAvailable", new Class[]{loadClass.getTLRPC$TL_help_appUpdateClass()}), new XC_MethodReplacement() {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) {
                                    return false;
                                }
                            }));

                    HMethod.hookMethod(
                            loadClass.getSharedConfigClass(),
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
