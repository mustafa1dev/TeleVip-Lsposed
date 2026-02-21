package com.my.televip.virtuals.Cells;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.televip.Drawable.ArrowDrawable;
import com.my.televip.MainHook;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class TextSettingsCell {

    Object textSettingsCell;

    public TextSettingsCell(Context context){
        textSettingsCell = XposedHelpers.newInstance(loadClass.getTextSettingsCellClass(), context);
    }

    public View getView(){
        return (View) textSettingsCell;
    }

    public void setText(CharSequence text, boolean divider){
        XposedHelpers.callMethod(textSettingsCell, "setText", text, divider);
    }

    public TextView getTextView(){
        return (TextView) XposedHelpers.getObjectField(textSettingsCell,"textView");
    }

}
