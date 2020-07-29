package com.sohee.accountnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Account_Note.db";
    public static final String NOTE_TABLE_NAME = "note";
    public static final String NOTE_ID = "id";
    public static final int DATABASE_VERSION = 1;
    public static final String NOTE_TITLE = "title";
    public static final String NOTE_COST ="cost";
    public static final String NOTE_CONTENT = "content1";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table note " +
                        "(id integer primary key,title text, cost Text, content Text)"
        );
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

    public Cursor getData(int id) {
        SQLiteDatabase  db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from note where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, NOTE_TABLE_NAME);
        return numRows;
    }

    public boolean updateNote(Integer id, String title, String cost, String content1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("cost", cost);
        contentValues.put("content1", content1);

        db.update("note", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteNote(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("note",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList getAllNote() {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from note", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(NOTE_ID))+" "+
                    res.getString(res.getColumnIndex(NOTE_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }


}
