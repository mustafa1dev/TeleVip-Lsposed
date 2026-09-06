package com.my.televip.virtuals.ui.Cells;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class TextCheckCell {

    public Object textCell;

    public TextCheckCell(Context context){
        textCell = XReflect.newInstance(ClassLoad.getClass(ClassNames.TEXT_CHECK_CELL), context);
    }

    public TextCheckCell(Object obj){
        textCell = obj;
    }

    public void setTextAndValueAndCheck(CharSequence text, String value, boolean checked, boolean multiline, boolean divider){
        XReflect.callMethod(textCell, AutomationResolver.resolve("TextCheckCell","setTextAndValueAndCheck", AutomationResolver.ResolverType.Method), text, value, checked, multiline,  divider);
    }

    public void setTextAndCheck(CharSequence text, boolean checked, boolean divider){
        XReflect.callMethod(textCell, AutomationResolver.resolve("TextCheckCell","setTextAndCheck", AutomationResolver.ResolverType.Method), text, checked,  divider);
    }

    public void setChecked(boolean checked){
        XReflect.callMethod(textCell, AutomationResolver.resolve("TextCheckCell","setChecked", AutomationResolver.ResolverType.Method), checked);
    }

    public boolean isChecked(){
        return (boolean) XReflect.callMethod(textCell, AutomationResolver.resolve("TextCheckCell","isChecked", AutomationResolver.ResolverType.Method));
    }

    public TextView getTextView(){
        return (TextView) XReflect.getObjectField(textCell,AutomationResolver.resolve("TextCheckCell","textView", AutomationResolver.ResolverType.Field));
    }

    public View getView(){
        return (View) textCell;
    }

}
