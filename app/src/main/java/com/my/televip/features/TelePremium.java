package com.my.televip.features;

import com.my.televip.Class.ClassNames;
import com.my.televip.ClientChecker;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.utils.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.logging.Logger;

import com.my.televip.reflect.XReflect;

public class TelePremium {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;

                if (ClassLoad.getClass(ClassNames.USER_CONFIG) != null) {

                    HMethod.hookMethod(ClassLoad.getClass(ClassNames.USER_CONFIG), AutomationResolver.resolve("UserConfig", "isPremium", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                        @Override
                        public void beforeMethod(AbstractMethodHook.MethodHookParam param) {
                            if (ConfigManager.telegramPremium.isEnable()) param.setResult(true);
                        }
                    });
                }
                if (ClientChecker.check(ClientChecker.ClientType.iMe) || ClientChecker.check(ClientChecker.ClientType.iMeWeb)) {
                    Class<?> ForkPremiumPreferencClass = XReflect.findClassIfExists("com.iMe.storage.data.locale.prefs.impl.ForkPremiumPreference", Utils.classLoader);
                    if (ForkPremiumPreferencClass != null) {
                        HMethod.hookMethod(ForkPremiumPreferencClass, "isPremium", new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                if (ConfigManager.telegramPremium.isEnable())
                                    param.setResult(true);
                            }
                        });
                    }
                }
            }
        } catch (Throwable t){
            Logger.e(t);
        }
    }

}
