package com.my.televip.virtuals.ui.Cells;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class TextSettingsCell {

    public Object textSettingsCell;

    public TextSettingsCell(Context context){
        textSettingsCell = XposedHelpers.newInstance(loadClass.getTextSettingsCellClass(), context);
    }

    public TextSettingsCell(Object obj){
        textSettingsCell = obj;
    }

    public View getView(){
        return (View) textSettingsCell;
    }

    public void setText(CharSequence text, boolean divider){
        XposedHelpers.callMethod(textSettingsCell, AutomationResolver.resolve("TextSettingsCell","setText", AutomationResolver.ResolverType.Method), text, divider);
    }

    public TextView getTextView(){
        return (TextView) XposedHelpers.getObjectField(textSettingsCell,AutomationResolver.resolve("TextSettingsCell","textView", AutomationResolver.ResolverType.Field));
    }

}
