package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import java.lang.reflect.Field;
import de.robv.android.xposed.XposedHelpers;

public class PreventMedia {
private static Field messageOwnerField;
    public static void init() {
        try {
            if (loadClass.getChatActivityClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "sendSecretMessageRead", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("5", new Class[]{com.my.televip.loadClass.getMessageObjectClass(), boolean.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        param.setResult(null);
                    }
                }));
                XposedHelpers.findAndHookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "sendSecretMediaDelete", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("6", new Class[]{com.my.televip.loadClass.getMessageObjectClass()}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        param.setResult(null);
                    }
                }));
            }

            Class<?> SecretMediaViewerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.SecretMediaViewer"), lpparam.classLoader);

            if (SecretMediaViewerClass != null) {

                Class<?> photoViewerproviderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.PhotoViewer$PhotoViewerProvider"), lpparam.classLoader);

                XposedHelpers.findAndHookMethod(SecretMediaViewerClass, AutomationResolver.resolve("SecretMediaViewer", "openMedia", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("7", new Class[]{com.my.televip.loadClass.getMessageObjectClass(), photoViewerproviderClass, java.lang.Runnable.class, java.lang.Runnable.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) throws Throwable {
                        param.args[2] = null;
                        param.args[3] = null;
                        // الحصول على كائن ChatActivity
                        Object forwardingMessage = param.args[0];

                        if (forwardingMessage != null) {
                            // الوصول إلى الحقل messageOwner داخل forwardingMessage
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
                }));
                XposedHelpers.findAndHookMethod(SecretMediaViewerClass, AutomationResolver.resolve("SecretMediaViewer", "closePhoto", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("para6", new Class[]{boolean.class, boolean.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        Object thisObject = param.thisObject;
                        XposedHelpers.setObjectField(thisObject, AutomationResolver.resolve("SecretMediaViewer", "onClose", AutomationResolver.ResolverType.Field), null);
                    }
                }));
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
