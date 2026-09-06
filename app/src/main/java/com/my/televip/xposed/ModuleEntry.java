package com.my.televip.xposed;

import android.app.Activity;
import android.os.Bundle;

import com.my.televip.ClientChecker;
import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.TeleVip;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.logging.Logger;
import com.my.televip.utils.Utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The one bootstrap both entry points funnel into.
 *
 * <p>{@link com.my.televip.MainHook} (legacy {@code assets/xposed_init}) and {@link TeleVipModule}
 * (modern {@code META-INF/xposed/java_init.list}) call {@link #attach}. A framework only activates
 * one of them, but the guard below keeps the module correct even if a host ever activated both.</p>
 */
public final class ModuleEntry {

    private ModuleEntry() {
    }

    private static final Set<String> ATTACHED = Collections.synchronizedSet(new HashSet<String>());

    private static volatile boolean started;

    public static void attach(String packageName, ClassLoader classLoader) {
        try {
            if (packageName == null || classLoader == null) return;
            if (!ClientChecker.ClientType.containsPackage(packageName)) return;
            if (!ATTACHED.add(packageName)) return;

            Utils.pkgName = packageName;
            Utils.classLoader = classLoader;
            Utils.modulePath = XBridge.modulePath();

            Logger.l("attached to " + packageName
                    + " | backend=" + XBridge.backendId()
                    + " | framework=" + XBridge.frameworkName() + " " + XBridge.frameworkVersion()
                    + " | modulePath=" + Utils.modulePath);

            HMethod.hookMethod(ClassLoad.getClass(ClassNames.LAUNCH_ACTIVITY), "onCreate",
                    Bundle.class, new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (started) return;
                            started = true;
                            Activity launchActivity = (Activity) param.thisObject;
                            ClientChecker.checkClientVersion(launchActivity);
                            TeleVip.startHook(launchActivity);
                        }
                    });
        } catch (Throwable throwable) {
            Logger.e(throwable);
        }
    }
}
