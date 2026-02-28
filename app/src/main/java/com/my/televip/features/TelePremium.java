package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.ClientChecker;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class TelePremium {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {

            if (loadClass.getUserConfigClass() != null) {

                XposedHelpers.findAndHookMethod(loadClass.getUserConfigClass(), AutomationResolver.resolve("UserConfig", "isPremium", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    public void beforeMethod(XC_MethodHook.MethodHookParam param) {
                        if (FeatureManager.getBoolean(FeatureManager.KEY_TELE_PREMIUM)) {
                            param.setResult(true);
                        }
                    }
                });
            }
            if (ClientChecker.check(ClientChecker.ClientType.iMe) || ClientChecker.check(ClientChecker.ClientType.iMeWeb)) {
                Class<?> ForkPremiumPreferencClass = XposedHelpers.findClassIfExists("com.iMe.storage.data.locale.prefs.impl.ForkPremiumPreference", lpparam.classLoader);
                if(ForkPremiumPreferencClass != null) {
                    XposedHelpers.findAndHookMethod(ForkPremiumPreferencClass, "isPremium", new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (FeatureManager.getBoolean(FeatureManager.KEY_TELE_PREMIUM)) {
                                param.setResult(true);
                            }
                        }
                    });
                }
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
