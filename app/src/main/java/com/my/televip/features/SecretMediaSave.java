package com.my.televip.features;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;

import com.my.televip.Configs.ConfigsManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;
import com.my.televip.virtuals.messenger.MessageObject;
import com.my.televip.virtuals.tgnet.TLRPC;
import com.my.televip.virtuals.ui.Cells.ChatMessageCell;
import com.my.televip.virtuals.ui.PhotoViewer;
import com.my.televip.virtuals.ui.SecretMediaViewer;

import java.io.File;

public class SecretMediaSave {

    public static boolean isEnable = false;

    public static long id;
    public static File pathImage;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;
                HMethod.hookMethod(loadClass.getMessageObjectClass(), AutomationResolver.resolve("MessageObject", "isSecret", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (ConfigsManager.secretMediaSave.isEnable()) param.setResult(false);
                    }
                });

                HMethod.hookMethod(loadClass.getChatActivity$ChatMessageCellDelegateClass(), AutomationResolver.resolve("ChatActivity$ChatMessageCellDelegate", "didPressImage", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("didPressImage", new Class[]{loadClass.getChatMessageCellClass(), float.class, float.class, boolean.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        try {
                            if (ConfigsManager.secretMediaSave.isEnable() && param.args[0] != null) {
                                ChatMessageCell messageCell = new ChatMessageCell(param.args[0]);
                                if (messageCell.getChatMessageCell() != null) {
                                    MessageObject messageObject = messageCell.getMessageObject();
                                    if (messageObject.getMessageObject() != null) {
                                        TLRPC.Message message = messageObject.getMessageOwner();
                                        if (message.getTtl() > 0) message.setTtl(0);
                                    }
                                    bindPhotoViewerToActivity(messageCell);
                                }
                            }
                        } catch (Exception e) {
                            Logger.e(e);
                        }
                    }
                }));

                HMethod.hookMethod(loadClass.getFileLoaderClass(), AutomationResolver.resolve("FileLoader", "getPathToMessage", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("getPathToMessage", new Class[]{loadClass.getTLRPC$MessageClass()}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        try {
                            if (ConfigsManager.secretMediaSave.isEnable() && param.args[0] != null && pathImage != null) {
                                TLRPC.Message message = new TLRPC.Message(param.args[0]);
                                if (message.getID() == id) {
                                    param.setResult(pathImage);
                                }
                            }
                        } catch (Exception e) {
                            Logger.e(e);
                        }
                    }
                }));


                if (ConfigsManager.secretMediaSave.isEnable()) SecretMediaViewer.openMedia();
            }
        } catch (Exception e){
            Logger.e(e);
        }
    }

    private static void bindPhotoViewerToActivity(ChatMessageCell cell) {
        try {
            if (!(cell.getChatMessageCell() instanceof View)) return;
            View view = (View) cell.getChatMessageCell();
            Context context = view.getContext();
            Activity activity = extractActivityFromContext(context);
            if (activity == null) return;

            PhotoViewer.getInstance().setParentActivity(activity);
        } catch (Throwable ignored) {}
    }

    private static Activity extractActivityFromContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) return (Activity) context;
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}
