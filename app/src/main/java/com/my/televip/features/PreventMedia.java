package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.MessageObject;
import com.my.televip.virtuals.tgnet.TLRPC;

import de.robv.android.xposed.XposedHelpers;

public class PreventMedia {

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            if (loadClass.getChatActivityClass() != null) {
                HMethod.hookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "sendSecretMessageRead", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("sendSecretMessageRead", new Class[]{com.my.televip.loadClass.getMessageObjectClass(), boolean.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.isPreventMedia()) param.setResult(null);
                    }
                }));
                HMethod.hookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "sendSecretMediaDelete", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("sendSecretMediaDelete", new Class[]{com.my.televip.loadClass.getMessageObjectClass()}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.isPreventMedia()) param.setResult(null);
                    }
                }));
            }

            if (loadClass.getSecretMediaViewerClass() != null) {

                HMethod.hookMethod(loadClass.getSecretMediaViewerClass(), AutomationResolver.resolve("SecretMediaViewer", "openMedia", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("PhotoViewer$PhotoViewerProvider", new Class[]{com.my.televip.loadClass.getMessageObjectClass(), loadClass.getPhotoViewer$PhotoViewerProviderClass(), java.lang.Runnable.class, java.lang.Runnable.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) throws Throwable {
                        if (FeatureManager.isPreventMedia()) {
                            param.args[2] = null;
                            param.args[3] = null;

                            MessageObject messageObject = new MessageObject(param.args[0]);
                            if (messageObject.getMessageObject() != null) {
                                TLRPC.Message messageOwner = messageObject.getMessageOwner();
                                if (messageOwner.message != null) {
                                    messageOwner.setTtl(0x7FFFFFFF);
                                }
                            }
                        }
                    }
                }));

                HMethod.hookMethod(loadClass.getSecretMediaViewerClass(), AutomationResolver.resolve("SecretMediaViewer", "closePhoto", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("closePhoto", new Class[]{boolean.class, boolean.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.isPreventMedia()) {
                            Object thisObject = param.thisObject;
                            XposedHelpers.setObjectField(thisObject, AutomationResolver.resolve("SecretMediaViewer", "onClose", AutomationResolver.ResolverType.Field), null);
                        }
                    }
                }));
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
