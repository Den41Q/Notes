package com.example.notes;

import android.app.Application;

public class App extends Application {

    private static NoteRepository baseNotes;
    private static KeyStore keyStore;

    @Override
    public void onCreate() {
        super.onCreate();

        baseNotes = new BaseNotes(this);
        keyStore = new PinCheck(this);
    }

    public static NoteRepository getBaseNotes() {
        return baseNotes;
    }

    public static KeyStore getKeyStore() {
        return keyStore;
    }

}