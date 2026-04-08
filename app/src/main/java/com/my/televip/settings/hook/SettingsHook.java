package com.my.televip.settings.hook;


import android.view.View;
import android.widget.ImageView;

import com.my.televip.Class.ClassNames;
import com.my.televip.ClientChecker;
import com.my.televip.Drawable.GhostDrawable;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.settings.controller.SettingsController;
import com.my.televip.logging.Logger;
import com.my.televip.virtuals.Adapters.DrawerLayoutAdapter;
import com.my.televip.virtuals.EventType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class SettingsHook {

    public int id_item_add = -1;
    private Constructor<?> itemConstructor;

    public void newSettings(Class<?> SettingsActivityClass, Class<?> SettingsActivity$SettingCell$FactoryClass, SettingsController settingsController){

        AbstractMethodHook fillItemsHook = new AbstractMethodHook() {
            @Override
            protected void afterMethod(final MethodHookParam param) {
                ArrayList<Object> arrayList = (ArrayList<Object>) param.args[0];
                if (arrayList != null) {

                    int color1 = 0xFFF46F6F;
                    int color2 = 0xFFDF5555;

                    Object uItem = XposedHelpers.callStaticMethod(SettingsActivity$SettingCell$FactoryClass, AutomationResolver.resolve("SettingCell$Factory","of", AutomationResolver.ResolverType.Method), 8353847,
                            color1,
                            color2,
                            8353847,
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
                Utils.classLoader
        );

        GhostDrawable ghostDrawable = new GhostDrawable();

        HMethod.hookMethod(SettingsActivity$SettingCellClass, AutomationResolver.resolve("SettingsActivity$SettingCell","set", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("set", new Class[]{int.class, int.class, int.class, CharSequence.class, CharSequence.class, CharSequence.class}), new AbstractMethodHook() {
            @Override
            protected void afterMethod(MethodHookParam param) {
                int id = (int) param.args[2];
                if (id == 8353847) {
                    ImageView iconView = (ImageView) XposedHelpers.getObjectField(param.thisObject, AutomationResolver.resolve("SettingsActivity$SettingCell", "iconView", AutomationResolver.ResolverType.Field));
                    iconView.setImageDrawable(ghostDrawable);
                }
            }
        }));

        Class<?> UniversalAdapterClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Components.UniversalAdapter"), Utils.classLoader);

        HMethod.hookMethod(SettingsActivityClass, AutomationResolver.resolve("SettingsActivity", "fillItems", AutomationResolver.ResolverType.Method),
                AutomationResolver.merge(AutomationResolver.resolveObject("fillItems",  new Class[]{java.util.ArrayList.class, UniversalAdapterClass}), fillItemsHook));

        AbstractMethodHook onClickHook = new AbstractMethodHook() {
            @Override
            protected void afterMethod(final MethodHookParam param) {
                Object uItem = param.args[0];
                if (uItem != null){
                    int id = XposedHelpers.getIntField(uItem, AutomationResolver.resolve("UItem","id", AutomationResolver.ResolverType.Field));
                    if (id == 8353847) {
                        settingsController.openView();
                    }
                }
            }
        };

        Class<?> UItemClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Components.UItem"), Utils.classLoader);

        HMethod.hookMethod(
                SettingsActivityClass,
                AutomationResolver.resolve("SettingsActivity", "onClick", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("onClick", new Class[]{UItemClass, View.class, int.class, float.class, float.class}), onClickHook));
    }

    public void oldSettings(SettingsController settingsController){
        final Class<?> itemClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Adapters.DrawerLayoutAdapter$Item"), Utils.classLoader);

        if (itemClass != null) {
            HMethod.hookMethod(
                    ClassLoad.getClass(ClassNames.DRAWER_LAYOUT_ADAPTER),
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

                            Object newItem = itemConstructor.newInstance(8353847, Translator.get(Keys.GhostMode), EventType.getIconSettings());

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

                    Object Launch = param.thisObject;

                    Object drawerLayoutAdapter = XposedHelpers.getObjectField(Launch, AutomationResolver.resolve("LaunchActivity", "drawerLayoutAdapter", AutomationResolver.ResolverType.Field));
                    if (drawerLayoutAdapter != null) {
                        Object args = param.args[1];

                        int id = (int) XposedHelpers.callMethod(drawerLayoutAdapter, AutomationResolver.resolve("DrawerLayoutAdapter", "getId", AutomationResolver.ResolverType.Method), args);
                        if (id == 8353847) {

                            Object drawerLayoutContainer = XposedHelpers.getObjectField(Launch, AutomationResolver.resolve("LaunchActivity", "drawerLayoutContainer", AutomationResolver.ResolverType.Field));
                            if (drawerLayoutContainer != null) {
                                XposedHelpers.callMethod(drawerLayoutContainer, AutomationResolver.resolve("DrawerLayoutContainer", "closeDrawer", AutomationResolver.ResolverType.Method));
                            }

                            settingsController.openView();
                        }

                    }
                }
            };

            if (ClassLoad.getClass(ClassNames.LAUNCH_ACTIVITY) != null) {

                Method onCreateMethod = null;
                for (Method method : ClassLoad.getClass(ClassNames.LAUNCH_ACTIVITY).getDeclaredMethods()) {
                    if (Arrays.equals(method.getParameterTypes(), AutomationResolver.resolveObject("onCreateMethod", new Class[]{android.view.View.class, int.class, float.class, float.class}))) {
                        onCreateMethod = method;
                        break;
                    }
                }

                if (onCreateMethod == null) {
                    Logger.w("Failed to hook onCreateMethod! Reason: No method found, " + Utils.issue);
                    return;
                }

                XposedBridge.hookMethod(onCreateMethod, onCreateHook);
            }
        }

    }
}

