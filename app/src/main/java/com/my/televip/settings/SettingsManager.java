package com.my.televip.settings;

import com.my.televip.ClientChecker;
import com.my.televip.Clients.Turrit;
import com.my.televip.utils.Utils;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.settings.controller.SettingsController;
import com.my.televip.settings.hook.SettingsHook;
import com.my.televip.settings.ui.SettingsActivity;

import de.robv.android.xposed.XposedHelpers;

public class SettingsManager {

    public static void init(SettingsController settingsController) {

        SettingsActivity.init(settingsController);

        Class<?> SettingsActivityClass = XposedHelpers.findClassIfExists(
                AutomationResolver.resolve("org.telegram.ui.SettingsActivity"),
                Utils.classLoader
        );

        Class<?> SettingsActivity$SettingCell$FactoryClass = XposedHelpers.findClassIfExists(
                AutomationResolver.resolve("org.telegram.ui.SettingsActivity$SettingCell$Factory"),
                Utils.classLoader
        );

        SettingsHook settings = new SettingsHook();
        if (SettingsActivityClass != null && SettingsActivity$SettingCell$FactoryClass != null) {
            settings.newSettings(SettingsActivityClass, SettingsActivity$SettingCell$FactoryClass, settingsController);
        } else {
            settings.oldSettings(settingsController);
        }
        if (ClientChecker.check(ClientChecker.ClientType.ForkgramClassic)) settings.oldSettings(settingsController);
        if (ClientChecker.check(ClientChecker.ClientType.Turrit)) Turrit.showGhostModeDialog(settingsController);
    }

}
