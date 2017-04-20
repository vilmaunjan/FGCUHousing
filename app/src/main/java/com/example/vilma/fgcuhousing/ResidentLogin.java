package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;

import static com.example.vilma.fgcuhousing.data.logIn.LogIN;


public class ResidentLogin extends AppCompatActivity {

    //Creates a connection to database
    DbHandler db = new DbHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void buttonOnClick(View v) {
        Button button = (Button)v;
        if (v == findViewById(R.id.btnLoginResidentLogin)) {

            /**
             * get's the values in the email and password fields
             */

            EditText Email = (EditText) findViewById(R.id.resEmail);
            EditText Password = (EditText) findViewById(R.id.resPassword);

            //gets the paramater from the android screen and convert to string
            String emailEntry = (Email.getText().toString()).trim();
            String passwordEntry = (Password.getText().toString()).replaceAll("[\\s]+$", "") ;
            Log.d("regexPlayground", passwordEntry);

            //Will be the value of the password stored in the database
            boolean password = db.searchPassword(emailEntry,passwordEntry);
            //If what the user entered is the correct password then move to EventList
            if(password) {

                Intent i = new Intent(getApplicationContext(), EventList.class);
                //Use to create current user
                CurrentUser usr = LogIN(getApplicationContext(),emailEntry);
                //Adds the Current user data to be passed
                i.putExtra("CurrentUser", usr);
                //Go to the next activity
                startActivity(i);

                Log.d("Caleb", " password");
            } else {
                Toast.makeText(getApplicationContext(), "Incorrect Password or EmailAddress",
                        Toast.LENGTH_SHORT).show();
                Log.d("Caleb", "Wrong password");
            }
        }
    }


}
