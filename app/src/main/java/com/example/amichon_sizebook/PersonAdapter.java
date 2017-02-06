package com.example.amichon_sizebook;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Alex on 2017-02-06.
 */

/**
 * creats a custom arrayList of type person
 */
public class PersonAdapter extends ArrayAdapter<Person> {

    public PersonAdapter(Context context, int resource, List<Person> Objects) {
        super(context, resource, Objects);
    }
}