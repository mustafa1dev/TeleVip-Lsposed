package com.my.televip.virtuals.SQLite;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.tgnet.NativeByteBuffer;

import de.robv.android.xposed.XposedHelpers;

public class SQLiteCursor {

    Object sqLiteCursor;

    public SQLiteCursor(Object obj){
        sqLiteCursor = obj;
    }

    public boolean next(){
        return (boolean) XposedHelpers.callMethod(sqLiteCursor, AutomationResolver.resolve("SQLiteCursor", "next", AutomationResolver.ResolverType.Method));
    }

    public NativeByteBuffer byteBufferValue(int columnIndex){
        return new NativeByteBuffer(XposedHelpers.callMethod(sqLiteCursor, AutomationResolver.resolve("SQLiteCursor", "byteBufferValue", AutomationResolver.ResolverType.Method), columnIndex));
    }

}
