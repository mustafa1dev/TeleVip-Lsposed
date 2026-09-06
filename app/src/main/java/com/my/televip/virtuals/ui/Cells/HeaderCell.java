package com.my.televip.virtuals.ui.Cells;

import android.content.Context;
import android.view.View;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class HeaderCell {

    public Object headerCell;

    public HeaderCell(Context context){
        headerCell = XReflect.newInstance(ClassLoad.getClass(ClassNames.HEADER_CELL), context);
    }

    public HeaderCell(Object obj){
       headerCell = obj;
    }

    public View getView(){
        return (View) headerCell;
    }

    public void setText(CharSequence text){
        XReflect.callMethod(headerCell, AutomationResolver.resolve("HeaderCell","setText", AutomationResolver.ResolverType.Method), text);
    }

}
