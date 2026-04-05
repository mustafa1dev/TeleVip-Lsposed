package com.my.televip.virtuals.messenger.browser;

import android.content.Context;

import com.my.televip.Utils;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class Browser {

    public static void openUrl(Context context, String url){
        XposedHelpers.callStaticMethod(
                XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.browser.Browser"), Utils.classLoader),
                AutomationResolver.resolve("Browser", "openUrl", AutomationResolver.ResolverType.Method), context, url
        );
    }

}
