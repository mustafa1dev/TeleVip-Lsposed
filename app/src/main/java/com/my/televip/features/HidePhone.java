package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class HidePhone {
private static Method getUserConfigMethod;
private static Method getClientUserIdMethod;

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            if (loadClass.getMessagesControllerClass() != null && loadClass.getBaseControllerClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "getUser", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("getUser", new Class[]{Long.class}), new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                        if (FeatureManager.getBoolean(FeatureManager.KEY_HIDE_PHONE)) {
                            Object userObject = param.getResult();
                            Object MessagesControllerInstance = param.thisObject;
                            if (userObject != null) {
                                if (getUserConfigMethod == null) {
                                    getUserConfigMethod = loadClass.getBaseControllerClass().getDeclaredMethod(AutomationResolver.resolve("BaseController", "getUserConfig", AutomationResolver.ResolverType.Method));
                                    getUserConfigMethod.setAccessible(true);
                                }
                                Object userConfig = getUserConfigMethod.invoke(MessagesControllerInstance);
                                if (getClientUserIdMethod == null) {
                                    getClientUserIdMethod = userConfig.getClass().getDeclaredMethod(AutomationResolver.resolve("UserConfig", "getClientUserId", AutomationResolver.ResolverType.Method));
                                    getClientUserIdMethod.setAccessible(true);
                                }
                                long clientUserId = (long) getClientUserIdMethod.invoke(userConfig);
                                long userid = (long) param.args[0];
                                if (clientUserId == userid) {
                                    XposedHelpers.setObjectField(userObject, AutomationResolver.resolve("UserConfig", "phone", AutomationResolver.ResolverType.Field), null);
                                }
                            }
                        }
                    }
                }));
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
