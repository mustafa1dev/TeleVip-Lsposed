package com.my.televip.virtuals.messenger.browser;

import com.my.televip.MainHook;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class Browser {

    public static void openUrl(String url){
        XposedHelpers.callStaticMethod(
                XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.browser.Browser"), MainHook.lpparam.classLoader),
                AutomationResolver.resolve("Browser", "openUrl", AutomationResolver.ResolverType.Method), MainHook.launchActivity.getApplicationContext(), url
        );
    }

}
