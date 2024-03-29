package com.example.notes;

import android.app.Application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class App extends Application {

    private static NoteRepository baseNotes;
    private static KeyStore keyStore;
    private static DateFormat dateFormat;

    @Override
    public void onCreate() {
        super.onCreate();

        baseNotes = new BaseNotes(this);
        keyStore = new PinCheck(this);
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }

    public static NoteRepository getBaseNotes() {
        return baseNotes;
    }

    public static KeyStore getKeyStore() {
        return keyStore;
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }
}