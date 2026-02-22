package com.my.televip.virtuals.Cells;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.my.televip.MainHook;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class TextCheckCell {

    Object textCell;

    public TextCheckCell(Context context){
        Class<?> textCheckClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Cells.TextCheckCell"), MainHook.lpparam.classLoader);
        textCell = XposedHelpers.newInstance(textCheckClass, context);
    }

    public void setTextAndValueAndCheck(CharSequence text, String value, boolean checked, boolean multiline, boolean divider){
        XposedHelpers.callMethod(textCell, AutomationResolver.resolve("TextCheckCell","setTextAndValueAndCheck", AutomationResolver.ResolverType.Method), text, value, checked, multiline,  divider);
    }

    public void setTextAndCheck(CharSequence text, boolean checked, boolean divider){
        XposedHelpers.callMethod(textCell, AutomationResolver.resolve("TextCheckCell","setTextAndCheck", AutomationResolver.ResolverType.Method), text, checked,  divider);
    }

    public void setChecked(boolean checked){
        XposedHelpers.callMethod(textCell, AutomationResolver.resolve("TextCheckCell","setChecked", AutomationResolver.ResolverType.Method), checked);
    }

    public boolean isChecked(){
        return (boolean) XposedHelpers.callMethod(textCell, AutomationResolver.resolve("TextCheckCell","isChecked", AutomationResolver.ResolverType.Method));
    }

    public TextView getTextView(){
        return (TextView) XposedHelpers.getObjectField(textCell,AutomationResolver.resolve("TextCheckCell","textView", AutomationResolver.ResolverType.Field));
    }

    public View getView(){
        return (View) textCell;
    }

}
