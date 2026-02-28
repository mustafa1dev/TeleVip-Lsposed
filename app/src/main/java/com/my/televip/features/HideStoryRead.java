package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class HideStoryRead {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {

            if (loadClass.getStoriesControllerClass() != null) {

                XposedHelpers.findAndHookMethod(
                        loadClass.getStoriesControllerClass(),
                        AutomationResolver.resolve("StoriesController", "markStoryAsRead", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("markStoryAsRead", new Class[]{loadClass.getTL_stories$PeerStoriesClass(), loadClass.getTL_stories$StoryItemClass(), boolean.class}), new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                if (FeatureManager.getBoolean(FeatureManager.KEY_HIDE_STORY_READ)) {
                                    param.setResult(false);
                                }
                            }
                        }));
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }
}
