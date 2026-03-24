package com.my.televip.virtuals.messenger;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.SQLite.SQLiteDatabase;

import java.lang.reflect.Proxy;

import de.robv.android.xposed.XposedHelpers;

public class MessagesStorage {

    Object messagesStorage;

    public MessagesStorage(Object obj){
        messagesStorage = obj;
    }

    public SQLiteDatabase getDatabase(){

        return new SQLiteDatabase(XposedHelpers.getObjectField(messagesStorage, AutomationResolver.resolve("MessagesStorage", "database", AutomationResolver.ResolverType.Field)));
    }

    public DispatchQueue getStorageQueue(){

        return new DispatchQueue(XposedHelpers.callMethod(messagesStorage, AutomationResolver.resolve("MessagesStorage", "getStorageQueue", AutomationResolver.ResolverType.Method)));
    }

    public void getDialogMaxMessageId(long dialog_id, Object callback) {
        XposedHelpers.callMethod(messagesStorage, AutomationResolver.resolve("MessagesStorage", "getDialogMaxMessageId", AutomationResolver.ResolverType.Method), dialog_id, callback);
    }

    public static MessagesStorage getInstance(int num){
        return new MessagesStorage(XposedHelpers.callStaticMethod(loadClass.getMessagesStorageClass(), AutomationResolver.resolve("MessagesStorage", "getInstance", AutomationResolver.ResolverType.Method), num));
    }


    @FunctionalInterface
    public interface IntCallback {
        void run(int param);
    }

    public static Object run(IntCallback lambda) {
        Class<?> intCallbackClass = loadClass.getIntCallbackClass();
        if (intCallbackClass != null) {
            return Proxy.newProxyInstance(
                    lpparam.classLoader,
                    new Class[]{intCallbackClass},
                    (proxy, method, args) -> {
                        if (method.getParameterCount() == 1 && method.getParameterTypes()[0] == int.class) {
                            lambda.run((int) args[0]);
                        }
                        return null;
                    }
            );
        }
        return null;
    }
}
