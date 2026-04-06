package com.my.televip.virtuals.messenger;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.tgnet.TLRPC;

import de.robv.android.xposed.XposedHelpers;

public class MessagesController {
    final Object messagesController;

    public MessagesController(Object instance)
    {
        this.messagesController = instance;
    }

    public void processNewDifferenceParams(int seq, int pts, int date, int pts_count) {
        XposedHelpers.callMethod(messagesController, AutomationResolver.resolve("MessagesController", "processNewDifferenceParams", AutomationResolver.ResolverType.Method), seq, pts, date, pts_count);
    }

    public void processNewDifferenceParams(int pts, int date, int pts_count) {
        //Nagram
        XposedHelpers.callMethod(messagesController, "processNewDifferenceParams", pts, date, pts_count);
    }

    public static Object getInputChannel(TLRPC.InputPeer peer) {
        return XposedHelpers.callStaticMethod(ClassLoad.getClass(ClassNames.MESSAGES_CONTROLLER), AutomationResolver.resolve("MessagesController", "getInputChannel", AutomationResolver.ResolverType.Method), peer.inputPeer);
    }

    public void hidePromoDialog() {
        XposedHelpers.callMethod(messagesController, AutomationResolver.resolve("MessagesController", "hidePromoDialog", AutomationResolver.ResolverType.Method));
    }

    public static Object getInputChannel(long id) {
        return XposedHelpers.callStaticMethod(ClassLoad.getClass(ClassNames.MESSAGES_CONTROLLER), AutomationResolver.resolve("MessagesController", "getInputChannel", AutomationResolver.ResolverType.Method), id);
    }

    public static MessagesController getInstance(int num){
        return new MessagesController(XposedHelpers.callStaticMethod(ClassLoad.getClass(ClassNames.MESSAGES_CONTROLLER), AutomationResolver.resolve("MessagesController", "getInstance", AutomationResolver.ResolverType.Method), num));
    }
}
