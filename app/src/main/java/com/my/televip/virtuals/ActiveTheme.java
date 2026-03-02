package com.my.televip.virtuals;

import com.my.televip.ClientChecker;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class ActiveTheme {

    public static boolean getActiveTheme() {
        try {
            if (ClientChecker.check(ClientChecker.ClientType.Telegraph)){
                boolean activeTheme = (boolean) XposedHelpers.callStaticMethod(loadClass.getThemeClass(), AutomationResolver.resolve("Theme","getActiveTheme", AutomationResolver.ResolverType.Method));
                activeTheme = !activeTheme;
                return !activeTheme;
            }

            Object currentThemeInfo = XposedHelpers.callStaticMethod(
                    loadClass.getThemeClass(),
                    AutomationResolver.resolve("Theme","getActiveTheme", AutomationResolver.ResolverType.Method));

            if (currentThemeInfo != null) {

                return !((boolean) XposedHelpers.callMethod(currentThemeInfo, AutomationResolver.resolve("Theme", "isDark", AutomationResolver.ResolverType.Method)));
            } else {
                XposedBridge.log("getActiveTheme returned null.");
                return true;
            }
        } catch (Exception e) {
            XposedBridge.log("getActiveTheme: Error while checking isDark - " + e.getMessage());
            return true;
        }
    }
}
