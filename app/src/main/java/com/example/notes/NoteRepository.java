package com.example.notes;

import java.util.List;

public interface NoteRepository {
    List<Note> getNotes();

    Note getNote(String idNote);
    void saveNote(Note note);
    void updateNote(String idNote, String title, String subtitle, String deadline);
    void deleteNote(Note note);
}
