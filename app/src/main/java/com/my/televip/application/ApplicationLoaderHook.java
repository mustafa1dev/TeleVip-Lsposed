package com.my.televip.application;

import android.content.Context;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class ApplicationLoaderHook {

    private static Context applicationContext;

    public static Context getApplicationContext() {
        if (applicationContext == null) {
            applicationContext = (Context) XReflect.getStaticObjectField(
                    ClassLoad.getClass(ClassNames.APPLICATION_LOADER),
                    AutomationResolver.resolve("ApplicationLoader", "applicationContext", AutomationResolver.ResolverType.Field)
            );
        }
        return applicationContext;
    }
}