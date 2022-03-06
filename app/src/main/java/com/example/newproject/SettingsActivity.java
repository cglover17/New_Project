package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

        initSettingsButton();
        initListButton();
        initNoteButton();
        initSettings();
        initSortByClick();
        initSortOrderClick();
    }

    private void initSettings() {
        String sortBy = getSharedPreferences("NoteAppPreferences",
                Context.MODE_PRIVATE).getString("sortfield", "subject");
        String sortOrder = getSharedPreferences("NoteAppPreferences",
                Context.MODE_PRIVATE).getString("sortorder", "ASC");

        RadioButton rbDate = findViewById(R.id.radDate);
        RadioButton rbPriority = findViewById(R.id.radPriority);
        RadioButton rbSubject = findViewById(R.id.radSubject);

        if (sortBy.equalsIgnoreCase("subject")) {
            rbSubject.setChecked(true);
        }
        else if (sortBy.equalsIgnoreCase("date")) {
            rbDate.setChecked(true);
        }
        else {
            rbPriority.setChecked(true);
        }

        RadioButton rbAscending = findViewById(R.id.radAscending);
        RadioButton rbDescending = findViewById(R.id.radDescending);
        if (sortOrder.equalsIgnoreCase("ASC")) {
            rbAscending.setChecked(true);
        }
        else {
            rbDescending.setChecked(true);
        }

    }

    private void initSortByClick() {
        RadioGroup rgSortBy = findViewById(R.id.radioGroup);
        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbSubject = findViewById(R.id.radSubject);
                RadioButton rbDate = findViewById(R.id.radDate);
                if (rbSubject.isChecked()) {
                    getSharedPreferences("NoteAppPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield", "subject").apply();
                }
                else if (rbDate.isChecked()) {
                    getSharedPreferences("NoteAppPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield", "date").apply();
                }
                else {
                    getSharedPreferences("NoteAppPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield", "priority").apply();
                }
            }
        });
    }

    private void initSortOrderClick() {
        RadioGroup rgSortOrder = findViewById(R.id.orderGroup);
        rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbAscending = findViewById(R.id.radAscending);
                if (rbAscending.isChecked()) {
                    getSharedPreferences("NoteAppPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortorder", "ASC").apply();
                }
                else {
                    getSharedPreferences("NoteAppPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortorder", "DESC").apply();
                }
            }
        });
    }

    private void initSettingsButton() {
        ImageButton ibSettings = findViewById(R.id.imageButtonSettings);
        ibSettings.setEnabled(false);
    }

    private void initListButton() {
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //reference created for current activity and which activity to start)
                Intent intent = new Intent(SettingsActivity.this, ListActivity.class);
                //intent flag set to alert the operating system to not make multiple copies of same activity

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initNoteButton() {
        ImageButton ibList = findViewById(R.id.imageButtonNote);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //reference created for current activity and which activity to start)
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                //intent flag set to alert the operating system to not make multiple copies of same activity

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}