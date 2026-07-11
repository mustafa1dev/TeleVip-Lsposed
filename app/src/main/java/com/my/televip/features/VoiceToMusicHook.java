package com.my.televip.features;


import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.MessageObject;

public class VoiceToMusicHook {

    public static boolean isEnable = false;
    private static boolean stop = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;

                HMethod.hookMethod(ClassLoad.getClass(ClassNames.MESSAGE_OBJECT), AutomationResolver.resolve("MessageObject", "isVoice", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (!stop) param.setResult(false);
                    }
                    @Override
                    protected void afterMethod(MethodHookParam param) {
                        stop = false;
                    }
                });
                HMethod.hookMethod(ClassLoad.getClass(ClassNames.MESSAGE_OBJECT),  AutomationResolver.resolve("MessageObject", "isMusic", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        MessageObject messageObject = new MessageObject(param.thisObject);
                        stop = true;
                        if (messageObject.isVoice()) param.setResult(true);
                    }
                });
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }

}
