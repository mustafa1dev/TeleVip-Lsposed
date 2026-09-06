package com.my.televip.virtuals.messenger;

import android.content.SharedPreferences;
import android.util.SparseArray;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.androidx.LongSparseArray;
import com.my.televip.virtuals.tgnet.TLRPC;

import com.my.televip.reflect.XReflect;

public class MessagesController {
    final Object messagesController;

    public MessagesController(Object instance)
    {
        this.messagesController = instance;
    }

    public void processNewDifferenceParams(int seq, int pts, int date, int pts_count) {
        XReflect.callMethod(messagesController, AutomationResolver.resolve("MessagesController", "processNewDifferenceParams", AutomationResolver.ResolverType.Method), seq, pts, date, pts_count);
    }

    public void processNewDifferenceParams(int pts, int date, int pts_count) {
        //Nagram
        XReflect.callMethod(messagesController, "processNewDifferenceParams", pts, date, pts_count);
    }

    public void removePromoDialog() {
        XReflect.callMethod(messagesController, AutomationResolver.resolve("MessagesController", "removePromoDialog", AutomationResolver.ResolverType.Method));
    }

    public static Object getInputChannel(TLRPC.InputPeer peer) {
        return XReflect.callStaticMethod(ClassLoad.getClass(ClassNames.MESSAGES_CONTROLLER), AutomationResolver.resolve("MessagesController", "getInputChannel", AutomationResolver.ResolverType.Method), peer.inputPeer);
    }

    public SparseArray<Object> getDialogMessagesByIds() {
        return (SparseArray<Object>) XReflect.getObjectField(messagesController, AutomationResolver.resolve("MessagesController", "dialogMessagesByIds", AutomationResolver.ResolverType.Field));
    }

    public LongSparseArray getDialogMessage() {
        return  new LongSparseArray(XReflect.getObjectField(messagesController, AutomationResolver.resolve("MessagesController", "dialogMessage", AutomationResolver.ResolverType.Field)));
    }

    public static SharedPreferences getGlobalMainSettings() {
        return (SharedPreferences) XReflect.callStaticMethod(ClassLoad.getClass(ClassNames.MESSAGES_CONTROLLER), AutomationResolver.resolve("MessagesController", "getGlobalMainSettings", AutomationResolver.ResolverType.Method));
    }

    public static Object getInputChannel(long id) {
        return XReflect.callStaticMethod(ClassLoad.getClass(ClassNames.MESSAGES_CONTROLLER), AutomationResolver.resolve("MessagesController", "getInputChannel", AutomationResolver.ResolverType.Method), id);
    }

    public MessagesStorage getMessagesStorage() {
        return new MessagesStorage(XReflect.callMethod(messagesController, AutomationResolver.resolve("MessagesController", "getMessagesStorage", AutomationResolver.ResolverType.Method)));
    }

    public static MessagesController getInstance(int num){
        return new MessagesController(XReflect.callStaticMethod(ClassLoad.getClass(ClassNames.MESSAGES_CONTROLLER), AutomationResolver.resolve("MessagesController", "getInstance", AutomationResolver.ResolverType.Method), num));
    }
}
