package com.my.televip.virtuals.ActionBar;

import android.text.Layout;
import android.view.View;

import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class SimpleTextView {

    Object simpleTextView;

    public SimpleTextView(Object textview){
        simpleTextView = textview;
    }

    public CharSequence getText(){
        return (CharSequence) XReflect.callMethod(simpleTextView, AutomationResolver.resolve("SimpleTextView", "getText", AutomationResolver.ResolverType.Method));
    }

    public void setText(CharSequence text){
        XReflect.callMethod(simpleTextView, AutomationResolver.resolve("SimpleTextView", "setText", AutomationResolver.ResolverType.Method), text);
    }

    public void setText(CharSequence text, boolean force){
        XReflect.callMethod(simpleTextView, AutomationResolver.resolve("SimpleTextView", "setText", AutomationResolver.ResolverType.Method), text, force);
    }

    public void setAlignment(Layout.Alignment alignment){
        XReflect.callMethod(simpleTextView, AutomationResolver.resolve("SimpleTextView", "setAlignment", AutomationResolver.ResolverType.Method), alignment);
    }

    public void setMaxLines(int value){
        XReflect.callMethod(simpleTextView, AutomationResolver.resolve("SimpleTextView", "setMaxLines", AutomationResolver.ResolverType.Method), value);
    }

    public View getSimpleTextView(){
        return (View) simpleTextView;
    }

}
