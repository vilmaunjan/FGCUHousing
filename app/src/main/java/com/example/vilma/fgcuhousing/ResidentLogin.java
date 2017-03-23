package com.example.vilma.fgcuhousing;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.HousingContract;

public class ResidentLogin extends AppCompatActivity {

    //Creates a connection to database


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

        }
    }



}
