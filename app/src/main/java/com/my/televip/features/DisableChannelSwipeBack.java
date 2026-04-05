package com.my.televip.features;

import android.view.MotionEvent;

import com.my.televip.Configs.ConfigsManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;

public class DisableChannelSwipeBack {
    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;
                HMethod.hookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "isSwipeBackEnabled", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("isSwipeBackEnabled", new Class[]{MotionEvent.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (ConfigsManager.disableChannelSwipeBack.isEnable()) {
                            param.setResult(false);
                        }
                    }
                }));
            }
        } catch (Exception e){
            Logger.e(e);
        }
    }

}
