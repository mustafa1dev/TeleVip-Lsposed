package com.my.televip.virtuals.ui;

import android.widget.FrameLayout;

import de.robv.android.xposed.XposedHelpers;

public class LaunchActivity {

    Object launchActivity;
    public FrameLayout frameLayout;

    public LaunchActivity(Object obj){
       launchActivity = obj;
       frameLayout = (FrameLayout) XposedHelpers.getObjectField(obj, "frameLayout");
    }

}
