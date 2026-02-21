package com.my.televip.ui;


import static com.my.televip.MainHook.lpparam;
import static com.my.televip.language.Language.GhostMode;
import static com.my.televip.language.Language.byMustafa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.my.televip.ClientChecker;
import com.my.televip.Clients.Telegraph;
import com.my.televip.Drawable.GhostDrawable;
import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.language.Language;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.Adapters.DrawerLayoutAdapter;
import com.my.televip.virtuals.EventType;
import com.my.televip.virtuals.ui.LaunchActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class addItem {

    public int id_item_add = -1;
    private Constructor<?> itemConstructor;


    @SuppressLint("StaticFieldLeak")
    public static FrameLayout settings;

    private  void openView(Context context){
        if (settings == null) {
            settings = new FrameLayout(MainHook.launchActivity);
        }

        settings.removeAllViews();
        SettingsActivity settingsActivity = new SettingsActivity(context);
        settings.addView(settingsActivity.createView());
        show(settings);
    }

    public void show(View target) {
        LaunchActivity launchActivity = new LaunchActivity(MainHook.launchActivity);
        if (target.getParent() == null) {
            launchActivity.frameLayout.addView(target);
        }

        for (int i = 0; i < launchActivity.frameLayout.getChildCount(); i++) {
            View child = launchActivity.frameLayout.getChildAt(i);
            child.setVisibility(child == target ? View.VISIBLE : View.GONE);
        }

        target.bringToFront();
    }

    public  void newTheme(Class<?> SettingsActivityClass, Class<?> SettingsActivity$SettingCell$FactoryClass){

        AbstractMethodHook fillItemsHook = new AbstractMethodHook() {
            @Override
            protected void afterMethod(final MethodHookParam param) {
                Language.init();
                ArrayList<Object> arrayList = (ArrayList<Object>) param.args[0];
                if (arrayList != null) {

                    int color1 = 0xFFF46F6F;
                    int color2 = 0xFFDF5555;
                    int id = 8353847;

                    Object uitem = XposedHelpers.callStaticMethod(SettingsActivity$SettingCell$FactoryClass, AutomationResolver.resolve("SettingCell$Factory","of", AutomationResolver.ResolverType.Method), id,
                            color1,
                            color2,
                            id,
                            GhostMode,
                            byMustafa);
                    if (id_item_add == -1) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            Object obj = arrayList.get(i);
                            int id_item = XposedHelpers.getIntField(obj, AutomationResolver.resolve("ArrayList","id", AutomationResolver.ResolverType.Field));
                            if (id_item > 0) {
                                arrayList.add(i, uitem);
                                id_item_add = i;
                                break;
                            }
                        }
                    } else {
                        arrayList.add(id_item_add, uitem);
                    }

                }

            }
        };

        Class<?> SettingsActivity$SettingCellClass = XposedHelpers.findClassIfExists(
                AutomationResolver.resolve("org.telegram.ui.SettingsActivity$SettingCell"),
                lpparam.classLoader
        );

        GhostDrawable ghostDrawable = new GhostDrawable();

        XposedHelpers.findAndHookMethod(SettingsActivity$SettingCellClass, "set", int.class, int.class, int.class, CharSequence.class, CharSequence.class, CharSequence.class, new AbstractMethodHook() {
            @Override
            protected void afterMethod(MethodHookParam param) {
                int id = (int) param.args[2];
                if (id == 8353847) {
                    ImageView iconView = (ImageView) XposedHelpers.getObjectField(param.thisObject, "iconView") ;
                    iconView.setImageDrawable(ghostDrawable);
                }
            }
        });

        Class<?> UniversalAdapterClass = XposedHelpers.findClassIfExists("org.telegram.ui.Components.UniversalAdapter", lpparam.classLoader);

        XposedHelpers.findAndHookMethod(SettingsActivityClass, AutomationResolver.resolve("SettingsActivity", "fillItems", AutomationResolver.ResolverType.Method),
                AutomationResolver.merge(AutomationResolver.resolveObject("10",  new Class[]{java.util.ArrayList.class, UniversalAdapterClass}), fillItemsHook));

        AbstractMethodHook onClickHook = new AbstractMethodHook() {
            @Override
            protected void afterMethod(final MethodHookParam param) {
                Object uitem = param.args[0];
                if (uitem != null){
                    int id = XposedHelpers.getIntField(uitem, "id");
                    if (id == 8353847) {
                        Object SettingsActivity = param.thisObject;
                        Context applicationContext = (Context) XposedHelpers.callMethod(SettingsActivity, "getContext");

                        openView(applicationContext);
                    }
                }
            }
        };

        Class<?> UItemClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Components.UItem"), lpparam.classLoader);

        XposedHelpers.findAndHookMethod(
                SettingsActivityClass,
                AutomationResolver.resolve("SettingsActivity", "onClick", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("12", new Class[]{UItemClass, View.class, int.class, float.class, float.class}), onClickHook));
    }

    public  void oldTheme(){
        final Class<?> itemClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Adapters.DrawerLayoutAdapter$Item"), lpparam.classLoader);

        if (itemClass != null) {
            XposedHelpers.findAndHookMethod(
                    loadClass.getDrawerLayoutAdapterClass(),
                    AutomationResolver.resolve("DrawerLayoutAdapter", "resetItems", AutomationResolver.ResolverType.Method),
                    new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(MethodHookParam param) throws Throwable {

                            DrawerLayoutAdapter drawerLayoutAdapter = new DrawerLayoutAdapter(param.thisObject);

                            ArrayList<?> items = drawerLayoutAdapter.getItems();
                            String param2 = "para4";
                            if (ClientChecker.check(ClientChecker.ClientType.Telegraph)){
                                param2 = "10";
                            }

                            if (itemConstructor == null) {
                                itemConstructor = itemClass.getDeclaredConstructor(AutomationResolver.resolveObject(param2, new Class[]{int.class, CharSequence.class, int.class}));
                                itemConstructor.setAccessible(true);
                            }

                            Language.init();
                            Object newItem;
                            if (ClientChecker.check(ClientChecker.ClientType.Telegraph)){
                                newItem = itemConstructor.newInstance(8353847, Language.GhostMode, 8353847, true, null, "");
                            } else {
                                newItem = itemConstructor.newInstance(8353847, GhostMode, EventType.IconSettings());
                            }


                            if (items instanceof ArrayList<?>) {
                                ArrayList<Object> typedItems = (ArrayList<Object>) items;
                                if (!ClientChecker.check(ClientChecker.ClientType.TelegramPlus)) {
                                    typedItems.add(newItem);
                                } else {
                                    typedItems.add(10, newItem);
                                }
                            }
                        }
                    }
            );

            AbstractMethodHook onCreateHook = new AbstractMethodHook() {
                @Override
                protected void afterMethod(final MethodHookParam param) {

                    Object LaunchActivtiy = param.thisObject;
                    if (ClientChecker.check(ClientChecker.ClientType.Telegraph)){
                        LaunchActivtiy = param.args[0];
                    }

                    Object drawerLayoutAdapter = XposedHelpers.getObjectField(LaunchActivtiy, AutomationResolver.resolve("LaunchActivity", "drawerLayoutAdapter", AutomationResolver.ResolverType.Field));
                    if (drawerLayoutAdapter != null) {
                        Object args = param.args[1];
                        if (ClientChecker.check(ClientChecker.ClientType.Telegraph)){
                            args = param.args[2];
                        }

                        int id = (int) XposedHelpers.callMethod(drawerLayoutAdapter, AutomationResolver.resolve("DrawerLayoutAdapter", "getId", AutomationResolver.ResolverType.Method), args);
                        if (id == 8353847) {

                            Object drawerLayoutContainer = XposedHelpers.getObjectField(LaunchActivtiy, AutomationResolver.resolve("LaunchActivity", "drawerLayoutContainer", AutomationResolver.ResolverType.Field));
                            if (drawerLayoutContainer != null) {
                                XposedHelpers.callMethod(drawerLayoutContainer, AutomationResolver.resolve("DrawerLayoutContainer", "closeDrawer", AutomationResolver.ResolverType.Method));
                            }

                            final Context applicationContext = (Context) LaunchActivtiy;
                            openView(applicationContext);
                        }

                    } else {
                        Utils.log("Not found DrawerLayoutAdapter, " + Utils.issue);
                    }
                }
            };
            String para = "para5";
            if (ClientChecker.check(ClientChecker.ClientType.Telegraph)){
                para = "12";
                Telegraph.onBindViewHolderHook();
            }
            Method onCreateMethod = null;
            for (Method method : loadClass.getLaunchActivityClass().getDeclaredMethods()) {
                if (Arrays.equals(method.getParameterTypes(), AutomationResolver.resolveObject(para, new Class[]{android.view.View.class,int.class, float.class, float.class}))) {
                    onCreateMethod = method;
                }
            }

            if (onCreateMethod == null) {
                Utils.log("Failed to hook onCreateMethod! Reason: No method found, " + Utils.issue);
                return;
            }
            XposedBridge.hookMethod(onCreateMethod, onCreateHook);
        }

    }
}

