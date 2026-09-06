package com.my.televip.base;

import com.my.televip.logging.Logger;

import java.lang.reflect.Member;

/**
 * Framework-agnostic method hook callback.
 *
 * <p>This class used to extend {@code de.robv.android.xposed.XC_MethodHook}. It no longer does,
 * so that the very same hook objects can be dispatched by either the legacy Xposed API (93) or the
 * modern libxposed API (100/102) backend. See {@code com.my.televip.xposed.XBridge}.</p>
 *
 * <p>The nested {@link MethodHookParam} intentionally mirrors the legacy
 * {@code AbstractMethodHook.MethodHookParam} surface (public {@code args} / {@code thisObject} fields,
 * {@code getResult()} / {@code setResult()}), so existing feature code keeps compiling unchanged.</p>
 */
public abstract class AbstractMethodHook {

    public static final int PRIORITY_LOWEST = -10000;
    public static final int PRIORITY_DEFAULT = 50;
    public static final int PRIORITY_HIGHEST = 10000;

    private final int priority;

    public AbstractMethodHook() {
        this(PRIORITY_DEFAULT);
    }

    public AbstractMethodHook(int priority) {
        this.priority = priority;
    }

    public final int getPriority() {
        return priority;
    }

    protected void beforeMethod(MethodHookParam param) throws Throwable {
    }

    protected void afterMethod(MethodHookParam param) throws Throwable {
    }

    /** Invoked by the active backend. Never throws: a broken feature must not crash the client. */
    public final void dispatchBefore(MethodHookParam param) {
        try {
            beforeMethod(param);
        } catch (Throwable throwable) {
            Logger.e(throwable);
        }
    }

    /** Invoked by the active backend. Never throws: a broken feature must not crash the client. */
    public final void dispatchAfter(MethodHookParam param) {
        try {
            afterMethod(param);
        } catch (Throwable throwable) {
            Logger.e(throwable);
        }
    }

    public static class MethodHookParam {

        /** The hooked method or constructor. */
        public Member method;
        /** The {@code this} reference, {@code null} for static methods and constructors. */
        public Object thisObject;
        /** Live argument array. Writing to a slot changes the argument the callee receives. */
        public Object[] args;

        private Object result;
        private Throwable throwable;
        private boolean resultSet;

        public MethodHookParam() {
        }

        public MethodHookParam(Member method, Object thisObject, Object[] args) {
            this.method = method;
            this.thisObject = thisObject;
            this.args = args;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
            this.throwable = null;
            this.resultSet = true;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public boolean hasThrowable() {
            return throwable != null;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
            this.result = null;
            this.resultSet = true;
        }

        public Object getResultOrThrowable() throws Throwable {
            if (throwable != null) throw throwable;
            return result;
        }

        /** True once the callback explicitly assigned a result or a throwable. */
        public boolean isResultSet() {
            return resultSet;
        }

        /** Seeds the outcome of the original call without marking it as overridden. */
        public void initResult(Object result) {
            this.result = result;
            this.throwable = null;
            this.resultSet = false;
        }

        /** Seeds the outcome of the original call without marking it as overridden. */
        public void initThrowable(Throwable throwable) {
            this.throwable = throwable;
            this.result = null;
            this.resultSet = false;
        }
    }
}
