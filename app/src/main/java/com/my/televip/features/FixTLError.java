package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.NotificationCenter;

public class FixTLError {

    public static boolean isEnable = false;

    public static void init(){
        isEnable = true;
        try {
            HMethod.hookMethod(loadClass.getLaunchActivityClass(), AutomationResolver.resolve("LaunchActivity", "didReceivedNotification", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("didReceivedNotification", new Class[]{int.class, int.class, Object[].class}), new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    int id = (int) param.args[0];
                    if (id == NotificationCenter.getTlSchemeParseException() && FeatureManager.isFixTLError()) param.setResult(null);
                }
            }));
        } catch (Throwable t){
        Utils.log(t);
    }
    }

}
