package com.my.televip.Clients;

import static com.my.televip.MainHook.lpparam;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.struct.ClassInfo;
import com.my.televip.obfuscate.struct.FieldInfo;
import com.my.televip.obfuscate.struct.MethodInfo;
import com.my.televip.virtuals.Adapters.DrawerLayoutAdapter;
import com.my.televip.virtuals.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

public class Telegraph {
    private static final List<ClassInfo> classList = new ArrayList<>();
    private static final List<FieldInfo> fieldList = new ArrayList<>();
    private static final List<MethodInfo> methodList = new ArrayList<>();



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
        classList.add(new ClassInfo("org.telegram.messenger.ApplicationLoader", "org.telegram.messenger.cOM4"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener", "org.telegram.ui.ActionBar.AlertDialog$COn"));
        classList.add(new ClassInfo("org.telegram.ui.Adapters.DrawerLayoutAdapter$Item", "org.telegram.messenger.GraphDrawerMenuController$MainMenuItem"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.Theme", "org.telegram.ui.ActionBar.q"));
        classList.add(new ClassInfo("org.telegram.messenger.LocaleController", "org.telegram.messenger.f9"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.ChatMessageCell","org.telegram.ui.Cells.Com6"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesController","org.telegram.messenger.Iq"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesStorage","org.telegram.messenger.bw"));
        classList.add(new ClassInfo("org.telegram.messenger.NotificationsController","org.telegram.messenger.Ex"));
        classList.add(new ClassInfo("org.telegram.messenger.NotificationCenter","org.telegram.messenger.vw"));
        classList.add(new ClassInfo("org.telegram.messenger.MessageObject","org.telegram.messenger.Yg"));
        classList.add(new ClassInfo("org.telegram.messenger.UserConfig","org.telegram.messenger.uE"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesController$ReadTask","org.telegram.messenger.Iq$NUl"));
        classList.add(new ClassInfo("org.telegram.ui.Stories.StoriesController","org.telegram.ui.Stories.t2"));
        classList.add(new ClassInfo("org.telegram.ui.Stories.PeerStoriesView$StoryItemHolder","org.telegram.ui.Stories.j$coM2"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.BaseFragment","org.telegram.ui.ActionBar.cOm7"));
        classList.add(new ClassInfo("org.telegram.ui.PhotoViewer$PhotoViewerProvider","org.telegram.ui.PhotoViewer$COM9"));
        classList.add(new ClassInfo("org.telegram.ui.ChatActivity","org.telegram.ui.Ng"));
        classList.add(new ClassInfo("org.telegram.ui.ChatActivity$ChatActivityEnterViewDelegate","org.telegram.ui.Ng$LPT6"));
        classList.add(new ClassInfo("org.telegram.messenger.BaseController","org.telegram.messenger.com7"));
        classList.add(new ClassInfo("org.telegram.messenger.AndroidUtilities","org.telegram.messenger.cOM3"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.TextCheckCell","org.telegram.ui.Cells.U0"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.ShadowSectionCell","org.telegram.ui.Cells.Y"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.HeaderCell","org.telegram.ui.Cells.lpt8"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.TextSettingsCell","org.telegram.ui.Cells.v1"));

        fieldList.add(new FieldInfo("ApplicationLoader", "applicationContext", "b"));
        fieldList.add(new FieldInfo("LaunchActivity", "drawerLayoutAdapter", "M"));
        fieldList.add(new FieldInfo("LaunchActivity", "drawerLayoutContainer", "L"));
        fieldList.add(new FieldInfo("LocaleController", "currentLocale", "v"));
        fieldList.add(new FieldInfo("ChatMessageCell","currentMessageObject","T6"));
        fieldList.add(new FieldInfo("MessagesController","dialogMessage","C"));
        fieldList.add(new FieldInfo("MessagesController","dialogMessagesByIds","F"));
        fieldList.add(new FieldInfo("NotificationCenter","messagesDeleted","c0"));
        fieldList.add(new FieldInfo("MessagesController$ReadTask","dialogId","a"));
        fieldList.add(new FieldInfo("Theme","chat_timePaint","o3"));
        fieldList.add(new FieldInfo("ChatMessageCell","currentTimeString","Ya"));
        fieldList.add(new FieldInfo("ChatMessageCell","timeTextWidth","Va"));
        fieldList.add(new FieldInfo("ChatMessageCell","timeWidth","Ua"));
        fieldList.add(new FieldInfo("ProfileActivity","userId","L0"));
        fieldList.add(new FieldInfo("LaunchActivity","frameLayout","I"));
        fieldList.add(new FieldInfo("Theme","key_windowBackgroundGray","Z7"));
        fieldList.add(new FieldInfo("Theme","key_actionBarDefault","q9"));
        fieldList.add(new FieldInfo("Theme","key_actionBarDefaultTitle","t9"));
        fieldList.add(new FieldInfo("Theme","key_actionBarDefaultIcon","t9"));
        fieldList.add(new FieldInfo("Theme","key_windowBackgroundWhite","d7"));
        fieldList.add(new FieldInfo("Theme","key_switchTrackBlueChecked","O7"));
        fieldList.add(new FieldInfo("ChatActivity","selectedObject","W4"));
        fieldList.add(new FieldInfo("MessagesStorage","database","b"));
        fieldList.add(new FieldInfo("UserConfig","clientUserId","i"));

        methodList.add(new MethodInfo("AlertDialog","setTitle", "H"));
        methodList.add(new MethodInfo("AlertDialog","setView", "O"));
        methodList.add(new MethodInfo("AlertDialog","setMessage", "G"));
        methodList.add(new MethodInfo("AlertDialog","setPositiveButton", "F"));
        methodList.add(new MethodInfo("AlertDialog","setNegativeButton", "z"));
        methodList.add(new MethodInfo("AlertDialog","setNeutralButton", "A"));
        methodList.add(new MethodInfo("AlertDialog","show", "R"));
        methodList.add(new MethodInfo("AlertDialog$OnButtonClickListener","onClick", "a"));
        methodList.add(new MethodInfo("Theme","getActiveTheme", "T3"));
        methodList.add(new MethodInfo("Theme","isDark", "J"));
        methodList.add(new MethodInfo("Theme","getEventType", "O2"));
        methodList.add(new MethodInfo("DrawerLayoutContainer", "closeDrawer", "e"));
        methodList.add(new MethodInfo("LocaleController", "getInstance", "w1"));
        methodList.add(new MethodInfo("ChatMessageCell","measureTime","w6"));
        methodList.add(new MethodInfo("MessagesController","deleteMessages","H9"));
        methodList.add(new MethodInfo("MessagesStorage","markMessagesAsDeleted","j7"));
        methodList.add(new MethodInfo("MessagesStorage","markMessagesAsDeletedInternal","m7"));
        methodList.add(new MethodInfo("MessagesStorage","updateDialogsWithDeletedMessagesInternal","f9"));
        methodList.add(new MethodInfo("MessagesStorage","updateDialogsWithDeletedMessages","e9"));
        methodList.add(new MethodInfo("NotificationsController","removeDeletedMessagesFromNotifications","p1"));
        methodList.add(new MethodInfo("NotificationCenter","postNotificationName","B"));
        methodList.add(new MethodInfo("UserConfig","isPremium","O"));
        methodList.add(new MethodInfo("MessagesController","completeReadTask","u9"));
        methodList.add(new MethodInfo("MessagesController","getUser","mc"));
        methodList.add(new MethodInfo("MessagesStorage","getDatabase","x5"));
        methodList.add(new MethodInfo("StoriesController","markStoryAsRead","G1"));
        methodList.add(new MethodInfo("StoriesController","hasStories","g1"));
        methodList.add(new MethodInfo("StoriesController","hasStories2","h1"));
        methodList.add(new MethodInfo("MessagesController","storiesEnabled","Dg"));
        methodList.add(new MethodInfo("MessagesController","storyEntitiesAllowed","Cg"));
        methodList.add(new MethodInfo("MessagesController","storyEntitiesAllowed2","Eg"));
        methodList.add(new MethodInfo("PeerStoriesView$StoryItemHolder","allowScreenshots","d"));
        methodList.add(new MethodInfo("UserConfig","getClientUserId","v"));
        methodList.add(new MethodInfo("ProfileActivity","updateProfileData","ye"));
        methodList.add(new MethodInfo("MessagesController","isChatNoForwards","Jc"));
        methodList.add(new MethodInfo("ChatActivity","sendSecretMessageRead","Ax"));
        methodList.add(new MethodInfo("ChatActivity","sendSecretMediaDelete","zx"));
        methodList.add(new MethodInfo("TextCheckCell","setTextAndValueAndCheck","j"));
        methodList.add(new MethodInfo("TextCheckCell","setTextAndCheck","i"));
        methodList.add(new MethodInfo("TextCheckCell","isChecked","d"));
        methodList.add(new MethodInfo("AndroidUtilities","isTablet","k4"));
        methodList.add(new MethodInfo("Theme","getColor","u2"));
        methodList.add(new MethodInfo("TextSettingsCell","setText","c"));
        methodList.add(new MethodInfo("LocaleController","formatShortNumber","x0"));
        methodList.add(new MethodInfo("ChatActivity","fillMessageMenu","Tr"));
        methodList.add(new MethodInfo("ChatActivity","processSelectedOption","Nw"));
        methodList.add(new MethodInfo("MessagesStorage","putMessages","N7"));

        Class<?> mainMenuItem = XposedHelpers.findClassIfExists("org.telegram.messenger.GraphDrawerMenuController$MainMenuItem", lpparam.classLoader);
        ParameterResolver.register("item", new Class[]{int.class, String.class, int.class, boolean.class, mainMenuItem, String.class});

        ParameterResolver.register("onCreateMethod", new Class[]{loadClass.getLaunchActivityClass(),android.view.View.class,int.class, float.class, float.class});

        ParameterResolver.register("fillMessageMenu", new Class[]{loadClass.getMessageObjectClass(), loadClass.getMessageObjectClass(), ArrayList.class, ArrayList.class, ArrayList.class});

    }

    public static void onBindViewHolderHook() {
        try {
            Class<?> viewHolder = XposedHelpers.findClassIfExists("androidx.recyclerview.widget.RecyclerView$ViewHolder", lpparam.classLoader);

            findAndHookMethod(
                    loadClass.getDrawerLayoutAdapterClass(),
                    "onBindViewHolder", viewHolder, int.class, new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(MethodHookParam param) {
                            Object holder = param.args[0];
                            DrawerLayoutAdapter drawerLayoutAdapter = new DrawerLayoutAdapter(param.thisObject);

                            int position = (int) param.args[1];
                            ArrayList<?> items = drawerLayoutAdapter.getItems();

                            int index = position - 2;

                            if (index < 0 || index >= items.size()) {
                                return;
                            }

                            Object mainMenuItem = items.get(index);

                            String str = (String) XposedHelpers.getObjectField(mainMenuItem, "b");
                            int id = XposedHelpers.getIntField(mainMenuItem, "c");

                            if (id == MainHook.id) {
                                Object itemView = XposedHelpers.getObjectField(holder, "itemView");
                                XposedHelpers.callMethod(itemView, "e", 0, str, EventType.getIconSettings());
                                XposedHelpers.callMethod(itemView, "setInfo", (Object) null);
                                XposedHelpers.callMethod(itemView, "c", (Object) null, 0);
                                param.setResult(null);
                            }
                        }
                    });
        } catch (Throwable t){
            Utils.log(t);
        }
    }

    public static void removeAd(){
        try {
            Class<?> connectionsManager = XposedHelpers.findClassIfExists("org.telegram.tgnet.ConnectionsManager", lpparam.classLoader);
            if (connectionsManager != null) {
                findAndHookMethod(connectionsManager, "native_expireFile", long.class, new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) {
                        return false;
                    }
                });
                findAndHookMethod(connectionsManager, "native_daysFile", long.class, new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) {
                        return 999;
                    }
                });
                findAndHookMethod(connectionsManager, "native_checkLicense", long.class, new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) {
                        return true;
                    }
                });
                findAndHookMethod(connectionsManager, "native_removeInstance", int.class, boolean.class, new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) {
                        return true;
                    }
                });
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
