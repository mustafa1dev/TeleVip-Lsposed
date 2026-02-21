package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.language.Language;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class copyName {

    private static Method getMessagesControllerMethed;
    private static Field userIdFiold;
    private static Method getUserMethed;
    private static Field chatIdFiold;
    private static  Method getUserNameMethod;

    public static void init() {
        try {
            if (loadClass.getProfileActivityClass() != null) {

                Class<?> conClass = XposedHelpers.findClassIfExists("android.content.Context", lpparam.classLoader);

                XposedHelpers.findAndHookMethod(loadClass.getProfileActivityClass(), AutomationResolver.resolve("ProfileActivity", "createView", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("9", new Class[]{conClass}), new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(MethodHookParam param) {
                        final Object profileActivityInstance = param.thisObject;

                        Object[] nameTextViewArray = (Object[]) XposedHelpers.getObjectField(profileActivityInstance, AutomationResolver.resolve("ProfileActivity", "nameTextView", AutomationResolver.ResolverType.Field));

                        if (nameTextViewArray != null && nameTextViewArray.length > 1) {

                            Object simpleTextView1 = nameTextViewArray[1];

                            if (simpleTextView1 != null) {

                                XposedHelpers.callMethod(simpleTextView1, AutomationResolver.resolve("SimpleTextView", "setOnClickListener", AutomationResolver.ResolverType.Method), (View.OnClickListener) v -> {
                                    try {

                                        if (loadClass.getBaseFragmentClass() != null && loadClass.getUserObjectClass() != null) {

                                            if (getMessagesControllerMethed == null) {
                                                getMessagesControllerMethed = loadClass.getBaseFragmentClass().getDeclaredMethod("getMessagesController");
                                                getMessagesControllerMethed.setAccessible(true);
                                            }
                                            Object messagesController = getMessagesControllerMethed.invoke(profileActivityInstance);

                                            if (messagesController != null) {

                                                if (userIdFiold == null) {
                                                    userIdFiold = loadClass.getProfileActivityClass().getDeclaredField(AutomationResolver.resolve("ProfileActivity", "userId", AutomationResolver.ResolverType.Field));
                                                    userIdFiold.setAccessible(true);
                                                }
                                                final long userId = userIdFiold.getLong(profileActivityInstance);


                                                Object userIdObject = Long.class.getDeclaredMethod("valueOf", long.class).invoke(null, userId);


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

                                                    Class<?> userClass = lpparam.classLoader.loadClass(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$User"));
                                                    if (getUserNameMethod == null) {
                                                        getUserNameMethod = loadClass.getUserObjectClass().getDeclaredMethod(AutomationResolver.resolve("UserObject", "getUserName", AutomationResolver.ResolverType.Method), userClass);
                                                        getUserNameMethod.setAccessible(true);
                                                    }
                                                    String userName = (String) getUserNameMethod.invoke(null, user);
                                                    if (userName != null) {
                                                        String user_name = Language.Copied + userName + Language.ToTheClipboard;
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
        } catch (Throwable t) {
            Utils.log(t);
        }
    }
}
