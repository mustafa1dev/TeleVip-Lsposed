package com.my.televip.features;

import com.my.televip.ClientChecker;
import com.my.televip.Configs.ConfigsManager;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class GhostMode {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;
                if (loadClass.getConnectionsManagerClass() != null && ConfigsManager.isGhostMode()) {
                    HMethod.hookMethod(loadClass.getConnectionsManagerClass(), AutomationResolver.resolve("ConnectionsManager", "sendRequestInternal", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("sendRequestInternal", new Class[]{loadClass.getTLObjectClass(), loadClass.getRequestDelegateClass(), loadClass.getRequestDelegateTimestampClass(), loadClass.getQuickAckDelegateClass(), loadClass.getWriteToSocketDelegateClass(), int.class, int.class, int.class, boolean.class, int.class}), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            try {
                                if (HideSeen.isReadMessages) {
                                    HideSeen.isReadMessages = false;
                                } else if (ConfigsManager.isGhostMode()) {
                                    Object object = param.args[0];
                                    if (ClientChecker.check(ClientChecker.ClientType.Nagram)) {
                                        HideSeen.saveReadHistory(object);
                                    }
                                    if (ConfigsManager.hideOnline.isEnable()) {
                                        if (loadClass.getTL_account$updateStatusClass() != null) {

                                            if (loadClass.getTL_account$updateStatusClass().isInstance(object)) {
                                                XposedHelpers.setBooleanField(object, AutomationResolver.resolve("TL_account$updateStatus", "offline", AutomationResolver.ResolverType.Field), true);
                                            }
                                        } else {
                                            Logger.w("Not found TL_account_updateStatus, " + Utils.issue);
                                        }
                                    }

                                    if (ConfigsManager.hideSeen.isEnable() && HideSeen.isReadMessageRequest(object)) {
                                        HideSeen.sendFakeReadResponse(param.args[1]);
                                        param.setResult(null);
                                        return;
                                    }

                                    if (ConfigsManager.hideTyping.isEnable() && HideTyping.isTypingRequest(object)) {
                                        param.setResult(null);
                                        return;
                                    }
                                    if (ConfigsManager.hideStoryView.isEnable() && HideStoryRead.isReadStoriesRequest(object)) {
                                        param.setResult(null);
                                        return;
                                    }
                                    HideSeen.handleReadAfterSend(object);
                                }
                            } catch (Exception e) {
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
