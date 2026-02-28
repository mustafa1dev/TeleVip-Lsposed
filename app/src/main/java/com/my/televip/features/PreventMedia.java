package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Field;

import de.robv.android.xposed.XposedHelpers;

public class PreventMedia {
private static Field messageOwnerField;
    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            if (loadClass.getChatActivityClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "sendSecretMessageRead", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("sendSecretMessageRead", new Class[]{com.my.televip.loadClass.getMessageObjectClass(), boolean.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.getBoolean(FeatureManager.KEY_PREVENT_MEDIA)) {
                            param.setResult(null);
                        }
                    }
                }));
                XposedHelpers.findAndHookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "sendSecretMediaDelete", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("sendSecretMediaDelete", new Class[]{com.my.televip.loadClass.getMessageObjectClass()}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.getBoolean(FeatureManager.KEY_PREVENT_MEDIA)) {
                            param.setResult(null);
                        }
                    }
                }));
            }

            if (loadClass.getSecretMediaViewerClass() != null) {

                XposedHelpers.findAndHookMethod(loadClass.getSecretMediaViewerClass(), AutomationResolver.resolve("SecretMediaViewer", "openMedia", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("PhotoViewer$PhotoViewerProvider", new Class[]{com.my.televip.loadClass.getMessageObjectClass(), loadClass.getPhotoViewer$PhotoViewerProviderClass(), java.lang.Runnable.class, java.lang.Runnable.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) throws Throwable {
                        if (FeatureManager.getBoolean(FeatureManager.KEY_PREVENT_MEDIA)) {
                            param.args[2] = null;
                            param.args[3] = null;

                            Object forwardingMessage = param.args[0];

                            if (forwardingMessage != null) {
                                Class<?> forwardingMessageClass = forwardingMessage.getClass();
                                if (messageOwnerField == null) {
                                    messageOwnerField = forwardingMessageClass.getDeclaredField(AutomationResolver.resolve("MessageObject", "messageOwner", AutomationResolver.ResolverType.Field));
                                    messageOwnerField.setAccessible(true);
                                }
                                Object messageOwner = messageOwnerField.get(forwardingMessage);

                                if (messageOwner != null) {
                                    XposedHelpers.setObjectField(messageOwner, AutomationResolver.resolve("TLRPC$Message", "ttl", AutomationResolver.ResolverType.Field), 0x7FFFFFFF);
                                }
                            }
                        }
                    }
                }));

                XposedHelpers.findAndHookMethod(loadClass.getSecretMediaViewerClass(), AutomationResolver.resolve("SecretMediaViewer", "closePhoto", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("closePhoto", new Class[]{boolean.class, boolean.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.getBoolean(FeatureManager.KEY_PREVENT_MEDIA)) {
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
