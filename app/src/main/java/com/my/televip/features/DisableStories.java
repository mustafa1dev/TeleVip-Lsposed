package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.ClientChecker;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import de.robv.android.xposed.XposedHelpers;

public class DisableStories {

    public static void init() {
        try {
            if (loadClass.getMessagesControllerClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "storiesEnabled", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        param.setResult(false);
                    }
                });
                XposedHelpers.findAndHookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "storyEntitiesAllowed", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        param.setResult(false);

                    }
                });

                Class<?> Userlass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$User"), lpparam.classLoader);

                XposedHelpers.findAndHookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "storyEntitiesAllowed2", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("storyEntitiesAllowed", new Class[]{Userlass}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        param.setResult(false);
                    }
                }));
            }
            Class<?> StoriesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.StoriesController"), lpparam.classLoader);
            if (StoriesControllerClass != null) {
                if (ClientChecker.check(ClientChecker.ClientType.NagramX)) {
                    XposedHelpers.findAndHookMethod(StoriesControllerClass, AutomationResolver.resolve("StoriesController", "hasStories2", AutomationResolver.ResolverType.Method), long.class, new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            param.setResult(false);

                        }
                    });
                } else {
                    XposedHelpers.findAndHookMethod(StoriesControllerClass, AutomationResolver.resolve("StoriesController", "hasStories", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            param.setResult(false);

                        }
                    });
                }
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
