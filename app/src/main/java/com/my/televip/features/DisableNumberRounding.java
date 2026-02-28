package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class DisableNumberRounding {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            if (loadClass.getLocaleControllerClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getLocaleControllerClass(), AutomationResolver.resolve("LocaleController", "formatShortNumber", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("formatShortNumber", new Class[]{int.class, int[].class}),
                        new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                if (FeatureManager.getBoolean(FeatureManager.KEY_Disable_Number_Rounding)) {
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

        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
