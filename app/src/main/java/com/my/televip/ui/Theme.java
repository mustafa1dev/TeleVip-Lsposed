package com.my.televip.ui;



import static com.my.televip.MainHook.lpparam;
import static com.my.televip.language.Language.*;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.my.televip.AlertDialog.onClickDialog;
import com.my.televip.ClientChecker;
import com.my.televip.Clients.Telegraph;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.FeatureManager;
import com.my.televip.language.Language;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActiveTheme;
import com.my.televip.virtuals.Adapters.DrawerLayoutAdapter;
import com.my.televip.virtuals.EventType;
import com.my.televip.xSharedPreferences;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


import de.robv.android.xposed.XposedHelpers;

public class Theme {

    public static int id_item_add = -1;
    private static Constructor<?> itemConstructor;
    public static MediaPlayer mediaPlayer;
    public static boolean playing=false;
    public static int regr=0;
    public static String audioUrl;
    public static boolean isSettings;


    //Under development
    /*
    private static void openView(Object obj){
        Class<?> dataSettings = XposedHelpers.findClassIfExists("org.telegram.ui.DataSettingsActivity", lpparam.classLoader);
        Object tele = XposedHelpers.newInstance(dataSettings);
        isSettings = true;
        XposedHelpers.callMethod(obj, "presentFragment", tele);
    }
     */

    private static void openDialog(Context applicationContext, final Object LaunchActivtiy){
        final Class<?> alertDialogBuilderClass = XposedHelpers.findClassIfExists(
                AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog.Builder"),
                lpparam.classLoader
        );
        if (alertDialogBuilderClass != null) {

            xSharedPreferences.SharedPre = applicationContext.getSharedPreferences(strTelevip, Activity.MODE_PRIVATE);
            //FeatureManager.readFeature();
            Object alertDialog = XposedHelpers.newInstance(alertDialogBuilderClass, applicationContext);
            // عرض رسالة أو تخصيص النافذة
            Language.init();
            ArrayList<String> list = FeatureManager.getArrayList();
            final String[] items = list.toArray(new String[0]);
            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setTitle", AutomationResolver.ResolverType.Method), Ghost_Mode);
            // إنشاء تخطيط جديد
            LinearLayout layout = new LinearLayout(applicationContext);
            layout.setOrientation(LinearLayout.VERTICAL);

// إضافة CheckBox لكل عنصر في القائمة مع إعدادات النص
            final List<CheckBox> checkBoxes = new ArrayList<>();
            for (String item : items) {
                CheckBox checkBox = new CheckBox(applicationContext);
                if (item.equals(TelegramPremium) && FeatureManager.isTelePremium()) {
                    checkBox.setChecked(true);
                } else if (item.equals(HideSeenUser) && FeatureManager.isHideSeenPrivate()) {
                    checkBox.setChecked(true);
                } else if (item.equals(HideSeenGroups) && FeatureManager.isHideSeenGroup()) {
                    checkBox.setChecked(true);
                }
                if (item.equals(HideTyping) && FeatureManager.isHideTyping()) {
                    checkBox.setChecked(true);
                } else if (item.equals(HideStoryView) && FeatureManager.isNoStoryRead()) {
                    checkBox.setChecked(true);
                } else if (item.equals(UnlockAllRestricted) && FeatureManager.isUnlockChannelFeature()) {
                    checkBox.setChecked(true);
                } else if (item.equals(AllowSavingvideos) && FeatureManager.isAllowSaveToGallery()) {
                    checkBox.setChecked(true);
                } else if (item.equals(HideOnline) && FeatureManager.isHideOnline()) {
                    checkBox.setChecked(true);
                } else if (item.equals(PreventMedia) && FeatureManager.isPreventMedia()) {
                    checkBox.setChecked(true);
                    try {
                        checkBox.setOnLongClickListener(_view -> {
                            if (!playing) {
                                regr = (int) (Math.random() * 3);
                                if (regr == 0) {
                                    audioUrl = "https://qurango.net/radio/abdulbasit_abdulsamad_mojawwad";
                                } else if (regr == 1) {
                                    audioUrl = "https://qurango.net/radio/yasser_aldosari";
                                } else {
                                    audioUrl = "https://backup.qurango.net/radio/maher";
                                }
                                mediaPlayer = new MediaPlayer();
                                //noinspection deprecation
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                                        .setUsage(AudioAttributes.USAGE_MEDIA)
                                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                        .build();

                                mediaPlayer.setAudioAttributes(audioAttributes);
                                try {
                                    mediaPlayer.setDataSource(audioUrl);
                                } catch (IllegalArgumentException |
                                         IllegalStateException |
                                         IOException e) {
                                    throw new RuntimeException(e);
                                }
                                mediaPlayer.prepareAsync();

                                mediaPlayer.setOnPreparedListener(mp -> {
                                    mediaPlayer.start();
                                    playing = true;
                                });
                            } else {
                                if (mediaPlayer.isPlaying()) {
                                    mediaPlayer.stop();
                                    mediaPlayer.release();

                                    mediaPlayer = new MediaPlayer();
                                    //noinspection deprecation
                                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                    AudioAttributes audioAttributes = new AudioAttributes.Builder()
                                            .setUsage(AudioAttributes.USAGE_MEDIA)
                                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                            .build();

                                    mediaPlayer.setAudioAttributes(audioAttributes);
                                    try {
                                        mediaPlayer.setDataSource(audioUrl);
                                    } catch (IllegalArgumentException |
                                             IllegalStateException |
                                             IOException e) {
                                        throw new RuntimeException(e);
                                    }

                                    playing = false;

                                }
                            }
                            return true;
                        });
                    } catch (Exception ex) {
                        Utils.log(ex.getMessage());
                    }
                } else if (item.equals(HidePhone) && FeatureManager.isHidePhone()) {
                    checkBox.setChecked(true);
                } else if (item.equals(ShowDeletedMessages) && FeatureManager.ishowDeletedMessages()) {
                    checkBox.setChecked(true);
                } else if (item.equals(DisableStories) && FeatureManager.isDisableStories()) {
                    checkBox.setChecked(true);
                }

                checkBox.setText(item);
                if (ActiveTheme.getActiveTheme()) {
                    checkBox.setTextColor(Color.BLACK); // تغيير لون النص إلى الأبيض
                } else {
                    checkBox.setTextColor(Color.WHITE);
                }
                checkBox.setPadding(10, 10, 10, 10); // إضافة هامش صغير حول النص
                checkBox.setTypeface(Typeface.DEFAULT_BOLD); // جعل النص مائلًا قليلاً
                checkBoxes.add(checkBox);
                layout.addView(checkBox);
            }

// إعداد AlertDialog واستخدام setView
            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setView", AutomationResolver.ResolverType.Method), layout);


// نحصل على الكلاس الداخلي OnButtonClickListener
            Object onDoneListener;
            Object onCnelListener;
            Class<?> listenerClass = XposedHelpers.findClassIfExists(
                    AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener"),
                    lpparam.classLoader
            );
            if (listenerClass != null) {
                onDoneListener = Proxy.newProxyInstance(
                        lpparam.classLoader,
                        new Class[]{listenerClass},
                        (proxy, method, args) -> {
                            if (method.getName().equals(AutomationResolver.resolve("AlertDialog$OnButtonClickListener", "onClick", AutomationResolver.ResolverType.Method))) {
                                onClickDialog.onClickSave(checkBoxes);
                            }
                            return null;
                        }
                );
                onCnelListener = Proxy.newProxyInstance(
                        lpparam.classLoader,
                        new Class[]{listenerClass},
                        (proxy, method, args) -> {
                            if (method.getName().equals(AutomationResolver.resolve("AlertDialog$OnButtonClickListener", "onClick", AutomationResolver.ResolverType.Method))) {
                                onClickDialog.onClickOpenUrl(applicationContext, LaunchActivtiy);
                            }
                            return null;
                        }
                );

            } else {
                onDoneListener = (DialogInterface.OnClickListener) (dialog, which) -> onClickDialog.onClickSave(checkBoxes);
                onCnelListener = (DialogInterface.OnClickListener) (dialog, which) -> onClickDialog.onClickOpenUrl(applicationContext, LaunchActivtiy);
            }
            // إعداد الزر الموجب
            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setPositiveButton", AutomationResolver.ResolverType.Method),
                    Save, onDoneListener
            );
            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setNegativeButton", AutomationResolver.ResolverType.Method),
                    DeveloperChannel, onCnelListener
            );


            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "show", AutomationResolver.ResolverType.Method));
        } else {
            Utils.log("Not found org.telegram.ui.ActionBar.AlertDialog.Builder, " + Utils.issue);
        }
    }

    public static void newTheme(Class<?> SettingsActivityClass, Class<?> SettingsActivity$SettingCell$FactoryClass){

        AbstractMethodHook fillItemsHook = new AbstractMethodHook() {
            @Override
            protected void afterMethod(final MethodHookParam param) {
                Language.init();
                ArrayList<Object> arrayList = (ArrayList<Object>) param.args[0];
                if (arrayList != null) {

// بعض القيم الثابتة
                    int color1 = 0xFFF46F6F;
                    int color2 = 0xFFDF5555;
                    int id = 8353847;

// إنشاء عنصر واجهة (UItem) باستخدام Factory
                    Object uitem = XposedHelpers.callStaticMethod(SettingsActivity$SettingCell$FactoryClass, "of", id,
                            color1,
                            color2,
                            EventType.IconSettings(),
                            GhostMode,
                            byMustafa);
                    if (id_item_add == -1) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            Object obj = arrayList.get(i);
                            int id_item = XposedHelpers.getIntField(obj, "id");
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

        XposedHelpers.findAndHookMethod(SettingsActivityClass, AutomationResolver.resolve("SettingsActivity", "fillItems", AutomationResolver.ResolverType.Method),
                AutomationResolver.merge(AutomationResolver.resolveObject("10"), fillItemsHook));

        AbstractMethodHook onClickHook = new AbstractMethodHook() {
            @Override
            protected void afterMethod(final MethodHookParam param) {
                Object uitem = param.args[0];
                if (uitem != null){
                    int id = XposedHelpers.getIntField(uitem, "id");
                    if (id == 8353847) {

                        Object SettingsActivity = param.thisObject;
                        Context applicationContext = (Context) XposedHelpers.callMethod(SettingsActivity, "getContext");
                        //openView(SettingsActivity);
                        openDialog(applicationContext, null);
                    }
                }
            }
        };


        XposedHelpers.findAndHookMethod(
                SettingsActivityClass,
                AutomationResolver.resolve("SettingsActivity", "onClick", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("12"), onClickHook));
    }

    public static void oldTheme(){
        final Class<?> itemClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Adapters.DrawerLayoutAdapter$Item"), lpparam.classLoader);

        if (itemClass != null) {
            XposedHelpers.findAndHookMethod(
                    loadClass.getDrawerLayoutAdapterClass(),
                    AutomationResolver.resolve("DrawerLayoutAdapter", "resetItems", AutomationResolver.ResolverType.Method),                                   // اسم الدالة
                    new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(MethodHookParam param) throws Throwable {

                            DrawerLayoutAdapter drawerLayoutAdapter = new DrawerLayoutAdapter(param.thisObject);

                            ArrayList<?> items = (ArrayList<?>) drawerLayoutAdapter.getItems();
                            String param2 = "para4";
                            if (ClientChecker.check(ClientChecker.ClientType.Telegraph)){
                                param2 = "10";
                            }
                            // استدعاء الكلاس Item باستخدام Class.forName
                            if (itemConstructor == null) {
                                itemConstructor = itemClass.getDeclaredConstructor(AutomationResolver.resolveObject(param2));
                                itemConstructor.setAccessible(true);
                            }
                            // استدعاء الطريقة مباشرة

                            Language.init();
                            Object newItem;
                            if (ClientChecker.check(ClientChecker.ClientType.Telegraph)){
                                newItem = itemConstructor.newInstance(8353847, Language.GhostMode, 8353847, true, null, "");
                            } else {
                                newItem = itemConstructor.newInstance(8353847, GhostMode, EventType.IconSettings());
                            }
                            // إضافة الكائن الجديد إلى القائمة
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
            Class<?> launchActivityClass = XposedHelpers.findClass(
                    AutomationResolver.resolve("org.telegram.ui.LaunchActivity"),
                    lpparam.classLoader
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
                        // استدعاء getId وطباعته
                        int id = (int) XposedHelpers.callMethod(drawerLayoutAdapter, AutomationResolver.resolve("DrawerLayoutAdapter", "getId", AutomationResolver.ResolverType.Method), args);
                        if (id == 8353847) {
                            final Context applicationContext = (Context) LaunchActivtiy;
                            Language.init();
                            openDialog(applicationContext, LaunchActivtiy);
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
            XposedHelpers.findAndHookMethod(
                    launchActivityClass,
                    AutomationResolver.resolve("LaunchActivity", "lambda$onCreate$8", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject(para), onCreateHook));
        }

    }
}

