package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.HousingContract;

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

    //Still needs work on this code --Andrew
    public void buttonOnClick(View v) {
        Button button = (Button)v;
        if (v == findViewById(R.id.btnLoginResidentLogin)) {

            EditText Email = (EditText) findViewById(R.id.resEmail);
            EditText Password = (EditText) findViewById(R.id.resPassword);

            //gets the paramater from the android screen and convert to string
            String emailEntry = Email.getText().toString();
            String passwordEntry = Password.getText().toString();

            //Will be the value of the password stored in the database
            String password = db.searchPassword(emailEntry);
            //If what the user entered is the correct password then move to EventList
            if(passwordEntry.equals(password)) {
                Intent i = new Intent(this, EventList.class);
                startActivity(i);
                Log.d("Caleb", " password");
            } else {
                Log.d("Caleb", "Wrong password");
            }
        }
    }
}
