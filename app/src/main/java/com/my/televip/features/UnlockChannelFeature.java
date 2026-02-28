package com.my.televip.features;


import static com.my.televip.MainHook.lpparam;

import com.my.televip.Utils;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

public class UnlockChannelFeature {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            if (loadClass.getMessagesControllerClass() != null) {

                Class<?> TLRPC$ChatClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Chat"), lpparam.classLoader);

                XposedHelpers.findAndHookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "isChatNoForwards", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("isChatNoForwards", new Class[]{TLRPC$ChatClass}), new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) {
                        return false;
                    }
                }));
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
