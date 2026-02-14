package com.my.televip;

import static com.my.televip.MainHook.lpparam;

import android.content.Context;

import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class loadClass {
    private static  Class<?> ChatActivityClass;
    private static  Class<?> MessageObjectClass;
    private static  Class<?> ProfileActivityClass;
    private static  Class<?> BaseFragmentClass;
    private static  Class<?> drawableClass;
    private static Class<?> UserObjectClass;
    private static Class<?> MessagesControllerClass;
    private static Class<?> MessagesStorageClass;
    private static Class<?> ThemeClass;
    private static Class<?> DrawerLayoutAdapterClass;
    private static Class<?> LocaleControllerClass;
    private static Class<?> NotificationsControllerClass;
    private static Class<?> NotificationCenterClass;
    private static Class<?> LaunchActivityClass;

    //Context
    private static Context applicationContext;

    public static Class<?> getMessageObjectClass() {
        if (MessageObjectClass == null) {
            MessageObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), lpparam.classLoader);
        }
        return MessageObjectClass;
    }

    public static Class<?> getChatActivityClass() {
        if (ChatActivityClass == null) {
            ChatActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity"), lpparam.classLoader);
        }
        return ChatActivityClass;
    }

    public static Class<?> getProfileActivityClass() {
        if (ProfileActivityClass == null) {
            ProfileActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ProfileActivity"), lpparam.classLoader);
        }
        return ProfileActivityClass;
    }

    public static Class<?> getBaseFragmentClass() {
        if (BaseFragmentClass == null) {
            BaseFragmentClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.BaseFragment"), lpparam.classLoader);
        }
        return BaseFragmentClass;
    }

    public static Class<?> getDrawableClass() {
        if (drawableClass == null) {
            drawableClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.R$drawable"), MainHook.lpparam.classLoader);
        }
        return drawableClass;
    }

    public static Class<?> getUserObjectClass() {
        if (UserObjectClass == null){
            UserObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.UserObject"),lpparam.classLoader);
        }
        return UserObjectClass;
    }

    public static Class<?> getMessagesControllerClass() {
        if (MessagesControllerClass == null) {
            MessagesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), lpparam.classLoader);
        }
        return MessagesControllerClass;
    }

    public static Class<?> getMessagesStorageClass() {
        if (MessagesStorageClass == null) {
            MessagesStorageClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesStorage"), lpparam.classLoader);
        }
        return MessagesStorageClass;
    }

    public static Class<?> getThemeClass() {
        if (ThemeClass == null) {
            ThemeClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.Theme"), lpparam.classLoader);
        }
        return ThemeClass;
    }

    public static Context getApplicationContext() {
        if (applicationContext == null) {
                    applicationContext = (Context) XposedHelpers.getStaticObjectField(
                    XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), lpparam.classLoader),
                    AutomationResolver.resolve("ApplicationLoader", "applicationContext", AutomationResolver.ResolverType.Field)
            );
        }
        return applicationContext;
    }

    public static Class<?> getDrawerLayoutAdapterClass() {
        if (DrawerLayoutAdapterClass == null) {
            DrawerLayoutAdapterClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Adapters.DrawerLayoutAdapter"), lpparam.classLoader);
        }
        return DrawerLayoutAdapterClass;
    }

    public static Class<?> getLocaleControllerClass() {
        if (LocaleControllerClass == null) {
            LocaleControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.LocaleController"), lpparam.classLoader);
        }
        return LocaleControllerClass;
    }

    public static Class<?> getNotificationsControllerClass() {
        if (NotificationsControllerClass == null) {
            NotificationsControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.NotificationsController"), lpparam.classLoader);
        }
        return NotificationsControllerClass;
    }

    public static Class<?> getNotificationCenterClass() {
        if (NotificationCenterClass == null) {
            NotificationCenterClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.NotificationCenter"), lpparam.classLoader);
        }
        return NotificationCenterClass;
    }

    public static Class<?> getLaunchActivityClass() {
        if (LaunchActivityClass == null) {
            LaunchActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.LaunchActivity"), lpparam.classLoader);
        }
        return LaunchActivityClass;
    }

}
