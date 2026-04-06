package com.my.televip.features;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.ClientChecker;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.logging.Logger;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class GhostMode {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;
                if (ClassLoad.getClass(ClassNames.CONNECTIONS_MANAGER) != null && ConfigManager.isGhostMode()) {
                    HMethod.hookMethod(ClassLoad.getClass(ClassNames.CONNECTIONS_MANAGER), AutomationResolver.resolve("ConnectionsManager", "sendRequestInternal", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("sendRequestInternal", new Class[]{ClassLoad.getClass(ClassNames.TL_OBJECT), ClassLoad.getClass(ClassNames.REQUEST_DELEGATE), ClassLoad.getClass(ClassNames.REQUEST_DELEGATE_TIMESTAMP), ClassLoad.getClass(ClassNames.QUICK_ACK_DELEGATE), ClassLoad.getClass(ClassNames.WRITE_TO_SOCKET_DELEGATE), int.class, int.class, int.class, boolean.class, int.class}), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            try {
                                if (HideSeen.isReadMessages) {
                                    HideSeen.isReadMessages = false;
                                } else if (ConfigManager.isGhostMode()) {
                                    Object object = param.args[0];
                                    if (ClientChecker.check(ClientChecker.ClientType.Nagram)) {
                                        HideSeen.saveReadHistory(object);
                                    }
                                    if (ConfigManager.hideOnline.isEnable()) {
                                        if (ClassLoad.getClass(ClassNames.TL_ACCOUNT_UPDATE_STATUS) != null) {

                                            if (ClassLoad.getClass(ClassNames.TL_ACCOUNT_UPDATE_STATUS).isInstance(object)) {
                                                XposedHelpers.setBooleanField(object, AutomationResolver.resolve("TL_account$updateStatus", "offline", AutomationResolver.ResolverType.Field), true);
                                            }
                                        } else {
                                            Logger.w("Not found TL_account_updateStatus, " + Utils.issue);
                                        }
                                    }

                                    if (ConfigManager.hideSeen.isEnable() && HideSeen.isReadMessageRequest(object)) {
                                        HideSeen.sendFakeReadResponse(param.args[1]);
                                        param.setResult(null);
                                        return;
                                    }

                                    if (ConfigManager.hideTyping.isEnable() && HideTyping.isTypingRequest(object)) {
                                        param.setResult(null);
                                        return;
                                    }
                                    if (ConfigManager.hideStoryView.isEnable() && HideStoryRead.isReadStoriesRequest(object)) {
                                        param.setResult(null);
                                        return;
                                    }
                                    HideSeen.handleReadAfterSend(object);
                                }
                            } catch (Throwable e) {
                                XposedBridge.log(e);
                            }
                        }
                    }));
                }
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }

}
