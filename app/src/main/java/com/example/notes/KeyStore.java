package com.example.notes;

public interface KeyStore {
    boolean checkPin(String pin);
    void saveNew(String pin);
}
