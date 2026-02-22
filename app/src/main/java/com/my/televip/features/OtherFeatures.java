package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.my.televip.ClientChecker;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.language.Language;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.AlertDialog;
import com.my.televip.virtuals.ActiveTheme;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import de.robv.android.xposed.XposedHelpers;

public class OtherFeatures extends Language {
private static final Class<?> longClass = Long.class;
private static Method chatIdObject;
private static Method getMessagesControllerMethod;
private static Field chatIdField;
private static Method getChatMethod;
private static Field userIdField;
private static Method userIdObject;
private static Method getUserMethod;
private static Field otherItemField;
private static Method addSubItemMethod;
private static Method lazilyAddSubItemMethod;
private static Field headerItemField;

    public static void init() {
        try {
            if (loadClass.getProfileActivityClass() != null && loadClass.getBaseFragmentClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getProfileActivityClass(), AutomationResolver.resolve("ProfileActivity", "createActionBarMenu", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("createActionBarMenu", new Class[]{boolean.class}), new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(MethodHookParam param) throws Throwable {
                        Object profileActivityInstance = param.thisObject;
                        if (getMessagesControllerMethod == null) {
                            getMessagesControllerMethod = loadClass.getBaseFragmentClass().getDeclaredMethod(AutomationResolver.resolve("BaseFragment", "getMessagesController", AutomationResolver.ResolverType.Method));
                            getMessagesControllerMethod.setAccessible(true);
                        }
                        Object messagesController = getMessagesControllerMethod.invoke(profileActivityInstance);

                        if (messagesController != null) {

                            if (chatIdField == null) {
                                chatIdField = loadClass.getProfileActivityClass().getDeclaredField(AutomationResolver.resolve("ProfileActivity", "chatId", AutomationResolver.ResolverType.Field));
                                chatIdField.setAccessible(true);
                            }
                            final long chatId = chatIdField.getLong(profileActivityInstance);

                            if (chatIdObject == null) {
                                chatIdObject = longClass.getDeclaredMethod("valueOf", long.class);
                            }
                            Object ChatIdObject = chatIdObject.invoke(null, chatId);

                            if (getChatMethod == null) {
                                getChatMethod = messagesController.getClass().getDeclaredMethod(AutomationResolver.resolve("MessagesController", "getChat", AutomationResolver.ResolverType.Method), AutomationResolver.resolveObject("getChat", new Class[]{Long.class}));
                                getChatMethod.setAccessible(true);
                            }
                            Object chat = getChatMethod.invoke(messagesController, ChatIdObject);

                            if (userIdField == null) {
                                userIdField = loadClass.getProfileActivityClass().getDeclaredField(AutomationResolver.resolve("ProfileActivity", "userId", AutomationResolver.ResolverType.Field));
                                userIdField.setAccessible(true);
                            }
                            final long userId = userIdField.getLong(profileActivityInstance);

                            if (userIdObject == null) {
                                userIdObject = longClass.getDeclaredMethod("valueOf", long.class);
                            }
                            Object UseridObject = userIdObject.invoke(null, userId);

                            if (getUserMethod == null) {
                                getUserMethod = messagesController.getClass().getDeclaredMethod(AutomationResolver.resolve("MessagesController", "getUser", AutomationResolver.ResolverType.Method), AutomationResolver.resolveObject("getUser",new Class[]{Long.class}));
                                getUserMethod.setAccessible(true);
                            }
                            Object user = getUserMethod.invoke(messagesController, UseridObject);

                            if (otherItemField == null) {
                                otherItemField = loadClass.getProfileActivityClass().getDeclaredField(AutomationResolver.resolve("ProfileActivity", "otherItem", AutomationResolver.ResolverType.Field));
                                otherItemField.setAccessible(true);
                            }
                            Object otherItem = otherItemField.get(profileActivityInstance);

                            if (otherItem != null) {
                                if (addSubItemMethod == null) {
                                    addSubItemMethod = otherItem.getClass().getDeclaredMethod(
                                            AutomationResolver.resolve("ActionBarMenuItem", "addSubItem", AutomationResolver.ResolverType.Method),
                                            AutomationResolver.resolveObject("addSubItem",new Class[]{int.class, int.class, CharSequence.class})
                                    );
                                    addSubItemMethod.setAccessible(true);
                                }

                                int drawableResource = 0x7f0806d3;

                                if (!ClientChecker.check(ClientChecker.ClientType.Nagram)) {
                                    drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), "msg_filled_menu_users");
                                }

                                if (chat != null) {
                                    FeatureManager.putLong("id", chatId);

                                    //noinspection JavaReflectionInvocation
                                    addSubItemMethod.invoke(otherItem, 8353847, drawableResource, String.valueOf(chatId));
                                } else if (user != null) {

                                    FeatureManager.putLong("id", userId);
                                    //noinspection JavaReflectionInvocation
                                    addSubItemMethod.invoke(otherItem, 8353847, drawableResource, String.valueOf(userId));
                                }
                            }
                        }
                    }
                }));
                Class<?> profileActivityClass6 = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ProfileActivity$6"), lpparam.classLoader);
                if (profileActivityClass6 != null) {
                    XposedHelpers.findAndHookMethod(
                            profileActivityClass6,
                            AutomationResolver.resolve("ProfileActivity", "onItemClick", AutomationResolver.ResolverType.Method),
                            AutomationResolver.merge(AutomationResolver.resolveObject("onItemClick", new Class[]{int.class}), new AbstractMethodHook() {
                                @Override
                                protected void afterMethod(MethodHookParam param) {
                                    int id = (int) param.args[0];

                                    if (id == 8353847) {

                                        long ID = FeatureManager.getLong("id");

                                        ((ClipboardManager) loadClass.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", String.valueOf(ID)));
                                        Toast.makeText(loadClass.getApplicationContext(), String.valueOf(ID), Toast.LENGTH_LONG).show();
                                    }

                                }
                            }));
                }
            }
            if (loadClass.getChatActivityClass() != null && loadClass.getDrawableClass() != null) {
                Class<?> ChatActivityClass$16 = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity$16"), lpparam.classLoader);
                if (ChatActivityClass$16 != null) {

                    XposedHelpers.findAndHookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "createView", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("createView", new Class[]{loadClass.getContextClass()}), new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(MethodHookParam param) throws Throwable {
                            Object ChatActivityInstance = param.thisObject;
                            if (headerItemField == null) {
                                headerItemField = loadClass.getChatActivityClass().getDeclaredField(AutomationResolver.resolve("ChatActivity", "headerItem", AutomationResolver.ResolverType.Field));
                                headerItemField.setAccessible(true);
                            }
                            Object headerItem = headerItemField.get(ChatActivityInstance);
                            if (headerItem != null) {
                                if (lazilyAddSubItemMethod == null) {
                                    lazilyAddSubItemMethod = headerItem.getClass().getDeclaredMethod(
                                            AutomationResolver.resolve("ActionBarMenuItem", "lazilyAddSubItem", AutomationResolver.ResolverType.Method),
                                            AutomationResolver.resolveObject("lazilyAddSubItem", new Class[]{int.class, int.class, CharSequence.class}));
                                    lazilyAddSubItemMethod.setAccessible(true);
                                }
                                int drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), "msg_go_up");

                                Language.init();
                                if (!ClientChecker.check(ClientChecker.ClientType.Cherrygram) && !ClientChecker.check(ClientChecker.ClientType.iMe) && !ClientChecker.check(ClientChecker.ClientType.iMeWeb) && !ClientChecker.check(ClientChecker.ClientType.TelegramPlus) && !ClientChecker.check(ClientChecker.ClientType.XPlus) && !ClientChecker.check(ClientChecker.ClientType.forkgram) && !ClientChecker.check(ClientChecker.ClientType.forkgramBeta)) {
                                    lazilyAddSubItemMethod.invoke(headerItem, 8353847, drawableResource, ToTheBeginning);
                                }
                                drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), "player_new_order");

                                lazilyAddSubItemMethod.invoke(headerItem, 8353848, drawableResource, ToTheMessage);

                            }

                        }
                    }));

                    XposedHelpers.findAndHookMethod(
                            ChatActivityClass$16,
                            "onItemClick",
                            int.class,
                            new AbstractMethodHook() {
                                @Override
                                protected void afterMethod(MethodHookParam param) {
                                    int id = (int) param.args[0];
                                    Object chatActivityInstance = param.thisObject;
                                    final Object chatActivity = XposedHelpers.getObjectField(chatActivityInstance, AutomationResolver.resolve("ChatActivity$13", "this$0", AutomationResolver.ResolverType.Field));
                                    if (id == 8353847) {
                                        XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("ChatActivity", "scrollToMessageId", AutomationResolver.ResolverType.Method), 1, 0, true, 0, true, 0);

                                    } else if (id == 8353848) {
                                        final Context applicationContext = (Context) XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("BaseFragment", "getContext", AutomationResolver.ResolverType.Method));
                                        if (applicationContext != null) {
                                            AlertDialog alertDialog = new AlertDialog(applicationContext);
                                            if (alertDialog != null) {
                                                alertDialog.setTitle(InputMessageId);


                                                final EditText editText = new EditText(applicationContext);
                                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                                if (ActiveTheme.getActiveTheme()) {
                                                    editText.setTextColor(0xFF000000);
                                                    editText.setHintTextColor(0xFF424242);
                                                } else {
                                                    editText.setTextColor(0xFFFFFFFF);
                                                    editText.setHintTextColor(0xFFBDBDBD);
                                                }
                                                editText.setTextSize(18);
                                                editText.setPadding(20, 20, 20, 20);

                                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                                );
                                                params.setMargins(20, 20, 20, 20);
                                                editText.setLayoutParams(params);

                                                LinearLayout layout = new LinearLayout(applicationContext);
                                                layout.setOrientation(LinearLayout.VERTICAL);
                                                layout.setPadding(30, 30, 30, 30);
                                                layout.addView(editText);

                                                alertDialog.setView(layout);

                                                alertDialog.setPositiveButton(Done, AlertDialog.click(() -> {
                                                    String inputText = editText.getText().toString().trim();

                                                    if (!inputText.isEmpty()) {
                                                        int msid = Integer.parseInt(inputText);
                                                        XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("ChatActivity", "scrollToMessageId", AutomationResolver.ResolverType.Method), msid, 0, true, 0, true, 0);
                                                    }
                                                    }));

                                                alertDialog.setNegativeButton(Cancel, null);

                                               alertDialog.show();
                                            } else {
                                                Utils.log("Not found org.telegram.ui.ActionBar.AlertDialog.Builder, " + Utils.issue);
                                            }
                                        }
                                    }
                                }
                            });
                }
            }

        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
