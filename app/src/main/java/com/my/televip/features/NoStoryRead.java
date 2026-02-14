package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.obfuscate.AutomationResolver;
import de.robv.android.xposed.XposedHelpers;

public class NoStoryRead {

    public static void init() {
        try {
            Class<?> StoriesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.StoriesController"), lpparam.classLoader);
            if (StoriesControllerClass != null) {
                XposedHelpers.findAndHookMethod(
                        StoriesControllerClass,
                        AutomationResolver.resolve("StoriesController", "markStoryAsRead", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("1"), new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                param.setResult(false);
                            }
                        }));
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }
}
