package com.my.televip.virtuals.ui;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.MessageObject;

import de.robv.android.xposed.XposedHelpers;

public class ChatActivity {


    Object chatActivity;

    public ChatActivity(Object obj){
        chatActivity = obj;
    }

    public MessageObject getSelectedObject(){
        return new MessageObject(XposedHelpers.getObjectField(chatActivity, AutomationResolver.resolve("ChatActivity","selectedObject", AutomationResolver.ResolverType.Field)));
    }

}
