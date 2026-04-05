package com.my.televip.features;

import com.my.televip.ClientChecker;
import com.my.televip.Configs.ConfigsManager;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class TelePremium {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;

                if (loadClass.getUserConfigClass() != null) {

                    HMethod.hookMethod(loadClass.getUserConfigClass(), AutomationResolver.resolve("UserConfig", "isPremium", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                        @Override
                        public void beforeMethod(XC_MethodHook.MethodHookParam param) {
                            if (ConfigsManager.telegramPremium.isEnable()) param.setResult(true);
                        }
                    });
                }
                if (ClientChecker.check(ClientChecker.ClientType.iMe) || ClientChecker.check(ClientChecker.ClientType.iMeWeb)) {
                    Class<?> ForkPremiumPreferencClass = XposedHelpers.findClassIfExists("com.iMe.storage.data.locale.prefs.impl.ForkPremiumPreference", Utils.classLoader);
                    if (ForkPremiumPreferencClass != null) {
                        HMethod.hookMethod(ForkPremiumPreferencClass, "isPremium", new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                if (ConfigsManager.telegramPremium.isEnable())
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
