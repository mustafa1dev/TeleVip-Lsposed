package com.my.televip.xposed;

import android.util.Log;

import com.my.televip.base.AbstractMethodHook;

import java.lang.reflect.Executable;
import java.lang.reflect.Member;
import java.util.List;

import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedInterfaceWrapper;

/**
 * Backend for the modern libxposed API (100 / 102), i.e. current LSPosed and Vector 2.2.
 *
 * <p>API 102 replaced the before/after callback pair with an OkHttp-style interceptor chain. The
 * adapter below rebuilds the classic semantics on top of it:</p>
 * <ul>
 *   <li>a result or throwable set in {@code beforeMethod} short-circuits the chain, so the original
 *       method is never invoked — the equivalent of the legacy {@code setResult} in "before";</li>
 *   <li>otherwise {@code chain.proceed(args)} runs with the (possibly mutated) argument array, and
 *       its outcome is handed to {@code afterMethod}, which may still override it.</li>
 * </ul>
 *
 * <p>Only ever loaded from {@link TeleVipModule}, so a legacy-only host never resolves
 * {@code io.github.libxposed.*}.</p>
 */
public final class ModernBackend implements XBridge.Backend {

    public static final String ID = "modern-102";

    private static final String TAG = "TeleVip";

    private final XposedInterfaceWrapper xposed;
    private final String modulePath;

    public ModernBackend(XposedInterfaceWrapper xposed, String modulePath) {
        this.xposed = xposed;
        this.modulePath = modulePath;
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public void log(String message) {
        xposed.log(Log.INFO, TAG, message);
    }

    @Override
    public void log(Throwable throwable) {
        xposed.log(Log.ERROR, TAG, String.valueOf(throwable), throwable);
    }

    @Override
    public void hook(Member member, AbstractMethodHook callback) {
        if (!(member instanceof Executable)) {
            log("cannot hook non-executable member: " + member);
            return;
        }
        xposed.hook((Executable) member)
                .setPriority(callback.getPriority())
                .setId(TAG)
                .intercept(new Interceptor(callback));
    }

    @Override
    public boolean deoptimize(Member member) {
        try {
            return member instanceof Executable && xposed.deoptimize((Executable) member);
        } catch (Throwable ignored) {
            return false;
        }
    }

    @Override
    public String modulePath() {
        return modulePath;
    }

    @Override
    public String frameworkName() {
        try {
            return xposed.getFrameworkName();
        } catch (Throwable ignored) {
            return "libxposed";
        }
    }

    @Override
    public String frameworkVersion() {
        try {
            return xposed.getFrameworkVersion() + " (api " + xposed.getApiVersion() + ")";
        } catch (Throwable ignored) {
            return "unknown";
        }
    }

    private static final class Interceptor implements XposedInterface.Hooker {

        private final AbstractMethodHook callback;

        Interceptor(AbstractMethodHook callback) {
            this.callback = callback;
        }

        @Override
        public Object intercept(XposedInterface.Chain chain) throws Throwable {
            Executable executable = chain.getExecutable();
            Object thisObject = chain.getThisObject();

            // getArgs() is immutable; feature code mutates param.args in place, so hand it a copy
            // and pass that copy to proceed().
            List<Object> immutableArgs = chain.getArgs();
            Object[] args = immutableArgs.toArray(new Object[0]);

            AbstractMethodHook.MethodHookParam before =
                    new AbstractMethodHook.MethodHookParam(executable, thisObject, args);
            callback.dispatchBefore(before);

            if (before.isResultSet()) {
                // Short-circuit: the original method (and every interceptor below us) is skipped.
                if (before.hasThrowable()) throw before.getThrowable();
                return before.getResult();
            }

            AbstractMethodHook.MethodHookParam after =
                    new AbstractMethodHook.MethodHookParam(executable, thisObject, args);
            try {
                after.initResult(chain.proceed(args));
            } catch (Throwable throwable) {
                after.initThrowable(throwable);
            }

            callback.dispatchAfter(after);

            if (after.hasThrowable()) throw after.getThrowable();
            return after.getResult();
        }
    }
}
