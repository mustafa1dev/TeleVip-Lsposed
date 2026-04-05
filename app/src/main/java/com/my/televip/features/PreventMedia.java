package com.my.televip.features;

import com.my.televip.Configs.ConfigsManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;
import com.my.televip.virtuals.ui.SecretMediaViewer;

import de.robv.android.xposed.XposedHelpers;

public class PreventMedia {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;

                if (loadClass.getChatActivityClass() != null) {
                    HMethod.hookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "sendSecretMessageRead", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("sendSecretMessageRead", new Class[]{com.my.televip.loadClass.getMessageObjectClass(), boolean.class}), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (ConfigsManager.preventMedia.isEnable()) param.setResult(null);
                        }
                    }));
                    HMethod.hookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "sendSecretMediaDelete", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("sendSecretMediaDelete", new Class[]{com.my.televip.loadClass.getMessageObjectClass()}), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (ConfigsManager.preventMedia.isEnable()) param.setResult(null);
                        }
                    }));
                }

                if (loadClass.getSecretMediaViewerClass() != null) {
                    SecretMediaViewer.openMedia();
                    HMethod.hookMethod(loadClass.getSecretMediaViewerClass(), AutomationResolver.resolve("SecretMediaViewer", "closePhoto", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("closePhoto", new Class[]{boolean.class, boolean.class}), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (ConfigsManager.preventMedia.isEnable()) {
                                Object thisObject = param.thisObject;
                                XposedHelpers.setObjectField(thisObject, AutomationResolver.resolve("SecretMediaViewer", "onClose", AutomationResolver.ResolverType.Field), null);
                            }
                        }
                    }));
                }
            }
        } catch (Throwable t){
            Logger.e(t);
        }
    }

}
