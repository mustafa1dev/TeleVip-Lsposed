package com.my.televip.Clients;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.hooks.HMethod;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.struct.ClassInfo;
import com.my.televip.obfuscate.struct.FieldInfo;
import com.my.televip.obfuscate.struct.MethodInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.televip.base.MethodReplacement;

public class Telegraph {
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
            objectList.put(name,classes);
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
        classList.add(new ClassInfo("org.telegram.messenger.AndroidUtilities", "org.telegram.messenger.COm4"));
        classList.add(new ClassInfo("org.telegram.messenger.ApplicationLoader", "org.telegram.messenger.COm5"));
        classList.add(new ClassInfo("org.telegram.messenger.LocaleController", "org.telegram.messenger.o9"));
        classList.add(new ClassInfo("org.telegram.messenger.MessageObject", "org.telegram.messenger.vh"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesController", "org.telegram.messenger.Jr"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesStorage", "org.telegram.messenger.px"));
        classList.add(new ClassInfo("org.telegram.messenger.NotificationCenter", "org.telegram.messenger.Ix"));
        classList.add(new ClassInfo("org.telegram.messenger.NotificationsController", "org.telegram.messenger.Vy"));
        classList.add(new ClassInfo("org.telegram.messenger.SharedConfig", "org.telegram.messenger.WD"));
        classList.add(new ClassInfo("org.telegram.messenger.UserConfig", "org.telegram.messenger.qG"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.ActionBar", "org.telegram.ui.ActionBar.cOn"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.ActionBar$ActionBarMenuOnItemClick", "org.telegram.ui.ActionBar.cOn$coN"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.ActionBarMenuItem", "org.telegram.ui.ActionBar.CoM2"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener", "org.telegram.ui.ActionBar.AlertDialog$coN"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.Theme", "org.telegram.ui.ActionBar.A"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.ChatMessageCell", "org.telegram.ui.Cells.coM7"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.HeaderCell", "org.telegram.ui.Cells.LPT6"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.ShadowSectionCell", "org.telegram.ui.Cells.T"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.TextCheckCell", "org.telegram.ui.Cells.P0"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.TextSettingsCell", "org.telegram.ui.Cells.r1"));
        classList.add(new ClassInfo("org.telegram.ui.ChatActivity", "org.telegram.ui.Oh"));
        classList.add(new ClassInfo("org.telegram.ui.ChatActivity$ChatMessageCellDelegate", "org.telegram.ui.Oh$Lpt7"));
        classList.add(new ClassInfo("org.telegram.ui.Components.UniversalAdapter", "org.telegram.ui.Components.NR"));
        classList.add(new ClassInfo("org.telegram.ui.PhotoViewer$PhotoViewerProvider", "org.telegram.ui.PhotoViewer$lPt1"));
        classList.add(new ClassInfo("org.telegram.ui.PhotoViewer$PlaceProviderObject", "org.telegram.ui.PhotoViewer$LPt1"));
        classList.add(new ClassInfo("org.telegram.ui.Stories.PeerStoriesView$StoryItemHolder", "org.telegram.ui.Stories.n$coM2"));
        classList.add(new ClassInfo("org.telegram.ui.Stories.StoriesController", "org.telegram.ui.Stories.A2"));

//Field
        fieldList.add(new FieldInfo("ApplicationLoader","applicationContext", "b"));
        fieldList.add(new FieldInfo("LocaleController","currentLocale", "y"));
        fieldList.add(new FieldInfo("LocaleController","isRTL", "U"));
        fieldList.add(new FieldInfo("MessagesController","dialogMessage", "C"));
        fieldList.add(new FieldInfo("MessagesController","dialogMessagesByIds", "F"));
        fieldList.add(new FieldInfo("NotificationCenter","messagesDeleted", "c0"));
        fieldList.add(new FieldInfo("NotificationCenter","tlSchemeParseException", "P4"));
        fieldList.add(new FieldInfo("UserConfig","clientUserId", "i"));
        fieldList.add(new FieldInfo("UserConfig","selectedAccount", "k0"));
        fieldList.add(new FieldInfo("Theme","chat_timePaint", "x3"));
        fieldList.add(new FieldInfo("ChatMessageCell","currentTimeString", "Mb"));
        fieldList.add(new FieldInfo("ChatMessageCell","timeTextWidth", "b0"));
        fieldList.add(new FieldInfo("ChatMessageCell","timeWidth", "Lb"));
        fieldList.add(new FieldInfo("UItem","id", "d"));
        fieldList.add(new FieldInfo("UItem","text", "l"));
        fieldList.add(new FieldInfo("UItem","subtext", "m"));
        fieldList.add(new FieldInfo("LaunchActivity","frameLayout", "L"));
        fieldList.add(new FieldInfo("PhotoViewer","galleryButton", "h0"));
        fieldList.add(new FieldInfo("PhotoViewer$PlaceProviderObject","imageReceiver", "a"));
        fieldList.add(new FieldInfo("SettingsActivity$SettingCell","iconView", "d"));

//Method
        methodList.add(new MethodInfo("AndroidUtilities","isTabletInternal","z4"));
        methodList.add(new MethodInfo("LocaleController","formatShortNumber","D0"));
        methodList.add(new MethodInfo("LocaleController","formatYearMont","W0"));
        methodList.add(new MethodInfo("LocaleController","getInstance","F1"));
        methodList.add(new MethodInfo("MessagesController","checkPromoInfoInternal","Q9"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJIZI","pa"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJIZIZ","qa"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJZIZJOI","ra"));
        methodList.add(new MethodInfo("MessagesController","deleteMessagesAAOJZIZJOIZI","sa"));
        methodList.add(new MethodInfo("MessagesController","getGlobalMainSettings","Ub"));
        methodList.add(new MethodInfo("MessagesController","getInputChannelJ","Yb"));
        methodList.add(new MethodInfo("MessagesController","getInputChannelO","Zb"));
        methodList.add(new MethodInfo("MessagesController","getInputChannelO2","ac"));
        methodList.add(new MethodInfo("MessagesController","getInstance","mc"));
        methodList.add(new MethodInfo("MessagesController","isChatNoForwardsJ","Ed"));
        methodList.add(new MethodInfo("MessagesController","isChatNoForwardsO","Fd"));
        methodList.add(new MethodInfo("MessagesController","processNewDifferenceParams","Yf"));
        methodList.add(new MethodInfo("MessagesController","removePromoDialog","Hg"));
        methodList.add(new MethodInfo("MessagesController","storiesEnabled","Mh"));
        methodList.add(new MethodInfo("MessagesController","storyEntitiesAllowed","Nh"));
        methodList.add(new MethodInfo("MessagesController","storyEntitiesAllowedO","Oh"));
        methodList.add(new MethodInfo("MessagesStorage","getDatabase","G5"));
        methodList.add(new MethodInfo("MessagesStorage","getInstance","U5"));
        methodList.add(new MethodInfo("MessagesStorage","getStorageQueue","n6"));
        methodList.add(new MethodInfo("MessagesStorage","markMessagesAsDeletedJAZZII","u7"));
        methodList.add(new MethodInfo("MessagesStorage","markMessagesAsDeletedJIZZ","t7"));
        methodList.add(new MethodInfo("MessagesStorage","putMessagesAZZZIIJ","X7"));
        methodList.add(new MethodInfo("MessagesStorage","putMessagesAZZZIZIJ","Y7"));
        methodList.add(new MethodInfo("MessagesStorage","putMessagesOJIIZIJ","Z7"));
        methodList.add(new MethodInfo("NotificationCenter","postNotificationName","C"));
        methodList.add(new MethodInfo("NotificationsController","removeDeletedMessagesFromNotifications","s1"));
        methodList.add(new MethodInfo("SharedConfig","isAppUpdateAvailable","p"));
        methodList.add(new MethodInfo("UserConfig","getClientUserId","v"));
        methodList.add(new MethodInfo("UserConfig","getCurrentUser","w"));
        methodList.add(new MethodInfo("UserConfig","isPremium","N"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIC","a0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIIC","d0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIICO","e0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIICZ","f0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIIDCZZ","b0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIIDCZZO","c0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIV","Z"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemIVII","g0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","addSubItemVII","h0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","lazilyAddSubItemIDC","N0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","lazilyAddSubItemIIC","M0"));
        methodList.add(new MethodInfo("ActionBarMenuItem","lazilyAddSubItemIIDCZZ","L0"));
        methodList.add(new MethodInfo("AlertDialog$Builder","create","c"));
        methodList.add(new MethodInfo("AlertDialog$Builder","getDismissRunnable","f"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setMessage","x"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setNegativeButton","z"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setNeutralButton","A"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setPositiveButton","F"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setTitle","H"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setViewV","O"));
        methodList.add(new MethodInfo("AlertDialog$Builder","setViewVI","P"));
        methodList.add(new MethodInfo("AlertDialog$Builder","show","R"));
        methodList.add(new MethodInfo("AlertDialog$OnButtonClickListener","onClick","a"));
        methodList.add(new MethodInfo("Theme","isCurrentThemeDark","V3"));
        methodList.add(new MethodInfo("ChatMessageCell","measureTime","V6"));
        methodList.add(new MethodInfo("HeaderCell","setTextC","setText"));
        methodList.add(new MethodInfo("HeaderCell","setTextCZ","d"));
        methodList.add(new MethodInfo("TextCheckCell","isChecked","d"));
        methodList.add(new MethodInfo("TextCheckCell","setTextAndCheck","i"));
        methodList.add(new MethodInfo("TextCheckCell","setTextAndValueAndCheck","j"));
        methodList.add(new MethodInfo("TextSettingsCell","setText","c"));
        methodList.add(new MethodInfo("TextSettingsCell","setTextAndValueCCZ","g"));
        methodList.add(new MethodInfo("TextSettingsCell","setTextAndValueCCZZ","h"));
        methodList.add(new MethodInfo("ChatActivity","createPinnedMessageView","Db"));
        methodList.add(new MethodInfo("ChatActivity","hasSelectedNoforwardsMessage","Oc"));
        methodList.add(new MethodInfo("ChatActivity","processSelectedOption","we"));
        methodList.add(new MethodInfo("ChatActivity","sendSecretMediaDelete","Se"));
        methodList.add(new MethodInfo("ChatActivity","sendSecretMessageRead","Te"));
        methodList.add(new MethodInfo("ChatActivity","updatePinnedMessageViewZ","rg"));
        methodList.add(new MethodInfo("ChatActivity","updatePinnedMessageViewZI","sg"));
        methodList.add(new MethodInfo("ChatActivity$ChatMessageCellDelegate","didPressImage","F"));
        methodList.add(new MethodInfo("PhotoViewer","getInstance","Kc"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoAIJJJO","de"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoAIO","ee"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoIOO","ce"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOIOJJJO","fe"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOJJJOZ","ge"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOO","oe"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOOJJJO","ie"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOOO","je"));
        methodList.add(new MethodInfo("PhotoViewer","openPhotoOOOOAAAIOOJJJZOI","he"));
        methodList.add(new MethodInfo("PhotoViewer","setIsAboutToSwitchToIndexIZZ","Ye"));
        methodList.add(new MethodInfo("PhotoViewer","setIsAboutToSwitchToIndexIZZZ","ff"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityA","mf"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityAO","of"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityAOO","nf"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityO","pf"));
        methodList.add(new MethodInfo("PhotoViewer","setParentActivityOO","qf"));
        methodList.add(new MethodInfo("SettingsActivity","fillItems","r0"));
        methodList.add(new MethodInfo("SettingsActivity","onClick","v0"));
        methodList.add(new MethodInfo("SettingsActivity$SettingCell","set","a"));
        methodList.add(new MethodInfo("PeerStoriesView$StoryItemHolder","allowScreenshots","d"));
        methodList.add(new MethodInfo("StoriesController","hasStories","h1"));
        methodList.add(new MethodInfo("StoriesController","hasStoriesJ","i1"));

        methodAlias.put("MessagesController#storyEntitiesAllowed2", "storyEntitiesAllowedO");
        methodAlias.put("StoriesController#hasStories2", "hasStoriesJ");
        methodAlias.put("PhotoViewer#setIsAboutToSwitchToIndex", "setIsAboutToSwitchToIndexIZZZ");
        methodAlias.put("MessagesStorage#markMessagesAsDeleted", "markMessagesAsDeletedJAZZII");
        methodAlias.put("MessagesController#deleteMessages", "deleteMessagesAAOJZIZJOIZI");
        methodAlias.put("TextSettingsCell#setTextAndValue", "setTextAndValueCCZZ");
        methodAlias.put("MessagesStorage#putMessages", "putMessagesOJIIZIJ");
        methodAlias.put("MessagesController#isChatNoForwards", "isChatNoForwardsO");
        methodAlias.put("ChatActivity#updatePinnedMessageView", "updatePinnedMessageViewZI");
        methodAlias.put("PhotoViewer#openPhoto", "openPhotoOJJJOZ");
        methodAlias.put("HeaderCell#setText", "setTextC");
        methodAlias.put("AlertDialog$Builder#setView", "setViewV");

        ParameterResolver.register("fillMessageMenu",new Class[]{ClassLoad.getClass(ClassNames.MESSAGE_OBJECT), ClassLoad.getClass(ClassNames.MESSAGE_OBJECT), ArrayList.class, ArrayList.class, ArrayList.class});
    }

    public static void removeAd(){
        try {
            Class<?> connectionsManager = ClassLoad.getClass(ClassNames.CONNECTIONS_MANAGER);
            if (connectionsManager != null) {
                HMethod.hookMethod(connectionsManager, "native_expireFile", long.class, new MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) {
                        return false;
                    }
                });
                HMethod.hookMethod(connectionsManager, "native_daysFile", long.class, new MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) {
                        return 999;
                    }
                });
                HMethod.hookMethod(connectionsManager, "native_checkLicense", long.class, new MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) {
                        return true;
                    }
                });
                HMethod.hookMethod(connectionsManager, "native_removeInstance", int.class, boolean.class, new MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) {
                        return true;
                    }
                });
            }
        } catch (Throwable t){
            Logger.e(t);
        }
    }

}
