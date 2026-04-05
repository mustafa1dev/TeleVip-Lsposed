package com.my.televip;

import static com.my.televip.obfuscate.AutomationResolver.resolverRegistry;

import android.app.Activity;

import com.my.televip.Configs.ConfigsManager;
import com.my.televip.application.AndroidUtilities;
import com.my.televip.application.ApplicationLoaderHook;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.dex.DexInjector;
import com.my.televip.features.SaveEditsHistory;
import com.my.televip.language.Translator;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.settings.SettingsActivity;
import com.my.televip.settings.SettingsInjector;
import com.my.televip.utils.Logger;
import com.my.televip.virtuals.TeleVip.Bridge.Bridge;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TeleVip {
    
    public static void startHook(final XC_LoadPackage.LoadPackageParam lpparam, Activity activity) {
        try {
            resolverRegistry.loadParameter();
            Translator.init();
            AndroidUtilities.init(activity);
            DexInjector.injectDex();
            Bridge.init(activity);
            ConfigsManager.loadAndRead(activity);
            SettingsActivity.init();

            ApplicationLoaderHook.init();


            Class<?> SettingsActivityClass = XposedHelpers.findClassIfExists(
                    AutomationResolver.resolve("org.telegram.ui.SettingsActivity"),
                    lpparam.classLoader
            );

            Class<?> SettingsActivity$SettingCell$FactoryClass = XposedHelpers.findClassIfExists(
                    AutomationResolver.resolve("org.telegram.ui.SettingsActivity$SettingCell$Factory"),
                    lpparam.classLoader
            );

            SettingsInjector settings = new SettingsInjector();
            if (SettingsActivityClass != null && SettingsActivity$SettingCell$FactoryClass != null) {
                settings.newSettings(activity, SettingsActivityClass, SettingsActivity$SettingCell$FactoryClass);
            } else {
                settings.oldSettings(activity);
            }

            XposedHelpers.findAndHookMethod(loadClass.getLaunchActivityClass(), "onDestroy", new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (SaveEditsHistory.messageDatabase != null) {
                        SaveEditsHistory.messageDatabase.closeDatabase();
                    }
                }
            });
        } catch (Exception e){
            Logger.e(e);
        }

    }

}
