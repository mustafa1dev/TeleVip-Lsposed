package com.my.televip.virtuals.ui;

import android.widget.FrameLayout;

import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class LaunchActivity {

    Object launchActivity;
    public FrameLayout frameLayout;

    public LaunchActivity(Object obj){
       launchActivity = obj;
       frameLayout = (FrameLayout) XposedHelpers.getObjectField(obj, AutomationResolver.resolve("LaunchActivity","frameLayout", AutomationResolver.ResolverType.Field));
    }

}
