package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

public class AllowSaveToGallery {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {

            if (loadClass.getPeerStoriesView$StoryItemHoldeClass() != null) {
                HMethod.hookMethod(loadClass.getPeerStoriesView$StoryItemHoldeClass(), AutomationResolver.resolve("PeerStoriesView$StoryItemHolder", "allowScreenshots", AutomationResolver.ResolverType.Method),
                        new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                if (FeatureManager.isAllowSaveGallery()) param.setResult(true);
                            }
                        });
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }
}
