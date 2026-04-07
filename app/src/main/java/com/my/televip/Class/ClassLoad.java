package com.my.televip.Class;

import android.content.Context;

import com.my.televip.ClientChecker;
import com.my.televip.Utils;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;

import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.XposedHelpers;

public class ClassLoad {


    private static final Map<String, Class<?>> cache = new HashMap<>();

    public static Class<?> getClass(String name) {
        String resolved = AutomationResolver.resolve(name);

        if (cache.containsKey(resolved)) {
            return cache.get(resolved);
        }

        try {
            Class<?> cls = XposedHelpers.findClassIfExists(
                    resolved,
                    Utils.classLoader
            );
            if (cls != null) {
                cache.put(resolved, cls);
            } else {
                if ((ClientChecker.check(ClientChecker.ClientType.Nagram) || ClientChecker.check(ClientChecker.ClientType.Momogram)) && name.equals(ClassNames.DRAWABLE)) return null;
                Logger.w("Not found " + name + ", " + resolved + " " + Utils.issue);
            }
            return cls;

        } catch (Throwable e) {
            Logger.e(e);
            return null;
        }
    }

    public static Class<?> getClass(String name, ClassLoader classLoader) {
        String resolved = AutomationResolver.resolve(name);

        if (cache.containsKey(resolved)) {
            return cache.get(resolved);
        }

        try {
            Class<?> cls = XposedHelpers.findClassIfExists(
                    resolved,
                    classLoader
            );
            if (cls != null) {
                cache.put(resolved, cls);
            } else {
                Logger.w("Not found " + name + ", " + resolved + " " + Utils.issue);
            }
            return cls;

        } catch (Throwable e) {
            Logger.e(e);
            return null;
        }
    }

    private static Context applicationContext;

    public static Context getApplicationContext() {
        if (applicationContext == null) {
            applicationContext = (Context) XposedHelpers.getStaticObjectField(
                    getClass(ClassNames.APPLICATION_LOADER),
                    AutomationResolver.resolve("ApplicationLoader", "applicationContext", AutomationResolver.ResolverType.Field)
            );
        }
        return applicationContext;
    }

}
