package com.example.amichon_sizebook;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-02-06.
 */

/**
 * allows for a global variable of an ArrayList of persons
 * also allows for filename access globally
 */
public class PeoplesApplication extends Application {
    public ArrayList<Person> personsList = null;
    public PeoplesApplication() {
        personsList = new ArrayList<Person>();
    }
    public static final String FILENAME = "file.sav";
}
