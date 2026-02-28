package com.my.televip.features;

import com.my.televip.ClientChecker;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class DisableStories {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            if (loadClass.getMessagesControllerClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "storiesEnabled", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.getBoolean(FeatureManager.KEY_DISABLE_STORIES)) {
                            param.setResult(false);
                        }
                    }
                });
                XposedHelpers.findAndHookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "storyEntitiesAllowed", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.getBoolean(FeatureManager.KEY_DISABLE_STORIES)) {
                            param.setResult(false);
                        }

                    }
                });

                XposedHelpers.findAndHookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "storyEntitiesAllowed2", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("storyEntitiesAllowed", new Class[]{loadClass.getTLRPC$UserClass()}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.getBoolean(FeatureManager.KEY_DISABLE_STORIES)) {
                            param.setResult(false);
                        }
                    }
                }));
            }

            if (loadClass.getStoriesControllerClass() != null) {
                if (ClientChecker.check(ClientChecker.ClientType.NagramX)) {
                    XposedHelpers.findAndHookMethod(loadClass.getStoriesControllerClass(), AutomationResolver.resolve("StoriesController", "hasStories2", AutomationResolver.ResolverType.Method), long.class, new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (FeatureManager.getBoolean(FeatureManager.KEY_DISABLE_STORIES)) {
                                param.setResult(false);
                            }
                        }
                    });
                } else {
                    XposedHelpers.findAndHookMethod(loadClass.getStoriesControllerClass(), AutomationResolver.resolve("StoriesController", "hasStories", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (FeatureManager.getBoolean(FeatureManager.KEY_DISABLE_STORIES)) {
                                param.setResult(false);
                            }

                        }
                    });
                }
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
