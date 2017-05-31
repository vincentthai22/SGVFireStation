package com.thai.vincent.sgvfirestation.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Vincent on 5/27/2017.
 */

public class FirebaseManager {

    public static long menuItemIdIncrementor = 0;

    public static final String INDICA_STRING = "Indica",
            HYBRID_STRING = "Sativa",
            SATIVA_STRING = "Hybrid",
            MENU_STRING = "Menu";

    private static FirebaseManager ourInstance = new FirebaseManager();

    private FirebaseManager(){

    }

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static FirebaseManager ourInstance() {
        return ourInstance;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public DatabaseReference getMenuReference() {
        return database.getReference("menu");
    }

    public DatabaseReference getIndicaMenuReference() {
        return getMenuReference().child("indica");
    }

    public DatabaseReference getSativaMenuReference() {
        return getMenuReference().child("sativa");
    }

    public DatabaseReference getHybridMenuReference() {
        return getMenuReference().child("hybrid");
    }

    public DatabaseReference getConcentratesMenuReference() {
        return getMenuReference().child("concentrates");
    }

}
