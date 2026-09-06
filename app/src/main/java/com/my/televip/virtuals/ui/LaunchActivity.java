package com.my.televip.virtuals.ui;

import android.widget.FrameLayout;

import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class LaunchActivity {

    Object launchActivity;
    public FrameLayout frameLayout;

    public LaunchActivity(Object obj){
       launchActivity = obj;
       frameLayout = (FrameLayout) XReflect.getObjectField(obj, AutomationResolver.resolve("LaunchActivity","frameLayout", AutomationResolver.ResolverType.Field));
    }

}
