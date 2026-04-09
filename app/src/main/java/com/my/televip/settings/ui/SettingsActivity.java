package com.my.televip.settings.ui;


import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.Drawable.ArrowDrawable;
import com.my.televip.audio;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.dex.DexInjector;
import com.my.televip.hooks.HMethod;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.settings.controller.SettingsController;
import com.my.televip.ui.toolBar.MainToolBar;
import com.my.televip.virtuals.TeleVip.Bridge.Bridge;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.ui.Components.RecyclerListView;

import de.robv.android.xposed.XposedHelpers;

public class SettingsActivity {

    public static boolean isSettings;

    private final Context context;

    public RecyclerListView listView;
    private LinearLayout layout;


    public View createView(SettingsController settingsController) {
        try {
            if (SettingsAdapter.items != null && !SettingsAdapter.items.isEmpty())
                SettingsAdapter.items.clear();
            SettingsAdapter.items = ConfigManager.getItems(context);

            layout = new LinearLayout(context);

            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setBackgroundColor(Theme.getBackgroundGrayColor());

            MainToolBar toolbar = new MainToolBar(context);

            toolbar.setColorTitle(Theme.getTextToolBarColor());
            toolbar.setRippleColor(Theme.getToolBarRippleColor());
            toolbar.setTextTitle(Translator.get(Keys.GhostMode));

            ArrowDrawable arrow = new ArrowDrawable();
            toolbar.setImageDrawable(arrow);
            toolbar.getImage().setOnClickListener(v -> settingsController.hide());

            layout.addView(toolbar);

            listView = new RecyclerListView(context);

            Object adapter = XposedHelpers.newInstance(
                    ClassLoad.getClass(ClassNames.SETTINGS_ADAPTER_LIST_ADAPTER, DexInjector.classLoader),
                    context);

            listView.setAdapter(adapter);

            listView.setBackgroundColor(Theme.getBackgroundWhiteOrBlueColor());

            listView.setVerticalScrollBarEnabled(false);
            listView.setLayoutManager(Bridge.getLayoutManager(context));

            LinearLayout.LayoutParams recyclerParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1f
            );
            recyclerParams.setMargins(10, 10, 10, 0);

            layout.addView(listView.getRecyclerListView(), recyclerParams);

        } catch (Throwable e) {
            Logger.e(e);
        }

        return layout;
    }

    public SettingsActivity(Context context) {
        this.context = context;
        isSettings = true;
    }

    public static void init(SettingsController settingsController) {
        audio.init();
        try {
            HMethod.hookMethod(ClassLoad.getClass(ClassNames.LAUNCH_ACTIVITY), "onBackPressed", new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (isSettings) {
                        settingsController.hide();
                        settingsController.settingsView = null;
                        param.setResult(null);
                    }
                }
            });

            HMethod.hookMethod(ClassLoad.getClass(ClassNames.ANDROID_UTILITIES), AutomationResolver.resolve("AndroidUtilities", "isTablet", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (isSettings) {
                        param.setResult(true);
                    }
                }
            });
        } catch (Throwable e) {
            Logger.e(e);
        }
    }
}
