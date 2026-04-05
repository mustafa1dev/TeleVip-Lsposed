package com.my.televip.virtuals.TeleVip.Bridge;


import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;

import com.my.televip.base.AbstractMethodHook;
import com.my.televip.dex.DexInjector;
import com.my.televip.loadClass;
import com.my.televip.settings.SettingsAdapter;
import com.my.televip.utils.Logger;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.ui.Cells.HeaderCell;
import com.my.televip.virtuals.ui.Cells.ShadowSectionCell;
import com.my.televip.virtuals.ui.Cells.TextCheckCell;
import com.my.televip.virtuals.ui.Cells.TextInfoCell;
import com.my.televip.virtuals.ui.Cells.TextSettingsCell;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class Bridge {

    public static Object getLayoutManager(Context context){
        return XposedHelpers.callStaticMethod(loadClass.getSettingsAdapterClass(), "getLayoutManager", context);
    }

    public static void init(Activity activity){
        try {
            Class<?> bridgeClass = XposedHelpers.findClassIfExists("com.televip.SettingsAdapter.Bridge", DexInjector.classLoader);
            Class<?> textCheckCellClass = XposedHelpers.findClassIfExists("com.televip.SettingsAdapter.SettingsAdapter$TextCheckCellHolder", DexInjector.classLoader);
            Class<?> textSettingsCellClass = XposedHelpers.findClassIfExists("com.televip.SettingsAdapter.SettingsAdapter$TextSettingsCellHolder", DexInjector.classLoader);
            Class<?> headerCellClass = XposedHelpers.findClassIfExists("com.televip.SettingsAdapter.SettingsAdapter$HeaderCellHolder", DexInjector.classLoader);
            Class<?> shadowSectionCellClass = XposedHelpers.findClassIfExists("com.televip.SettingsAdapter.SettingsAdapter$ShadowSectionCellHolder", DexInjector.classLoader);
            Class<?> textInfoCellClass = XposedHelpers.findClassIfExists("com.televip.SettingsAdapter.SettingsAdapter$TextInfoCellHolder", DexInjector.classLoader);

            XposedHelpers.findAndHookMethod(bridgeClass, "getRow", int.class, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(XC_MethodHook.MethodHookParam param) {
                    param.setResult(SettingsAdapter.getRow((int) param.args[0]));
                }
            });

            XposedHelpers.findAndHookMethod(bridgeClass, "log", String.class, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(XC_MethodHook.MethodHookParam param) {
                    Logger.l((String) param.args[0]);
                }
            });

            XposedHelpers.findAndHookMethod(bridgeClass, "getRowCount", new AbstractMethodHook() {
                @Override
                protected void beforeMethod(XC_MethodHook.MethodHookParam param) {
                    param.setResult(SettingsAdapter.getRowCount());
                }
            });
            
            XposedHelpers.findAndHookMethod(bridgeClass, "onBindViewHolder", Object.class, int.class, int.class, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(XC_MethodHook.MethodHookParam param) {
                   SettingsAdapter.onBindViewHolder(activity, param.args[0], (int) param.args[1], (int) param.args[2]);
                }
            });


            TypedValue outValue = new TypedValue();
            activity.getTheme().resolveAttribute(
                    android.R.attr.selectableItemBackground,
                    outValue,
                    true
            );
            
            XposedHelpers.findAndHookConstructor(textCheckCellClass, View.class, Object.class, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(XC_MethodHook.MethodHookParam param) {
                    TextCheckCell textCheckCell = new TextCheckCell(activity);
                    textCheckCell.getView().setBackgroundColor(Theme.getBackgroundWhiteOrBlueColor());

                    textCheckCell.getView().setBackgroundResource(outValue.resourceId);
                    textCheckCell.getView().setClickable(true);
                    textCheckCell.getView().setFocusable(true);
                    param.args[0] = textCheckCell.getView();
                    param.args[1] = textCheckCell.textCell;
                }
            });

            XposedHelpers.findAndHookConstructor(textSettingsCellClass, View.class, Object.class, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(XC_MethodHook.MethodHookParam param) {
                    TextSettingsCell textSettingsCell = new TextSettingsCell(activity);
                    textSettingsCell.getView().setBackgroundColor(Theme.getBackgroundWhiteOrBlueColor());
                    textSettingsCell.getView().setBackgroundResource(outValue.resourceId);
                    textSettingsCell.getView().setClickable(true);
                    textSettingsCell.getView().setFocusable(true);
                    param.args[0] = textSettingsCell.getView();
                    param.args[1] = textSettingsCell.textSettingsCell;
                }
            });

            XposedHelpers.findAndHookConstructor(headerCellClass, View.class, Object.class, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(XC_MethodHook.MethodHookParam param) {
                    HeaderCell header = new HeaderCell(activity);
                    header.getView().setBackgroundColor(Theme.getBackgroundWhiteOrBlueColor());
                    param.args[0] = header.getView();
                    param.args[1] = header.headerCell;
                }
            });

            XposedHelpers.findAndHookConstructor(shadowSectionCellClass, View.class, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(XC_MethodHook.MethodHookParam param) {
                    param.args[0] = new ShadowSectionCell(activity).getView();
                }
            });

            XposedHelpers.findAndHookConstructor(textInfoCellClass, View.class, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(XC_MethodHook.MethodHookParam param) {
                    TextInfoCell textInfoCell = new TextInfoCell(activity);
                    textInfoCell.setBackgroundColor(Theme.getBackgroundGrayColor());
                    param.args[0] = textInfoCell;
                }
            });

        } catch (Exception e){
            Logger.e(e);
        }
    }
}
