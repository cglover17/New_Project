package com.example.newproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NoteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "noteapp.db";
    private static final int DATABASE_VERSION = 3;

    private static final String CREATE_TABLE_CONTACT =
            "create table notes (_id integer primary key autoincrement, "
            + "notesubject text not null, notemessage text,"
                    + "priority text, time text);";

    public NoteDBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /* Log.w(NoteDBHelper.class.getName(),
                "Upgrading database from version" + oldVersion + " to "
        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);

        */

        try {
            db.execSQL("ALTER TABLE notes ADD COLUMN time text");
        }
        catch (Exception e) {

        }

    }


}
