package com.my.televip.Clients;

import com.my.televip.obfuscate.struct.ClassInfo;
import com.my.televip.obfuscate.struct.FieldInfo;
import com.my.televip.obfuscate.struct.MethodInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nekogram {
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
        classList.add(new ClassInfo("androidx.collection.LongSparseArray", "bd9"));
        classList.add(new ClassInfo("org.telegram.messenger.AndroidUtilities", "org.telegram.messenger.b"));
        classList.add(new ClassInfo("org.telegram.messenger.ApplicationLoader", "org.telegram.messenger.d"));
        classList.add(new ClassInfo("org.telegram.messenger.DispatchQueue", "of5"));
        classList.add(new ClassInfo("org.telegram.messenger.FileLoadOperation", "org.telegram.messenger.u"));
        classList.add(new ClassInfo("org.telegram.messenger.FileLoader", "org.telegram.messenger.v"));
        classList.add(new ClassInfo("org.telegram.messenger.LocaleController", "org.telegram.messenger.h0"));
        classList.add(new ClassInfo("org.telegram.messenger.MessageObject", "org.telegram.messenger.l0"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesController", "org.telegram.messenger.n0"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesStorage", "org.telegram.messenger.o0"));
        classList.add(new ClassInfo("org.telegram.messenger.NotificationCenter", "org.telegram.messenger.p0"));
        classList.add(new ClassInfo("org.telegram.messenger.NotificationsController", "org.telegram.messenger.q0"));
        classList.add(new ClassInfo("org.telegram.messenger.SharedConfig", "org.telegram.messenger.y0"));
        classList.add(new ClassInfo("org.telegram.messenger.UserConfig", "org.telegram.messenger.f1"));
        classList.add(new ClassInfo("org.telegram.messenger.browser.Browser", "m71"));
        classList.add(new ClassInfo("org.telegram.messenger.time.FastDateFormat", "u76"));
        classList.add(new ClassInfo("org.telegram.tgnet.QuickAckDelegate", "bzd"));
        classList.add(new ClassInfo("org.telegram.tgnet.RequestDelegateTimestamp", "org.telegram.tgnet.a"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLObject", "hjh"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$Chat", "org.telegram.tgnet.TLRPC$o"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$EncryptedChat", "org.telegram.tgnet.TLRPC$p0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$InputPeer", "org.telegram.tgnet.TLRPC$s1"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$Message", "org.telegram.tgnet.TLRPC$k2"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$Peer", "org.telegram.tgnet.TLRPC$c3"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_channels_readHistory", "org.telegram.tgnet.TLRPC$pf"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_channels_readMessageContents", "org.telegram.tgnet.TLRPC$qf"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_inputPeerChannel", "org.telegram.tgnet.TLRPC$xx"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_affectedMessages", "org.telegram.tgnet.TLRPC$fe0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_readDiscussion", "org.telegram.tgnet.TLRPC$rl0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_readEncryptedHistory", "org.telegram.tgnet.TLRPC$sl0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_readHistory", "org.telegram.tgnet.TLRPC$ul0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_readMessageContents", "org.telegram.tgnet.TLRPC$wl0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_sendMedia", "org.telegram.tgnet.TLRPC$un0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_sendMessage", "org.telegram.tgnet.TLRPC$vn0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_sendMultiMedia", "org.telegram.tgnet.TLRPC$wn0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_sendPaidReaction", "org.telegram.tgnet.TLRPC$xn0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_sendReaction", "org.telegram.tgnet.TLRPC$zn0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_setEncryptedTyping", "org.telegram.tgnet.TLRPC$ko0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_messages_setTyping", "org.telegram.tgnet.TLRPC$mo0"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$User", "org.telegram.tgnet.TLRPC$y81"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$messages_Messages", "org.telegram.tgnet.TLRPC$sa1"));
        classList.add(new ClassInfo("org.telegram.tgnet.WriteToSocketDelegate", "nqk"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_account$updateStatus", "fvh"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_stories$TL_stories_incrementStoryViews", "hji"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_stories$TL_stories_readStories", "jji"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_update$TL_updateDeleteChannelMessages", "zli"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_update$TL_updateDeleteMessages", "bmi"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.ActionBar", "org.telegram.ui.ActionBar.a"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.ActionBar$ActionBarMenuOnItemClick", "org.telegram.ui.ActionBar.a$m"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.ActionBarMenuItem", "org.telegram.ui.ActionBar.d"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener", "org.telegram.ui.ActionBar.AlertDialog$l"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.Theme", "org.telegram.ui.ActionBar.r"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.ChatMessageCell", "nk3"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.HeaderCell", "ek7"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.ShadowSectionCell", "uif"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.TextCheckCell", "iui"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.TextSettingsCell", "kwi"));
        classList.add(new ClassInfo("org.telegram.ui.Components.UItem", "org.telegram.ui.Components.a5"));
        classList.add(new ClassInfo("org.telegram.ui.Components.UniversalAdapter", "org.telegram.ui.Components.i5"));
        classList.add(new ClassInfo("org.telegram.ui.PhotoViewer$PhotoViewerProvider", "org.telegram.ui.PhotoViewer$y2"));
        classList.add(new ClassInfo("org.telegram.ui.PhotoViewer$PlaceProviderObject", "org.telegram.ui.PhotoViewer$z2"));
        classList.add(new ClassInfo("org.telegram.ui.SettingsActivity$SettingCell", "org.telegram.ui.SettingsActivity$k"));
        classList.add(new ClassInfo("org.telegram.ui.SettingsActivity$SettingCell$Factory", "org.telegram.ui.SettingsActivity$k$b"));
        classList.add(new ClassInfo("org.telegram.ui.Stories.PeerStoriesView$StoryItemHolder", "org.telegram.ui.Stories.PeerStoriesView$q0"));
        classList.add(new ClassInfo("org.telegram.ui.Stories.StoriesController", "org.telegram.ui.Stories.g"));

//Field
        fieldList.add(new FieldInfo("ApplicationLoader","applicationContext", "b"));
        fieldList.add(new FieldInfo("FileLoadOperation","downloadChunkSizeBig", "l"));
        fieldList.add(new FieldInfo("FileLoadOperation","maxCdnParts", "q"));
        fieldList.add(new FieldInfo("FileLoadOperation","maxDownloadRequests", "n"));
        fieldList.add(new FieldInfo("FileLoadOperation","maxDownloadRequestsBig", "o"));
        fieldList.add(new FieldInfo("LocaleController","currentLocale", "y"));
        fieldList.add(new FieldInfo("LocaleController","isRTL", "S"));
        fieldList.add(new FieldInfo("MessagesController","dialogMessagesByIds", "T"));
        fieldList.add(new FieldInfo("NotificationCenter","messagesDeleted", "z"));
        fieldList.add(new FieldInfo("NotificationCenter","tlSchemeParseException", "s"));
        fieldList.add(new FieldInfo("UserConfig","clientUserId", "p"));
        fieldList.add(new FieldInfo("UserConfig","selectedAccount", "r0"));
        fieldList.add(new FieldInfo("Utilities","stageQueue", "d"));
        fieldList.add(new FieldInfo("TLRPC$InputPeer","channel_id", "d"));
        fieldList.add(new FieldInfo("TLRPC$InputPeer","chat_id", "e"));
        fieldList.add(new FieldInfo("TLRPC$InputPeer","user_id", "c"));
        fieldList.add(new FieldInfo("TLRPC$Message","flags", "k"));
        fieldList.add(new FieldInfo("TLRPC$Message","from_id", "b"));
        fieldList.add(new FieldInfo("TLRPC$Message","id", "a"));
        fieldList.add(new FieldInfo("TLRPC$Message","message", "i"));
        fieldList.add(new FieldInfo("TLRPC$Message","ttl", "m0"));
        fieldList.add(new FieldInfo("TLRPC$Peer","channel_id", "c"));
        fieldList.add(new FieldInfo("TLRPC$Peer","chat_id", "b"));
        fieldList.add(new FieldInfo("TLRPC$Peer","user_id", "a"));
        fieldList.add(new FieldInfo("TLRPC$TL_channels_readHistory","channel", "a"));
        fieldList.add(new FieldInfo("TLRPC$TL_channels_readHistory","max_id", "b"));
        fieldList.add(new FieldInfo("TLRPC$TL_messages_affectedMessages","pts", "a"));
        fieldList.add(new FieldInfo("TLRPC$TL_messages_affectedMessages","pts_count", "b"));
        fieldList.add(new FieldInfo("TLRPC$TL_messages_readHistory","max_id", "b"));
        fieldList.add(new FieldInfo("TLRPC$TL_messages_readHistory","peer", "a"));
        fieldList.add(new FieldInfo("TLRPC$TL_messages_readDiscussion","peer", "a"));
        fieldList.add(new FieldInfo("TLRPC$TL_messages_sendMedia","peer", "h"));
        fieldList.add(new FieldInfo("TLRPC$TL_messages_sendMessage","peer", "j"));
        fieldList.add(new FieldInfo("TLRPC$TL_messages_sendMultiMedia","peer", "h"));
        fieldList.add(new FieldInfo("TLRPC$TL_messages_sendPaidReaction","peer", "c"));
        fieldList.add(new FieldInfo("TLRPC$TL_messages_sendReaction","peer", "d"));
        fieldList.add(new FieldInfo("TLRPC$User","phone", "f"));
        fieldList.add(new FieldInfo("TLRPC$messages_Messages","messages", "a"));
        fieldList.add(new FieldInfo("TL_account$updateStatus","offline", "a"));
        fieldList.add(new FieldInfo("TL_update$TL_updateDeleteChannelMessages","channel_id", "a"));
        fieldList.add(new FieldInfo("TL_update$TL_updateDeleteChannelMessages","messages", "b"));
        fieldList.add(new FieldInfo("TL_update$TL_updateDeleteMessages","messages", "a"));
        fieldList.add(new FieldInfo("Theme","chat_timePaint", "U2"));

//Method
        methodList.add(new MethodInfo("LongSparseArray","getJ","h"));
        methodList.add(new MethodInfo("LongSparseArray","getJO","i"));
        methodList.add(new MethodInfo("SQLiteCursor","byteBufferValue","b"));
        methodList.add(new MethodInfo("SQLiteCursor","dispose","d"));
        methodList.add(new MethodInfo("SQLiteCursor","intValue","g"));
        methodList.add(new MethodInfo("SQLiteCursor","longValue","i"));
        methodList.add(new MethodInfo("SQLiteCursor","next","j"));
        methodList.add(new MethodInfo("SQLiteDatabase","executeFast","e"));
        methodList.add(new MethodInfo("SQLiteDatabase","queryFinalized","h"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindByteBufferIB","b"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindByteBufferIO","a"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindByteBufferJIBI","bindByteBuffer"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindInteger","c"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindLongIJ","d"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","bindLongJIJ","bindLong"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","dispose","i"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","requery","m"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","step","n"));
        methodList.add(new MethodInfo("SQLitePreparedStatement","stepJ","step"));
        methodList.add(new MethodInfo("AndroidUtilities","isTabletInternal","B3"));
        methodList.add(new MethodInfo("DispatchQueue","postRunnableR","k"));
        methodList.add(new MethodInfo("DispatchQueue","postRunnableRJ","l"));
        methodList.add(new MethodInfo("FileLoadOperation","updateParams","b1"));
        methodList.add(new MethodInfo("FileLoader","getInstance","K0"));
        methodList.add(new MethodInfo("FileLoader","getPathToMessageO","U0"));
        methodList.add(new MethodInfo("FileLoader","getPathToMessageOZ","V0"));
        methodList.add(new MethodInfo("FileLoader","getPathToMessageOZZ","W0"));
        methodList.add(new MethodInfo("ImageReceiver","getImageLocation","K"));
        methodList.add(new MethodInfo("LocaleController","formatShortNumber","E0"));
        methodList.add(new MethodInfo("LocaleController","formatYearMont","X0"));
        methodList.add(new MethodInfo("LocaleController","getInstance","D1"));
        methodList.add(new MethodInfo("MessageObject","canForwardMessage","O"));
        methodList.add(new MethodInfo("MessageObject","getDialogId","i1"));
        methodList.add(new MethodInfo("MessageObject","getDialogIdO","j1"));
        methodList.add(new MethodInfo("MessageObject","isMusic","y5"));
        methodList.add(new MethodInfo("MessageObject","isSecret","v4"));
        methodList.add(new MethodInfo("MessageObject","isVoice","T6"));

        methodList.add(new MethodInfo("MessagesController","checkPromoInfoInternal","x9"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJIZI","W9"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJIZIZ","X9"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJZIZJOI","Y9"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJZIZJOIZI","Z9"));
        methodList.add(new MethodInfo("MessagesController","getGlobalMainSettings","wb"));
        methodList.add(new MethodInfo("MessagesController","getInputChannelJ","Ab"));
        methodList.add(new MethodInfo("MessagesController","getInputChannelO","Bb"));
        methodList.add(new MethodInfo("MessagesController","getInputChannelO2","Cb"));
        methodList.add(new MethodInfo("MessagesController","getInstance","Nb"));
        methodList.add(new MethodInfo("MessagesController","isChatNoForwardsJ","ed"));
        methodList.add(new MethodInfo("MessagesController","isChatNoForwardsO","fd"));
        methodList.add(new MethodInfo("MessagesController","processNewDifferenceParams","zn"));
        methodList.add(new MethodInfo("MessagesController","removePromoDialog","io"));
        methodList.add(new MethodInfo("MessagesController","storiesEnabled","lp"));
        methodList.add(new MethodInfo("MessagesController","storyEntitiesAllowed","mp"));
        methodList.add(new MethodInfo("MessagesController","storyEntitiesAllowedO","np"));
        methodList.add(new MethodInfo("MessagesStorage","getDatabase","u5"));
        methodList.add(new MethodInfo("MessagesStorage","getInstance","I5"));
        methodList.add(new MethodInfo("MessagesStorage","getStorageQueue","b6"));
        methodList.add(new MethodInfo("MessagesStorage","markMessagesAsDeletedJAZZII","Hb"));
        methodList.add(new MethodInfo("MessagesStorage","markMessagesAsDeletedJIZZ","Gb"));
        methodList.add(new MethodInfo("MessagesStorage","putMessagesAZZZIIJ","ic"));
        methodList.add(new MethodInfo("MessagesStorage","putMessagesAZZZIZIJ","jc"));
        methodList.add(new MethodInfo("MessagesStorage","putMessagesOJIIZIJ","kc"));
        methodList.add(new MethodInfo("NotificationCenter","postNotificationName","G"));
        methodList.add(new MethodInfo("NotificationsController","removeDeletedMessagesFromNotifications","Y1"));
        methodList.add(new MethodInfo("SharedConfig","isAppUpdateAvailable","N"));
        methodList.add(new MethodInfo("SharedConfig","setNewAppVersionAvailable","q0"));
        methodList.add(new MethodInfo("UserConfig","getClientUserId","n"));
        methodList.add(new MethodInfo("UserConfig","getCurrentUser","o"));
        methodList.add(new MethodInfo("UserConfig","isPremium","C"));
        methodList.add(new MethodInfo("Browser","openUrlCS","H"));
        methodList.add(new MethodInfo("Browser","openUrlCSZ","I"));
        methodList.add(new MethodInfo("Browser","openUrlCSZZ","J"));
        methodList.add(new MethodInfo("Browser","openUrlCU","C"));
        methodList.add(new MethodInfo("Browser","openUrlCUZ","D"));
        methodList.add(new MethodInfo("Browser","openUrlCUZZ","E"));
        methodList.add(new MethodInfo("Browser","openUrlCUZZO","F"));
        methodList.add(new MethodInfo("Browser","openUrlCUZZZOSZZZ","G"));
        methodList.add(new MethodInfo("FastDateFormat","formatD","c"));
        methodList.add(new MethodInfo("FastDateFormat","formatJ","a"));
        methodList.add(new MethodInfo("FastDateFormat","formatOSF","format"));
        methodList.add(new MethodInfo("TLRPC$Message","TLdeserialize","c"));
        methodList.add(new MethodInfo("TLRPC$Message","readAttachPath","d"));
        methodList.add(new MethodInfo("ActionBar","setActionBarMenuOnItemClick","G0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIC","b0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIIC","e0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIICO","f0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIICZ","g0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIIDCZZ","c0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIIDCZZO","d0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIV","a0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIVII","i0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemVII","j0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","lazilyAddSubItemIDC","h1"));
        methodList.add(new MethodInfo("ActionBarMenuItem","lazilyAddSubItemIIC","g1"));
        methodList.add(new MethodInfo("ActionBarMenuItem","lazilyAddSubItemIIDCZZ","f1"));
        methodList.add(new MethodInfo("AlertDialog$Builder","create","c"));
        methodList.add(new MethodInfo("AlertDialog$Builder","getDismissRunnable","f"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setMessage","t"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setNegativeButton","v"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setNeutralButton","w"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setPositiveButton","B"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setTitle","D"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setViewV","K"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setViewVI","L"));
        methodList.add(new MethodInfo("AlertDialog$Builder","show","N"));
        methodList.add(new MethodInfo("AlertDialog$OnButtonClickListener","onClick","a"));
        methodList.add(new MethodInfo("Theme","isCurrentThemeDark","Y2"));
        methodList.add(new MethodInfo("ChatMessageCell","getMessageObject","q"));
        methodList.add(new MethodInfo("ChatMessageCell","measureTime","x8"));
        methodList.add(new MethodInfo("HeaderCell","setTextC","j"));
        methodList.add(new MethodInfo("HeaderCell","setTextCZ","k"));
        methodList.add(new MethodInfo("TextCheckCell","isChecked","f"));
        methodList.add(new MethodInfo("TextCheckCell","setChecked","k"));
        methodList.add(new MethodInfo("TextCheckCell","setTextAndCheck","r"));
        methodList.add(new MethodInfo("TextCheckCell","setTextAndValueAndCheck","s"));
        methodList.add(new MethodInfo("TextSettingsCell","setText","j"));
        methodList.add(new MethodInfo("TextSettingsCell","setTextAndValueCCZ","k"));
        methodList.add(new MethodInfo("TextSettingsCell","setTextAndValueCCZZ","l"));
        methodList.add(new MethodInfo("ChatActivity","createPinnedMessageView","Ku"));
        methodList.add(new MethodInfo("ChatActivity","createView","C0"));
        methodList.add(new MethodInfo("ChatActivity","fillMessageMenu","vv"));
        methodList.add(new MethodInfo("ChatActivity","hasSelectedNoforwardsMessage","Yw"));
        methodList.add(new MethodInfo("ChatActivity","isSwipeBackEnabled","S1"));
        methodList.add(new MethodInfo("ChatActivity","processSelectedOption","RH"));
        methodList.add(new MethodInfo("ChatActivity","scrollToMessageIdIIZIZI","L"));
        methodList.add(new MethodInfo("ChatActivity","scrollToMessageIdIIZIZIIABR","xI"));
        methodList.add(new MethodInfo("ChatActivity","scrollToMessageIdIIZIZIIR","wI"));
        methodList.add(new MethodInfo("ChatActivity","sendSecretMediaDelete","JI"));
        methodList.add(new MethodInfo("ChatActivity","sendSecretMessageRead","KI"));
        methodList.add(new MethodInfo("ChatActivity","updatePinnedMessageViewZ","YK"));
        methodList.add(new MethodInfo("ChatActivity","updatePinnedMessageViewZI","ZK"));
        methodList.add(new MethodInfo("ChatActivity$ChatMessageCellDelegate","didPressImage","j0"));
        methodList.add(new MethodInfo("PhotoViewer","getInstance","Fc"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoAIJJJO","bh"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoAIO","ch"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoIOO","ah"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOIOJJJO","dh"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOJJJOZ","eh"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOO","ih"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOOJJJO","gh"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOOO","hh"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOOOOAAAIOOJJJZOI","fh"));
        methodList.add(new MethodInfo("PhotoViewer","setIsAboutToSwitchToIndexIZZ","Wh"));
        methodList.add(new MethodInfo("PhotoViewer","setIsAboutToSwitchToIndexIZZZ","ci"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityA","hi"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityAO","ji"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityAOO","ii"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityO","ki"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityOO","li"));
        methodList.add(new MethodInfo("PhotoViewer$PhotoViewerProvider","getPlaceForPhoto","n"));
        methodList.add(new MethodInfo("ProfileActivity","isSwipeBackEnabled","S1"));
        methodList.add(new MethodInfo("SecretMediaViewer","closePhoto","o0"));
        methodList.add(new MethodInfo("SecretMediaViewer","openMedia","N0"));
        methodList.add(new MethodInfo("SettingsActivity","fillItems","m4"));
        methodList.add(new MethodInfo("SettingsActivity","onClick","M4"));
        methodList.add(new MethodInfo("SettingsActivity$SettingCell","set","a"));
        methodList.add(new MethodInfo("SettingsActivity$SettingCell$Factory","ofIIIIC","k"));
        methodList.add(new MethodInfo("SettingsActivity$SettingCell$Factory","ofIIIICC","l"));
        methodList.add(new MethodInfo("SettingsActivity$SettingCell$Factory","ofIIIICCC","m"));
        methodList.add(new MethodInfo("PeerStoriesView$StoryItemHolder","allowScreenshots","d"));
        methodList.add(new MethodInfo("StoriesController","hasStories","h1"));
        methodList.add(new MethodInfo("StoriesController","hasStoriesJ","i1"));


        fieldList.add(new FieldInfo("MessagesController","dialogMessage", "Q"));

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
