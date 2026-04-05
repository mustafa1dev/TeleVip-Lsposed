package com.my.televip.features;

import com.my.televip.Configs.ConfigsManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;

public class EnableSavingStories {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;

                if (loadClass.getPeerStoriesView$StoryItemHoldeClass() != null) {
                    HMethod.hookMethod(loadClass.getPeerStoriesView$StoryItemHoldeClass(), AutomationResolver.resolve("PeerStoriesView$StoryItemHolder", "allowScreenshots", AutomationResolver.ResolverType.Method),
                            new AbstractMethodHook() {
                                @Override
                                protected void beforeMethod(MethodHookParam param) {
                                    if (ConfigsManager.enableSavingStories.isEnable())
                                        param.setResult(true);
                                }
                            });
                }
            }
        } catch (Throwable t){
            Logger.e(t);
        }
    }
}
