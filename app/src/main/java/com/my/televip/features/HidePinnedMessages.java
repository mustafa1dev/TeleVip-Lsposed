package com.my.televip.features;

import android.view.View;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ui.ChatActivity;

public class HidePinnedMessages {
    public static boolean isEnable = false;

    public static void init() {
        try {
            isEnable = true;

            HMethod.hookMethod(loadClass.getChatActivityClass(),
                    AutomationResolver.resolve("ChatActivity", "createPinnedMessageView", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                @Override
                protected void afterMethod(MethodHookParam param) {
                    if (FeatureManager.isHidePinnedMessages()) {
                        View button = new ChatActivity(param.thisObject).getPinnedMessageView();
                        if (button != null && button.getVisibility() != View.GONE)
                            button.setVisibility(View.GONE);
                    }
                }
            });
            HMethod.hookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "updatePinnedMessageView", AutomationResolver.ResolverType.Method), boolean.class, int.class, new AbstractMethodHook() {
                @Override
                protected void afterMethod(MethodHookParam param) {
                    if (FeatureManager.isHidePinnedMessages()) {
                        View button = new ChatActivity(param.thisObject).getPinnedMessageView();
                        if (button != null && button.getVisibility() != View.GONE)
                            button.setVisibility(View.GONE);
                    }
                }
            });
        } catch (Exception e){
            Utils.log(e);
        }
    }

}
