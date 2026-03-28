package com.my.televip.virtuals.ui.Components;

import android.content.Context;
import android.view.View;

import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.androidx.ViewHolder;

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

    public void post(Runnable runnable) {
        XposedHelpers.callMethod(recyclerListView, AutomationResolver.resolve("RecyclerListView", "post", AutomationResolver.ResolverType.Method), runnable);
    }

    public void scrollToPosition(int position) {
        XposedHelpers.callMethod(recyclerListView, AutomationResolver.resolve("RecyclerListView", "scrollToPosition", AutomationResolver.ResolverType.Method), position);
    }

    public ViewHolder findViewHolderForAdapterPosition(int position) {
        return new ViewHolder(XposedHelpers.callMethod(recyclerListView, AutomationResolver.resolve("RecyclerListView", "findViewHolderForAdapterPosition", AutomationResolver.ResolverType.Method), position));
    }

    public View getRecyclerListView() {
       return (View) recyclerListView;
    }

}
