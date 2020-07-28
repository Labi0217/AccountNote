package com.sohee.accountnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Account_Note.db";
    public static final int DATABASE_VERSION = 1;
    public static final String NOTE_TITLE = "title";
    public static final String NOTE_COST ="cost";
    public static final String NOTE_CONTENT = "content1";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS note");
        onCreate(db);
    }

    public boolean insertNote(String title, String cost, String content1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("cost", cost);
        contentValues.put("content1", content1);

        db.insert("note", null, contentValues);
        return true;
    }
}
