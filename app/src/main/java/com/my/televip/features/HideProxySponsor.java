package com.my.televip.features;

import com.my.televip.Class.ClassNames;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.logging.Logger;
import com.my.televip.virtuals.messenger.MessagesController;

public class HideProxySponsor {

    public static boolean isEnable;

    public static void init(){
        try {
            if (!isEnable) {
                isEnable = true;
                if (ClassLoad.getClass(ClassNames.MESSAGES_CONTROLLER) != null) {
                    HMethod.hookMethod(ClassLoad.getClass(ClassNames.MESSAGES_CONTROLLER), AutomationResolver.resolve("MessagesController", "checkPromoInfoInternal", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("checkPromoInfoInternal", new Class[]{boolean.class}), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            new MessagesController(param.thisObject).hidePromoDialog();
                            param.setResult(false);
                        }
                    }));
                }
            }
        } catch (Throwable e){
            Logger.e(e);
        }
    }

}
