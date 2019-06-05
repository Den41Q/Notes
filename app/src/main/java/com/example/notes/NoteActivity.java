package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.Calendar;


public class NoteActivity extends AppCompatActivity {

    private EditText inputDeadline;
    private MaterialCheckBox checkBoxDeadline;
    private EditText inputTitle;
    private EditText inputSubtitle;


    private NoteRepository baseNotes;
    private String idNote;
    private Note changedNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initView();
        noteChanges();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.saveNote:
                saveNote();
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void initView() {
        Toolbar toolbar = findViewById(R.id.toolbarNote);
        setSupportActionBar(toolbar);
        inputDeadline = findViewById(R.id.input_deadline);
        checkBoxDeadline = findViewById(R.id.deadlineCheckbox);
        inputTitle = findViewById(R.id.input_title);
        inputSubtitle = findViewById(R.id.input_subtitle);

        idNote = getIntent().hasExtra("idNote") ?
                getIntent().getStringExtra("idNote") : "";


        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        baseNotes = App.getBaseNotes();
    }

    public void createDeadline(View view) {

        final Calendar todayCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener onDateSet = (view1, year, month, dayOfMonth) -> {
            inputDeadline.setText(dayOfMonth + "." + (month + 1) + "." + year);
            if (!checkBoxDeadline.isChecked()) {
                inputDeadline.setText("");
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                onDateSet,
                todayCalendar.get(Calendar.YEAR),
                todayCalendar.get(Calendar.MONTH),
                todayCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void checkDeadline(View view) {
        if (!checkBoxDeadline.isChecked()) {
            inputDeadline.setText("");
        }
    }

    public void saveNote() {
        String title = inputTitle.getText().toString();
        String subtitle = inputSubtitle.getText().toString();
        String deadline = inputDeadline.getText().toString();

        if ("".equals(idNote)) {
            Note note = new Note(title, subtitle, deadline);
            baseNotes.saveNote(note);
            Toast.makeText(this, "Заметка добавлена", Toast.LENGTH_LONG).show();
        } else {
            baseNotes.updateNote(changedNote.getIdNote(), title, subtitle, deadline);
            Toast.makeText(this, "Заметка изменена", Toast.LENGTH_LONG).show();
        }
    }

    private void noteChanges() {
        if (!"".equals(idNote)) {
            changedNote = baseNotes.getNote(idNote);

            inputTitle.setText(changedNote.getTitle());
            inputSubtitle.setText(changedNote.getSubtitle());
            inputDeadline.setText(changedNote.getDeadline());
        }
    }
}