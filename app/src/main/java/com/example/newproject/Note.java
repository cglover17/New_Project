package com.example.newproject;

import java.sql.Time;
import java.sql.Timestamp;

public class Note {

    private int noteID;
    private String noteSubject;
    private String noteMessage;
    private String notePriority;
    private String timestamp;



    public Note() {
        noteID = -1;
    }


    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteSubject() {
        return noteSubject;
    }

    public void setNoteSubject(String noteSubject) {
        this.noteSubject = noteSubject;
    }

    public String getNoteMessage() {
        return noteMessage;
    }

    public void setNoteMessage(String noteMessage) {
        this.noteMessage = noteMessage;
    }

    public String getNotePriority() {
        return notePriority;
    }

    public void setNotePriority(String notePriority) {
        this.notePriority = notePriority;
    }

    public String getTimestamp(){ return timestamp;}

    public void setTimestamp(String timestamp){this.timestamp=timestamp;}
}



