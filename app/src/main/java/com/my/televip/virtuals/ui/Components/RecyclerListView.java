package com.my.televip.virtuals.ui.Components;

import android.content.Context;
import android.view.View;

import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class RecyclerListView {

    public final Object recyclerListView;

    public RecyclerListView(Context context){
        recyclerListView = XposedHelpers.newInstance(loadClass.getRecyclerListViewClass(), context);
    }

    public void setAdapter(Object adapter) {
        XposedHelpers.callMethod(recyclerListView, AutomationResolver.resolve("RecyclerListView", "setAdapter", AutomationResolver.ResolverType.Method), adapter);
    }

    public void setBackgroundColor(int color) {
        ((View)recyclerListView).setBackgroundColor(color);
    }

    public void setVerticalScrollBarEnabled(boolean b) {
        ((View)recyclerListView).setVerticalScrollBarEnabled(b);
    }

    public void setLayoutManager(Object layout) {
        XposedHelpers.callMethod(recyclerListView, AutomationResolver.resolve("RecyclerListView", "setLayoutManager", AutomationResolver.ResolverType.Method), layout);
    }

    public View getRecyclerListView() {
       return (View) recyclerListView;
    }

}
