package com.my.televip.Clients;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.LoaderParameter;
import com.my.televip.MainHook;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.obfuscate.struct.ClassInfo;
import com.my.televip.obfuscate.struct.FieldInfo;
import com.my.televip.obfuscate.struct.MethodInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.XposedHelpers;

public class forkgramBeta {
    private static final List<ClassInfo> classList = new ArrayList<>();
    private static final List<FieldInfo> fieldList = new ArrayList<>();
    private static final List<MethodInfo> methodList = new ArrayList<>();

    static {

        classList.add(new ClassInfo("org.telegram.ui.ChatActivity$16", "org.telegram.ui.ChatActivity$14"));
        methodList.add(new MethodInfo("LaunchActivity", "lambda$onCreate$8", "lambda$onCreate$7"));

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
        public void loadParameter10() {}

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
                    int.class});
        }
        public void loadParameter12() {}

        public void loadParameter13() {
            Class<?> browser$Progress = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.browser.Browser$Progress"), lpparam.classLoader);
            ParameterResolver.register("13", new Class[]{boolean.class ,browser$Progress});
        }
    }
}
