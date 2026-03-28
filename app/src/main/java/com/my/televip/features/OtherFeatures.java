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
import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.ActionBarMenuItem;
import com.my.televip.virtuals.ActionBar.AlertDialog;
import com.my.televip.virtuals.ActiveTheme;
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

    public static void init() {
        try {
            if (!FeatureManager.getBoolean(KEY_CHAT_ON_ITEM_CLICK_boolean) || FeatureManager.getString(KEY_CHAT_ON_ITEM_CLICK) == null || !FeatureManager.getBoolean(KEY_PROFILE_ON_ITEM_CLICK_boolean) || FeatureManager.getString(KEY_PROFILE_ON_ITEM_CLICK) == null ) {
                Class<?> actionBarClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.ActionBar"), lpparam.classLoader);
                Class<?> actionBar$ActionBarMenuOnItemClickClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.ActionBar$ActionBarMenuOnItemClick"), lpparam.classLoader);

                XposedHelpers.findAndHookMethod(
                        actionBarClass,
                        AutomationResolver.resolve("ActionBar", "setActionBarMenuOnItemClick", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("setActionBarMenuOnItemClick", new Class[]{actionBar$ActionBarMenuOnItemClickClass}), new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {
                                if (!FeatureManager.getBoolean(KEY_CHAT_ON_ITEM_CLICK_boolean) || FeatureManager.getString(KEY_CHAT_ON_ITEM_CLICK) == null || !FeatureManager.getBoolean(KEY_PROFILE_ON_ITEM_CLICK_boolean) || FeatureManager.getString(KEY_PROFILE_ON_ITEM_CLICK) == null ) {
                                    Object clazz = param.args[0];
                                    if (clazz != null && clazz.getClass() != null) {
                                        if (clazz.getClass().getName().contains(AutomationResolver.resolve("ChatActivity"))) {
                                            FeatureManager.putBoolean(KEY_CHAT_ON_ITEM_CLICK_boolean, true);
                                            FeatureManager.putString(KEY_CHAT_ON_ITEM_CLICK, clazz.getClass().getName());
                                            startHook(clazz.getClass().getName(), true);
                                        } else if (clazz.getClass().getName().contains(AutomationResolver.resolve("ProfileActivity"))) {
                                            FeatureManager.putBoolean(KEY_PROFILE_ON_ITEM_CLICK_boolean, true);
                                            FeatureManager.putString(KEY_PROFILE_ON_ITEM_CLICK, clazz.getClass().getName());
                                            startHook(clazz.getClass().getName(), false);
                                        }
                                    }
                                }
                            }
                        }));
            } else {
                startHook(FeatureManager.getString(KEY_CHAT_ON_ITEM_CLICK), true);
                startHook(FeatureManager.getString(KEY_PROFILE_ON_ITEM_CLICK), false);
            }

        } catch (Throwable t){
            Utils.log(t);
        }
    }

    public static void startHook(String className, boolean isChat){
        try {
            if (className != null) {

                Class<?> clazz = XposedHelpers.findClassIfExists(className, lpparam.classLoader);

                if (clazz != null) {

                    if (loadClass.getChatActivityClass() != null && loadClass.getDrawableClass() != null && isChat && !isEnableChat) {
                        isEnableChat = true;
                        XposedHelpers.findAndHookMethod(loadClass.getChatActivityClass(), AutomationResolver.resolve("ChatActivity", "createView", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("createView", new Class[]{loadClass.getContextClass()}), new AbstractMethodHook() {
                            @Override
                            protected void afterMethod(MethodHookParam param) {
                                ChatActivity chatActivity = new ChatActivity(param.thisObject);

                                ActionBarMenuItem headerItem = chatActivity.getHeaderItem();
                                if (headerItem.getActionBarMenuItem() != null) {

                                    int drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), "msg_go_up");

                                    if (!ClientChecker.check(ClientChecker.ClientType.Cherrygram) && !ClientChecker.check(ClientChecker.ClientType.iMe) && !ClientChecker.check(ClientChecker.ClientType.iMeWeb) && !ClientChecker.check(ClientChecker.ClientType.TelegramPlus) && !ClientChecker.check(ClientChecker.ClientType.XPlus) && !ClientChecker.check(ClientChecker.ClientType.forkgram) && !ClientChecker.check(ClientChecker.ClientType.forkgramBeta)) {
                                        headerItem.lazilyAddSubItem(MainHook.id, drawableResource, Translator.get(Keys.TO_THE_BEGINNING));
                                    }
                                    drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), "player_new_order");

                                    headerItem.lazilyAddSubItem(8353848, drawableResource, Translator.get(Keys.TO_THE_MESSAGE));

                                }

                            }
                        }));

                        XposedHelpers.findAndHookMethod(
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
                                        if (id == MainHook.id) {
                                            chatActivity.scrollToMessageId(1, 0, true, 0, true, 0);

                                        } else if (id == 8353848) {
                                            AlertDialog alertDialog = new AlertDialog(MainHook.launchActivity);
                                            alertDialog.setTitle(Translator.get(Keys.INPUT_MESSAGE_ID));

                                            final EditText editText = new EditText(MainHook.launchActivity);
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

                                            LinearLayout layout = new LinearLayout(MainHook.launchActivity);
                                            layout.setOrientation(LinearLayout.VERTICAL);
                                            layout.setPadding(30, 30, 30, 30);
                                            layout.addView(editText);

                                            alertDialog.setView(layout);

                                            alertDialog.setPositiveButton(Translator.get(Keys.DONE), AlertDialog.click(() -> {
                                                String inputText = editText.getText().toString().trim();

                                                if (!inputText.isEmpty()) {
                                                    int ID = Integer.parseInt(inputText);
                                                    chatActivity.scrollToMessageId(ID, 0, true, 0, true, 0);
                                                }
                                            }));

                                            alertDialog.setNegativeButton(Translator.get(Keys.CANCEL), null);

                                            alertDialog.show();
                                        }
                                    }
                                });

                    } else if (loadClass.getProfileActivityClass() != null && !isChat && !isEnableProfile) {

                        isEnableProfile = true;

                        XposedHelpers.findAndHookMethod(loadClass.getProfileActivityClass(), AutomationResolver.resolve("ProfileActivity", "createActionBarMenu", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("createActionBarMenu", new Class[]{boolean.class}), new AbstractMethodHook() {
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
                                        drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), "msg_filled_menu_users");
                                    }

                                    if (id > 1) {
                                        FeatureManager.putLong("id", id);
                                        otherItem.addSubItem(MainHook.id, drawableResource, String.valueOf(id));
                                    }
                                }
                            }
                        }));

                        XposedHelpers.findAndHookMethod(
                                clazz,
                                AutomationResolver.resolve("ProfileActivity", "onItemClick", AutomationResolver.ResolverType.Method),
                                AutomationResolver.merge(AutomationResolver.resolveObject("onItemClick", new Class[]{int.class}), new AbstractMethodHook() {
                                    @Override
                                    protected void afterMethod(MethodHookParam param) {
                                        int id = (int) param.args[0];

                                        if (id == MainHook.id) {

                                            long ID = FeatureManager.getLong("id");

                                            ((ClipboardManager) MainHook.launchActivity.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", String.valueOf(ID)));
                                            Toast.makeText(MainHook.launchActivity, String.valueOf(ID), Toast.LENGTH_LONG).show();
                                        }

                                    }
                                }));
                    }
                } else {
                    reset();
                }
            }
        } catch (Throwable t){
            reset();
            Utils.log(t);
        }
    }

    private static void reset(){
        FeatureManager.remove(KEY_CHAT_ON_ITEM_CLICK);
        FeatureManager.remove(KEY_CHAT_ON_ITEM_CLICK_boolean);

        FeatureManager.remove(KEY_PROFILE_ON_ITEM_CLICK);
        FeatureManager.remove(KEY_PROFILE_ON_ITEM_CLICK_boolean);

        isEnableChat = false;
        isEnableProfile = false;
        init();
    }

}
