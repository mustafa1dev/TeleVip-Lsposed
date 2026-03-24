package com.my.televip.virtuals.androidx;

import android.view.View;

import de.robv.android.xposed.XposedHelpers;

public class ViewHolder {

    Object viewHolder;

    public ViewHolder(Object view){
        viewHolder = view;
    }

    public View getItemView(){
        return (View) XposedHelpers.getObjectField(viewHolder, "itemView");
    }
    public int getAdapterPosition(){
        return (int)XposedHelpers.callMethod(viewHolder, "getAdapterPosition");
    }

}
