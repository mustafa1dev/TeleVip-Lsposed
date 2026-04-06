package com.my.televip.features;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.NotificationCenter;

public class FixTLError {

    public static boolean isEnable = false;

    public static void init(){
        try {
            if (!isEnable) {
                isEnable = true;
                if (ClassLoad.getClass(ClassNames.LAUNCH_ACTIVITY) != null) {
                    HMethod.hookMethod(ClassLoad.getClass(ClassNames.LAUNCH_ACTIVITY), AutomationResolver.resolve("LaunchActivity", "didReceivedNotification", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("didReceivedNotification", new Class[]{int.class, int.class, Object[].class}), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            int id = (int) param.args[0];
                            if (id == NotificationCenter.getTlSchemeParseException() && ConfigManager.fixTLError.isEnable())
                                param.setResult(null);
                        }
                    }));
                }
            }
        } catch (Throwable t){
            Logger.e(t);
    }
    }

}
