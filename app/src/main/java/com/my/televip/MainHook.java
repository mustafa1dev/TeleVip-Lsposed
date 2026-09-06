package com.my.televip;

import com.my.televip.xposed.LegacyBackend;
import com.my.televip.xposed.ModuleEntry;
import com.my.televip.xposed.XBridge;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Legacy entry point (Xposed module API 93), registered through {@code assets/xposed_init}.
 *
 * <p>Kept so the module keeps working on frameworks that do not expose the modern libxposed API.
 * On LSPosed 1.10+/Vector the modern {@link com.my.televip.xposed.TeleVipModule} entry is used
 * instead and this class is never touched.</p>
 */
public class MainHook implements IXposedHookLoadPackage, IXposedHookZygoteInit {

    private volatile String modulePath;

    @Override
    public void initZygote(StartupParam startupParam) {
        modulePath = startupParam.modulePath;
        XBridge.setModulePath(modulePath);
    }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        if (!ClientChecker.ClientType.containsPackage(lpparam.packageName)) {
            return;
        }
        // initZygote is not guaranteed to have run for an app-scoped module; XBridge falls back to
        // deriving the APK path from our own class loader when modulePath is still null.
        XBridge.install(new LegacyBackend(modulePath));
        ModuleEntry.attach(lpparam.packageName, lpparam.classLoader);
    }
}
