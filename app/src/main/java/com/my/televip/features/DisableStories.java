package com.my.televip.features;

import com.my.televip.ClientChecker;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

public class DisableStories {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            if (loadClass.getMessagesControllerClass() != null) {
                HMethod.hookMethod(loadClass.getMessagesControllerClass(), new String[]{AutomationResolver.resolve("MessagesController", "storiesEnabled", AutomationResolver.ResolverType.Method), AutomationResolver.resolve("MessagesController", "storyEntitiesAllowed", AutomationResolver.ResolverType.Method)}, new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.isDisableStories()) param.setResult(false);
                    }
                });

                HMethod.hookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "storyEntitiesAllowed2", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("storyEntitiesAllowed", new Class[]{loadClass.getTLRPC$UserClass()}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.isDisableStories()) param.setResult(false);
                    }
                }));
            }

            if (loadClass.getStoriesControllerClass() != null) {
                if (ClientChecker.check(ClientChecker.ClientType.NagramX)) {
                    HMethod.hookMethod(loadClass.getStoriesControllerClass(), AutomationResolver.resolve("StoriesController", "hasStories2", AutomationResolver.ResolverType.Method), long.class, new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (FeatureManager.isDisableStories()) param.setResult(false);
                        }
                    });
                } else {
                    HMethod.hookMethod(loadClass.getStoriesControllerClass(), AutomationResolver.resolve("StoriesController", "hasStories", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (FeatureManager.isDisableStories()) param.setResult(false);
                        }
                    });
                }
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
