package com.my.televip.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.my.televip.language.Language;

import java.io.File;

public class MessageDatabase extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "SaveMessages.db";
        private static final int DATABASE_VERSION = 1;

        public static final String TABLE_MESSAGES = "messages";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_MSG_ID = "msg_id";
        public static final String COLUMN_MESSAGE = "message";

        private static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_MESSAGES + " (" +
                        COLUMN_ID + " LONG, " +
                        COLUMN_MSG_ID + " INTEGER, " +
                        COLUMN_MESSAGE + " TEXT " +
                        ");";

        public MessageDatabase(Context context) {
            super(context, getDataBasePath(context), null, DATABASE_VERSION);
        }

        public static String getDataBasePath(Context context){
            File appDir = new File(context.getDataDir(), Language.strTelevip);
            if (!appDir.exists()){
                appDir.mkdirs();
            }
            return new File(appDir,DATABASE_NAME).getAbsolutePath();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
            onCreate(db);
        }

        public void addMessage(long id, int msgId, String message) {

            if (!searchMessage(id,msgId,message)) {

                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(COLUMN_ID, id);
                values.put(COLUMN_MSG_ID, msgId);
                values.put(COLUMN_MESSAGE, message);

                db.insert(TABLE_MESSAGES, null, values);
                db.close();
            }
        }

        public boolean searchMessage(long id, int msgId, String message) {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_MESSAGES +
                    " WHERE " + COLUMN_ID + " = ? AND " +
                    COLUMN_MSG_ID + " = ? AND " +
                    COLUMN_MESSAGE + " = ?";

            Cursor cursor = db.rawQuery(query, new String[]{
                    String.valueOf(id),
                    String.valueOf(msgId),
                    message
            });

            boolean exists = cursor.getCount() > 0;
            cursor.close();
            db.close();
            return exists;
        }

    public String getMessage(long id, int msgId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_MESSAGE + " FROM " + TABLE_MESSAGES +
                " WHERE " + COLUMN_ID + " = ? AND " + COLUMN_MSG_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id), String.valueOf(msgId)});
        String message = null;
        if (cursor.moveToFirst()) {
            message = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MESSAGE));
        }
        cursor.close();
        db.close();
        return message; // يرجع null إذا ما وجد
    }
}
