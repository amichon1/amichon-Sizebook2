package com.example.amichon_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView oldPersonsList;
    private PersonAdapter adapter;

    //------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gets old list of people when starting
        oldPersonsList = (ListView) findViewById(R.id.oldPersonsList);
        oldPersonsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                editPerson(bundle);
                adapter.notifyDataSetChanged();
            }
        });
    }

    //------------------------------------------

    //creates an intent of the editPerson class
    public void editPerson ( Bundle bundle) {
        Intent intent = new Intent(this, EditPerson.class);
        intent.putExtras(bundle);
        startActivity(intent);
        adapter.notifyDataSetChanged();
    }

    //------------------------------------------

    // creates an intent of the addPerson class
    public void addPerson(View view) {
        Intent intent = new Intent(this, AddPerson.class);
        startActivity(intent);
        adapter.notifyDataSetChanged();
    }

    //------------------------------------------

    //displays number of entries in application
    protected void onResume() {
        super.onResume();
        int numPersons = ((PeoplesApplication)getApplicationContext()).personsList.size();
        TextView textView = (TextView) this.findViewById(R.id.Persons);
        String personsCount = "Entries " + String.valueOf(numPersons);
        textView.setText(personsCount);
    }

    //------------------------------------------

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        adapter = new PersonAdapter(this, R.layout.record_list,((PeoplesApplication)getApplicationContext()).personsList);
        oldPersonsList.setAdapter(adapter);
    }
    //------------------------------------------

    //loads previous arraylist of persons from file
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(((PeoplesApplication)getApplicationContext()).FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            //http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylist
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Person>>() {}.getType();
            ((PeoplesApplication)getApplicationContext()).personsList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            ((PeoplesApplication)getApplicationContext()).personsList = new ArrayList<Person>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
