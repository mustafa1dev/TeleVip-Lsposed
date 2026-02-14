package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.my.televip.AlertDialog.onClickDialog;
import com.my.televip.ClientChecker;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.language.Language;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActiveTheme;
import com.my.televip.xSharedPreferences;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import de.robv.android.xposed.XposedBridge;
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
private static Method getMessagesControllerMethed;
private static Field userIdFiold;
private static Method getUserMethed;
private static Field chatIdFiold;
private static  Method getUserNameMethod;

    public static void init() {
        try {
            if (loadClass.getProfileActivityClass() != null && loadClass.getBaseFragmentClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getProfileActivityClass(), AutomationResolver.resolve("ProfileActivity", "createActionBarMenu", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("para8"), new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(MethodHookParam param) throws Throwable {
                        Object profileActivityInstance = param.thisObject;
                        if (getMessagesControllerMethod == null) {
                            getMessagesControllerMethod = loadClass.getBaseFragmentClass().getDeclaredMethod(AutomationResolver.resolve("BaseFragment", "getMessagesController", AutomationResolver.ResolverType.Method));
                            getMessagesControllerMethod.setAccessible(true);
                        }
                        Object messagesController = getMessagesControllerMethod.invoke(profileActivityInstance);

                        if (messagesController != null) {
                            // الحصول على chatId
                            if (chatIdField == null) {
                                chatIdField = loadClass.getProfileActivityClass().getDeclaredField(AutomationResolver.resolve("ProfileActivity", "chatId", AutomationResolver.ResolverType.Field));
                                chatIdField.setAccessible(true);
                            }
                            final long chatId = chatIdField.getLong(profileActivityInstance);
                            // تحويل chatId إلى Long
                            if (chatIdObject == null) {
                                chatIdObject = longClass.getDeclaredMethod("valueOf", long.class);
                            }
                            Object ChatIdObject = chatIdObject.invoke(null, chatId);

                            // استدعاء getChat
                            if (getChatMethod == null) {
                                getChatMethod = messagesController.getClass().getDeclaredMethod(AutomationResolver.resolve("MessagesController", "getChat", AutomationResolver.ResolverType.Method), AutomationResolver.resolveObject("para1"));
                                getChatMethod.setAccessible(true);
                            }
                            Object chat = getChatMethod.invoke(messagesController, ChatIdObject);

                            // الحصول على userId
                            if (userIdField == null) {
                                userIdField = loadClass.getProfileActivityClass().getDeclaredField(AutomationResolver.resolve("ProfileActivity", "userId", AutomationResolver.ResolverType.Field));
                                userIdField.setAccessible(true);
                            }
                            final long userId = userIdField.getLong(profileActivityInstance);

                            // تحويل userId إلى Long
                            if (userIdObject == null) {
                                userIdObject = longClass.getDeclaredMethod("valueOf", long.class);
                            }
                            Object UseridObject = userIdObject.invoke(null, userId);

                            // استدعاء getUser
                            if (getUserMethod == null) {
                                getUserMethod = messagesController.getClass().getDeclaredMethod(AutomationResolver.resolve("MessagesController", "getUser", AutomationResolver.ResolverType.Method), AutomationResolver.resolveObject("para1"));
                                getUserMethod.setAccessible(true);
                            }
                            Object user = getUserMethod.invoke(messagesController, UseridObject);

                            // الحصول على otherItem
                            if (otherItemField == null) {
                                otherItemField = loadClass.getProfileActivityClass().getDeclaredField(AutomationResolver.resolve("ProfileActivity", "otherItem", AutomationResolver.ResolverType.Field));
                                otherItemField.setAccessible(true);
                            }
                            Object otherItem = otherItemField.get(profileActivityInstance);

                            // استدعاء addSubItem على otherItem
                            if (otherItem != null) {
                                if (addSubItemMethod == null) {
                                    addSubItemMethod = otherItem.getClass().getDeclaredMethod(
                                            AutomationResolver.resolve("ActionBarMenuItem", "addSubItem", AutomationResolver.ResolverType.Method),
                                            AutomationResolver.resolveObject("para2")
                                    );
                                    addSubItemMethod.setAccessible(true);
                                }

                                if (xSharedPreferences.SharedPre == null) {
                                    xSharedPreferences.SharedPre = loadClass.getApplicationContext().getSharedPreferences(strTelevip, Activity.MODE_PRIVATE);
                                }
                                if (xSharedPreferences.SharedPre != null) {
                                    if (!ClientChecker.check(ClientChecker.ClientType.Nagram)) {
                                        if (loadClass.getDrawableClass() != null) {
                                            int drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), "msg_filled_menu_users");
                                            if (chat != null) {
                                                xSharedPreferences.SharedPre.edit().putString("id", String.valueOf(chatId)).apply();
                                                //noinspection JavaReflectionInvocation
                                                addSubItemMethod.invoke(otherItem, 8353847, drawableResource, String.valueOf(chatId));
                                            } else if (user != null) {
                                                xSharedPreferences.SharedPre.edit().putString("id", String.valueOf(userId)).apply();
                                                //noinspection JavaReflectionInvocation
                                                addSubItemMethod.invoke(otherItem, 8353847, drawableResource, String.valueOf(userId));
                                            }
                                        }
                                    } else {
                                        int drawableResource = 0x7f0806d3;
                                        if (chat != null) {
                                            xSharedPreferences.SharedPre.edit().putString("id", String.valueOf(chatId)).apply();
                                            //noinspection JavaReflectionInvocation
                                            addSubItemMethod.invoke(otherItem, 8353847, drawableResource, String.valueOf(chatId));
                                        } else if (user != null) {
                                            xSharedPreferences.SharedPre.edit().putString("id", String.valueOf(userId)).apply();
                                            //noinspection JavaReflectionInvocation
                                            addSubItemMethod.invoke(otherItem, 8353847, drawableResource, String.valueOf(userId));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }));
                Class<?> profileActivityClass6 = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ProfileActivity$6"), lpparam.classLoader);
                if (profileActivityClass6 != null) {
                    XposedHelpers.findAndHookMethod(
                            profileActivityClass6,
                            AutomationResolver.resolve("ProfileActivity", "onItemClick", AutomationResolver.ResolverType.Method), // اسم الدالة
                            AutomationResolver.merge(AutomationResolver.resolveObject("para3"), new AbstractMethodHook() {
                                @Override
                                protected void afterMethod(MethodHookParam param) {
                                    int id = (int) param.args[0];

                                    if (id == 8353847) {
                                        if (xSharedPreferences.SharedPre == null) {
                                            xSharedPreferences.SharedPre = loadClass.getApplicationContext().getSharedPreferences(strTelevip, Activity.MODE_PRIVATE);
                                        }
                                        if (xSharedPreferences.SharedPre != null) {
                                            ((ClipboardManager) loadClass.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", xSharedPreferences.SharedPre.getString("id", "")));
                                            Toast.makeText(loadClass.getApplicationContext(), xSharedPreferences.SharedPre.getString("id", ""), Toast.LENGTH_LONG).show();
                                        }
                                    }

                                }
                            }));
                }
            }
            if (loadClass.getChatActivityClass() != null && loadClass.getDrawableClass() != null) {
                Class<?> ChatActivityClass$16 = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity$16"), lpparam.classLoader);
                if (ChatActivityClass$16 != null) {
                    XposedHelpers.findAndHookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "createView", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("9"), new AbstractMethodHook() {
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
                                            AutomationResolver.resolveObject("para7"));
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
                            "onItemClick", // اسم الدالة
                            int.class,
                            new AbstractMethodHook() {
                                @Override
                                protected void afterMethod(MethodHookParam param) {
                                    int id = (int) param.args[0];
                                    Object chatActivityInstance = param.thisObject;
                                    final Object chatActivity = XposedHelpers.getObjectField(chatActivityInstance, AutomationResolver.resolve("ChatActivity$13", "this$0", AutomationResolver.ResolverType.Field));
                                    if (id == 8353847) {
                                        XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("ChatActivity", "scrollToMessageId", AutomationResolver.ResolverType.Method), 1, 0, true, 0, true, 0);
                                        //  XposedBridge.log("scrollToMessageId is call.");
                                    } else if (id == 8353848) {
                                        final Context applicationContext = (Context) XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("BaseFragment", "getContext", AutomationResolver.ResolverType.Method));
                                        if (applicationContext != null) {
                                            Object getResourceProvider = XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("ChatActivity", "getResourceProvider", AutomationResolver.ResolverType.Method));

                                            Object alertDialog = XposedHelpers.newInstance(
                                                    XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog.Builder"), lpparam.classLoader),
                                                    applicationContext,
                                                    getResourceProvider
                                            );
                                            if (alertDialog != null) {
                                                XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setTitle", AutomationResolver.ResolverType.Method), InputMessageId);
                                                // إنشاء EditText مع تصميم جميل
                                                final EditText editText = new EditText(applicationContext);
                                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                                if (ActiveTheme.getActiveTheme()) {
                                                    editText.setTextColor(0xFF000000);
                                                    editText.setHintTextColor(0xFF424242);
                                                } else {
                                                    editText.setTextColor(0xFFFFFFFF);
                                                    editText.setHintTextColor(0xFFBDBDBD);
                                                }
                                                editText.setTextSize(18); // تكبير النص
                                                editText.setPadding(20, 20, 20, 20); // إضافة هوامش داخلية

// تحديد حجم EditText ليكون أكبر
                                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                                );
                                                params.setMargins(20, 20, 20, 20); // إضافة هوامش خارجية
                                                editText.setLayoutParams(params);

// إنشاء تخطيط (Layout) لتضمين EditText
                                                LinearLayout layout = new LinearLayout(applicationContext);
                                                layout.setOrientation(LinearLayout.VERTICAL);
                                                layout.setPadding(30, 30, 30, 30); // هوامش إضافية داخل التخطيط
                                                layout.addView(editText);

                                                XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setView", AutomationResolver.ResolverType.Method), layout);

// إعداد زر الحفظ

                                                Object onDoneListener;
                                                Object onCnelListener;
                                                Class<?> listenerClass = XposedHelpers.findClassIfExists(
                                                        AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener"),
                                                        lpparam.classLoader
                                                );
                                                if (listenerClass != null) {
                                                    onDoneListener = Proxy.newProxyInstance(
                                                            lpparam.classLoader,
                                                            new Class[]{listenerClass},
                                                            (proxy, method, args) -> {
                                                                if (method.getName().equals("onClick")) {
                                                                    // هذا AlertDialog
                                                                    onClickDialog.onClickToMessageId(editText, chatActivity);
                                                                }
                                                                //noinspection SuspiciousInvocationHandlerImplementation
                                                                return null;
                                                            }
                                                    );
                                                    onCnelListener = Proxy.newProxyInstance(
                                                            lpparam.classLoader,
                                                            new Class[]{listenerClass},
                                                            (proxy, method, args) -> {
                                                                if (method.getName().equals("onClick")) {
                                                                    Object object = args[0]; // هذا AlertDialog
                                                                    if (object instanceof DialogInterface) {
                                                                        DialogInterface dialog = (DialogInterface) object;
                                                                        onClickDialog.onClickDismiss(dialog);
                                                                    }
                                                                }
                                                                //noinspection SuspiciousInvocationHandlerImplementation
                                                                return null;
                                                            }
                                                    );
                                                } else {
                                                    onDoneListener = (DialogInterface.OnClickListener) (dialog, which) -> onClickDialog.onClickToMessageId(editText, chatActivity);
                                                    onCnelListener = (DialogInterface.OnClickListener) (dialog, which) -> onClickDialog.onClickDismiss(dialog);
                                                }
                                                XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setPositiveButton", AutomationResolver.ResolverType.Method), Done, onDoneListener
                                                );

                                                XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setNegativeButton", AutomationResolver.ResolverType.Method),
                                                        Cancel,
                                                        onCnelListener
                                                );

                                                XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "show", AutomationResolver.ResolverType.Method));
                                            } else {
                                                Utils.log("Not found org.telegram.ui.ActionBar.AlertDialog.Builder, " + Utils.issue);
                                            }
                                        }
                                    }
                                }
                            });
                }
            }
            if (loadClass.getProfileActivityClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getProfileActivityClass(), AutomationResolver.resolve("ProfileActivity", "createView", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("9"), new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(MethodHookParam param) {
                        final Object profileActivityInstance = param.thisObject;

                        // الحصول على الحقل nameTextView (كمصفوفة Objects لأنه لا يمكننا تعريف SimpleTextView مباشرة)
                        Object[] nameTextViewArray = (Object[]) XposedHelpers.getObjectField(profileActivityInstance, AutomationResolver.resolve("ProfileActivity", "nameTextView", AutomationResolver.ResolverType.Field));

                        if (nameTextViewArray != null && nameTextViewArray.length > 1) {
                            // الحصول على SimpleTextView[1]
                            Object simpleTextView1 = nameTextViewArray[1];

                            if (simpleTextView1 != null) {

                                // إضافة حدث النقر باستخدام callMethod
                                XposedHelpers.callMethod(simpleTextView1, AutomationResolver.resolve("SimpleTextView", "setOnClickListener", AutomationResolver.ResolverType.Method), (View.OnClickListener) v -> {
                                    try {
                                        // تحميل الكلاسات المطلوبة
                                        if (loadClass.getBaseFragmentClass() != null && loadClass.getUserObjectClass() != null) {

                                            // استدعاء MessagesController
                                            if (getMessagesControllerMethed == null) {
                                                getMessagesControllerMethed = loadClass.getBaseFragmentClass().getDeclaredMethod("getMessagesController");
                                                getMessagesControllerMethed.setAccessible(true);
                                            }
                                            Object messagesController = getMessagesControllerMethed.invoke(profileActivityInstance);

                                            if (messagesController != null) {
                                                // الحصول على userId
                                                if (userIdFiold == null) {
                                                    userIdFiold = loadClass.getProfileActivityClass().getDeclaredField(AutomationResolver.resolve("ProfileActivity", "userId", AutomationResolver.ResolverType.Field));
                                                    userIdFiold.setAccessible(true);
                                                }
                                                final long userId = userIdFiold.getLong(profileActivityInstance);

                                                // تحويل userId إلى Long
                                                Object userIdObject = Long.class.getDeclaredMethod("valueOf", long.class).invoke(null, userId);

                                                // استدعاء getUser
                                                if (getUserMethed == null) {
                                                    getUserMethed = messagesController.getClass().getDeclaredMethod(AutomationResolver.resolve("MessagesController", "getUser", AutomationResolver.ResolverType.Method), Long.class);
                                                    getUserMethed.setAccessible(true);
                                                }
                                                //noinspection JavaReflectionInvocation
                                                Object user = getUserMethed.invoke(messagesController, userIdObject);
                                                if (chatIdFiold == null) {
                                                    chatIdFiold = loadClass.getProfileActivityClass().getDeclaredField(AutomationResolver.resolve("ProfileActivity", "chatId", AutomationResolver.ResolverType.Field));
                                                    chatIdFiold.setAccessible(true);
                                                }
                                                final long chatId = chatIdFiold.getLong(profileActivityInstance);

                                                if (user != null && chatId == 0) {
                                                    // استدعاء getUserName
                                                    Class<?> userClass = lpparam.classLoader.loadClass(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$User"));
                                                    if (getUserNameMethod == null) {
                                                        getUserNameMethod = loadClass.getUserObjectClass().getDeclaredMethod(AutomationResolver.resolve("UserObject", "getUserName", AutomationResolver.ResolverType.Method), userClass);
                                                        getUserNameMethod.setAccessible(true);
                                                    }
                                                    String userName = (String) getUserNameMethod.invoke(null, user);
                                                    if (userName != null) {
                                                        String user_name = Copied + userName + ToTheClipboard;
                                                        if (loadClass.getApplicationContext() != null) {
                                                            ((ClipboardManager) loadClass.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", userName));
                                                            Toast.makeText(loadClass.getApplicationContext(), user_name, Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        XposedBridge.log("Error: " + e.getMessage());
                                    }
                                });
                            }
                        }
                    }
                }));
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
