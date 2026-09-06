package com.my.televip.xposed;

import com.my.televip.base.AbstractMethodHook;

import java.lang.reflect.Member;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

/**
 * Backend for the classic {@code de.robv.android.xposed} API (module API 93).
 *
 * <p>Only ever loaded from {@link com.my.televip.MainHook}, i.e. only when the framework already
 * proved that the legacy bridge exists in this process. Nothing else in the module references
 * {@code de.robv.*}, so a modern-API-only host never has to resolve this class.</p>
 */
public final class LegacyBackend implements XBridge.Backend {

    public static final String ID = "legacy-93";

    private final String modulePath;

    public LegacyBackend(String modulePath) {
        this.modulePath = modulePath;
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public void log(String message) {
        XposedBridge.log(message);
    }

    @Override
    public void log(Throwable throwable) {
        XposedBridge.log(throwable);
    }

    @Override
    public void hook(Member member, AbstractMethodHook callback) {
        XposedBridge.hookMethod(member, new Adapter(callback));
    }

    @Override
    public boolean deoptimize(Member member) {
        try {
            if (member instanceof java.lang.reflect.Method) {
                return XposedBridge.deoptimizeMethod((java.lang.reflect.Method) member);
            }
        } catch (Throwable ignored) {
            // deoptimizeMethod only exists on newer LSPosed builds
        }
        return false;
    }

    @Override
    public String modulePath() {
        return modulePath;
    }

    @Override
    public String frameworkName() {
        return "Xposed (legacy API)";
    }

    @Override
    public String frameworkVersion() {
        try {
            return String.valueOf(XposedBridge.getXposedVersion());
        } catch (Throwable ignored) {
            return "unknown";
        }
    }

    /** Translates the legacy before/after callback pair into our framework-neutral param. */
    private static final class Adapter extends XC_MethodHook {

        private final AbstractMethodHook callback;

        Adapter(AbstractMethodHook callback) {
            super(callback.getPriority());
            this.callback = callback;
        }

        @Override
        protected void beforeHookedMethod(MethodHookParam param) {
            AbstractMethodHook.MethodHookParam neutral =
                    new AbstractMethodHook.MethodHookParam(param.method, param.thisObject, param.args);
            callback.dispatchBefore(neutral);
            if (!neutral.isResultSet()) return;
            if (neutral.hasThrowable()) {
                param.setThrowable(neutral.getThrowable());
            } else {
                param.setResult(neutral.getResult());
            }
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) {
            AbstractMethodHook.MethodHookParam neutral =
                    new AbstractMethodHook.MethodHookParam(param.method, param.thisObject, param.args);
            if (param.hasThrowable()) {
                neutral.initThrowable(param.getThrowable());
            } else {
                neutral.initResult(param.getResult());
            }
            callback.dispatchAfter(neutral);
            if (!neutral.isResultSet()) return;
            if (neutral.hasThrowable()) {
                param.setThrowable(neutral.getThrowable());
            } else {
                param.setResult(neutral.getResult());
            }
        }
    }
}
