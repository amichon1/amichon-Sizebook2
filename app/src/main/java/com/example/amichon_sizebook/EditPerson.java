package com.example.amichon_sizebook;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Alex on 2017-02-06.
 */

public class EditPerson extends AppCompatActivity {
    //all applicable fields initialized
    public Integer id;
    private EditText nameBody;
    private EditText dateBody;
    private EditText neckBody;
    private EditText bustBody;
    private EditText chestBody;
    private EditText waistBody;
    private EditText hipBody;
    private EditText inseamBody;
    private EditText commentBody;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        //set at activity_edit view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("pos");
        Button deleteButton = (Button) findViewById(R.id.delete);

        //delete button removes personList
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                ((PeoplesApplication) getApplicationContext()).personsList.remove(((PeoplesApplication) getApplicationContext()).personsList.get(id));
                saveInFile();
            }
        });

        //------------------------------------------

        Button saveButton = (Button) findViewById(R.id.saveChanges);
        // allow button to save changes in field
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Person person = ((PeoplesApplication)getApplicationContext()).personsList.get(id);



                dateBody = (EditText) findViewById(R.id.dateField);
                nameBody = (EditText) findViewById(R.id.nameField);
                neckBody = (EditText) findViewById(R.id.neckField);
                bustBody = (EditText) findViewById(R.id.bustField);
                chestBody = (EditText) findViewById(R.id.chestField);
                waistBody = (EditText) findViewById(R.id.waistField);
                hipBody = (EditText) findViewById(R.id.hipField);
                inseamBody = (EditText) findViewById(R.id.inseamField);
                commentBody = (EditText) findViewById(R.id.commentField);

                //------------------------------------------

                String date = dateBody.getText().toString();
                String name = nameBody.getText().toString();
                String neck = neckBody.getText().toString();
                String bust = bustBody.getText().toString();
                String chest = chestBody.getText().toString();
                String waist = waistBody.getText().toString();
                String hip = hipBody.getText().toString();
                String inseam = inseamBody.getText().toString();
                String comment = commentBody.getText().toString();


                //------------------------------------------


                // if the field is not empty, update it
                if (date != null && !date.isEmpty()) {
                    person.setDate(date);
                }
                if(name != null && !name.isEmpty()) {
                    person.setName(name);
                }
                if(neck != null && !neck.isEmpty()) {
                    person.setNeckSize(neck);
                }
                if(bust != null && !bust.isEmpty()) {
                    person.setBustSize(bust);
                }
                if(chest != null && !chest.isEmpty()) {
                    person.setChestSize(chest);
                }
                if(waist != null && !waist.isEmpty()) {
                    person.setWaistSize(waist);
                }
                if(hip != null && !hip.isEmpty()) {
                    person.setHipSize(hip);
                }
                if(inseam != null && !inseam.isEmpty()) {
                    person.setInseamSize(inseam);
                }
                if(comment != null && !comment.isEmpty()) {
                    person.setComment(comment);
                }

                //------------------------------------------

                //call save
                saveInFile();
            }
        });
    }

    //saves newly added information to file
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(((PeoplesApplication) getApplicationContext()).FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(((PeoplesApplication) getApplicationContext()).personsList, out);
            out.flush();

            fos.close();
        }
        catch (FileNotFoundException e) {throw new RuntimeException();}
        catch (IOException e) {throw new RuntimeException();}
    }

    //loads from file
    //fromJson doenst want to work
    private void  loadFromFile() {
        try {
            FileInputStream fis = openFileInput(((PeoplesApplication) getApplicationContext()).FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt //
            //gson.fromJson(personsList, in);

            fis.close();
        }
        //throws exception if FileNotFound or IOError
        catch (FileNotFoundException e) {throw new RuntimeException();}
        catch (IOException e) {throw new RuntimeException();}
    }

}
