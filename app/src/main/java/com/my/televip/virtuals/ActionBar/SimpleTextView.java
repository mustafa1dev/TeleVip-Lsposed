package com.my.televip.virtuals.ActionBar;

import android.view.View;

import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class SimpleTextView {

    Object simpleTextView;

    public SimpleTextView(Object textview){
        simpleTextView = textview;
    }

    public CharSequence getText(){
        return (CharSequence) XposedHelpers.getObjectField(simpleTextView, AutomationResolver.resolve("SimpleTextView", "text", AutomationResolver.ResolverType.Field));
    }

    public void setText(CharSequence text){
        XposedHelpers.callMethod(simpleTextView, AutomationResolver.resolve("SimpleTextView", "setText", AutomationResolver.ResolverType.Method), text);
    }

    public View getSimpleTextView(){
        return (View) simpleTextView;
    }

}
