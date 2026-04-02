package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.UserConfig;
import com.my.televip.virtuals.tgnet.TLRPC;

public class HidePhone {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            if (loadClass.getUserConfigClass() != null) {
                HMethod.hookMethod(loadClass.getUserConfigClass(), AutomationResolver.resolve("UserConfig", "getClientUserId", AutomationResolver.ResolverType.Method),  new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.isHidePhone()) {
                            UserConfig userConfig = new UserConfig(param.thisObject);
                            if (userConfig.getCurrentUser().getUser() != null) {
                                TLRPC.User user = userConfig.getCurrentUser();
                                if (user.getPhone() != null) {
                                    user.setPhone(null);
                                }
                            }
                        }
                    }
                });
            }

        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
