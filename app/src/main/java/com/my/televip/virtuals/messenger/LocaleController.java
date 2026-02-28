package com.my.televip.virtuals.messenger;

import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.util.Locale;

import de.robv.android.xposed.XposedHelpers;

public class LocaleController {

    Object localeController;

    public LocaleController(){
        localeController = XposedHelpers.callStaticMethod(loadClass.getLocaleControllerClass(), AutomationResolver.resolve("LocaleController", "getInstance", AutomationResolver.ResolverType.Method));
    }

    public Locale getCurrentLocale() {
        return (Locale) XposedHelpers.getObjectField(localeController, AutomationResolver.resolve("LocaleController", "currentLocale", AutomationResolver.ResolverType.Field));
    }

}
