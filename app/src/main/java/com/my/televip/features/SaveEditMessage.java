package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import android.content.Context;

import com.my.televip.Database.SaveEditMessageDatabase;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.TLRPC;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;

import de.robv.android.xposed.XposedHelpers;

public class SaveEditMessage {
    public static void init() {
        Class<?> MessagesStorageClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesStorage"), lpparam.classLoader);
        Class<?> TLRPC$messages_MessagesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$messages_Messages"), lpparam.classLoader);
        Class<?> TLRPC$MessageClass = XposedHelpers.findClassIfExists("org.telegram.tgnet.TLRPC$Message", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(
                MessagesStorageClass, "putMessages", TLRPC$messages_MessagesClass, long.class, int.class, int.class, boolean.class, int.class, long.class, new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(MethodHookParam param) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

                        Object MessagesStorage = param.thisObject;
                        Object messagesObject = param.args[0];

                        if (messagesObject != null) {

                            TLRPC.Message messages = new TLRPC.Message(messagesObject);
                            ArrayList<Object> messages2 = (ArrayList<Object>) messages.getMessages();

                            int load_type = (int) param.args[2];
                            int count = messages2.size();

                            Field databaseField = MessagesStorageClass.getDeclaredField("database");
                            databaseField.setAccessible(true);

                            Object database = databaseField.get(MessagesStorage);

                            Class<?> BaseControllerClass = XposedHelpers.findClassIfExists("org.telegram.messenger.BaseController", lpparam.classLoader);
                            Method getUserConfigMethod = BaseControllerClass.getDeclaredMethod("getUserConfig");
                            getUserConfigMethod.setAccessible(true);

                            Object userConfig = getUserConfigMethod.invoke(MessagesStorage);
                            long clientUserId = XposedHelpers.getLongField(userConfig, "clientUserId");

                            SaveEditMessageDatabase saveMessage = new SaveEditMessageDatabase(loadClass.getApplicationContext());

                            for (int a = 0; a < count; a++) {

                                TLRPC.Message message = new TLRPC.Message(messages2.get(a));

                                if (load_type == -2) {
                                    int id = message.getID();

                                    Object getDialogId = XposedHelpers.callStaticMethod(loadClass.getMessageObjectClass(), "getDialogId", messages2.get(a));
                                    Object cursor = XposedHelpers.callMethod(database, "queryFinalized", String.format(Locale.US, "SELECT mid, data, ttl, mention, read_state, send_state, custom_params FROM messages_v2 WHERE mid = %d AND uid = %d", id, getDialogId), new Object[0]);

                                    Method nextMethod = cursor.getClass().getDeclaredMethod("next");
                                    nextMethod.setAccessible(true);

                                    boolean next = (boolean) nextMethod.invoke(cursor);

                                    if (next) {
                                        Object data = XposedHelpers.callMethod(cursor, "byteBufferValue", 1);

                                        if (data != null) {

                                            Object oldMessageObject = XposedHelpers.callStaticMethod(TLRPC$MessageClass, "TLdeserialize", data, XposedHelpers.callMethod(data, "readInt32", false), false);

                                            XposedHelpers.callMethod(oldMessageObject, "readAttachPath", data, clientUserId);
                                            XposedHelpers.callMethod(data, "reuse");

                                            TLRPC.Message oldMessage = new TLRPC.Message(oldMessageObject);

                                            if (oldMessage.getFrom_id() != null && (!oldMessage.getMessage().equals(message.getMessage()))) {
                                                long user_id = message.getFrom_id().getUser_id();
                                                long chat_id = message.getFrom_id().getChat_id();
                                                long channel_id = message.getFrom_id().getChannel_id();
                                                long dialogId = 0;

                                                if (user_id != 0) {
                                                    dialogId = user_id;
                                                } else if (chat_id != 0) {
                                                    dialogId = chat_id;
                                                } else if (channel_id != 0) {
                                                    dialogId = channel_id;
                                                }

                                                if (dialogId != 0) {
                                                    saveMessage.addMessage(dialogId, oldMessage.getID(), oldMessage.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }
}
