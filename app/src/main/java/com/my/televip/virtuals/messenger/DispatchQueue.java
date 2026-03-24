package com.my.televip.virtuals.messenger;

import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class DispatchQueue {

    Object dispatchQueue;

    public DispatchQueue(Object obj){
        dispatchQueue = obj;
    }

    public void postRunnable(Runnable runnable) {
        XposedHelpers.callMethod(dispatchQueue, AutomationResolver.resolve("DispatchQueue", "postRunnable", AutomationResolver.ResolverType.Method), runnable);
    }

}
