package com.my.televip.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.my.televip.logging.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MessageDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SaveMessages.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MESSAGES = "messages";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MSG_ID = "msg_id";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_MESSAGE_EDITED = "message_Edited";
    public static final String COLUMN_MSG_COUNT = "msg_count";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_MESSAGES + " (" +
                    COLUMN_ID + " LONG, " +
                    COLUMN_MSG_ID + " INTEGER, " +
                    COLUMN_MSG_COUNT + " INTEGER, " +
                    COLUMN_MESSAGE + " TEXT, " +
                    COLUMN_MESSAGE_EDITED + " TEXT " +
                    ");";

    public MessageDatabase(Context context) {
        super(context, getDataBasePath(context), null, DATABASE_VERSION);
    }

    public static String getDataBasePath(Context context) {
        File appDir = new File(context.getDataDir(), "TeleVip");
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        return new File(appDir, DATABASE_NAME).getAbsolutePath();
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
        try {
            SQLiteDatabase database = getWritableDatabase();
            if (!searchMessage(id, msgId, message)) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_ID, id);
                values.put(COLUMN_MSG_ID, msgId);
                values.put(COLUMN_MESSAGE, message);

                int maxMsgCount = getMaxMessageCount(id, msgId);
                values.put(COLUMN_MSG_COUNT, maxMsgCount + 1);

                String formattedDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US).format(new Date());
                values.put(COLUMN_MESSAGE_EDITED, formattedDate);

                database.insert(TABLE_MESSAGES, null, values);
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }
    public boolean searchMessage(long id, int msgId, String message) {
        if (message == null) return false;

        try {
            SQLiteDatabase database = getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_MESSAGES +
                    " WHERE " + COLUMN_ID + " = ? AND " +
                    COLUMN_MSG_ID + " = ? AND " +
                    COLUMN_MESSAGE + " = ?";
            Cursor cursor = database.rawQuery(query, new String[]{
                    String.valueOf(id),
                    String.valueOf(msgId),
                    message
            });
            boolean exists = cursor.getCount() > 0;
            cursor.close();
            return exists;
        } catch (Throwable t) {
            Logger.e(t);
        }
        return false;
    }

    public boolean searchMessage(long id, int msgId) {
        try {
            SQLiteDatabase database = getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_MESSAGES +
                    " WHERE " + COLUMN_ID + " = ? AND " +
                    COLUMN_MSG_ID + " = ?";
            Cursor cursor = database.rawQuery(query, new String[]{
                    String.valueOf(id),
                    String.valueOf(msgId)
            });
            boolean exists = cursor.getCount() > 0;
            cursor.close();
            return exists;
        } catch (Throwable t) {
            Logger.e(t);
        }
        return false;
    }


    public String getMessage(long id, int msgId) {
        try {
            SQLiteDatabase database = getReadableDatabase();
            String query = "SELECT " + COLUMN_MESSAGE + " FROM " + TABLE_MESSAGES +
                    " WHERE " + COLUMN_ID + " = ? AND " + COLUMN_MSG_ID + " = ?";
            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id), String.valueOf(msgId)});
            String message = null;
            if (cursor.moveToFirst()) {
                message = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MESSAGE));
            }
            cursor.close();
            return message;
        } catch (Throwable t) {
            Logger.e(t);
        }
        return null;
    }

    public String getMessage(long id, int msgId, int msgCount) {
        try {
            SQLiteDatabase database = getReadableDatabase();
            String query = "SELECT " + COLUMN_MESSAGE + " FROM " + TABLE_MESSAGES +
                    " WHERE " + COLUMN_ID + " = ? AND " + COLUMN_MSG_ID + " = ? AND " + COLUMN_MSG_COUNT + " = ?";
            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id), String.valueOf(msgId), String.valueOf(msgCount)});
            String message = null;
            if (cursor.moveToFirst()) {
                message = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MESSAGE));
            }
            cursor.close();
            return message;
        } catch (Throwable t) {
            Logger.e(t);
        }
        return null;
    }

    public String getMessageEdited(long id, int msgId, int msgCount) {
        try {
            SQLiteDatabase database = getReadableDatabase();
            String query = "SELECT " + COLUMN_MESSAGE_EDITED + " FROM " + TABLE_MESSAGES +
                    " WHERE " + COLUMN_ID + " = ? AND " + COLUMN_MSG_ID + " = ? AND " + COLUMN_MSG_COUNT + " = ?";
            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id), String.valueOf(msgId), String.valueOf(msgCount)});
            String messageEdited = null;
            if (cursor.moveToFirst()) {
                messageEdited = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MESSAGE_EDITED));
            }
            cursor.close();
            return messageEdited;
        } catch (Throwable t) {
            Logger.e(t);
        }
        return null;
    }

    public int getMaxMessageCount(long id, int msgId) {
        int maxCount = 0;
        try {
            SQLiteDatabase database = getReadableDatabase();
            String query = "SELECT MAX(" + COLUMN_MSG_COUNT + ") as max_count FROM " + TABLE_MESSAGES +
                    " WHERE " + COLUMN_ID + " = ? AND " + COLUMN_MSG_ID + " = ?";
            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id), String.valueOf(msgId)});
            if (cursor.moveToFirst()) {
                maxCount = cursor.getInt(cursor.getColumnIndexOrThrow("max_count"));
            }
            cursor.close();
        } catch (Throwable t) {
            Logger.e(t);
        }
        return maxCount;
    }
}