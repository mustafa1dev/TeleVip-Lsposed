package com.my.televip.features;


import com.my.televip.Configs.ConfigsManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;

public class RemovesContentSaving {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;

                HMethod.hookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "isChatNoForwards", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("isChatNoForwards", new Class[]{loadClass.getTLRPC$ChatClass()}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (ConfigsManager.removesContentSaving.isEnable()) param.setResult(false);
                    }
                }));
                HMethod.hookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "hasSelectedNoforwardsMessage", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (ConfigsManager.removesContentSaving.isEnable()) param.setResult(false);
                    }
                });
                HMethod.hookMethod(loadClass.getMessageObjectClass(), AutomationResolver.resolve("MessageObject", "canForwardMessage", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (ConfigsManager.removesContentSaving.isEnable()) param.setResult(true);
                    }
                });
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }

}
