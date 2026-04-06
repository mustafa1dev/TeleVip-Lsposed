package com.my.televip.features;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;

public class DisableNumberRounding {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;

                if (ClassLoad.getClass(ClassNames.LOCALE_CONTROLLER) != null) {
                    HMethod.hookMethod(ClassLoad.getClass(ClassNames.LOCALE_CONTROLLER), AutomationResolver.resolve("LocaleController", "formatShortNumber", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("formatShortNumber", new Class[]{int.class, int[].class}),
                            new AbstractMethodHook() {
                                @Override
                                protected void beforeMethod(MethodHookParam param) {
                                    if (ConfigManager.disableNumberRounding.isEnable()) {
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
        } catch (Throwable t) {
            Logger.e(t);
        }
    }
}
