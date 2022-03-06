package com.example.newproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class NoteDataSource {

    private SQLiteDatabase database;
    private NoteDBHelper dbHelper;

    public NoteDataSource(Context context) {
        dbHelper = new NoteDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertNote (Note n) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("notesubject", n.getNoteSubject());
            initialValues.put("notemessage", n.getNoteMessage());
            initialValues.put("priority", n.getNotePriority());

        }
        catch  (Exception e) {

        }
        return didSucceed;

    }

    public boolean updateNote(Note n) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) n.getNoteID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("notesubject", n.getNoteSubject());
            updateValues.put("notemessage", n.getNoteMessage());
            updateValues.put("priority", n.getNotePriority());

            didSucceed = database.update("notes", updateValues, "_id" + rowId,
                    null) > 0;
        }
        catch (Exception e) {
        }
        return didSucceed;
    }
}
