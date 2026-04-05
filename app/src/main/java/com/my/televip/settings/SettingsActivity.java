package com.my.televip.settings;


import static com.my.televip.settings.SettingsInjector.hide;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.my.televip.Configs.ConfigMapper;
import com.my.televip.Configs.ConfigPreferences;
import com.my.televip.Drawable.ArrowDrawable;
import com.my.televip.audio;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;
import com.my.televip.virtuals.ActionBar.AlertDialog;
import com.my.televip.virtuals.TeleVip.Bridge.Bridge;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.messenger.browser.Browser;
import com.my.televip.virtuals.ui.Components.RecyclerListView;

import de.robv.android.xposed.XposedHelpers;

public class SettingsActivity {

    public static boolean isSettings;

    private final Context context;

    public RecyclerListView listView;


    public View createView() {
        if (SettingsAdapter.items != null && !SettingsAdapter.items.isEmpty()) SettingsAdapter.items.clear();
        SettingsAdapter.items = ConfigMapper.getItems(context);

        LinearLayout fragmentView = new LinearLayout(context);
        fragmentView.setOrientation(LinearLayout.VERTICAL);
        fragmentView.setBackgroundColor(Theme.getBackgroundGrayColor());
        try {

            SettingsToolBar toolbar = new SettingsToolBar(context);

            toolbar.setColorTitle(Theme.getTextToolBarColor());
            toolbar.setTextTitle(Translator.get(Keys.GhostMode));

            ArrowDrawable arrow = new ArrowDrawable();
            toolbar.setImageDrawable(arrow);
            toolbar.getImage().setOnClickListener(v -> hide());

            fragmentView.addView(toolbar);

            listView = new RecyclerListView(context);

            Object adapter = XposedHelpers.newInstance(
                    loadClass.getSettingsAdapter$ListAdapterClass(),
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

            fragmentView.addView(listView.getRecyclerListView(), recyclerParams);

        } catch (Exception e) {
            Logger.e(e);
        }

        return fragmentView;
    }

    public SettingsActivity(Context context) {
        this.context = context;
        try {
            isSettings = true;

            if (!ConfigPreferences.getBoolean("DSA")) {
                AlertDialog alertDialog = new AlertDialog(context);

                alertDialog.setTitle(Translator.get(Keys.GhostMode));
                alertDialog.setMessage(Translator.get(Keys.JoinTeleVip));

                alertDialog.setPositiveButton(Translator.get(Keys.Join), AlertDialog.click(() -> {
                    Browser.openUrl(context, "https://t.me/t_l0_e");
                    hide();
                }));

                alertDialog.setNegativeButton(Translator.get(Keys.Cancel), null);
                alertDialog.setNeutralButton(Translator.get(Keys.DontShowAgain), AlertDialog.click(() -> ConfigPreferences.putBoolean("DSA", true)));
                alertDialog.show();
            }
        } catch (Exception e) {
            Logger.e(e);
        }
    }

    public static void init() {
        audio.init();
        try {

            HMethod.hookMethod(loadClass.getLaunchActivityClass(), "onBackPressed", new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (isSettings) {
                        hide();
                        SettingsInjector.settings = null;
                        param.setResult(null);
                    }
                }
            });

            HMethod.hookMethod(loadClass.getAndroidUtilitiesClass(), AutomationResolver.resolve("AndroidUtilities", "isTablet", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (isSettings) {
                        param.setResult(true);
                    }
                }
            });
        } catch (Exception e) {
            Logger.e(e);
        }
    }
}
