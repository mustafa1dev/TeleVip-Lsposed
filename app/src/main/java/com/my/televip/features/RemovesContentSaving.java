package com.my.televip.features;


import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

public class RemovesContentSaving {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            if (loadClass.getMessagesControllerClass() != null) {

                HMethod.hookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "isChatNoForwards", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.isRemovesContentSaving()) param.setResult(false);
                    }
                });
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
