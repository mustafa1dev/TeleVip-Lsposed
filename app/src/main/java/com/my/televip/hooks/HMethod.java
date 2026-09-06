package com.my.televip.hooks;

import com.my.televip.ClientChecker;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.xposed.XBridge;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class HMethod {

    public static void hookMethod(Class<?> cls, String name, Object... args) {
        try {
            if (cls != null) {
                XBridge.findAndHookMethod(cls, name, args);
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }

    public static void hookConstructor(Class<?> cls, Object... args) {
        try {
            if (cls != null) {
                XBridge.findAndHookConstructor(cls, args);
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }

    public static void hookMethod(Class<?> cls, String className, String[] names, Object... args) {
        try {
            if (cls != null) {
                for (String name : names) {
                    if (ClientChecker.check(ClientChecker.ClientType.Nagram) && name.equals("formatPmEditedDate")) continue;
                    XBridge.findAndHookMethod(cls, AutomationResolver.resolve(className, name, AutomationResolver.ResolverType.Method), args);
                }
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }

    public static void hookMethod(Method method, AbstractMethodHook callback) {
        hookMember(method, callback);
    }

    public static void hookMember(Member member, AbstractMethodHook callback) {
        try {
            if (member != null) {
                XBridge.hook(member, callback);
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }
}
