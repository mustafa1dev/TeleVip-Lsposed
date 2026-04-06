package com.my.televip.features;

import android.view.View;

import com.my.televip.Class.ClassNames;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.logging.Logger;
import com.my.televip.virtuals.ui.ChatActivity;

public class HidePinnedMessages {
    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;
                if (ClassLoad.getClass(ClassNames.CHAT_ACTIVITY) != null) {
                    HMethod.hookMethod(ClassLoad.getClass(ClassNames.CHAT_ACTIVITY),
                            AutomationResolver.resolve("ChatActivity", "createPinnedMessageView", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                                @Override
                                protected void afterMethod(MethodHookParam param) {
                                    if (ConfigManager.hidePinnedMessages.isEnable()) {
                                        View button = new ChatActivity(param.thisObject).getPinnedMessageView();
                                        if (button != null && button.getVisibility() != View.GONE)
                                            button.setVisibility(View.GONE);
                                    }
                                }
                            });
                    HMethod.hookMethod(ClassLoad.getClass(ClassNames.CHAT_ACTIVITY), AutomationResolver.resolve("ChatActivity", "updatePinnedMessageView", AutomationResolver.ResolverType.Method), boolean.class, int.class, new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(MethodHookParam param) {
                            if (ConfigManager.hidePinnedMessages.isEnable()) {
                                View button = new ChatActivity(param.thisObject).getPinnedMessageView();
                                if (button != null && button.getVisibility() != View.GONE)
                                    button.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        } catch (Throwable e){
            Logger.e(e);
        }
    }

}
