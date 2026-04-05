package com.my.televip;

import android.content.Context;

import com.my.televip.dex.DexInjector;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class loadClass {
    private static Class<?> ChatActivityClass;
    private static Class<?> MessageObjectClass;
    private static Class<?> ProfileActivityClass;
    private static Class<?> drawableClass;
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
    private static Class<?> TLRPC$TL_help_appUpdateClass;
    private static Class<?> TLRPC$TL_updateDeleteMessagesClass;
    private static Class<?> TLRPC$TL_updateDeleteChannelMessagesClass;
    private static Class<?> ChatMessageCellClass;
    private static Class<?> DownloadControllerClass;
    private static Class<?> TLRPC$MessageClass;
    private static Class<?> SecretMediaViewerClass;
    private static Class<?> PhotoViewer$PhotoViewerProviderClass;
    private static Class<?> PhotoViewerClass;
    private static Class<?> UserConfigClass;
    private static Class<?> TLRPC$messages_MessagesClass;
    private static Class<?> AlertDialog$OnButtonClickListenerClass;
    private static Class<?> RecyclerListViewClass;
    private static Class<?> SettingsAdapter$ListAdapterClass;
    private static Class<?> SettingsAdapterClass;
    private static Class<?> UtilitiesClass;
    private static Class<?> TL_messages_affectedMessagesClass;
    private static Class<?> TL_channels_readHistoryClass;
    private static Class<?> TL_messages_readHistoryClass;
    private static Class<?> TLRPC$ChatClass;
    private static Class<?> ChatActivity$ChatMessageCellDelegateClass;
    private static Class<?> FileLoaderClass;

    //Context
    private static Context applicationContext;

    public static Class<?> getMessageObjectClass() {
        if (MessageObjectClass == null) {
            MessageObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), Utils.classLoader);
        }
        return MessageObjectClass;
    }

    public static Class<?> getChatActivityClass() {
        if (ChatActivityClass == null) {
            ChatActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity"), Utils.classLoader);
        }
        return ChatActivityClass;
    }

    public static Class<?> getProfileActivityClass() {
        if (ProfileActivityClass == null) {
            ProfileActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ProfileActivity"), Utils.classLoader);
        }
        return ProfileActivityClass;
    }

    public static Class<?> getDrawableClass() {
        if (drawableClass == null) {
            drawableClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.R$drawable"), Utils.classLoader);
        }
        return drawableClass;
    }

    public static Class<?> getMessagesControllerClass() {
        if (MessagesControllerClass == null) {
            MessagesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), Utils.classLoader);
        }
        return MessagesControllerClass;
    }

    public static Class<?> getMessagesStorageClass() {
        if (MessagesStorageClass == null) {
            MessagesStorageClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesStorage"), Utils.classLoader);
        }
        return MessagesStorageClass;
    }

    public static Class<?> getThemeClass() {
        if (ThemeClass == null) {
            ThemeClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.Theme"), Utils.classLoader);
        }
        return ThemeClass;
    }


    public static Class<?> getApplicationLoaderClass() {
        if (ApplicationLoaderClass == null) {
            ApplicationLoaderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), Utils.classLoader);
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
            DrawerLayoutAdapterClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Adapters.DrawerLayoutAdapter"), Utils.classLoader);
        }
        return DrawerLayoutAdapterClass;
    }

    public static Class<?> getLocaleControllerClass() {
        if (LocaleControllerClass == null) {
            LocaleControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.LocaleController"), Utils.classLoader);
        }
        return LocaleControllerClass;
    }

    public static Class<?> getNotificationsControllerClass() {
        if (NotificationsControllerClass == null) {
            NotificationsControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.NotificationsController"), Utils.classLoader);
        }
        return NotificationsControllerClass;
    }

    public static Class<?> getNotificationCenterClass() {
        if (NotificationCenterClass == null) {
            NotificationCenterClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.NotificationCenter"), Utils.classLoader);
        }
        return NotificationCenterClass;
    }

    public static Class<?> getLaunchActivityClass() {
        if (LaunchActivityClass == null) {
            LaunchActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.LaunchActivity"), Utils.classLoader);
        }
        return LaunchActivityClass;
    }

    public static Class<?> getSharedConfigClass() {
        if (SharedConfigClass == null) {
            SharedConfigClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.SharedConfig"), Utils.classLoader);
        }
        return SharedConfigClass;
    }

    public static Class<?> getAndroidUtilitiesClass() {
        if (AndroidUtilitiesClass == null) {
            AndroidUtilitiesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.AndroidUtilities"), Utils.classLoader);
        }
        return AndroidUtilitiesClass;
    }

    public static Class<?> getAlertDialogBuilderClass() {
        if (alertDialogBuilderClass == null) {
            alertDialogBuilderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog.Builder"), Utils.classLoader);
        }
        return alertDialogBuilderClass;
    }

    public static Class<?> getTextSettingsCellClass() {
        if (textSettingsCellClass == null) {
            textSettingsCellClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Cells.TextSettingsCell"), Utils.classLoader);
        }
        return textSettingsCellClass;
    }

    public static Class<?> getTLRPC$EncryptedChatClass() {
        if (TLRPC$EncryptedChatClass == null) {
            TLRPC$EncryptedChatClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$EncryptedChat"), Utils.classLoader);
        }
        return TLRPC$EncryptedChatClass;
    }

    public static Class<?> getTLObjectClass() {
        if (TLObjectClass == null) {
            TLObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLObject"), Utils.classLoader);
        }
        return TLObjectClass;
    }

    public static Class<?> getContextClass() {
        if (ContextClass == null) {
            ContextClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("android.content.Context"), Utils.classLoader);
        }
        return ContextClass;
    }

    public static Class<?> getPeerStoriesView$StoryItemHoldeClass() {
        if (PeerStoriesView$StoryItemHoldeClass == null) {
            PeerStoriesView$StoryItemHoldeClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.PeerStoriesView$StoryItemHolder"), Utils.classLoader);
        }
        return PeerStoriesView$StoryItemHoldeClass;
    }

    public static Class<?> getTLRPC$UserClass() {
        if (TLRPC$UserClass == null) {
            TLRPC$UserClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$User"), Utils.classLoader);
        }
        return TLRPC$UserClass;
    }

    public static Class<?> getStoriesControllerClass() {
        if (StoriesControllerClass == null) {
            StoriesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.StoriesController"), Utils.classLoader);
        }
        return StoriesControllerClass;
    }

    public static Class<?> getFileLoadOperationClass() {
        if (FileLoadOperationClass == null) {
            FileLoadOperationClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.FileLoadOperation"), Utils.classLoader);
        }
        return FileLoadOperationClass;
    }

    public static Class<?> getConnectionsManagerClass() {
        if (ConnectionsManagerClass == null) {
            ConnectionsManagerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.ConnectionsManager"), Utils.classLoader);
        }
        return ConnectionsManagerClass;
    }

    public static Class<?> getTL_account$updateStatusClass() {
        if (TL_account$updateStatusClass == null) {
            TL_account$updateStatusClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_account$updateStatus"), Utils.classLoader);
        }
        return TL_account$updateStatusClass;
    }

    public static Class<?> getRequestDelegateClass() {
        if (RequestDelegateClass == null) {
            RequestDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegate"), Utils.classLoader);
        }
        return RequestDelegateClass;
    }

    public static Class<?> getRequestDelegateTimestampClass() {
        if (RequestDelegateTimestampClass == null) {
            RequestDelegateTimestampClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegateTimestamp"), Utils.classLoader);
        }
        return RequestDelegateTimestampClass;
    }

    public static Class<?> getQuickAckDelegateClass() {
        if (QuickAckDelegateClass == null) {
            QuickAckDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.QuickAckDelegate"), Utils.classLoader);
        }
        return QuickAckDelegateClass;
    }

    public static Class<?> getWriteToSocketDelegateClass() {
        if (WriteToSocketDelegateClass == null) {
            WriteToSocketDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.WriteToSocketDelegate"), Utils.classLoader);
        }
        return WriteToSocketDelegateClass;
    }

    public static Class<?> getTLRPC$TL_help_appUpdateClass() {
        if (TLRPC$TL_help_appUpdateClass == null) {
            TLRPC$TL_help_appUpdateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$TL_help_appUpdate"), Utils.classLoader);
        }
        return TLRPC$TL_help_appUpdateClass;
    }

    public static Class<?> getTLRPC$TL_updateDeleteMessagesClass() {
        if (TLRPC$TL_updateDeleteMessagesClass == null) {
            TLRPC$TL_updateDeleteMessagesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$TL_updateDeleteMessages"), Utils.classLoader);
        }
        return TLRPC$TL_updateDeleteMessagesClass;
    }

    public static Class<?> getTLRPC$TL_updateDeleteChannelMessagesClass() {
        if (TLRPC$TL_updateDeleteChannelMessagesClass == null) {
            TLRPC$TL_updateDeleteChannelMessagesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$TL_updateDeleteChannelMessages"), Utils.classLoader);
        }
        return TLRPC$TL_updateDeleteChannelMessagesClass;
    }

    public static Class<?> getChatMessageCellClass() {
        if (ChatMessageCellClass == null) {
            ChatMessageCellClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Cells.ChatMessageCell"), Utils.classLoader);
        }
        return ChatMessageCellClass;
    }

    public static Class<?> getDownloadControllerClass() {
        if (DownloadControllerClass == null) {
            DownloadControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.DownloadController"), Utils.classLoader);
        }
        return DownloadControllerClass;
    }

    public static Class<?> getTLRPC$MessageClass() {
        if (TLRPC$MessageClass == null) {
            TLRPC$MessageClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Message"), Utils.classLoader);
        }
        return TLRPC$MessageClass;
    }

    public static Class<?> getSecretMediaViewerClass() {
        if (SecretMediaViewerClass == null) {
            SecretMediaViewerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.SecretMediaViewer"), Utils.classLoader);
        }
        return SecretMediaViewerClass;
    }

    public static Class<?> getPhotoViewer$PhotoViewerProviderClass() {
        if (PhotoViewer$PhotoViewerProviderClass == null) {
            PhotoViewer$PhotoViewerProviderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.PhotoViewer$PhotoViewerProvider"), Utils.classLoader);
        }
        return PhotoViewer$PhotoViewerProviderClass;
    }

    public static Class<?> getPhotoViewerClass() {
        if (PhotoViewerClass == null) {
            PhotoViewerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.PhotoViewer"), Utils.classLoader);
        }
        return PhotoViewerClass;
    }

    public static Class<?> getUserConfigClass() {
        if (UserConfigClass == null) {
            UserConfigClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.UserConfig"), Utils.classLoader);
        }
        return UserConfigClass;
    }

    public static Class<?> getTLRPC$messages_MessagesClass() {
        if (TLRPC$messages_MessagesClass == null) {
            TLRPC$messages_MessagesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$messages_Messages"), Utils.classLoader);
        }
        return TLRPC$messages_MessagesClass;
    }

    public static Class<?> getAlertDialog$OnButtonClickListenerClass() {
        if (AlertDialog$OnButtonClickListenerClass == null) {
            AlertDialog$OnButtonClickListenerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener"), Utils.classLoader);
        }
        return AlertDialog$OnButtonClickListenerClass;
    }

    public static Class<?> getRecyclerListViewClass() {
        if (RecyclerListViewClass == null) {
            RecyclerListViewClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("com.televip.SettingsAdapter.SettingsAdapter$RecyclerListView"), DexInjector.classLoader);
        }
        return RecyclerListViewClass;
    }

    public static Class<?> getSettingsAdapterClass() {
        if (SettingsAdapterClass == null) {
            SettingsAdapterClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("com.televip.SettingsAdapter.SettingsAdapter"), DexInjector.classLoader);
        }
        return SettingsAdapterClass;
    }

    public static Class<?> getSettingsAdapter$ListAdapterClass() {
        if (SettingsAdapter$ListAdapterClass == null) {
            SettingsAdapter$ListAdapterClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("com.televip.SettingsAdapter.SettingsAdapter$ListAdapter"), DexInjector.classLoader);
        }
        return SettingsAdapter$ListAdapterClass;
    }

    public static Class<?> getUtilitiesClass() {
        if (UtilitiesClass == null) {
            UtilitiesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.Utilities"), Utils.classLoader);
        }
        return UtilitiesClass;
    }

    public static Class<?> getTL_messages_affectedMessagesClass() {
        if (TL_messages_affectedMessagesClass == null) {
            TL_messages_affectedMessagesClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$TL_messages_affectedMessages"), Utils.classLoader);
        }
        return TL_messages_affectedMessagesClass;
    }

    public static Class<?> getTL_channels_readHistoryClass() {
        if (TL_channels_readHistoryClass == null) {
            TL_channels_readHistoryClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$TL_channels_readHistory"), Utils.classLoader);
        }
        return TL_channels_readHistoryClass;
    }

    public static Class<?> getTL_messages_readHistoryClass() {
        if (TL_messages_readHistoryClass == null) {
            TL_messages_readHistoryClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$TL_messages_readHistory"), Utils.classLoader);
        }
        return TL_messages_readHistoryClass;
    }

    public static Class<?> getTLRPC$ChatClass() {
        if (TLRPC$ChatClass == null) {
            TLRPC$ChatClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Chat"), Utils.classLoader);
        }
        return TLRPC$ChatClass;
    }

    public static Class<?> getChatActivity$ChatMessageCellDelegateClass() {
        if (ChatActivity$ChatMessageCellDelegateClass == null) {
            ChatActivity$ChatMessageCellDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity$ChatMessageCellDelegate"), Utils.classLoader);
        }
        return ChatActivity$ChatMessageCellDelegateClass;
    }

    public static Class<?> getFileLoaderClass() {
        if (FileLoaderClass == null) {
            FileLoaderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.FileLoader"), Utils.classLoader);
        }
        return FileLoaderClass;
    }

}
