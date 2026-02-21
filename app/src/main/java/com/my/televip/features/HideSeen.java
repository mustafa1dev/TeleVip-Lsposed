package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import java.lang.reflect.Method;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class HideSeen {
private static Method getUserMethod;
    public static void init() {
        try {
            if (loadClass.getMessagesControllerClass() != null) {
                Class<?> readTaskClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController$ReadTask"), lpparam.classLoader);
                XposedHelpers.findAndHookMethod(
                        loadClass.getMessagesControllerClass(),
                        AutomationResolver.resolve("MessagesController", "completeReadTask", AutomationResolver.ResolverType.Method), // اسم الدالة
                        AutomationResolver.merge(AutomationResolver.resolveObject("2", new Class[]{readTaskClass}), new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                // التحقق من الإعدادات
                                if (FeatureManager.getBoolean(FeatureManager.KEY_HIDE_SEEN_PRIVATE) && FeatureManager.getBoolean(FeatureManager.KEY_HIDE_SEEN_GROUP)) {
                                    //XposedBridge.log("completeReadTask method is blocked.");
                                    param.setResult(null); // إيقاف تنفيذ الدالة الأصلية
                                    return;
                                }

                                // الحصول على الكائن task
                                Object task = param.args[0];
                                if (task != null) {
                                    // استخراج dialogId من الكائن task
                                    long dialogId = (long) XposedHelpers.getObjectField(task, AutomationResolver.resolve("MessagesController$ReadTask", "dialogId", AutomationResolver.ResolverType.Field));
                                    if (dialogId != 0) {
                                        // الحصول على الكائن الحالي لـ MessagesController
                                        Object messagesControllerInstance = param.thisObject;

                                        try {
                                            if (getUserMethod == null) {
                                                getUserMethod = loadClass.getMessagesControllerClass().getDeclaredMethod(AutomationResolver.resolve("MessagesController", "getUser", AutomationResolver.ResolverType.Method), AutomationResolver.resolveObject("para1", new Class[]{Long.class}));
                                                getUserMethod.setAccessible(true);
                                            }
                                            // تحويل dialogId إلى Long
                                            Long useridObject = dialogId;

                                            // استدعاء getChat باستخدام الكائن messagesControllerInstance
                                            Object user = getUserMethod.invoke(messagesControllerInstance, useridObject);

                                            if (user != null) {
                                                if (FeatureManager.getBoolean(FeatureManager.KEY_HIDE_SEEN_PRIVATE)) {
                                                    //     XposedBridge.log("completeReadTask method is blocked.");
                                                    param.setResult(null); // إيقاف تنفيذ الدالة الأصلية
                                                }
                                            } else {
                                                if (FeatureManager.getBoolean(FeatureManager.KEY_HIDE_SEEN_GROUP)) {
                                                    // XposedBridge.log("completeReadTask method is blocked.");
                                                    param.setResult(null); // إيقاف تنفيذ الدالة الأصلية
                                                }
                                            }
                                        } catch (Exception e) {
                                            XposedBridge.log("Error invoking getUser: " + e.getMessage());
                                        }
                                    } else {
                                        XposedBridge.log("dialogId is 0.");
                                    }
                                } else {
                                    XposedBridge.log("Task is null.");
                                }
                            }

                        }));
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }
    }
