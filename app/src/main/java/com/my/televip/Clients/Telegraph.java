package com.my.televip.Clients;

import static com.my.televip.MainHook.lpparam;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.my.televip.LoaderParameter;
import com.my.televip.MainHook;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.obfuscate.struct.ClassInfo;
import com.my.televip.obfuscate.struct.FieldInfo;
import com.my.televip.obfuscate.struct.MethodInfo;
import com.my.televip.virtuals.Adapters.DrawerLayoutAdapter;
import com.my.televip.virtuals.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.XposedHelpers;

public class Telegraph {
    private static final List<ClassInfo> classList = new ArrayList<>();
    private static final List<FieldInfo> fieldList = new ArrayList<>();
    private static final List<MethodInfo> methodList = new ArrayList<>();

    static {
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

        methodList.add(new MethodInfo("AlertDialog","setTitle", "H"));
        methodList.add(new MethodInfo("AlertDialog","setView", "O"));
        methodList.add(new MethodInfo("AlertDialog","setPositiveButton", "F"));
        methodList.add(new MethodInfo("AlertDialog","setNegativeButton", "z"));
        methodList.add(new MethodInfo("AlertDialog","show", "R"));
        methodList.add(new MethodInfo("AlertDialog$OnButtonClickListener","onClick", "a"));
        methodList.add(new MethodInfo("LaunchActivity","lambda$onCreate$8", "C1"));
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

        ParameterResolver.register("para1",new Class[]{Long.class});
        ParameterResolver.register("para2",new Class[]{int.class, int.class, CharSequence.class});
        ParameterResolver.register("para3",new Class[]{int.class});
        ParameterResolver.register("para4",new Class[]{int.class, CharSequence.class, int.class});
        ParameterResolver.register("para5",new Class[]{android.view.View.class,int.class, float.class, float.class});
        ParameterResolver.register("para6",new Class[]{boolean.class, boolean.class});
        ParameterResolver.register("para7",new Class[]{int.class, int.class, CharSequence.class});
        ParameterResolver.register("para8",new Class[]{boolean.class});
        ParameterResolver.register("para9",new Class[]{long.class, ArrayList.class, boolean.class, boolean.class, int.class, int.class,});
        ParameterResolver.register("para10",new Class[]{int.class, Object[].class});
        ParameterResolver.register("para11",new Class[]{long.class, ArrayList.class, boolean.class, int.class, int.class});
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
    public static class loadParameter implements LoaderParameter {

        public void loadParameter1() {
                Class<?> classStories$StoryItem = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$StoryItem"), MainHook.lpparam.classLoader);
                Class<?> classsStories$PeerStories = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$PeerStories"), MainHook.lpparam.classLoader);
                ParameterResolver.register("1", new Class[]{classsStories$PeerStories, classStories$StoryItem, boolean.class});
        }
        public void loadParameter2() {
                Class<?> readTaskClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController$ReadTask"), lpparam.classLoader);
                ParameterResolver.register("2", new Class[]{readTaskClass});

        }
        public void loadParameter3() {
                Class<?> TLRPC$ChatClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Chat"), lpparam.classLoader);
                ParameterResolver.register("3", new Class[]{TLRPC$ChatClass});

        }
        public void loadParameter4() {
                Class<?> tlObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLObject"), lpparam.classLoader);
                Class<?> requestDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegate"), lpparam.classLoader);
                Class<?> requestDelegateTimestampClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegateTimestamp"), lpparam.classLoader);
                Class<?> quickAckDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.QuickAckDelegate"), lpparam.classLoader);
                Class<?> writeToSocketDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.WriteToSocketDelegate"), lpparam.classLoader);

                 ParameterResolver.register("4", new Class[]{tlObjectClass, requestDelegateClass, requestDelegateTimestampClass, quickAckDelegateClass, writeToSocketDelegateClass, int.class, int.class, int.class, boolean.class, int.class});

        }
        public void loadParameter5() {
            ParameterResolver.register("5", new Class[]{com.my.televip.loadClass.getMessageObjectClass(), boolean.class});
        }
        public void loadParameter6() {
            ParameterResolver.register("6", new Class[]{com.my.televip.loadClass.getMessageObjectClass()});
        }
        public void loadParameter7() {
                Class<?> photoViewerproviderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.PhotoViewer$PhotoViewerProvider"), lpparam.classLoader);
                ParameterResolver.register("7", new Class[]{com.my.televip.loadClass.getMessageObjectClass(), photoViewerproviderClass, Runnable.class, Runnable.class});
        }
        public void loadParameter8() {
                Class<?> Userlass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$User"), lpparam.classLoader);
                ParameterResolver.register("8", new Class[]{Userlass});
        }
        public void loadParameter9() {
                Class<?> conClass = XposedHelpers.findClassIfExists("android.content.Context", lpparam.classLoader);
                ParameterResolver.register("9", new Class[]{conClass});
        }
        public void loadParameter10() {
            Class<?> mainMenuItem = XposedHelpers.findClassIfExists("org.telegram.messenger.GraphDrawerMenuController$MainMenuItem", lpparam.classLoader);
            ParameterResolver.register("10", new Class[]{int.class, String.class, int.class, boolean.class, mainMenuItem, String.class});
        }

        public void loadParameter11() {
            Class<?> EncryptedChatClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$EncryptedChat"), lpparam.classLoader);
            Class<?> TLObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLObject"), lpparam.classLoader);
            ParameterResolver.register("11", new Class[]{ArrayList.class,
                    ArrayList.class,
                    EncryptedChatClass, // الفئة المحددة
                    long.class,
                    boolean.class,
                    int.class,
                    boolean.class,
                    long.class,
                    TLObjectClass,
                    int.class,
                    boolean.class,
                    int.class,});
        }
        public void loadParameter12() {
            Class<?> LaunchActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.LaunchActivity"), lpparam.classLoader);
            ParameterResolver.register("12", new Class[]{LaunchActivityClass,android.view.View.class,int.class, float.class, float.class});

        }
    }

    public static void onBindViewHolderHook(){
        Class<?> viewHolder = XposedHelpers.findClassIfExists("androidx.recyclerview.widget.RecyclerView$ViewHolder", lpparam.classLoader);

        XposedHelpers.findAndHookMethod(
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

                        if (id == 8353847) {
                            Object itemView = XposedHelpers.getObjectField(holder, "itemView");
                            XposedHelpers.callMethod(itemView, "e", 0, str, EventType.IconSettings());
                            XposedHelpers.callMethod(itemView, "setInfo", (Object) null);
                            XposedHelpers.callMethod(itemView, "c", (Object) null, 0);
                            param.setResult(null);
                        }
                    }
                });

    }

}
