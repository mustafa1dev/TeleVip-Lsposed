package com.my.televip.features;

import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;
import com.my.televip.virtuals.messenger.MessagesController;

public class HideProxySponsor {

    public static boolean isEnable;

    public static void init(){
        try {
            if (!isEnable) {
                isEnable = true;
                HMethod.hookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "checkPromoInfoInternal", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("checkPromoInfoInternal", new Class[]{boolean.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        new MessagesController(param.thisObject).hidePromoDialog();
                        param.setResult(false);
                    }
                }));
            }
        } catch (Exception e){
            Logger.e(e);
        }
    }

}
