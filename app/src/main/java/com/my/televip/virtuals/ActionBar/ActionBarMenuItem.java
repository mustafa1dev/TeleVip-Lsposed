package com.my.televip.virtuals.ActionBar;

import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class ActionBarMenuItem {

    Object actionBarMenuItem;

    public ActionBarMenuItem(Object obj){
        actionBarMenuItem = obj;
    }

    public void lazilyAddSubItem(int id, int resId, String name){
        XposedHelpers.callMethod(actionBarMenuItem, AutomationResolver.resolve("ActionBarMenuItem", "lazilyAddSubItem", AutomationResolver.ResolverType.Method), id, resId, name);
    }
    public void addSubItem(int id, int resId, String name){
        XposedHelpers.callMethod(actionBarMenuItem, AutomationResolver.resolve("ActionBarMenuItem", "addSubItem", AutomationResolver.ResolverType.Method), id, resId, name);
    }

    public Object getActionBarMenuItem(){
        return  actionBarMenuItem;
    }
}
