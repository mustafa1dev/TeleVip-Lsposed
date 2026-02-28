package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class AllowSaveToGallery {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {

            if (loadClass.getPeerStoriesView$StoryItemHoldeClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getPeerStoriesView$StoryItemHoldeClass(), AutomationResolver.resolve("PeerStoriesView$StoryItemHolder", "allowScreenshots", AutomationResolver.ResolverType.Method),
                        new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                if (FeatureManager.getBoolean(FeatureManager.KEY_ALLOW_SAVE_GALLERY)) {
                                    param.setResult(true);
                                }
                            }
                        });
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }
}
