package com.my.televip.xposed;

import io.github.libxposed.api.XposedModule;
import io.github.libxposed.api.XposedModuleInterface;

/**
 * Modern entry point (libxposed API 100 / 102).
 *
 * <p>Registered through {@code META-INF/xposed/java_init.list}. Used by current LSPosed and by
 * Vector 2.2 running on Zygisk Next / NeoZygisk. The legacy {@code assets/xposed_init} entry is
 * still shipped for older frameworks; whichever the host picks, both land in
 * {@link ModuleEntry#attach}.</p>
 *
 * <p>Per the API contract nothing is initialised in the constructor — the framework attaches itself
 * first and then calls {@link #onModuleLoaded}.</p>
 */
public class TeleVipModule extends XposedModule {

    public TeleVipModule() {
        super();
    }

    @Override
    public void onModuleLoaded(XposedModuleInterface.ModuleLoadedParam param) {
        super.onModuleLoaded(param);

        String modulePath = null;
        try {
            // Replaces the legacy initZygote() startupParam.modulePath, which the modern API
            // does not have. Needed to read assets/lang/*.json out of our own APK.
            modulePath = getModuleApplicationInfo().sourceDir;
        } catch (Throwable ignored) {
        }

        XBridge.install(new ModernBackend(this, modulePath));
        XBridge.setModulePath(modulePath);
    }

    @Override
    public void onPackageReady(XposedModuleInterface.PackageReadyParam param) {
        super.onPackageReady(param);
        if (!param.isFirstPackage()) return;
        // getClassLoader() rather than getDefaultClassLoader(): it is correct even for clients
        // shipping a custom AppComponentFactory, and it carries no minSdk 29 requirement.
        ModuleEntry.attach(param.getPackageName(), param.getClassLoader());
    }
}
