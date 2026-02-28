package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class HideTyping {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            if (loadClass.getChatActivity$ChatActivityEnterViewDelegateClass() != null) {
                XposedHelpers.findAndHookMethod(
                        loadClass.getChatActivity$ChatActivityEnterViewDelegateClass(),
                        AutomationResolver.resolve("ChatActivity$ChatActivityEnterViewDelegate", "needSendTyping", AutomationResolver.ResolverType.Method),
                        new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                if (FeatureManager.getBoolean(FeatureManager.KEY_HIDE_TYPING)) {
                                    param.setResult(null);
                                }
                            }
                        });
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }
}
