package com.my.televip.features;

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

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {

            if (loadClass.getConnectionsManagerClass() != null) {


                XposedHelpers.findMethodExact(loadClass.getConnectionsManagerClass(), AutomationResolver.resolve("ConnectionsManager", "sendRequestInternal", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("sendRequestInternal", new Class[]{loadClass.getTLObjectClass(), loadClass.getRequestDelegateClass(), loadClass.getRequestDelegateTimestampClass(), loadClass.getQuickAckDelegateClass(), loadClass.getWriteToSocketDelegateClass(), int.class, int.class, int.class, boolean.class, int.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (FeatureManager.getBoolean(FeatureManager.KEY_HIDE_ONLINE)) {
                            try {
                                if (loadClass.getTL_account$updateStatusClass() != null) {
                                    Object object = param.args[0];
                                    if (loadClass.getTL_account$updateStatusClass().isInstance(object)) {

                                        XposedHelpers.setBooleanField(object, AutomationResolver.resolve("TL_account$updateStatus", "offline", AutomationResolver.ResolverType.Field), true);
                                    }
                                } else {
                                    Utils.log("Not found TL_account_updateStatus, " + Utils.issue);
                                }
                            } catch (Exception e) {
                                XposedBridge.log("Error while handling TL_account_updateStatus: " + e.getMessage());
                            }
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
                                            if (FeatureManager.getBoolean(FeatureManager.KEY_HIDE_ONLINE)) {
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

                                                            Object simpleTextView1 = onlineTextViewArray[1];

                                                            if (simpleTextView1 != null) {

                                                                XposedHelpers.callMethod(simpleTextView1, AutomationResolver.resolve("SimpleTextView", "setText", AutomationResolver.ResolverType.Method), UserOffline);
                                                            }
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
