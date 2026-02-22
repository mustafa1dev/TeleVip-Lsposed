package com.my.televip.virtuals.Cells;

import android.content.Context;
import android.view.View;

import com.my.televip.MainHook;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class HeaderCell {

    Object headerCell;

    public HeaderCell(Context context){
        Class<?> headerCellClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Cells.HeaderCell"), MainHook.lpparam.classLoader);
        headerCell = XposedHelpers.newInstance(headerCellClass, context);
    }

    public View getView(){
        return (View) headerCell;
    }

    public void setText(CharSequence text){
        XposedHelpers.callMethod(headerCell, AutomationResolver.resolve("HeaderCell","setText", AutomationResolver.ResolverType.Method), text);
    }

}
