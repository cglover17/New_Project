package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ArrayList<Note> notes;

    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int noteId = notes.get(position).getNoteID();
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            intent.putExtra("noteID", noteId);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initListButton();
        initSettingsButton();
        initNoteButton();

        String sortBy = getSharedPreferences("NoteAppPreferences",
                Context.MODE_PRIVATE).getString("sortfield", "notesubject");
        String sortOrder = getSharedPreferences("NoteAppPreferences",
                Context.MODE_PRIVATE).getString("sortorder", "ASC");

        NoteDataSource ds = new NoteDataSource(this);
        //ArrayList<Note> notes;

        try {

            ds.open();
            notes = ds.getNotes(sortBy, sortOrder);
            ds.close();
            RecyclerView noteList = findViewById(R.id.rvNotes);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            noteList.setLayoutManager(layoutManager);
            NoteAdapter noteAdapter = new NoteAdapter(notes);
            noteList.setAdapter(noteAdapter);
            noteAdapter.setOnItemClickListener(onItemClickListener);
        }
        catch (Exception e) {
            Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }
    }

    private void initListButton() {
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setEnabled(false);
    }

    private void initNoteButton() {
        ImageButton ibNote = findViewById(R.id.imageButtonNote);
        ibNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //reference created for current activity and which activity to start)
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                //intent flag set to alert the operating system to not make multiple copies of same activity

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initSettingsButton() {
        ImageButton ibList = findViewById(R.id.imageButtonSettings);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //reference created for current activity and which activity to start)
                Intent intent = new Intent(ListActivity.this, SettingsActivity.class);
                //intent flag set to alert the operating system to not make multiple copies of same activity

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}