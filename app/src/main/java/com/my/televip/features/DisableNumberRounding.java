package com.my.televip.features;

import com.my.televip.Configs.ConfigsManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;

public class DisableNumberRounding {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;

                if (loadClass.getLocaleControllerClass() != null) {
                    HMethod.hookMethod(loadClass.getLocaleControllerClass(), AutomationResolver.resolve("LocaleController", "formatShortNumber", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("formatShortNumber", new Class[]{int.class, int[].class}),
                            new AbstractMethodHook() {
                                @Override
                                protected void beforeMethod(MethodHookParam param) {
                                    if (ConfigsManager.disableNumberRounding.isEnable()) {
                                        int[] rounded = (int[]) param.args[1];
                                        int number = (int) param.args[0];
                                        if (rounded != null) {
                                            rounded[0] = number;
                                        }
                                        param.setResult(String.valueOf(number));
                                    }
                                }
                            }));
                }
            }
        } catch (Throwable t){
            Logger.e(t);
        }
    }

}
