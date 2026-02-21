package com.my.televip.virtuals.Cells;

import android.content.Context;
import android.view.View;

import com.my.televip.MainHook;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class ShadowSectionCell {

    Object shadowSectionCell;

    public ShadowSectionCell(Context context){
        Class<?> shadowSectionCellClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Cells.ShadowSectionCell"), MainHook.lpparam.classLoader);
        shadowSectionCell = XposedHelpers.newInstance(shadowSectionCellClass, context);
    }

    public View getView(){
        return (View) shadowSectionCell;
    }


}
