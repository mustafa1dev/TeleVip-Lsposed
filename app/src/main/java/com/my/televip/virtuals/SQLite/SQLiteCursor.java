package com.my.televip.virtuals.SQLite;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.tgnet.NativeByteBuffer;

import com.my.televip.reflect.XReflect;

public class SQLiteCursor {

    Object sqLiteCursor;

    public SQLiteCursor(Object obj){
        sqLiteCursor = obj;
    }

    public boolean next(){
        return (boolean) XReflect.callMethod(sqLiteCursor, AutomationResolver.resolve("SQLiteCursor", "next", AutomationResolver.ResolverType.Method));
    }

    public int intValue(int columnIndex) {
        return (int) XReflect.callMethod(sqLiteCursor, AutomationResolver.resolve("SQLiteCursor", "intValue", AutomationResolver.ResolverType.Method), columnIndex);
    }

    public long longValue(int columnIndex) {
        return (long) XReflect.callMethod(sqLiteCursor, AutomationResolver.resolve("SQLiteCursor", "longValue", AutomationResolver.ResolverType.Method), columnIndex);
    }

    public void dispose() {
        XReflect.callMethod(sqLiteCursor, AutomationResolver.resolve("SQLiteCursor", "dispose", AutomationResolver.ResolverType.Method));
    }

    public NativeByteBuffer byteBufferValue(int columnIndex){
        return new NativeByteBuffer(XReflect.callMethod(sqLiteCursor, AutomationResolver.resolve("SQLiteCursor", "byteBufferValue", AutomationResolver.ResolverType.Method), columnIndex));
    }

}
