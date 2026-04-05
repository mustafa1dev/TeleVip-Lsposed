package com.my.televip.virtuals.ui.Cells;

import android.content.Context;
import android.view.View;

import com.my.televip.Utils;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class ShadowSectionCell {

    Object shadowSectionCell;

    public ShadowSectionCell(Context context){
        Class<?> shadowSectionCellClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Cells.ShadowSectionCell"), Utils.classLoader);
        shadowSectionCell = XposedHelpers.newInstance(shadowSectionCellClass, context);
    }

    public View getView(){
        return (View) shadowSectionCell;
    }


}
