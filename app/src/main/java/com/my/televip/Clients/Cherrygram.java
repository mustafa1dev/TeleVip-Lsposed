package com.my.televip.Clients;

import com.my.televip.obfuscate.struct.ClassInfo;
import com.my.televip.obfuscate.struct.FieldInfo;
import com.my.televip.obfuscate.struct.MethodInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cherrygram {
    private static final List<ClassInfo> classList = new ArrayList<>();
    private static final List<FieldInfo> fieldList = new ArrayList<>();
    private static final List<MethodInfo> methodList = new ArrayList<>();
    private static final Map<String, String> methodAlias = new HashMap<>();

    public static String resolveMethodName(String className, String name) {
        return methodAlias.getOrDefault(className + "#" + name, name);
    }

    public static class ClassResolver
    {
        public static String resolve(String name) {
            for (ClassInfo info : classList)
                if (info.getOriginal().equals(name))
                    return info.getResolved();

            return null;
        }

        public static boolean has(String name)
        {
            boolean has = false;
            for (ClassInfo info : classList) {
                if (info.getOriginal().equals(name)) {
                    has = true;
                    break;
                }
            }
            return has;
        }
    }

    public static class FieldResolver
    {
        public static String resolve(String className, String name) {
            for (FieldInfo info : fieldList)
                if (info.getClassName().equals(className) && info.getOriginal().equals(name))
                    return info.getResolved();

            return null;
        }

        public static boolean has(String className, String name)
        {
            boolean has = false;
            for (FieldInfo info : fieldList) {
                if (info.getClassName().equals(className) && info.getOriginal().equals(name)) {
                    has = true;
                    break;
                }
            }
            return has;
        }
    }

    public static class MethodResolver
    {
        public static String resolve(String className, String name) {
            for (MethodInfo info : methodList)
                if (info.getClassName().equals(className) && info.getOriginal().equals(name))
                    return info.getResolved();

            return null;
        }

        public static boolean has(String className, String name)
        {
            boolean has = false;
            for (MethodInfo info : methodList) {
                if (info.getClassName().equals(className) && info.getOriginal().equals(name)) {
                    has = true;
                    break;
                }
            }
            return has;
        }
    }

    public static class ParameterResolver
    {
        static Map<String,Class<?>[]> objectList = new HashMap<>();

        public static void register(String name,  Class<?>[] classes){
            objectList.put(name, classes);
        }

        public static Class<?>[] resolve(String name) {
            return objectList.get(name);
        }

        public static boolean has(String name)
        {
            boolean has = false;
           Class<?>[] classes = objectList.get(name);
           if (classes != null){
               has = true;
           }
            return has;
        }
    }

    public static void loadParameter() {

        //Class
        classList.add(new ClassInfo("androidx.collection.LongSparseArray", "tx3"));
        classList.add(new ClassInfo("org.telegram.messenger.AndroidUtilities", "org.telegram.messenger.ᵎ"));
        classList.add(new ClassInfo("org.telegram.messenger.ApplicationLoader", "org.telegram.messenger.㨤"));
        classList.add(new ClassInfo("org.telegram.messenger.DispatchQueue", "ee"));
        classList.add(new ClassInfo("org.telegram.messenger.FileLoadOperation", "org.telegram.messenger.ₛ"));
        classList.add(new ClassInfo("org.telegram.messenger.FileLoader", "org.telegram.messenger.ᶎ"));
        classList.add(new ClassInfo("org.telegram.messenger.LocaleController", "org.telegram.messenger.ဪ"));
        classList.add(new ClassInfo("org.telegram.messenger.MessageObject", "org.telegram.messenger.㕽"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesController", "org.telegram.messenger.ឌ"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesStorage", "org.telegram.messenger.㦴"));
        classList.add(new ClassInfo("org.telegram.messenger.NotificationCenter", "org.telegram.messenger.㝊"));
        classList.add(new ClassInfo("org.telegram.messenger.NotificationsController", "org.telegram.messenger.ፀ"));
        classList.add(new ClassInfo("org.telegram.messenger.SharedConfig", "org.telegram.messenger.ᣃ"));
        classList.add(new ClassInfo("org.telegram.messenger.UserConfig", "org.telegram.messenger.ϰ"));
        classList.add(new ClassInfo("org.telegram.messenger.browser.Browser", "㕅"));
        classList.add(new ClassInfo("org.telegram.messenger.time.FastDateFormat", "ha0"));
        classList.add(new ClassInfo("org.telegram.tgnet.QuickAckDelegate", "iw8"));
        classList.add(new ClassInfo("org.telegram.tgnet.RequestDelegateTimestamp", "org.telegram.tgnet.ㄙ"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLObject", "iuc"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$Chat", "org.telegram.tgnet.TLRPC$ᑃ"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$EncryptedChat", "org.telegram.tgnet.TLRPC$ឌ"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$InputPeer", "org.telegram.tgnet.TLRPC$㓄"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$Message", "org.telegram.tgnet.TLRPC$ⱂ"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$Peer", "org.telegram.tgnet.TLRPC$ᳶ"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$User", "org.telegram.tgnet.TLRPC$Ḕ"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$messages_Messages", "org.telegram.tgnet.TLRPC$㒸"));
        classList.add(new ClassInfo("org.telegram.tgnet.WriteToSocketDelegate", "vag"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_account$updateStatus", "p6d"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_stories$TL_stories_incrementStoryViews", "tud"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_stories$TL_stories_readStories", "vud"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_update$TL_updateDeleteChannelMessages", "lxd"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_update$TL_updateDeleteMessages", "nxd"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.ActionBar", "org.telegram.ui.ActionBar.ㄙ"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.ActionBar$ActionBarMenuOnItemClick", "org.telegram.ui.ActionBar.ㄙ$ㆁ"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.ActionBarMenuItem", "org.telegram.ui.ActionBar.㨤"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener", "org.telegram.ui.ActionBar.AlertDialog$ᅥ"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.Theme", "org.telegram.ui.ActionBar.ॷ"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.ChatMessageCell", "㢬"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.HeaderCell", "mu1"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.ShadowSectionCell", "toa"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.TextCheckCell", "w6e"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.TextSettingsCell", "d9e"));
        classList.add(new ClassInfo("org.telegram.ui.Components.UItem", "org.telegram.ui.Components.ⱒ"));
        classList.add(new ClassInfo("org.telegram.ui.Components.UniversalAdapter", "org.telegram.ui.Components.щ"));
        classList.add(new ClassInfo("org.telegram.ui.PhotoViewer$PhotoViewerProvider", "org.telegram.ui.PhotoViewer$Ⲅ"));
        classList.add(new ClassInfo("org.telegram.ui.PhotoViewer$PlaceProviderObject", "org.telegram.ui.PhotoViewer$㧶"));
        classList.add(new ClassInfo("org.telegram.ui.SettingsActivity$SettingCell", "org.telegram.ui.SettingsActivity$ᚏ"));
        classList.add(new ClassInfo("org.telegram.ui.SettingsActivity$SettingCell$Factory", "org.telegram.ui.SettingsActivity$ᚏ$ᵎ"));
        classList.add(new ClassInfo("org.telegram.ui.Stories.PeerStoriesView$StoryItemHolder", "org.telegram.ui.Stories.PeerStoriesView$㦴"));
        classList.add(new ClassInfo("org.telegram.ui.Stories.StoriesController", "org.telegram.ui.Stories.ࠔ"));

//Field
        fieldList.add(new FieldInfo("ApplicationLoader","applicationContext", "ᣒ"));
        fieldList.add(new FieldInfo("FileLoadOperation","downloadChunkSizeBig", "থ"));
        fieldList.add(new FieldInfo("FileLoadOperation","maxCdnParts", "ゞ"));
        fieldList.add(new FieldInfo("FileLoadOperation","maxDownloadRequests", "ӆ"));
        fieldList.add(new FieldInfo("FileLoadOperation","maxDownloadRequestsBig", "ڌ"));
        fieldList.add(new FieldInfo("LocaleController","currentLocale", "㩂"));
        fieldList.add(new FieldInfo("LocaleController","isRTL", "ᅀ"));
        fieldList.add(new FieldInfo("MessagesController","dialogMessage", "㜁"));
        fieldList.add(new FieldInfo("MessagesController","dialogMessagesByIds", "ᱳ"));
        fieldList.add(new FieldInfo("NotificationCenter","messagesDeleted", "㩂"));
        fieldList.add(new FieldInfo("NotificationCenter","tlSchemeParseException", "ᣎ"));
        fieldList.add(new FieldInfo("UserConfig","clientUserId", "㜜"));
        fieldList.add(new FieldInfo("UserConfig","selectedAccount", "ⳡ"));
        fieldList.add(new FieldInfo("Utilities","stageQueue", "㟹"));
        fieldList.add(new FieldInfo("Theme","chat_timePaint", "㠧"));

//Method
        methodList.add(new MethodInfo("LongSparseArray","getJ","ম"));
        methodList.add(new MethodInfo("LongSparseArray","getJO","㞬"));
        methodList.add(new MethodInfo("SQLiteCursor","byteBufferValue","Ӵ"));
        methodList.add(new MethodInfo("SQLiteCursor","dispose","㟹"));
        methodList.add(new MethodInfo("SQLiteCursor","intValue","ᡶ"));
        methodList.add(new MethodInfo("SQLiteCursor","longValue","ম"));
        methodList.add(new MethodInfo("SQLiteCursor","next","㞬"));
        methodList.add(new MethodInfo("SQLiteDatabase","executeFast","㪕"));
        methodList.add(new MethodInfo("SQLiteDatabase","queryFinalized","ㄆ"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindByteBufferIB","Ӵ"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindByteBufferIO","ạ"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindByteBufferJIBI","bindByteBuffer"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindInteger","ᄅ"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindLongIJ","㟹"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindLongJIJ","bindLong"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","dispose","ম"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","requery","ぎ"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","step","ӆ"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","stepJ","step"));
        methodList.add(new MethodInfo("AndroidUtilities","isTabletInternal","ᡎ"));
        methodList.add(new MethodInfo("DispatchQueue","postRunnableR","থ"));
        methodList.add(new MethodInfo("DispatchQueue","postRunnableRJ","ぎ"));
        methodList.add(new MethodInfo("FileLoadOperation","updateParams","ὲ"));
        methodList.add(new MethodInfo("FileLoader","getInstance","㧸"));
        methodList.add(new MethodInfo("FileLoader","getPathToMessageO","ඍ"));
        methodList.add(new MethodInfo("FileLoader","getPathToMessageOZ","㠼"));
        methodList.add(new MethodInfo("FileLoader","getPathToMessageOZZ","㐞"));
        methodList.add(new MethodInfo("ImageReceiver","getImageLocation","ⳑ"));
        methodList.add(new MethodInfo("LocaleController","formatShortNumber","ሲ"));
        methodList.add(new MethodInfo("LocaleController","formatYearMont","ⴗ"));
        methodList.add(new MethodInfo("LocaleController","getInstance","㠶"));
        methodList.add(new MethodInfo("MessageObject","canForwardMessage","ᆈ"));
        methodList.add(new MethodInfo("MessageObject","getDialogId","ᴢ"));
        methodList.add(new MethodInfo("MessageObject","getDialogIdO","㘥"));
        methodList.add(new MethodInfo("MessageObject","isMusic","㥊"));
        methodList.add(new MethodInfo("MessageObject","isSecret","ᢊ"));
        methodList.add(new MethodInfo("MessageObject","isVoice","㔂"));
        methodList.add(new MethodInfo("MessagesController","checkPromoInfoInternal","ᅱ"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJIZI","㛿"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJIZIZ","㐚"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJZIZJOI","ᇟ"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJZIZJOIZI","ↇ"));
        methodList.add(new MethodInfo("MessagesController","getGlobalMainSettings","㝨"));
        methodList.add(new MethodInfo("MessagesController","getInputChannelJ","ᘉ"));
        methodList.add(new MethodInfo("MessagesController","getInputChannelO","㤖"));
        methodList.add(new MethodInfo("MessagesController","getInputChannelO2","ඨ"));
        methodList.add(new MethodInfo("MessagesController","getInstance","㫈"));
        methodList.add(new MethodInfo("MessagesController","isChatNoForwardsJ","ఴ"));
        methodList.add(new MethodInfo("MessagesController","isChatNoForwardsO","ઢ"));
        methodList.add(new MethodInfo("MessagesController","processNewDifferenceParams","Ꮏ"));
        methodList.add(new MethodInfo("MessagesController","removePromoDialog","ب"));
        methodList.add(new MethodInfo("MessagesController","storiesEnabled","π"));
        methodList.add(new MethodInfo("MessagesController","storyEntitiesAllowed","ܭ"));
        methodList.add(new MethodInfo("MessagesController","storyEntitiesAllowedO","Δ"));
        methodList.add(new MethodInfo("MessagesStorage","getDatabase","ϲ"));
        methodList.add(new MethodInfo("MessagesStorage","getInstance","χ"));
        methodList.add(new MethodInfo("MessagesStorage","getStorageQueue","Ꮸ"));
        methodList.add(new MethodInfo("MessagesStorage","markMessagesAsDeletedJAZZII","㤖"));
        methodList.add(new MethodInfo("MessagesStorage","markMessagesAsDeletedJIZZ","ᘉ"));
        methodList.add(new MethodInfo("MessagesStorage","putMessagesAZZZIIJ","ᇞ"));
        methodList.add(new MethodInfo("MessagesStorage","putMessagesAZZZIZIJ","〲"));
        methodList.add(new MethodInfo("MessagesStorage","putMessagesOJIIZIJ","ᬛ"));
        methodList.add(new MethodInfo("NotificationCenter","postNotificationName","ᇅ"));
        methodList.add(new MethodInfo("NotificationsController","removeDeletedMessagesFromNotifications","㗦"));
        methodList.add(new MethodInfo("SharedConfig","isAppUpdateAvailable","㘿"));
        methodList.add(new MethodInfo("SharedConfig","setNewAppVersionAvailable","ᬑ"));
        methodList.add(new MethodInfo("UserConfig","getClientUserId","ӆ"));
        methodList.add(new MethodInfo("UserConfig","getCurrentUser","ڌ"));
        methodList.add(new MethodInfo("UserConfig","isPremium","㐬"));
        methodList.add(new MethodInfo("Browser","openUrlCS","ཡ"));
        methodList.add(new MethodInfo("Browser","openUrlCSZ","ຫ"));
        methodList.add(new MethodInfo("Browser","openUrlCSZZ","㘿"));
        methodList.add(new MethodInfo("Browser","openUrlCU","ⲗ"));
        methodList.add(new MethodInfo("Browser","openUrlCUZ","ኺ"));
        methodList.add(new MethodInfo("Browser","openUrlCUZZ","ᇅ"));
        methodList.add(new MethodInfo("Browser","openUrlCUZZO","Ⴙ"));
        methodList.add(new MethodInfo("Browser","openUrlCUZZZOSZZZ","ណ"));
        methodList.add(new MethodInfo("FastDateFormat","formatD","ᄅ"));
        methodList.add(new MethodInfo("FastDateFormat","formatJ","ạ"));
        methodList.add(new MethodInfo("FastDateFormat","formatOSF","format"));
        methodList.add(new MethodInfo("TLRPC$Message","TLdeserialize","ᄅ"));
        methodList.add(new MethodInfo("TLRPC$Message","readAttachPath","㟹"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIC","ᛋ"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIIC","ᦱ"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIICO","ⲹ"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIICZ","㪃"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIIDCZZ","ᔅ"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIIDCZZO","Ⱑ"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIV","Ặ"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIVII","ڠ"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemVII","ㅽ"));
        methodList.add(new MethodInfo("ActionBarMenuItem","lazilyAddSubItemIDC","ῦ"));
        methodList.add(new MethodInfo("ActionBarMenuItem","lazilyAddSubItemIIC","㐞"));
        methodList.add(new MethodInfo("ActionBarMenuItem","lazilyAddSubItemIIDCZZ","㠼"));
        methodList.add(new MethodInfo("AlertDialog$Builder","create","ᄅ"));
        methodList.add(new MethodInfo("AlertDialog$Builder","getDismissRunnable","ㅌ"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setMessage","ʛ"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setNegativeButton","ᅠ"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setNeutralButton","㜜"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setPositiveButton","ቕ"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setTitle","㢼"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setViewV","ຫ"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setViewVI","㘿"));
        methodList.add(new MethodInfo("AlertDialog$Builder","show","ᆈ"));
        methodList.add(new MethodInfo("AlertDialog$OnButtonClickListener","onClick","ạ"));
        methodList.add(new MethodInfo("Theme","isCurrentThemeDark","ᖴ"));
        methodList.add(new MethodInfo("ChatMessageCell","measureTime","ڢ"));
        methodList.add(new MethodInfo("HeaderCell","setTextC","setText"));
        methodList.add(new MethodInfo("HeaderCell","setTextCZ","㟹"));
        methodList.add(new MethodInfo("TextCheckCell","isChecked","㟹"));
        methodList.add(new MethodInfo("TextCheckCell","setTextAndCheck","ম"));
        methodList.add(new MethodInfo("TextCheckCell","setTextAndValueAndCheck","㞬"));
        methodList.add(new MethodInfo("TextSettingsCell","setText","ᄅ"));
        methodList.add(new MethodInfo("TextSettingsCell","setTextAndValueCCZ","㟹"));
        methodList.add(new MethodInfo("TextSettingsCell","setTextAndValueCCZZ","㪕"));
        methodList.add(new MethodInfo("ChatActivity","createPinnedMessageView","ມ"));
        methodList.add(new MethodInfo("ChatActivity","fillMessageMenu","ԧ"));
        methodList.add(new MethodInfo("ChatActivity","hasSelectedNoforwardsMessage","た"));
        methodList.add(new MethodInfo("ChatActivity","processSelectedOption","য়"));
        methodList.add(new MethodInfo("ChatActivity","scrollToMessageIdIIZIZI","㢼"));
        methodList.add(new MethodInfo("ChatActivity","scrollToMessageIdIIZIZIIABR","ⴀ"));
        methodList.add(new MethodInfo("ChatActivity","scrollToMessageIdIIZIZIIR","Ә"));
        methodList.add(new MethodInfo("ChatActivity","sendSecretMediaDelete","ᐞ"));
        methodList.add(new MethodInfo("ChatActivity","sendSecretMessageRead","㓖"));
        methodList.add(new MethodInfo("ChatActivity","updatePinnedMessageViewZ","ፖ"));
        methodList.add(new MethodInfo("ChatActivity","updatePinnedMessageViewZI","㙎"));
        methodList.add(new MethodInfo("ChatActivity$ChatMessageCellDelegate","didPressImage","㦃"));
        methodList.add(new MethodInfo("PhotoViewer","getInstance","ኣ"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoAIJJJO","ᡖ"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoAIO","㧝"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoIOO","も"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOIOJJJO","㘺"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOJJJOZ","㫐"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOO","ߚ"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOOJJJO","ⅱ"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOOO","ᘐ"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOOOOAAAIOOJJJZOI","ܧ"));
        methodList.add(new MethodInfo("PhotoViewer","setIsAboutToSwitchToIndexIZZ","㦲"));
        methodList.add(new MethodInfo("PhotoViewer","setIsAboutToSwitchToIndexIZZZ","ԛ"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityA","व"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityAO","ᦲ"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityAOO","ஒ"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityO","㐽"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityOO","㐖"));
        methodList.add(new MethodInfo("PhotoViewer$PhotoViewerProvider","getPlaceForPhoto","ӆ"));
        methodList.add(new MethodInfo("SecretMediaViewer","closePhoto","㓁"));
        methodList.add(new MethodInfo("SecretMediaViewer","openMedia","㒡"));
        methodList.add(new MethodInfo("SettingsActivity$SettingCell","set","ㅌ"));
        methodList.add(new MethodInfo("SettingsActivity$SettingCell$Factory","ofIIIIC","থ"));
        methodList.add(new MethodInfo("SettingsActivity$SettingCell$Factory","ofIIIICC","ぎ"));
        methodList.add(new MethodInfo("SettingsActivity$SettingCell$Factory","ofIIIICCC","ӆ"));
        methodList.add(new MethodInfo("PeerStoriesView$StoryItemHolder","allowScreenshots","㟹"));
        methodList.add(new MethodInfo("StoriesController","hasStories","ϼ"));
        methodList.add(new MethodInfo("StoriesController","hasStoriesJ","ஏ"));
        methodList.add(new MethodInfo("ProfileActivity","updateProfileData","ࢣ"));

        methodAlias.put("Browser#openUrl", "openUrlCS");
        methodAlias.put("MessagesController#storyEntitiesAllowed2", "storyEntitiesAllowedO");
        methodAlias.put("StoriesController#hasStories2", "hasStoriesJ");
        methodAlias.put("PhotoViewer#setIsAboutToSwitchToIndex", "setIsAboutToSwitchToIndexIZZZ");
        methodAlias.put("MessagesStorage#markMessagesAsDeleted", "markMessagesAsDeletedJAZZII");
        methodAlias.put("MessagesController#deleteMessages", "deleteMessagesAAOJZIZJOIZI");
        methodAlias.put("MessageObject#getDialogId", "getDialogIdO");
        methodAlias.put("TextSettingsCell#setTextAndValue", "setTextAndValueCCZZ");
        methodAlias.put("MessagesStorage#putMessages", "putMessagesOJIIZIJ");
        methodAlias.put("MessagesController#isChatNoForwards", "isChatNoForwardsO");
        methodAlias.put("ChatActivity#updatePinnedMessageView", "updatePinnedMessageViewZI");
        methodAlias.put("PhotoViewer#openPhoto", "openPhotoOJJJOZ");
        methodAlias.put("SettingsActivity$SettingCell$Factory#of", "ofIIIICC");
        methodAlias.put("HeaderCell#setText", "setTextC");
        methodAlias.put("DispatchQueue#postRunnable", "postRunnableR");
        methodAlias.put("SQLitePreparedStatement#bindByteBuffer", "bindByteBufferIO");
        methodAlias.put("SQLitePreparedStatement#bindLong", "bindLongIJ");
        methodAlias.put("LongSparseArray#get", "getJ");
        methodAlias.put("AlertDialog$Builder#setView", "setViewV");
        methodAlias.put("FileLoader#getPathToMessage", "getPathToMessageO");
    }
}
