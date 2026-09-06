package com.my.televip.virtuals.androidx;

import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class Adapter {

    private final Object adapter;

    public Adapter(Object adapter){
        this.adapter = adapter;
    }

    public void notifyItemChanged(int position) {
        XReflect.callMethod(adapter, AutomationResolver.resolve("RecyclerListView", "notifyItemChanged", AutomationResolver.ResolverType.Method), position);
    }

}
