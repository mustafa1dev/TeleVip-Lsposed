package com.my.televip.virtuals.ui.Cells;

import android.content.Context;
import android.view.View;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;

import com.my.televip.reflect.XReflect;

public class ShadowSectionCell {

    Object shadowSectionCell;

    public ShadowSectionCell(Context context){
        shadowSectionCell = XReflect.newInstance(ClassLoad.getClass(ClassNames.SHADOW_SECTION_CELL), context);
    }

    public View getView(){
        return (View) shadowSectionCell;
    }


}
