package com.my.televip.virtuals.messenger;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.tgnet.TLRPC;

import de.robv.android.xposed.XposedHelpers;

public class MessageObject {

    static Object messageObject;

    public MessageObject(Object obj){
        messageObject = obj;
    }

    public TLRPC.Message getMessageOwner(){
        return new TLRPC.Message(XposedHelpers.getObjectField(messageObject, AutomationResolver.resolve("MessageObject","messageOwner", AutomationResolver.ResolverType.Field)));
    }

    public long getDialogId() {
        return (long) XposedHelpers.callMethod(messageObject, AutomationResolver.resolve("MessageObject", "getDialogId", AutomationResolver.ResolverType.Method));
    }

    public static long getDialogId(TLRPC.Message message) {
        return (long) XposedHelpers.callStaticMethod(ClassLoad.getClass(ClassNames.MESSAGE_OBJECT), AutomationResolver.resolve("MessageObject", "getDialogId", AutomationResolver.ResolverType.Method), message.message);
    }

    public Object getMessageObject(){
        return messageObject;
    }

}
