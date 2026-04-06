package com.my.televip.virtuals.tgnet;

import com.my.televip.Class.ClassNames;
import com.my.televip.Utils;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Proxy;

import de.robv.android.xposed.XposedHelpers;

public class RequestDelegate {

    public Object requestDelegate;

    public RequestDelegate(Object obj){
        requestDelegate = obj;
    }

    public void run(Object response, Object error){
        XposedHelpers.callMethod(requestDelegate, AutomationResolver.resolve("RequestDelegate", "run", AutomationResolver.ResolverType.Method), response, error);
    }


    @FunctionalInterface
    public interface requestDelegate {
        void run(Object response, Object error);
    }

    public static Object run(requestDelegate lambda) {
        Class<?> requestDelegateClass = ClassLoad.getClass(ClassNames.REQUEST_DELEGATE);
        if (requestDelegateClass != null) {
            return Proxy.newProxyInstance(
                    Utils.classLoader,
                    new Class[]{requestDelegateClass},
                    (proxy, method, args) -> {
                        if (method.getParameterCount() == 2 && method.getParameterTypes()[0] == ClassLoad.getClass(ClassNames.TL_OBJECT)) {
                            lambda.run(args[0], args[1]);
                        }
                        return null;
                    }
            );
        }
        return null;
    }
}
