package com.my.televip.base;

/**
 * Framework-agnostic replacement for {@code de.robv.android.xposed.XC_MethodReplacement}.
 * The original method is never invoked; {@link #replaceHookedMethod} supplies the return value.
 */
public abstract class MethodReplacement extends AbstractMethodHook {

    public MethodReplacement() {
        super();
    }

    public MethodReplacement(int priority) {
        super(priority);
    }

    @Override
    protected final void beforeMethod(MethodHookParam param) throws Throwable {
        param.setResult(replaceHookedMethod(param));
    }

    protected abstract Object replaceHookedMethod(MethodHookParam param) throws Throwable;

    public static final MethodReplacement DO_NOTHING = new MethodReplacement(PRIORITY_HIGHEST) {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return null;
        }
    };

    public static MethodReplacement returnConstant(final Object value) {
        return new MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) {
                return value;
            }
        };
    }
}
