package com.my.televip.ui;


import static com.my.televip.MainHook.lpparam;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.my.televip.ClientChecker;
import com.my.televip.Drawable.GhostDrawable;
import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
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

    private void openView(){
        if (settings == null) {
            settings = new FrameLayout(MainHook.launchActivity);
        }

        settings.removeAllViews();
        SettingsActivity settingsActivity = new SettingsActivity(MainHook.launchActivity);
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
                ArrayList<Object> arrayList = (ArrayList<Object>) param.args[0];
                if (arrayList != null) {

                    int color1 = 0xFFF46F6F;
                    int color2 = 0xFFDF5555;

                    Object uItem = XposedHelpers.callStaticMethod(SettingsActivity$SettingCell$FactoryClass, AutomationResolver.resolve("SettingCell$Factory","of", AutomationResolver.ResolverType.Method), MainHook.id,
                            color1,
                            color2,
                            MainHook.id,
                            Translator.get(Keys.GhostMode),
                            Translator.get(Keys.ByMustafa));
                    if (id_item_add == -1) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            Object obj = arrayList.get(i);
                            int id_item = XposedHelpers.getIntField(obj, AutomationResolver.resolve("UItem","id", AutomationResolver.ResolverType.Field));
                            if (id_item > 0) {
                                arrayList.add(i, uItem);
                                id_item_add = i;
                                break;
                            }
                        }
                    } else {
                        arrayList.add(id_item_add, uItem);
                    }

                }
            }
        };

        Class<?> SettingsActivity$SettingCellClass = XposedHelpers.findClassIfExists(
                AutomationResolver.resolve("org.telegram.ui.SettingsActivity$SettingCell"),
                lpparam.classLoader
        );

        GhostDrawable ghostDrawable = new GhostDrawable();

        XposedHelpers.findAndHookMethod(SettingsActivity$SettingCellClass, AutomationResolver.resolve("SettingsActivity$SettingCell","set", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("set", new Class[]{int.class, int.class, int.class, CharSequence.class, CharSequence.class, CharSequence.class}), new AbstractMethodHook() {
            @Override
            protected void afterMethod(MethodHookParam param) {
                int id = (int) param.args[2];
                if (id == MainHook.id) {
                    ImageView iconView = (ImageView) XposedHelpers.getObjectField(param.thisObject, AutomationResolver.resolve("SettingsActivity$SettingCell", "iconView", AutomationResolver.ResolverType.Field));
                    iconView.setImageDrawable(ghostDrawable);
                }
            }
        }));

        Class<?> UniversalAdapterClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Components.UniversalAdapter"), lpparam.classLoader);

        XposedHelpers.findAndHookMethod(SettingsActivityClass, AutomationResolver.resolve("SettingsActivity", "fillItems", AutomationResolver.ResolverType.Method),
                AutomationResolver.merge(AutomationResolver.resolveObject("fillItems",  new Class[]{java.util.ArrayList.class, UniversalAdapterClass}), fillItemsHook));

        AbstractMethodHook onClickHook = new AbstractMethodHook() {
            @Override
            protected void afterMethod(final MethodHookParam param) {
                Object uItem = param.args[0];
                if (uItem != null){
                    int id = XposedHelpers.getIntField(uItem, AutomationResolver.resolve("UItem","id", AutomationResolver.ResolverType.Field));
                    if (id == MainHook.id) {
                        openView();
                    }
                }
            }
        };

        Class<?> UItemClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Components.UItem"), lpparam.classLoader);

        XposedHelpers.findAndHookMethod(
                SettingsActivityClass,
                AutomationResolver.resolve("SettingsActivity", "onClick", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("onClick", new Class[]{UItemClass, View.class, int.class, float.class, float.class}), onClickHook));
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

                            if (itemConstructor == null) {
                                itemConstructor = itemClass.getDeclaredConstructor(AutomationResolver.resolveObject("item", new Class[]{int.class, CharSequence.class, int.class}));
                                itemConstructor.setAccessible(true);
                            }

                            Object newItem = itemConstructor.newInstance(MainHook.id, Translator.get(Keys.GhostMode), EventType.getIconSettings());

                            if (items instanceof ArrayList<?>) {
                                ArrayList<Object> typedItems = (ArrayList<Object>) items;
                                if (!ClientChecker.check(ClientChecker.ClientType.Telegraph)) {
                                    typedItems.add(newItem);
                                } else {
                                    typedItems.add(0, newItem);
                                }
                            }
                        }
                    }
            );

            AbstractMethodHook onCreateHook = new AbstractMethodHook() {
                @Override
                protected void afterMethod(final MethodHookParam param) {

                    Object LaunchActivtiy = param.thisObject;

                    Object drawerLayoutAdapter = XposedHelpers.getObjectField(LaunchActivtiy, AutomationResolver.resolve("LaunchActivity", "drawerLayoutAdapter", AutomationResolver.ResolverType.Field));
                    if (drawerLayoutAdapter != null) {
                        Object args = param.args[1];

                        int id = (int) XposedHelpers.callMethod(drawerLayoutAdapter, AutomationResolver.resolve("DrawerLayoutAdapter", "getId", AutomationResolver.ResolverType.Method), args);
                        if (id == MainHook.id) {

                            Object drawerLayoutContainer = XposedHelpers.getObjectField(LaunchActivtiy, AutomationResolver.resolve("LaunchActivity", "drawerLayoutContainer", AutomationResolver.ResolverType.Field));
                            if (drawerLayoutContainer != null) {
                                XposedHelpers.callMethod(drawerLayoutContainer, AutomationResolver.resolve("DrawerLayoutContainer", "closeDrawer", AutomationResolver.ResolverType.Method));
                            }

                            openView();
                        }

                    } else {
                        Utils.log("Not found DrawerLayoutAdapter, " + Utils.issue);
                    }
                }
            };

            Method onCreateMethod = null;
            for (Method method : loadClass.getLaunchActivityClass().getDeclaredMethods()) {
                if (Arrays.equals(method.getParameterTypes(), AutomationResolver.resolveObject("onCreateMethod", new Class[]{android.view.View.class,int.class, float.class, float.class}))) {
                    onCreateMethod = method;
                    break;
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

