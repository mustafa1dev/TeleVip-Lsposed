package com.my.televip.virtuals.messenger;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.SQLite.SQLiteDatabase;

import com.my.televip.reflect.XReflect;

public class MessagesStorage {

    Object messagesStorage;

    public MessagesStorage(Object obj) {
        messagesStorage = obj;
    }

    public SQLiteDatabase getDatabase() {

        return new SQLiteDatabase(XReflect.callMethod(messagesStorage, AutomationResolver.resolve("MessagesStorage", "getDatabase", AutomationResolver.ResolverType.Method)));
    }

    public DispatchQueue getStorageQueue() {

        return new DispatchQueue(XReflect.callMethod(messagesStorage, AutomationResolver.resolve("MessagesStorage", "getStorageQueue", AutomationResolver.ResolverType.Method)));
    }

    public static MessagesStorage getInstance(int num) {
        return new MessagesStorage(XReflect.callStaticMethod(ClassLoad.getClass(ClassNames.MESSAGES_STORAGE), AutomationResolver.resolve("MessagesStorage", "getInstance", AutomationResolver.ResolverType.Method), num));
    }

}
