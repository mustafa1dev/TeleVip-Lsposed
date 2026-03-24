package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.loadClass;
import com.my.televip.virtuals.messenger.MessagesController;
import com.my.televip.virtuals.messenger.MessagesStorage;
import com.my.televip.virtuals.messenger.UserConfig;
import com.my.televip.virtuals.messenger.Utilities;
import com.my.televip.virtuals.tgnet.ConnectionsManager;
import com.my.televip.virtuals.tgnet.RequestDelegate;
import com.my.televip.virtuals.tgnet.TLRPC;

import de.robv.android.xposed.XposedHelpers;

public class HideSeen {

    public static void sendFakeReadResponse(Object onCompleteOrig) {
        try {
            TLRPC.TL_messages_affectedMessages fakeRes = new TLRPC.TL_messages_affectedMessages();
            fakeRes.setPts(-1);
            fakeRes.setPtsCount(0);
            RequestDelegate onComplete = new RequestDelegate(onCompleteOrig);
            Utilities.getStageQueue().postRunnable(() -> {
                try {
                    if (onComplete.requestDelegate != null) {
                        onComplete.run(fakeRes.getTL_messages_affectedMessages(), null);
                    }
                } catch (Exception e) {
                    Utils.log(e);
                }
            });
        } catch (Exception e){
            Utils.log(e);
        }
    }

    public static boolean isReadMessageRequest(Object object) {
        return object.getClass().getName().contains("TL_messages_readHistory") ||
                object.getClass().getName().contains("TL_messages_readEncryptedHistory") ||
                object.getClass().getName().contains("TL_messages_readDiscussion") ||
                object.getClass().getName().contains("TL_messages_readMessageContents") ||
                object.getClass().getName().contains("TL_channels_readMessageContents") ||
                object.getClass().getName().contains("TL_channels_readHistory");
    }

    public static void handleReadAfterSend(Object object) {
        try {
            if (FeatureManager.getBoolean(FeatureManager.KEY_HIDE_SEEN) && FeatureManager.getBoolean(FeatureManager.KEY_MARK_READ_AFTER_SEND)) {
                TLRPC.InputPeer peer = extractPeerFromSendObject(object);

                if (peer != null && peer.inputPeer != null) {
                    Long dialogId = getDialogId(peer);
                    getMessagesStorage().getStorageQueue().postRunnable(() ->
                            getMessagesStorage().getDialogMaxMessageId(dialogId, MessagesStorage.run((param -> markReadOnServer(param, peer))))
                    );
                }
            }
        } catch (Exception e){
            Utils.log(e);
        }
    }
    public static boolean isReadMessages = false;

    public static void markReadOnServer(int messageId, TLRPC.InputPeer peer) {
        try {
            Object req;
            if (peer.inputPeer.getClass().getName().contains("TL_inputPeerChannel")) {
                TLRPC.TL_channels_readHistory request = new TLRPC.TL_channels_readHistory();
                request.setChannel(MessagesController.getInputChannel(peer));
                request.setMax_id(messageId);
                req = request.getTL_channels_readHistory();
            } else {
                TLRPC.TL_messages_readHistory request = new TLRPC.TL_messages_readHistory();
                request.setPeer(peer);
                request.setMax_id(messageId);
                req = request.getTL_messages_readHistory();
            }

            isReadMessages = true;
            getConnectionsManager().sendRequest(req, RequestDelegate.run((response, error) -> {
                if (error == null) {
                    if (loadClass.getTL_messages_affectedMessagesClass().isInstance(response)) {
                        TLRPC.TL_messages_affectedMessages res = new TLRPC.TL_messages_affectedMessages(response);
                        getMessagesController().processNewDifferenceParams(-1, res.getPts(), -1, res.getPtsCount());
                    }

                }
            }));
        } catch (Exception e){
            Utils.log(e);
        }
    }

    private static TLRPC.InputPeer extractPeerFromSendObject(Object object) {
        if (object.getClass().getName().contains("TL_messages_sendMessage") ||
                object.getClass().getName().contains("TL_messages_sendMedia") ||
                object.getClass().getName().contains("TL_messages_sendMultiMedia")) {
            return new TLRPC.InputPeer(getPeer(object));
        }
        return null;
    }

    private static Object getPeer(Object msg){
        return XposedHelpers.getObjectField(msg, "peer");
    }

    public static Long getDialogId(TLRPC.InputPeer peer) {
        long dialogId;
        if (peer.getChat_id() != 0) {
            dialogId = -peer.getChat_id();
        } else if (peer.getChannel_id() != 0) {
            dialogId = -peer.getChannel_id();
        } else {
            dialogId = peer.getUser_id();
        }

        return dialogId;
    }

    public static MessagesStorage getMessagesStorage() {
        return MessagesStorage.getInstance(UserConfig.getSelectedAccount());
    }

    public static ConnectionsManager getConnectionsManager() {
        return ConnectionsManager.getInstance(UserConfig.getSelectedAccount());
    }

    public static MessagesController getMessagesController() {
        return MessagesController.getInstance(UserConfig.getSelectedAccount());
    }

}
