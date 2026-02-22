package com.my.televip;


import static com.my.televip.obfuscate.AutomationResolver.resolverRegistry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.my.televip.application.ApplicationLoaderHook;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.FeatureManager;
import com.my.televip.language.Language;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.ui.SettingsActivity;
import com.my.televip.ui.addItem;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class MainHook implements IXposedHookLoadPackage {
    public static XC_LoadPackage.LoadPackageParam lpparam;

    public boolean isStart;
    @SuppressLint("StaticFieldLeak")
    public static Activity launchActivity;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        if (!ClientChecker.ClientType.containsPackage(lpparam.packageName)) {
            return;
        }

        MainHook.lpparam = lpparam;
        Utils.pkgName = lpparam.packageName;

        XposedHelpers.findAndHookMethod(loadClass.getLaunchActivityClass(), "onCreate", Bundle.class, new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                launchActivity = (Activity) param.thisObject;
                if (!isStart) {
                    startHook();
                    isStart = true;
                }
            }
        });
    }

    public void startHook() {
        resolverRegistry.loadParameter();
        Language.init();
        FeatureManager.init();
        SettingsActivity.init();

        ApplicationLoaderHook.init(lpparam.classLoader);


        Class<?> SettingsActivityClass = XposedHelpers.findClassIfExists(
                AutomationResolver.resolve("org.telegram.ui.SettingsActivity"),
                lpparam.classLoader
        );

        Class<?> SettingsActivity$SettingCell$FactoryClass = XposedHelpers.findClassIfExists(
                AutomationResolver.resolve("org.telegram.ui.SettingsActivity$SettingCell$Factory"),
                lpparam.classLoader
        );

        addItem theme = new addItem();
        if (SettingsActivityClass != null && SettingsActivity$SettingCell$FactoryClass != null) {
            theme.newTheme(SettingsActivityClass, SettingsActivity$SettingCell$FactoryClass);
        } else {
            theme.oldTheme();
        }

        FeatureManager.readFeature();

    }

}

