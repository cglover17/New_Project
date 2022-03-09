package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.Timestamp;

public class MainActivity extends AppCompatActivity {

    private Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListButton();
        initSettingsButton();
        initNoteButton();
        initToggleButton();
        setForEditing(false);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            initNote(extras.getInt("noteID"));
        }
        else {
            currentNote = new Note();
        }

        initTextChangedEvents();
        initPriorityChanged();
        saveButton();

    }

   private void initNote (int id) {
        NoteDataSource ds = new NoteDataSource(MainActivity.this);
        try {
            ds.open();
            currentNote = ds.getSpecificNote(id);
            ds.close();
        }
        catch (Exception e) {
            Toast.makeText(this, "Load Contact Failed", Toast.LENGTH_LONG).show();
        }

        EditText editSubject = findViewById(R.id.subField);
        EditText editMessage = findViewById(R.id.noteField);
        RadioButton radLow = findViewById(R.id.radioLow);
        RadioButton radMed = findViewById(R.id.radioMedium);
        RadioButton radHigh = findViewById(R.id.radioHigh);

        editSubject.setText(currentNote.getNoteSubject());
        editMessage.setText(currentNote.getNoteMessage());

        if (currentNote.getNotePriority().equalsIgnoreCase("Low")) {
            radLow.setChecked(true);
        }
       else if (currentNote.getNotePriority().equalsIgnoreCase("Medium")) {
           radMed.setChecked(true);
       }
       else {
           radHigh.setChecked(true);
        }

    }

    private void saveButton() {
        Button saveButton = findViewById(R.id.butttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                NoteDataSource ds = new NoteDataSource(MainActivity.this);
                try {
                    ds.open();
                    if (currentNote.getNoteID() == -1) {

                        Long datetime = System.currentTimeMillis();
                        Timestamp timestamp = new Timestamp(datetime);
                        currentNote.setTimestamp(timestamp.toString());
                        wasSuccessful = ds.insertNote(currentNote);

                    }
                    else {
                        wasSuccessful = ds.updateNote(currentNote);

                    }
                    ds.close();
                }
                catch (Exception e) {
                    wasSuccessful = false;
                }

                if (wasSuccessful) {
                    ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
                    editToggle.toggle();
                    setForEditing(false);
                }
            }
        });

    }


    private void initTextChangedEvents() {
        final EditText etSubject = findViewById(R.id.subField);
        etSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentNote.setNoteSubject(etSubject.getText().toString());

            }
        });

        final EditText etMessage = findViewById(R.id.noteField);
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentNote.setNoteMessage(etMessage.getText().toString());

            }
        });
    }

    private void initPriorityChanged() {
        String lowPriority = "Low";
        String medPriority = "Medium";
        String highPriority = "High";

        RadioGroup rgPriority = findViewById(R.id.radPriority);
        rgPriority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbLow = findViewById(R.id.radioLow);
                RadioButton rbMedium = findViewById(R.id.radioMedium);

                if (rbLow.isChecked()) {
                    currentNote.setNotePriority(lowPriority);

                    //Boolean status = rbLow.isChecked();
                    //noteAdapter.setPriority3(status);
                }
               else if (rbMedium.isChecked()) {
                    currentNote.setNotePriority(medPriority);

                    //Boolean status = rbMedium.isChecked();
                    //noteAdapter.setPriority2(status);
                }
               else {
                   currentNote.setNotePriority(highPriority);

                }
            }
        });
    }


    private void initNoteButton() {
        ImageButton ibNote = findViewById(R.id.imageButtonNote);
        ibNote.setEnabled(false);
    }

    private void initListButton() {
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //reference created for current activity and which activity to start)
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
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
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                //intent flag set to alert the operating system to not make multiple copies of same activity

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initToggleButton() {
        final ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
        editToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setForEditing(editToggle.isChecked());

            }
        });
    }

    private void setForEditing(boolean enabled) {

       EditText editSubject = findViewById(R.id.subField);
       EditText editNote = findViewById(R.id.noteField);
       RadioButton radioLow = findViewById(R.id.radioLow);
       RadioButton radioMedium = findViewById(R.id.radioMedium);
       RadioButton radioHigh = findViewById(R.id.radioHigh);
       Button buttonSave = findViewById(R.id.butttonSave);


       buttonSave.setEnabled(enabled);
       editNote.setEnabled(enabled);
       radioLow.setEnabled(enabled);
       radioMedium.setEnabled(enabled);
       radioHigh.setEnabled(enabled);
       editSubject.setEnabled(enabled);

        if (enabled) {
            editSubject.requestFocus();
        }

    }

}