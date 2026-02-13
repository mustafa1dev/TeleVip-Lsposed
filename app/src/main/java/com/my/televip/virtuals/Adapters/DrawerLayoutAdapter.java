package com.my.televip.virtuals.Adapters;

import com.my.televip.obfuscate.AutomationResolver;

import java.util.ArrayList;

import de.robv.android.xposed.XposedHelpers;

public class DrawerLayoutAdapter {

    private final Object drawerLayout;

    public DrawerLayoutAdapter(Object obj){
        drawerLayout = obj;
    }

    public ArrayList<?> getItems(){
        return (ArrayList<?>) XposedHelpers.getObjectField(drawerLayout, AutomationResolver.resolve("DrawerLayoutAdapter", "items", AutomationResolver.ResolverType.Field));
    }

}
