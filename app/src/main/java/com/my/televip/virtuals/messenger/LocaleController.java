package com.my.televip.virtuals.messenger;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;

import java.util.Locale;

import com.my.televip.reflect.XReflect;

public class LocaleController {

    Object localeController;

    public LocaleController(){
        localeController = XReflect.callStaticMethod(ClassLoad.getClass(ClassNames.LOCALE_CONTROLLER), AutomationResolver.resolve("LocaleController", "getInstance", AutomationResolver.ResolverType.Method));
    }

    public Locale getCurrentLocale() {
        return (Locale) XReflect.getObjectField(localeController, AutomationResolver.resolve("LocaleController", "currentLocale", AutomationResolver.ResolverType.Field));
    }

    public static boolean isRTL() {
        return (boolean) XReflect.getStaticBooleanField(ClassLoad.getClass(ClassNames.LOCALE_CONTROLLER), AutomationResolver.resolve("LocaleController", "isRTL", AutomationResolver.ResolverType.Field));
    }

}
