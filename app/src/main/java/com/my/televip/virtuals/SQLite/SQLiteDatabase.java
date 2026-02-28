package com.my.televip.virtuals.SQLite;

import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class SQLiteDatabase {

    Object sqLiteDatabase;

    public SQLiteDatabase(Object obj){ sqLiteDatabase = obj; }

    public SQLiteCursor queryFinalized(String s){
        return new SQLiteCursor(XposedHelpers.callMethod(sqLiteDatabase, AutomationResolver.resolve("SQLiteDatabase", "queryFinalized", AutomationResolver.ResolverType.Method), s, new Object[0]));
    }

}
