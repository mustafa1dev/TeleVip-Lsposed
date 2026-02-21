package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.obfuscate.AutomationResolver;
import de.robv.android.xposed.XposedHelpers;

public class HideStoryRead {

    public static void init() {
        try {
            Class<?> StoriesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.StoriesController"), lpparam.classLoader);
            if (StoriesControllerClass != null) {

                Class<?> classStories$StoryItem = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$StoryItem"), MainHook.lpparam.classLoader);
                Class<?> classsStories$PeerStories = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$PeerStories"), MainHook.lpparam.classLoader);

                XposedHelpers.findAndHookMethod(
                        StoriesControllerClass,
                        AutomationResolver.resolve("StoriesController", "markStoryAsRead", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("1", new Class[]{classsStories$PeerStories, classStories$StoryItem, boolean.class}), new AbstractMethodHook() {
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
