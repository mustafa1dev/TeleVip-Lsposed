package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class NoUpdateApp {

    public static void init() {
        try {
            if (loadClass.getLaunchActivityClass() != null) {
                XposedHelpers.findAndHookMethod(
                        loadClass.getLaunchActivityClass(),
                        AutomationResolver.resolve("LaunchActivity", "checkAppUpdate", AutomationResolver.ResolverType.Method), // اسم الدالة
                        AutomationResolver.merge(AutomationResolver.resolveObject("13"), new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                param.setResult(null);
                            }

                        }));
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }
}
