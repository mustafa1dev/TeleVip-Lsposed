package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.NotificationCenter;

import de.robv.android.xposed.XposedHelpers;

public class FixTLError {

    public static boolean isEnable = false;

    public static void init(){
        isEnable = true;
        try {
            XposedHelpers.findAndHookMethod(loadClass.getLaunchActivityClass(), AutomationResolver.resolve("LaunchActivity", "didReceivedNotification", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("didReceivedNotification", new Class[]{int.class, int.class, Object[].class}), new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    int id = (int) param.args[0];

                    if (id == NotificationCenter.getTlSchemeParseException() && FeatureManager.getBoolean(FeatureManager.KEY_FIX_TL_ERROR)) {
                        param.setResult(null);
                    }
                }
            }));
        } catch (Throwable t){
        Utils.log(t);
    }
    }

}
