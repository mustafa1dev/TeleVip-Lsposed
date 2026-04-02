package com.my.televip.features;

import android.text.method.ScrollingMovementMethod;
import android.widget.ScrollView;
import android.widget.TextView;

import com.my.televip.ClientChecker;
import com.my.televip.Database.MessageDatabase;
import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.AlertDialog;
import com.my.televip.virtuals.EventType;
import com.my.televip.virtuals.SQLite.SQLiteCursor;
import com.my.televip.virtuals.SQLite.SQLiteDatabase;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.messenger.BaseController;
import com.my.televip.virtuals.messenger.MessageObject;
import com.my.televip.virtuals.messenger.MessagesStorage;
import com.my.televip.virtuals.messenger.UserConfig;
import com.my.televip.virtuals.tgnet.NativeByteBuffer;
import com.my.televip.virtuals.tgnet.TLRPC;
import com.my.televip.virtuals.ui.ChatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class SaveEditsHistory {

    public static MessageDatabase messageDatabase;

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            messageDatabase = new MessageDatabase(MainHook.launchActivity);

            HMethod.hookMethod(
                    loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "fillMessageMenu", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("fillMessageMenu", new Class[]{loadClass.getMessageObjectClass(), ArrayList.class, ArrayList.class, ArrayList.class}), new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(MethodHookParam param) {
                            if (FeatureManager.isSaveEditsHistory()) {
                                ChatActivity chatActivity = new ChatActivity(param.thisObject);

                                if (chatActivity.getSelectedObject() != null) {
                                    MessageObject messageObject = chatActivity.getSelectedObject();

                                    if (messageObject.getMessageOwner() != null) {

                                        TLRPC.Message message = messageObject.getMessageOwner();
                                        if (message != null && message.getFrom_id() != null && message.getID() > 0) {

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

                                            if (dialogId != 0 && messageDatabase.getMessage(dialogId, message.getID()) != null) {

                                                ArrayList<Integer> icons;
                                                ArrayList<CharSequence> items;
                                                ArrayList<Integer> options;

                                                if (ClientChecker.check(ClientChecker.ClientType.Telegraph)) {
                                                    icons = (ArrayList<Integer>) param.args[2];
                                                    items = (ArrayList<CharSequence>) param.args[3];
                                                    options = (ArrayList<Integer>) param.args[4];
                                                } else {
                                                    icons = (ArrayList<Integer>) param.args[1];
                                                    items = (ArrayList<CharSequence>) param.args[2];
                                                    options = (ArrayList<Integer>) param.args[3];
                                                }

                                                items.add(Translator.get(Keys.EditsHistory));
                                                options.add(MainHook.id);
                                                icons.add(EventType.getIconSettings());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }));

            HMethod.hookMethod(
                    loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "processSelectedOption", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("processSelectedOption", new Class[]{int.class}), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (FeatureManager.isSaveEditsHistory()) {
                                int option = (int) param.args[0];
                                ChatActivity chatActivity = new ChatActivity(param.thisObject);

                                if (option == MainHook.id) {

                                    if (chatActivity.getSelectedObject() != null) {
                                        MessageObject messageObject = chatActivity.getSelectedObject();

                                        if (messageObject.getMessageOwner() != null) {

                                            TLRPC.Message message = messageObject.getMessageOwner();
                                            if (message != null && message.getFrom_id() != null && message.getID() > 0 && message.getMessage() != null) {

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

                                                if (dialogId != 0 & messageDatabase.searchMessage(dialogId, message.getID())) {

                                                    AlertDialog alertDialog = new AlertDialog(MainHook.launchActivity);
                                                    alertDialog.setTitle(Translator.get(Keys.EditsHistory));

                                                    TextView textView = new TextView(MainHook.launchActivity);

                                                    int maxMsgCount = messageDatabase.getMaxMessageCount(dialogId, message.getID());

                                                    StringBuilder builder = new StringBuilder();

                                                    if (maxMsgCount > 0) {

                                                        for (int i = 1; i <= maxMsgCount; i++) {

                                                            String msg = messageDatabase.getMessage(dialogId, message.getID(), i);
                                                            String messageEdited = messageDatabase.getMessageEdited(dialogId, message.getID(), i);

                                                            if (msg != null && messageEdited != null) {
                                                                builder.append(Translator.get(Keys.Message)).append(i).append(Translator.get(Keys.Edited)).append(messageEdited).append("\n");
                                                                builder.append(msg).append("\n");
                                                            }
                                                        }
                                                    } else {
                                                        builder.append(messageDatabase.getMessage(dialogId, message.getID()));
                                                    }

                                                    textView.setText(builder.toString());
                                                    textView.setPadding(32, 32, 32, 32);
                                                    textView.setTextSize(16);
                                                    textView.setTextColor(Theme.getTextColor());
                                                    textView.setMovementMethod(new ScrollingMovementMethod());
                                                    textView.setTextIsSelectable(true);

                                                    ScrollView scrollView = new ScrollView(MainHook.launchActivity);
                                                    scrollView.addView(textView);
                                                    alertDialog.setView(scrollView);
                                                    alertDialog.setPositiveButton(Translator.get(Keys.Done), null);
                                                    alertDialog.show();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }));

            HMethod.hookMethod(
                    loadClass.getMessagesStorageClass(), AutomationResolver.resolve("MessagesStorage","putMessages", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("putMessages", new Class[]{loadClass.getTLRPC$messages_MessagesClass(), long.class, int.class, int.class, boolean.class, int.class, long.class}), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (FeatureManager.isSaveEditsHistory()) {
                                int load_type = (int) param.args[2];
                                if (ClientChecker.check(ClientChecker.ClientType.Nagram)){
                                    load_type = (int) param.args[0];
                                }

                                if (load_type == -2) {
                                    Object messagesStorageObject = param.thisObject;
                                    Object messagesObject = param.args[0];
                                    if (ClientChecker.check(ClientChecker.ClientType.Nagram)){
                                        messagesObject = param.args[5];
                                    }

                                    if (messagesObject != null) {
                                        MessagesStorage messagesStorage = new MessagesStorage(messagesStorageObject);

                                        TLRPC.messages_Messages messages = new TLRPC.messages_Messages(messagesObject);

                                        int count = messages.getMessages().size();

                                        BaseController baseController = new BaseController(messagesStorageObject);
                                        UserConfig userConfig = baseController.getUserConfig();
                                        SQLiteDatabase sqLiteDatabase = messagesStorage.getDatabase();
                                        for (int a = 0; a < count; a++) {

                                            TLRPC.Message message = new TLRPC.Message(messages.getMessages().get(a));

                                            int id = message.getID();
                                            SQLiteCursor cursor = sqLiteDatabase.queryFinalized(String.format(Locale.US, "SELECT mid, data, ttl, mention, read_state, send_state, custom_params FROM messages_v2 WHERE mid = %d AND uid = %d", id, MessageObject.getDialogId(message)));

                                            if (cursor.next()) {
                                                NativeByteBuffer data = cursor.byteBufferValue(1);

                                                if (data.nativeByteBuffer != null) {

                                                    TLRPC.Message oldMessage = TLRPC.Message.TLdeserialize(data, data.readInt32(false), false);

                                                    oldMessage.readAttachPath(data, userConfig.getClientUserId());
                                                    data.reuse();

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
                                                            messageDatabase.addMessage(dialogId, oldMessage.getID(), oldMessage.getMessage());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }));
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
