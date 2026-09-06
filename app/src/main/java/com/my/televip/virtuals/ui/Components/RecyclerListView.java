package com.my.televip.virtuals.ui.Components;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.dex.DexInjector;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.androidx.Adapter;

import com.my.televip.reflect.XReflect;

public class RecyclerListView {

    public final Object recyclerListView;

    public RecyclerListView(Context context){
        if (DexInjector.classLoader != null) {
            recyclerListView = XReflect.newInstance(ClassLoad.getClass(ClassNames.SETTINGS_ADAPTER_RECYCLER_LIST_VIEW, DexInjector.classLoader), context);
        } else {
            recyclerListView = new RecyclerView(context);
        }
    }

    public void setAdapter(Object adapter) {
        XReflect.callMethod(recyclerListView, AutomationResolver.resolve("RecyclerListView", "setAdapter", AutomationResolver.ResolverType.Method), adapter);
    }

    public Adapter getAdapter() {
        return new Adapter(XReflect.callMethod(recyclerListView, AutomationResolver.resolve("RecyclerListView", "getAdapter", AutomationResolver.ResolverType.Method)));
    }

    public void setBackgroundColor(int color) {
        ((View)recyclerListView).setBackgroundColor(color);
    }

    public void setVerticalScrollBarEnabled(boolean b) {
        ((View)recyclerListView).setVerticalScrollBarEnabled(b);
    }

    public void setLayoutManager(Object layout) {
        XReflect.callMethod(recyclerListView, AutomationResolver.resolve("RecyclerListView", "setLayoutManager", AutomationResolver.ResolverType.Method), layout);
    }

    public View getRecyclerListView() {
       return (View) recyclerListView;
    }

}
