package com.my.televip.features;

import android.view.MotionEvent;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;

public class DisableProfileSwipeBack {
    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;

                if (ClassLoad.getClass(ClassNames.CHAT_ACTIVITY) != null) {

                    HMethod.hookMethod(ClassLoad.getClass(ClassNames.CHAT_ACTIVITY), AutomationResolver.resolve("ProfileActivity", "isSwipeBackEnabled", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("isSwipeBackEnabled", new Class[]{MotionEvent.class}), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (ConfigManager.disableProfileSwipeBack.isEnable()) {
                                param.setResult(false);
                            }
                        }
                    }));
                }
            }
        } catch (Throwable e){
            Logger.e(e);
        }
    }

}
