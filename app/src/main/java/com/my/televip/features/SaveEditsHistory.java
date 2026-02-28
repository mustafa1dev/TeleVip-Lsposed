package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import android.text.method.ScrollingMovementMethod;
import android.widget.ScrollView;
import android.widget.TextView;

import com.my.televip.Database.MessageDatabase;
import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.language.Language;
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

import de.robv.android.xposed.XposedHelpers;

public class SaveEditsHistory {

    private static MessageDatabase messageDatabase;

    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            messageDatabase = new MessageDatabase(MainHook.launchActivity);

            XposedHelpers.findAndHookMethod(
                    loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "fillMessageMenu", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("fillMessageMenu", new Class[]{loadClass.getMessageObjectClass(), ArrayList.class, ArrayList.class, ArrayList.class}), new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(MethodHookParam param) {
                            if (FeatureManager.getBoolean(FeatureManager.KEY_Save_Edits_History)) {
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

                                                ArrayList<Integer> icons = (ArrayList<Integer>) param.args[1];
                                                ArrayList<CharSequence> items = (ArrayList<CharSequence>) param.args[2];
                                                ArrayList<Integer> options = (ArrayList<Integer>) param.args[3];

                                                items.add(Language.EditsHistory);
                                                options.add(MainHook.id);
                                                icons.add(EventType.IconSettings());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }));

            XposedHelpers.findAndHookMethod(
                    loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "processSelectedOption", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("processSelectedOption", new Class[]{int.class}), new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (FeatureManager.getBoolean(FeatureManager.KEY_Save_Edits_History)) {
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

                                                if (dialogId != 0 && messageDatabase.getMessage(dialogId, message.getID()) != null) {
                                                    AlertDialog alertDialog = new AlertDialog(MainHook.launchActivity);
                                                    alertDialog.setTitle(Language.EditsHistory);

                                                    TextView textView = new TextView(MainHook.launchActivity);
                                                    textView.setText(messageDatabase.getMessage(dialogId, message.getID()));
                                                    textView.setPadding(32, 32, 32, 32);
                                                    textView.setTextSize(16);
                                                    textView.setTextColor(Theme.getColor(Theme.getKey_actionBarDefaultTitle()));
                                                    textView.setMovementMethod(new ScrollingMovementMethod());

                                                    ScrollView scrollView = new ScrollView(MainHook.launchActivity);
                                                    scrollView.addView(textView);
                                                    alertDialog.setView(scrollView);
                                                    alertDialog.setPositiveButton(Language.Done, null);
                                                    alertDialog.show();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }));

            Class<?> TLRPC$messages_MessagesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$messages_Messages"), lpparam.classLoader);

            XposedHelpers.findAndHookMethod(
                    loadClass.getMessagesStorageClass(), "putMessages", TLRPC$messages_MessagesClass, long.class, int.class, int.class, boolean.class, int.class, long.class, new AbstractMethodHook() {
                        @Override
                        protected void beforeMethod(MethodHookParam param) {
                            if (FeatureManager.getBoolean(FeatureManager.KEY_Save_Edits_History)) {
                                int load_type = (int) param.args[2];
                                if (load_type == -2) {
                                    Object messagesStorageObject = param.thisObject;
                                    Object messagesObject = param.args[0];

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
                    });
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
