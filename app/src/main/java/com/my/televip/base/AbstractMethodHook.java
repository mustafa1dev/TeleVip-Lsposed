package com.my.televip.base;

import com.my.televip.utils.Logger;

import de.robv.android.xposed.XC_MethodHook;

public abstract class AbstractMethodHook extends XC_MethodHook {
    protected void beforeMethod(MethodHookParam param) throws Throwable {

    }

    protected void afterMethod(MethodHookParam param) throws Throwable {

    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        try {
            beforeMethod(param);
        } catch (Throwable throwable) {
            Logger.e(throwable);
        }
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        try {
            afterMethod(param);
        } catch (Throwable throwable) {
            Logger.e(throwable);
        }
    }
}
