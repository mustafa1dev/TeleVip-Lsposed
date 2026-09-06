package com.my.televip.virtuals.androidx;

import android.view.View;

import com.my.televip.reflect.XReflect;

public class ViewHolder {

    Object viewHolder;

    public ViewHolder(Object view){
        viewHolder = view;
    }

    public View getItemView(){
        return (View) XReflect.getObjectField(viewHolder, "itemView");
    }
    public int getAdapterPosition(){
        return (int)XReflect.callMethod(viewHolder, "getAdapterPosition");
    }

}
