package com.example.newproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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

    public int getLastContactID() {
        int lastId;
        try {
            String query = "Select MAX(_id) from notes";
            Cursor cursor = database.rawQuery(query,null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }


    public Note getSpecificNote(int noteId){
        Note note = new Note();
        String query = "SELECT * FROM notes WHERE _id=" + noteId;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            note.setNoteID(cursor.getInt(0));
            note.setNoteSubject(cursor.getString(1));
            note.setNoteMessage(cursor.getString(2));
            note.setNotePriority(cursor.getString(3));
            note.setTimestamp(Timestamp.valueOf(cursor.getString(4)));
            cursor.close();

        }
        return note;
    }



    public ArrayList<Note> getNotes(String sortField, String sortOrder) {
        ArrayList<Note> notes = new ArrayList<Note>();
        try {
            String query = "SELECT * FROM notes ORDER BY " + sortField + " " + sortOrder;
            Cursor cursor = database.rawQuery(query,null);

            Note newNote;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newNote = new Note();
                newNote.setNoteID(cursor.getInt(0));
                newNote.setNoteSubject(cursor.getString(1));
                newNote.setNoteMessage(cursor.getString(2));
                newNote.setNotePriority(cursor.getString(3));
                newNote.setTimestamp(Timestamp.valueOf(cursor.getString(4)));

                notes.add(newNote);
                cursor.moveToNext();

            }
            cursor.close();
        }
        catch (Exception e) {
            notes = new ArrayList<Note>();
        }
        return notes;
    }

    public boolean insertNote (Note n) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("notesubject", n.getNoteSubject());
            initialValues.put("notemessage", n.getNoteMessage());
            initialValues.put("priority", n.getNotePriority());
            initialValues.put("time", n.getTimestamp().toString());
            didSucceed = database.insert("notes", null, initialValues) > 0;

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
          //  updateValues.put("time", n.getTimestamp().toString());

            didSucceed = database.update("notes", updateValues, "_id=" + rowId,
                    null) > 0;
        }
        catch (Exception e) {
        }
        return didSucceed;
    }
    public boolean deleteSubject(int subjectId){
        boolean didDelete = false;
        try{
            didDelete = database.delete("contact", "_id=" + subjectId, null) > 0;
        }
        catch (Exception e){
        }
        return didDelete;
    }
}
