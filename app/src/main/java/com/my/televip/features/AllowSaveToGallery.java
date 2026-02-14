package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class AllowSaveToGallery {

    public static void init() {
        try {
            Class<?> PeerStoriesView$StoryItemHolderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.PeerStoriesView$StoryItemHolder"), lpparam.classLoader);
            if (PeerStoriesView$StoryItemHolderClass != null) {
                XposedHelpers.findAndHookMethod(PeerStoriesView$StoryItemHolderClass, AutomationResolver.resolve("PeerStoriesView$StoryItemHolder", "allowScreenshots", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        param.setResult(true);
                    }
                });
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }
}
