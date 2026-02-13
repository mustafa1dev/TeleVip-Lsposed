package com.my.televip;


import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import android.os.Bundle;
import de.robv.android.xposed.*;
import java.util.ArrayList;

import androidx.annotation.NonNull;

//TeleVip
import com.my.televip.application.ApplicationLoaderHook;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.DownloadSpeed;
import com.my.televip.features.FeatureManager;
import com.my.televip.features.NEWAntiRecall;
import com.my.televip.features.OtherFeatures;
import com.my.televip.language.Language;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.ui.Theme;


public class MainHook extends Language implements IXposedHookLoadPackage {
    public static XC_LoadPackage.LoadPackageParam lpparam;

    public static boolean isStart;


    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        if (!ClientChecker.ClientType.containsPackage(lpparam.packageName)) {
            return;
        }
        MainHook.lpparam = lpparam;
        Utils.pkgName = lpparam.packageName;
        Class<?> launchActivityClass = XposedHelpers.findClass(
                AutomationResolver.resolve("org.telegram.ui.LaunchActivity"),
                lpparam.classLoader
        );
        XposedHelpers.findAndHookMethod(launchActivityClass, "onCreate", Bundle.class, new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                if (!isStart) {
                    TeleVip.startHook();
                    isStart = true;
                }
            }
        });
    }
    public static class TeleVip {
        public static void startHook() {
            if (ClientChecker.check(ClientChecker.ClientType.Cherrygram)) {
                strTelevip = "cherrygram";
                XposedHelpers.findAndHookMethod("org.telegram.messenger.KotlinFragmentsManager",
                        lpparam.classLoader,
                        "vnwpoih23nkjhqj",
                        java.lang.CharSequence.class,
                        new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                //ازالة حماية cherrygram لتحقق من اذا كان TeleVip مفعل داخل عميل
                                param.setResult(null);
                            }
                        });
            } else {
                strTelevip = "televip";
            }

            xSharedPreferences.xSharedPre = new XSharedPreferences(lpparam.packageName, strTelevip);
            ClassLoader classLoader = lpparam.classLoader;
            ApplicationLoaderHook.init(classLoader);

            //TeleVipActivity.init();

            Class<?> SettingsActivityClass = XposedHelpers.findClassIfExists(
                    AutomationResolver.resolve("org.telegram.ui.SettingsActivity"),
                    lpparam.classLoader
            );
            Class<?> SettingsActivity$SettingCell$FactoryClass = XposedHelpers.findClassIfExists(
                    AutomationResolver.resolve("org.telegram.ui.SettingsActivity$SettingCell$Factory"),
                    lpparam.classLoader
            );

            if (SettingsActivityClass != null && SettingsActivity$SettingCell$FactoryClass != null){
                Theme.newTheme(SettingsActivityClass, SettingsActivity$SettingCell$FactoryClass);
            } else {
                Theme.oldTheme();
            }
            NEWAntiRecall.initUI(lpparam.classLoader);
            FeatureManager.readFeature();
            DownloadSpeed.init();
            OtherFeatures.init();

        }
    }
}

