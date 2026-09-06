package com.my.televip.virtuals.messenger;

import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class DispatchQueue {

    Object dispatchQueue;

    public DispatchQueue(Object obj){
        dispatchQueue = obj;
    }

    public void postRunnable(Runnable runnable) {
        XReflect.callMethod(dispatchQueue, AutomationResolver.resolve("DispatchQueue", "postRunnable", AutomationResolver.ResolverType.Method), runnable);
    }

}
