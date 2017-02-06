package com.example.amichon_sizebook;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * Created by Alex on 2017-02-06.
 */

public class AddPerson extends AppCompatActivity {
    private EditText nameBody;
    private EditText dateBody;
    private EditText neckBody;
    private EditText bustBody;
    private EditText chestBody;
    private EditText waistBody;
    private EditText hipBody;
    private EditText inseamBody;
    private EditText commentBody;

//------------------------------------------

    /**
     * uses activity_input when created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                nameBody = (EditText) findViewById(R.id.nameField);
                String name = nameBody.getText().toString();

                // if name field is blank, show error message
                if (name.matches("")) {
                    Toast.makeText(getApplicationContext(), "Need Name", Toast.LENGTH_LONG).show();
                    return;
                }

                //------------------------------------------
                //finds each field
                Person person = new Person(name);

                dateBody = (EditText) findViewById(R.id.dateField);
                neckBody = (EditText) findViewById(R.id.neckField);
                bustBody = (EditText) findViewById(R.id.bustField);
                chestBody = (EditText) findViewById(R.id.chestField);
                waistBody = (EditText) findViewById(R.id.waistField);
                hipBody = (EditText) findViewById(R.id.hipField);
                inseamBody = (EditText) findViewById(R.id.inseamField);
                commentBody = (EditText) findViewById(R.id.commentField);


                //------------------------------------------

                //changes each field to a string
                String date = dateBody.getText().toString();
                String neckText = neckBody.getText().toString();
                String bustText = bustBody.getText().toString();
                String chestText = chestBody.getText().toString();
                String waistText = waistBody.getText().toString();
                String hipText = hipBody.getText().toString();
                String inseamText = inseamBody.getText().toString();
                String commentText = commentBody.getText().toString();


                //------------------------------------------

                // If field is empty, write Empty
                if (date.matches("")) {person.setDate("Empty");}
                else {person.setDate(date);}

                if (neckText.matches("")) {person.setNeckSize("Empty");}
                else {
                    neckText = String.format("%.1f", Float.parseFloat(neckText));
                    person.setNeckSize(neckText);
                }

                if (bustText.matches("")) {person.setBustSize("Empty");}
                else {
                    bustText = String.format("%.1f", Float.parseFloat(bustText));
                    person.setBustSize(bustText);
                }

                if (chestText.matches("")) {person.setChestSize("Empty");}
                else {
                    chestText = String.format("%.1f", Float.parseFloat(chestText));
                    person.setChestSize(chestText);
                }

                if (waistText.matches("")) {person.setWaistSize("Empty");}
                else {
                    waistText = String.format("%.1f", Float.parseFloat(waistText));
                    person.setWaistSize(waistText);
                }

                if (hipText.matches("")) {person.setHipSize("Empty");}
                else {
                    hipText = String.format("%.1f", Float.parseFloat(hipText));
                    person.setHipSize(hipText);
                }

                if (inseamText.matches("")) {person.setInseamSize("Empty");}
                else {
                    inseamText = String.format("%.1f", Float.parseFloat(inseamText));
                    person.setInseamSize(inseamText);
                }

                if (commentText.matches("")) {person.setComment("Empty");}
                else {
                    commentText = String.format("%.1f", Float.parseFloat(commentText));
                    person.setComment(commentText);
                }

                // add a person
                ((PeoplesApplication)getApplicationContext()).personsList.add(person);
                saveInFile();
            }
        });
    }

    //------------------------------------------
    //save personslist to file
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(((PeoplesApplication)getApplicationContext()).FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(((PeoplesApplication)getApplicationContext()).personsList, out);
            out.flush();

            fos.close();
        }

        catch (FileNotFoundException e) {throw new RuntimeException();}
        catch (IOException e) {throw new RuntimeException(); }
    }
}