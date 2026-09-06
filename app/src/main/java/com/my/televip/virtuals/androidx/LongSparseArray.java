package com.my.televip.virtuals.androidx;

import com.my.televip.obfuscate.AutomationResolver;

import java.util.ArrayList;

import com.my.televip.reflect.XReflect;

public class LongSparseArray {

    Object longSparseArray;

    public LongSparseArray(Object longSparseArray) {
        this.longSparseArray = longSparseArray;
    }

    public ArrayList<Object> get(long id){
        return (ArrayList<Object>) XReflect.callMethod(longSparseArray, AutomationResolver.resolve("LongSparseArray", "get", AutomationResolver.ResolverType.Method), id);
    }
}
