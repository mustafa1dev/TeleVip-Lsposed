package com.my.televip.virtuals.messenger;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.SQLite.SQLiteDatabase;

import de.robv.android.xposed.XposedHelpers;

public class MessagesStorage {

    Object messagesStorage;

    public MessagesStorage(Object obj){
        messagesStorage = obj;
    }

    public SQLiteDatabase getDatabase(){

        return new SQLiteDatabase(XposedHelpers.getObjectField(messagesStorage, AutomationResolver.resolve("MessagesStorage", "database", AutomationResolver.ResolverType.Field)));
    }

}
