package com.my.televip.settings.hook;


import android.view.View;
import android.widget.ImageView;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.ClientChecker;
import com.my.televip.Drawable.GhostDrawable;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.settings.controller.SettingsController;
import com.my.televip.utils.Utils;
import com.my.televip.virtuals.Adapters.DrawerLayoutAdapter;
import com.my.televip.virtuals.SettingsIconResolver;
import com.my.televip.virtuals.ui.Components.UItem;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import com.my.televip.reflect.XReflect;

public class SettingsHook {

    private Constructor<?> itemConstructor;

    public void newSettings(Class<?> SettingsActivityClass, Class<?> SettingsActivity$SettingCell$FactoryClass, SettingsController settingsController){
        try {

            GhostDrawable ghostDrawable = new GhostDrawable();

            HMethod.hookMethod(ClassLoad.getClass(ClassNames.SETTINGS_ACTIVITY_SETTING_CELL), AutomationResolver.resolve("SettingsActivity$SettingCell", "set", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("set", new Class[]{int.class, int.class, int.class, CharSequence.class, CharSequence.class, CharSequence.class}), new AbstractMethodHook() {
                @Override
                protected void afterMethod(MethodHookParam param) {
                    int id = (int) param.args[2];
                    if (id == 8353847) {
                        ImageView iconView = (ImageView) XReflect.getObjectField(param.thisObject, AutomationResolver.resolve("SettingsActivity$SettingCell", "iconView", AutomationResolver.ResolverType.Field));
                        iconView.setImageDrawable(ghostDrawable);
                    }
                }
            }));

            HMethod.hookMethod(SettingsActivityClass, AutomationResolver.resolve("SettingsActivity", "fillItems", AutomationResolver.ResolverType.Method),
                    AutomationResolver.merge(AutomationResolver.resolveObject("fillItems", new Class[]{java.util.ArrayList.class, ClassLoad.getClass(ClassNames.UNIVERSAL_ADAPTER)}), new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(final MethodHookParam param) {
                            ArrayList<Object> arrayList = (ArrayList<Object>) param.args[0];
                            if (arrayList != null) {

                                int color1 = 0xFFF46F6F;
                                int color2 = 0xFFDF5555;

                                Object uItem = XReflect.callStaticMethod(SettingsActivity$SettingCell$FactoryClass, AutomationResolver.resolve("SettingsActivity$SettingCell$Factory", "of", AutomationResolver.ResolverType.Method), 8353847,
                                        color1,
                                        color2,
                                        8353847,
                                        Translator.get(Keys.GhostMode),
                                        Translator.get(Keys.ByMustafa));
                                for (int i = 0; i < arrayList.size(); i++) {
                                    UItem item = new UItem(arrayList.get(i));

                                    if (item.getText() != null && item.getSubtext() != null) {
                                        arrayList.add(i, uItem);
                                        break;
                                    }
                                }

                            }
                        }
                    }));


            Class<?> UItemClass = ClassLoad.getClass(ClassNames.UITEM);

            HMethod.hookMethod(
                    SettingsActivityClass,
                    AutomationResolver.resolve("SettingsActivity", "onClick", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("onClick", new Class[]{UItemClass, View.class, int.class, float.class, float.class}), new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(final MethodHookParam param) {
                            UItem uItem = new UItem(param.args[0]);
                            if (uItem.getUItem() != null) {
                                if (uItem.getID() == 8353847) {
                                    settingsController.openView();
                                }
                            }
                        }
                    }));
        } catch (Throwable t){
            Logger.e(t);
        }
    }

    public void oldSettings(SettingsController settingsController){
        final Class<?> itemClass = XReflect.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Adapters.DrawerLayoutAdapter$Item"), Utils.classLoader);

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

                            Object newItem = itemConstructor.newInstance(8353847, Translator.get(Keys.GhostMode), SettingsIconResolver.getIconSettings());

                            if (items instanceof ArrayList<?>) {
                                ArrayList<Object> typedItems = (ArrayList<Object>) items;
                                typedItems.add(newItem);
                            }
                        }
                    }
            );

            AbstractMethodHook onCreateHook = new AbstractMethodHook() {
                @Override
                protected void afterMethod(final MethodHookParam param) {

                    Object Launch = param.thisObject;

                    Object drawerLayoutAdapter = XReflect.getObjectField(Launch, AutomationResolver.resolve("LaunchActivity", "drawerLayoutAdapter", AutomationResolver.ResolverType.Field));
                    if (drawerLayoutAdapter != null) {
                        Object args = param.args[1];

                        int id = (int) XReflect.callMethod(drawerLayoutAdapter, AutomationResolver.resolve("DrawerLayoutAdapter", "getId", AutomationResolver.ResolverType.Method), args);
                        if (id == 8353847) {

                            Object drawerLayoutContainer = XReflect.getObjectField(Launch, AutomationResolver.resolve("LaunchActivity", "drawerLayoutContainer", AutomationResolver.ResolverType.Field));
                            if (drawerLayoutContainer != null) {
                                if (!ClientChecker.check(ClientChecker.ClientType.ForkgramClassic)) {
                                    XReflect.callMethod(drawerLayoutContainer, AutomationResolver.resolve("DrawerLayoutContainer", "closeDrawer", AutomationResolver.ResolverType.Method));
                                } else {
                                    XReflect.callMethod(drawerLayoutContainer, AutomationResolver.resolve("DrawerLayoutContainer", "closeDrawer", AutomationResolver.ResolverType.Method), true);
                                }
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

                HMethod.hookMember(onCreateMethod, onCreateHook);
            }
        }

    }
}

