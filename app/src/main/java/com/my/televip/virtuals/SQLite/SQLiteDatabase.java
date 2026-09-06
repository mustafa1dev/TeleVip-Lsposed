package com.my.televip.virtuals.SQLite;

import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class SQLiteDatabase {

    Object sqLiteDatabase;

    public SQLiteDatabase(Object obj){ sqLiteDatabase = obj; }

    public SQLiteCursor queryFinalized(String s, Object[] objects){
        return new SQLiteCursor(XReflect.callMethod(sqLiteDatabase, AutomationResolver.resolve("SQLiteDatabase", "queryFinalized", AutomationResolver.ResolverType.Method), s, objects));
    }

    public SQLitePreparedStatement executeFast(String s){
        return new SQLitePreparedStatement(XReflect.callMethod(sqLiteDatabase, AutomationResolver.resolve("SQLiteDatabase", "executeFast", AutomationResolver.ResolverType.Method), s));
    }

}
