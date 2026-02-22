package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;
import static com.my.televip.language.Language.UserOffline;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class HideOnline {
    private static Method getUserConfigMethod;
    private static Method getClientUserIdMethod;
    private static  Field userIdField;
    public static void init() {
        try {
            // العثور على الكلاسات المطلوبة للمعاملات
            Class<?> connectionsManagerClass = XposedHelpers.findClassIfExists(
                    AutomationResolver.resolve("org.telegram.tgnet.ConnectionsManager"),
                    lpparam.classLoader
            );
            if (connectionsManagerClass != null) {

                Class<?> tlObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLObject"), lpparam.classLoader);
                Class<?> requestDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegate"), lpparam.classLoader);
                Class<?> requestDelegateTimestampClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegateTimestamp"), lpparam.classLoader);
                Class<?> quickAckDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.QuickAckDelegate"), lpparam.classLoader);
                Class<?> writeToSocketDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.WriteToSocketDelegate"), lpparam.classLoader);


                XposedHelpers.findMethodExact(connectionsManagerClass, AutomationResolver.resolve("ConnectionsManager", "sendRequestInternal", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("sendRequestInternal", new Class[]{tlObjectClass, requestDelegateClass, requestDelegateTimestampClass, quickAckDelegateClass, writeToSocketDelegateClass, int.class, int.class, int.class, boolean.class, int.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        try {
                            Class<?> tlAccountUpdateStatusClass = XposedHelpers.findClassIfExists(
                                    AutomationResolver.resolve("org.telegram.tgnet.tl.TL_account$updateStatus"), lpparam.classLoader
                            );
                            if (tlAccountUpdateStatusClass != null) {
                                // التحقق من النوع وتعديله
                                Object object = param.args[0]; // أول معامل في الطريقة
                                if (tlAccountUpdateStatusClass.isInstance(object)) {
                                    // تعديل الخاصية offline إلى true
                                    XposedHelpers.setBooleanField(object, AutomationResolver.resolve("TL_account$updateStatus", "offline", AutomationResolver.ResolverType.Field), true);
                                    // XposedBridge.log("Modified TL_account_updateStatus: offline set to true.");
                                }
                            } else {
                                Utils.log("Not found TL_account_updateStatus, " + Utils.issue);
                            }
                        } catch (Exception e) {
                            XposedBridge.log("Error while handling TL_account_updateStatus: " + e.getMessage());
                        }

                    }
                }));
                if (loadClass.getProfileActivityClass() != null && loadClass.getBaseFragmentClass() != null) {

                    XposedHelpers.findAndHookMethod(loadClass.getProfileActivityClass(),
                            AutomationResolver.resolve("ProfileActivity", "updateProfileData", AutomationResolver.ResolverType.Method),
                            AutomationResolver.merge(AutomationResolver.resolveObject("updateProfileData", new Class[]{boolean.class}),
                                    new AbstractMethodHook() {
                                        @Override
                                        protected void afterMethod(MethodHookParam param) throws Throwable {
                                            final Object profileActivityInstance = param.thisObject;
                                            if (getUserConfigMethod == null) {
                                                getUserConfigMethod = loadClass.getBaseFragmentClass().getDeclaredMethod(AutomationResolver.resolve("BaseFragment", "getUserConfig", AutomationResolver.ResolverType.Method));
                                                getUserConfigMethod.setAccessible(true);
                                            }
                                            Object userConfig = getUserConfigMethod.invoke(profileActivityInstance);

                                            if (userConfig != null) {
                                                if (getClientUserIdMethod == null) {
                                                    getClientUserIdMethod = userConfig.getClass().getDeclaredMethod(AutomationResolver.resolve("UserConfig", "getClientUserId", AutomationResolver.ResolverType.Method));
                                                    getClientUserIdMethod.setAccessible(true);
                                                }
                                                //noinspection DataFlowIssue
                                                long clientUserId = (long) getClientUserIdMethod.invoke(userConfig);
                                                if (userIdField == null) {
                                                    userIdField = loadClass.getProfileActivityClass().getDeclaredField(AutomationResolver.resolve("ProfileActivity", "userId", AutomationResolver.ResolverType.Field));
                                                    userIdField.setAccessible(true);
                                                }
                                                final long userId = userIdField.getLong(profileActivityInstance);
                                                if (userId != 0 && userId == clientUserId) {
                                                    Object[] onlineTextViewArray = (Object[]) XposedHelpers.getObjectField(profileActivityInstance, AutomationResolver.resolve("ProfileActivity", "onlineTextView", AutomationResolver.ResolverType.Field));

                                                    if (onlineTextViewArray != null && onlineTextViewArray.length > 1) {
                                                        // الحصول على SimpleTextView[1]
                                                        Object simpleTextView1 = onlineTextViewArray[1];

                                                        if (simpleTextView1 != null) {
                                                            // استدعاء setText باستخدام LSPosed
                                                            XposedHelpers.callMethod(simpleTextView1, AutomationResolver.resolve("SimpleTextView", "setText", AutomationResolver.ResolverType.Method), UserOffline);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    })
                    );
                }
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
