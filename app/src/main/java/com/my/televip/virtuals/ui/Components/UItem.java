package com.my.televip.virtuals.ui.Components;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Utils;

import com.my.televip.reflect.XReflect;

public class UItem {

    Object uItem;

    public UItem(Object uItem){
        this.uItem = uItem;
    }

    public int getID(){
        return XReflect.getIntField(uItem, AutomationResolver.resolve("UItem", "id", AutomationResolver.ResolverType.Field));
    }

    public String getText(){
        return Utils.getFieldAsString(XReflect.getObjectField(uItem, AutomationResolver.resolve("UItem", "text", AutomationResolver.ResolverType.Field)));
    }

    public String getSubtext(){
        return Utils.getFieldAsString(XReflect.getObjectField(uItem, AutomationResolver.resolve("UItem", "subtext", AutomationResolver.ResolverType.Field)));
    }

    public Object getUItem(){
        return uItem;
    }
}
