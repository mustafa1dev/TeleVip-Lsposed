package com.my.televip.virtuals.ui;

import android.view.View;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.ActionBarMenuItem;
import com.my.televip.virtuals.messenger.MessageObject;

import de.robv.android.xposed.XposedHelpers;

public class ChatActivity {


    final Object chatActivity;

    public ChatActivity(Object obj){
        chatActivity = obj;
    }

    public MessageObject getSelectedObject(){
        return new MessageObject(XposedHelpers.getObjectField(chatActivity, AutomationResolver.resolve("ChatActivity","selectedObject", AutomationResolver.ResolverType.Field)));
    }

    public ActionBarMenuItem getHeaderItem(){
        return new ActionBarMenuItem(XposedHelpers.getObjectField(chatActivity, AutomationResolver.resolve("ChatActivity", "headerItem", AutomationResolver.ResolverType.Field)));
    }
    public View getPinnedMessageView(){
        return (View) XposedHelpers.getObjectField(chatActivity, AutomationResolver.resolve("ChatActivity", "pinnedMessageView", AutomationResolver.ResolverType.Field));
    }

    public void scrollToMessageId(int id, int fromMessageId, boolean select, int loadIndex, boolean forceScroll, int forcePinnedMessageId){
        XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("ChatActivity", "scrollToMessageId", AutomationResolver.ResolverType.Method), id, fromMessageId, select, loadIndex, forceScroll, forcePinnedMessageId);
    }

}
