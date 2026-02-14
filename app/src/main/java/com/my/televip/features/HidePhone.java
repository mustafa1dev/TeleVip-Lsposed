package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

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
    public static void init() {
        try {
            if (loadClass.getMessagesControllerClass() != null) {
                Class<?> baseControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.BaseController"), lpparam.classLoader);
                if (baseControllerClass != null) {

                    XposedHelpers.findAndHookMethod(loadClass.getMessagesControllerClass(), AutomationResolver.resolve("MessagesController", "getUser", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("para1"), new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                            Object userObject = param.getResult();
                            Object MessagesControllerInstance = param.thisObject;
                            if (userObject != null) {
                                if (getUserConfigMethod == null) {
                                    getUserConfigMethod = baseControllerClass.getDeclaredMethod(AutomationResolver.resolve("BaseController", "getUserConfig", AutomationResolver.ResolverType.Method));
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
                    }));
                }
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
