package com.my.televip;

import static com.my.televip.MainHook.lpparam;

import android.content.Context;

import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class loadClass {
    private static Class<?> ChatActivityClass;
    private static Class<?> MessageObjectClass;
    private static Class<?> ProfileActivityClass;
    private static Class<?> BaseFragmentClass;
    private static Class<?> drawableClass;
    private static Class<?> UserObjectClass;
    private static Class<?> MessagesControllerClass;
    private static Class<?> MessagesStorageClass;
    private static Class<?> ThemeClass;
    private static Class<?> DrawerLayoutAdapterClass;
    private static Class<?> LocaleControllerClass;
    private static Class<?> NotificationsControllerClass;
    private static Class<?> NotificationCenterClass;
    private static Class<?> LaunchActivityClass;
    private static Class<?> SharedConfigClass;
    private static Class<?> AndroidUtilitiesClass;
    private static Class<?> alertDialogBuilderClass;
    private static Class<?> textSettingsCellClass;
    private static Class<?> TLRPC$EncryptedChatClass;
    private static Class<?> TLObjectClass;
    private static Class<?> PeerStoriesView$StoryItemHoldeClass;
    private static Class<?> TLRPC$UserClass;
    private static Class<?> StoriesControllerClass;
    private static Class<?> FileLoadOperationClass;
    private static Class<?> ConnectionsManagerClass;
    private static Class<?> TL_account$updateStatusClass;
    private static Class<?> RequestDelegateClass;
    private static Class<?> ApplicationLoaderClass;
    private static Class<?> RequestDelegateTimestampClass;
    private static Class<?> QuickAckDelegateClass;
    private static Class<?> WriteToSocketDelegateClass;
    private static Class<?> ContextClass;
    private static Class<?> BaseControllerClass;
    private static Class<?> MessagesController$ReadTaskClass;
    private static Class<?> TL_stories$StoryItemClass;
    private static Class<?> TL_stories$PeerStoriesClass;
    private static Class<?> ChatActivity$ChatActivityEnterViewDelegateClass;
    private static Class<?> TLRPC$TL_help_appUpdateClass;
    private static Class<?> TLRPC$TL_updateDeleteMessagesClass;
    private static Class<?> TLRPC$TL_updateDeleteChannelMessagesClass;
    private static Class<?> ChatMessageCellClass;
    private static Class<?> DownloadControllerClass;
    private static Class<?> TLRPC$MessageClass;
    private static Class<?> SecretMediaViewerClass;
    private static Class<?> PhotoViewer$PhotoViewerProviderClass;
    private static Class<?> UserConfigClass;
    private static Class<?> TLRPC$messages_MessagesClass;

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


    public static Class<?> getApplicationLoaderClass() {
        if (ApplicationLoaderClass == null) {
            ApplicationLoaderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), lpparam.classLoader);
        }
        return ApplicationLoaderClass;
    }

    public static Context getApplicationContext() {
        if (applicationContext == null) {
                    applicationContext = (Context) XposedHelpers.getStaticObjectField(
                    getApplicationLoaderClass(),
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

    public static Class<?> getSharedConfigClass() {
        if (SharedConfigClass == null) {
            SharedConfigClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.SharedConfig"), lpparam.classLoader);
        }
        return SharedConfigClass;
    }

    public static Class<?> getAndroidUtilitiesClass() {
        if (AndroidUtilitiesClass == null) {
            AndroidUtilitiesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.AndroidUtilities"), lpparam.classLoader);
        }
        return AndroidUtilitiesClass;
    }

    public static Class<?> getAlertDialogBuilderClass() {
        if (alertDialogBuilderClass == null) {
            alertDialogBuilderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog.Builder"), lpparam.classLoader);
        }
        return alertDialogBuilderClass;
    }

    public static Class<?> getTextSettingsCellClass() {
        if (textSettingsCellClass == null) {
            textSettingsCellClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Cells.TextSettingsCell"), lpparam.classLoader);
        }
        return textSettingsCellClass;
    }

    public static Class<?> getTLRPC$EncryptedChatClass() {
        if (TLRPC$EncryptedChatClass == null) {
            TLRPC$EncryptedChatClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$EncryptedChat"), lpparam.classLoader);
        }
        return TLRPC$EncryptedChatClass;
    }

    public static Class<?> getTLObjectClass() {
        if (TLObjectClass == null) {
            TLObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLObject"), lpparam.classLoader);
        }
        return TLObjectClass;
    }

    public static Class<?> getContextClass() {
        if (ContextClass == null) {
            ContextClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("android.content.Context"), lpparam.classLoader);
        }
        return ContextClass;
    }

    public static Class<?> getPeerStoriesView$StoryItemHoldeClass() {
        if (PeerStoriesView$StoryItemHoldeClass == null) {
            PeerStoriesView$StoryItemHoldeClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.PeerStoriesView$StoryItemHolder"), lpparam.classLoader);
        }
        return PeerStoriesView$StoryItemHoldeClass;
    }

    public static Class<?> getTLRPC$UserClass() {
        if (TLRPC$UserClass == null) {
            TLRPC$UserClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$User"), lpparam.classLoader);
        }
        return TLRPC$UserClass;
    }

    public static Class<?> getStoriesControllerClass() {
        if (StoriesControllerClass == null) {
            StoriesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.StoriesController"), lpparam.classLoader);
        }
        return StoriesControllerClass;
    }

    public static Class<?> getFileLoadOperationClass() {
        if (FileLoadOperationClass == null) {
            FileLoadOperationClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.FileLoadOperation"), lpparam.classLoader);
        }
        return FileLoadOperationClass;
    }

    public static Class<?> getConnectionsManagerClass() {
        if (ConnectionsManagerClass == null) {
            ConnectionsManagerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.ConnectionsManager"), lpparam.classLoader);
        }
        return ConnectionsManagerClass;
    }

    public static Class<?> getTL_account$updateStatusClass() {
        if (TL_account$updateStatusClass == null) {
            TL_account$updateStatusClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_account$updateStatus"), lpparam.classLoader);
        }
        return TL_account$updateStatusClass;
    }

    public static Class<?> getRequestDelegateClass() {
        if (RequestDelegateClass == null) {
            RequestDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegate"), lpparam.classLoader);
        }
        return RequestDelegateClass;
    }

    public static Class<?> getRequestDelegateTimestampClass() {
        if (RequestDelegateTimestampClass == null) {
            RequestDelegateTimestampClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegateTimestamp"), lpparam.classLoader);
        }
        return RequestDelegateTimestampClass;
    }

    public static Class<?> getQuickAckDelegateClass() {
        if (QuickAckDelegateClass == null) {
            QuickAckDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.QuickAckDelegate"), lpparam.classLoader);
        }
        return QuickAckDelegateClass;
    }

    public static Class<?> getWriteToSocketDelegateClass() {
        if (WriteToSocketDelegateClass == null) {
            WriteToSocketDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.WriteToSocketDelegate"), lpparam.classLoader);
        }
        return WriteToSocketDelegateClass;
    }

    public static Class<?> getBaseControllerClass() {
        if (BaseControllerClass == null) {
            BaseControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.BaseController"), lpparam.classLoader);
        }
        return BaseControllerClass;
    }

    public static Class<?> getMessagesController$ReadTaskClass() {
        if (MessagesController$ReadTaskClass == null) {
            MessagesController$ReadTaskClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController$ReadTask"), lpparam.classLoader);
        }
        return MessagesController$ReadTaskClass;
    }

    public static Class<?> getTL_stories$StoryItemClass() {
        if (TL_stories$StoryItemClass == null) {
            TL_stories$StoryItemClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$StoryItem"), lpparam.classLoader);
        }
        return TL_stories$StoryItemClass;
    }

    public static Class<?> getTL_stories$PeerStoriesClass() {
        if (TL_stories$PeerStoriesClass == null) {
            TL_stories$PeerStoriesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$PeerStories"), lpparam.classLoader);
        }
        return TL_stories$PeerStoriesClass;
    }

    public static Class<?> getChatActivity$ChatActivityEnterViewDelegateClass() {
        if (ChatActivity$ChatActivityEnterViewDelegateClass == null) {
            ChatActivity$ChatActivityEnterViewDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity$ChatActivityEnterViewDelegate"), lpparam.classLoader);
        }
        return ChatActivity$ChatActivityEnterViewDelegateClass;
    }

    public static Class<?> getTLRPC$TL_help_appUpdateClass() {
        if (TLRPC$TL_help_appUpdateClass == null) {
            TLRPC$TL_help_appUpdateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$TL_help_appUpdate"), lpparam.classLoader);
        }
        return TLRPC$TL_help_appUpdateClass;
    }

    public static Class<?> getTLRPC$TL_updateDeleteMessagesClass() {
        if (TLRPC$TL_updateDeleteMessagesClass == null) {
            TLRPC$TL_updateDeleteMessagesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$TL_updateDeleteMessages"), lpparam.classLoader);
        }
        return TLRPC$TL_updateDeleteMessagesClass;
    }

    public static Class<?> getTLRPC$TL_updateDeleteChannelMessagesClass() {
        if (TLRPC$TL_updateDeleteChannelMessagesClass == null) {
            TLRPC$TL_updateDeleteChannelMessagesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$TL_updateDeleteChannelMessages"), lpparam.classLoader);
        }
        return TLRPC$TL_updateDeleteChannelMessagesClass;
    }

    public static Class<?> getChatMessageCellClass() {
        if (ChatMessageCellClass == null) {
            ChatMessageCellClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Cells.ChatMessageCell"), lpparam.classLoader);
        }
        return ChatMessageCellClass;
    }

    public static Class<?> getDownloadControllerClass() {
        if (DownloadControllerClass == null) {
            DownloadControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.DownloadController"), lpparam.classLoader);
        }
        return DownloadControllerClass;
    }

    public static Class<?> getTLRPC$MessageClass() {
        if (TLRPC$MessageClass == null) {
            TLRPC$MessageClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Message"), lpparam.classLoader);
        }
        return TLRPC$MessageClass;
    }

    public static Class<?> getSecretMediaViewerClass() {
        if (SecretMediaViewerClass == null) {
            SecretMediaViewerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.SecretMediaViewer"), lpparam.classLoader);
        }
        return SecretMediaViewerClass;
    }

    public static Class<?> getPhotoViewer$PhotoViewerProviderClass() {
        if (PhotoViewer$PhotoViewerProviderClass == null) {
            PhotoViewer$PhotoViewerProviderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.PhotoViewer$PhotoViewerProvider"), lpparam.classLoader);
        }
        return PhotoViewer$PhotoViewerProviderClass;
    }

    public static Class<?> getUserConfigClass() {
        if (UserConfigClass == null) {
            UserConfigClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.UserConfig"), lpparam.classLoader);
        }
        return UserConfigClass;
    }
    public static Class<?> getTLRPC$messages_MessagesClass() {
        if (TLRPC$messages_MessagesClass == null) {
            TLRPC$messages_MessagesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$messages_Messages"), lpparam.classLoader);
        }
        return TLRPC$messages_MessagesClass;
    }

}
