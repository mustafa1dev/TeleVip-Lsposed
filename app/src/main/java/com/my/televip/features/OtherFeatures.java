package com.my.televip.features;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.ClientChecker;
import com.my.televip.Configs.ConfigPreferences;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.ActionBarMenuItem;
import com.my.televip.virtuals.ActionBar.AlertDialog;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.ui.ChatActivity;
import com.my.televip.virtuals.ui.ProfileActivity;

import de.robv.android.xposed.XposedHelpers;

public class OtherFeatures {

    public static String KEY_CHAT_ON_ITEM_CLICK = "ChatOnItemClick";
    public static String KEY_CHAT_ON_ITEM_CLICK_boolean = "ChatOnItemClick_boolean";
    public static String KEY_PROFILE_ON_ITEM_CLICK = "ProfileOnItemClick";
    public static String KEY_PROFILE_ON_ITEM_CLICK_boolean = "ProfileOnItemClick_boolean";


    public static boolean isEnableChat = false;
    public static boolean isEnableProfile = false;

    public static void init(Context context) {
        try {
            if (!ConfigPreferences.getBoolean(KEY_CHAT_ON_ITEM_CLICK_boolean) || ConfigPreferences.getString(KEY_CHAT_ON_ITEM_CLICK) == null || !ConfigPreferences.getBoolean(KEY_PROFILE_ON_ITEM_CLICK_boolean) || ConfigPreferences.getString(KEY_PROFILE_ON_ITEM_CLICK) == null ) {
                Class<?> actionBarClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.ActionBar"), Utils.classLoader);
                Class<?> actionBar$ActionBarMenuOnItemClickClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.ActionBar$ActionBarMenuOnItemClick"), Utils.classLoader);

                HMethod.hookMethod(
                        actionBarClass,
                        AutomationResolver.resolve("ActionBar", "setActionBarMenuOnItemClick", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("setActionBarMenuOnItemClick", new Class[]{actionBar$ActionBarMenuOnItemClickClass}), new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                if (!ConfigPreferences.getBoolean(KEY_CHAT_ON_ITEM_CLICK_boolean) || ConfigPreferences.getString(KEY_CHAT_ON_ITEM_CLICK) == null || !ConfigPreferences.getBoolean(KEY_PROFILE_ON_ITEM_CLICK_boolean) || ConfigPreferences.getString(KEY_PROFILE_ON_ITEM_CLICK) == null ) {
                                    Object clazz = param.args[0];
                                    if (clazz != null && clazz.getClass() != null) {
                                        if (clazz.getClass().getName().contains(AutomationResolver.resolve("ChatActivity"))) {
                                            ConfigPreferences.putBoolean(KEY_CHAT_ON_ITEM_CLICK_boolean, true);
                                            ConfigPreferences.putString(KEY_CHAT_ON_ITEM_CLICK, clazz.getClass().getName());
                                            startHook(context, clazz.getClass().getName(), true);
                                        } else if (clazz.getClass().getName().contains(AutomationResolver.resolve("ProfileActivity"))) {
                                            ConfigPreferences.putBoolean(KEY_PROFILE_ON_ITEM_CLICK_boolean, true);
                                            ConfigPreferences.putString(KEY_PROFILE_ON_ITEM_CLICK, clazz.getClass().getName());
                                            startHook(context, clazz.getClass().getName(), false);
                                        }
                                    }
                                }
                            }
                        }));
            } else {
                startHook(context, ConfigPreferences.getString(KEY_CHAT_ON_ITEM_CLICK), true);
                startHook(context, ConfigPreferences.getString(KEY_PROFILE_ON_ITEM_CLICK), false);
            }

        } catch (Throwable t){
            Logger.e(t);
        }
    }

    public static void startHook(Context context, String className, boolean isChat){
        try {
            if (className != null) {

                Class<?> clazz = XposedHelpers.findClassIfExists(className, Utils.classLoader);

                if (clazz != null) {

                    if (ClassLoad.getClass(ClassNames.CHAT_ACTIVITY) != null && ClassLoad.getClass(ClassNames.DRAWABLE) != null && isChat && !isEnableChat) {
                        isEnableChat = true;
                        HMethod.hookMethod(ClassLoad.getClass(ClassNames.CHAT_ACTIVITY), AutomationResolver.resolve("ChatActivity", "createView", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("createView", new Class[]{Context.class}), new AbstractMethodHook() {
                            @Override
                            protected void afterMethod(MethodHookParam param) {
                                ChatActivity chatActivity = new ChatActivity(param.thisObject);

                                ActionBarMenuItem headerItem = chatActivity.getHeaderItem();
                                if (headerItem.getActionBarMenuItem() != null) {

                                    int drawableResource = XposedHelpers.getStaticIntField(ClassLoad.getClass(ClassNames.DRAWABLE), "msg_go_up");

                                    if (!ClientChecker.check(ClientChecker.ClientType.Cherrygram) && !ClientChecker.check(ClientChecker.ClientType.iMe) && !ClientChecker.check(ClientChecker.ClientType.iMeWeb) && !ClientChecker.check(ClientChecker.ClientType.TelegramPlus) && !ClientChecker.check(ClientChecker.ClientType.XPlus) && !ClientChecker.check(ClientChecker.ClientType.forkgram) && !ClientChecker.check(ClientChecker.ClientType.forkgramBeta)) {
                                        headerItem.lazilyAddSubItem(8353847, drawableResource, Translator.get(Keys.ToTheBeginning));
                                    }
                                    drawableResource = XposedHelpers.getStaticIntField(ClassLoad.getClass(ClassNames.DRAWABLE), "player_new_order");

                                    headerItem.lazilyAddSubItem(8353848, drawableResource, Translator.get(Keys.ToTheMessage));

                                }

                            }
                        }));

                        HMethod.hookMethod(
                                clazz,
                                "onItemClick",
                                int.class,
                                new AbstractMethodHook() {
                                    @Override
                                    protected void afterMethod(MethodHookParam param) {
                                        int id = (int) param.args[0];
                                        Object chatActivityInstance = param.thisObject;
                                        final Object thisClass = XposedHelpers.getObjectField(chatActivityInstance, AutomationResolver.resolve("ChatActivity", "this$0", AutomationResolver.ResolverType.Field));
                                        ChatActivity chatActivity = new ChatActivity(thisClass);
                                        if (id == 8353847) {
                                            chatActivity.scrollToMessageId(1, 0, true, 0, true, 0);

                                        } else if (id == 8353848) {
                                            AlertDialog alertDialog = new AlertDialog(context);
                                            alertDialog.setTitle(Translator.get(Keys.InputMessageId));

                                            final EditText editText = new EditText(context);
                                            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                            if (Theme.isLight()) {
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

                                            LinearLayout layout = new LinearLayout(context);
                                            layout.setOrientation(LinearLayout.VERTICAL);
                                            layout.setPadding(30, 30, 30, 30);
                                            layout.addView(editText);

                                            alertDialog.setView(layout);

                                            alertDialog.setPositiveButton(Translator.get(Keys.Done), AlertDialog.click(() -> {
                                                String inputText = editText.getText().toString().trim();

                                                if (!inputText.isEmpty()) {
                                                    int ID = Integer.parseInt(inputText);
                                                    chatActivity.scrollToMessageId(ID, 0, true, 0, true, 0);
                                                }
                                            }));

                                            alertDialog.setNegativeButton(Translator.get(Keys.Cancel), null);

                                            alertDialog.show();
                                        }
                                    }
                                });

                    } else if (ClassLoad.getClass(ClassNames.PROFILE_ACTIVITY) != null && !isChat && !isEnableProfile) {

                        isEnableProfile = true;

                        HMethod.hookMethod(ClassLoad.getClass(ClassNames.PROFILE_ACTIVITY), AutomationResolver.resolve("ProfileActivity", "createActionBarMenu", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("createActionBarMenu", new Class[]{boolean.class}), new AbstractMethodHook() {
                            @Override
                            protected void afterMethod(MethodHookParam param) {
                                ProfileActivity profileActivity = new ProfileActivity(param.thisObject);
                                long id;
                                if (profileActivity.getChatId() > 1) {
                                    id = profileActivity.getChatId();
                                } else {
                                    id = profileActivity.getUserId();
                                }
                                ActionBarMenuItem otherItem = profileActivity.getOtherItem();

                                if (otherItem.getActionBarMenuItem() != null) {

                                    int drawableResource = 0x7f0806d3;

                                    if (!ClientChecker.check(ClientChecker.ClientType.Nagram)) {
                                        drawableResource = XposedHelpers.getStaticIntField(ClassLoad.getClass(ClassNames.DRAWABLE), "msg_filled_menu_users");
                                    }

                                    if (id > 1) {
                                        ConfigPreferences.putLong("id", id);
                                        otherItem.addSubItem(8353847, drawableResource, String.valueOf(id));
                                    }
                                }
                            }
                        }));

                        HMethod.hookMethod(
                                clazz,
                                AutomationResolver.resolve("ProfileActivity", "onItemClick", AutomationResolver.ResolverType.Method),
                                AutomationResolver.merge(AutomationResolver.resolveObject("onItemClick", new Class[]{int.class}), new AbstractMethodHook() {
                                    @Override
                                    protected void afterMethod(MethodHookParam param) {
                                        int id = (int) param.args[0];

                                        if (id == 8353847) {

                                            long ID = ConfigPreferences.getLong("id");

                                            ((ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", String.valueOf(ID)));
                                            Toast.makeText(context, String.valueOf(ID), Toast.LENGTH_LONG).show();
                                        }

                                    }
                                }));
                    }
                } else {
                    reset(context);
                }
            }
        } catch (Throwable t){
            reset(context);
            Logger.e(t);
        }
    }

    private static void reset(Context context){
        ConfigPreferences.remove(KEY_CHAT_ON_ITEM_CLICK);
        ConfigPreferences.remove(KEY_CHAT_ON_ITEM_CLICK_boolean);

        ConfigPreferences.remove(KEY_PROFILE_ON_ITEM_CLICK);
        ConfigPreferences.remove(KEY_PROFILE_ON_ITEM_CLICK_boolean);

        isEnableChat = false;
        isEnableProfile = false;
        init(context);
    }

}
