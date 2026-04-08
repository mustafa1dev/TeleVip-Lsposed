package com.my.televip.virtuals.ui;

import com.my.televip.Class.ClassNames;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.SecretMediaSave;
import com.my.televip.hooks.HMethod;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.logging.Logger;
import com.my.televip.virtuals.messenger.FileLoader;
import com.my.televip.virtuals.messenger.MessageObject;
import com.my.televip.virtuals.messenger.UserConfig;
import com.my.televip.virtuals.tgnet.TLRPC;

import java.io.File;

public class SecretMediaViewer {

    private static boolean isEnable = false;

    public static void openMedia() {
        try {
            if (!isEnable) {
                isEnable = true;

                if (ClassLoad.getClass(ClassNames.SECRET_MEDIA_VIEWER) == null) return;

                HMethod.hookMethod(ClassLoad.getClass(ClassNames.SECRET_MEDIA_VIEWER), AutomationResolver.resolve("SecretMediaViewer", "openMedia", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("PhotoViewer$PhotoViewerProvider", new Class[]{ClassLoad.getClass(ClassNames.MESSAGE_OBJECT), ClassLoad.getClass(ClassNames.PHOTO_VIEWER_PROVIDER), java.lang.Runnable.class, java.lang.Runnable.class}), new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (ConfigManager.preventMedia.isEnable() && !ConfigManager.secretMediaSave.isEnable()) {
                            param.args[2] = null;
                            param.args[3] = null;

                            MessageObject messageObject = new MessageObject(param.args[0]);
                            if (messageObject.getMessageObject() != null) {
                                TLRPC.Message messageOwner = messageObject.getMessageOwner();
                                if (messageOwner.message != null) {
                                    messageOwner.setTtl(0);
                                }
                            }
                        }

                        if (ConfigManager.secretMediaSave.isEnable() && (param.args.length >= 2 || param.args[0] != null)) {
                            MessageObject messageObject = new MessageObject(param.args[0]);
                            PhotoViewer.PhotoViewerProvider provider = new PhotoViewer.PhotoViewerProvider(param.args[1]);
                            final PhotoViewer.PlaceProviderObject object = provider.getPlaceForPhoto(messageObject, null, 0, true, false);
                            if (object.getImageReceiver().imageReceiver != null) {
                                FileLoader fileLoader = FileLoader.getInstance(UserConfig.getSelectedAccount());
                                File image = fileLoader.getLocalFile(object.getImageReceiver().getImageLocation());
                                if (image != null) {
                                    SecretMediaSave.pathImage = image;
                                    SecretMediaSave.id = messageObject.getMessageOwner().getID();
                                }
                                PhotoViewer.getInstance().openPhoto(messageObject, messageObject.getDialogId(), 0L, 0L, provider, false);
                                param.setResult(null);
                            }
                        }
                    }
                }));
            }
        } catch (Throwable e) {
            Logger.e(e);
        }
    }

}