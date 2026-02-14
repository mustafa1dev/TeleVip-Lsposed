package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class HideTyping {

    public static void init() {
        try {
            Class<?> chatActivityEnterViewDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity$ChatActivityEnterViewDelegate"), lpparam.classLoader);
            if (chatActivityEnterViewDelegateClass != null) {
                XposedHelpers.findAndHookMethod(
                        chatActivityEnterViewDelegateClass,
                        AutomationResolver.resolve("ChatActivity$ChatActivityEnterViewDelegate", "needSendTyping", AutomationResolver.ResolverType.Method),
                        new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                //XposedBridge.log("needSendTyping method is blocked.");
                                param.setResult(null);
                            }
                        });
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }
}
