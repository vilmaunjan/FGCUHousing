package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.vilma.fgcuhousing.data.DbHandler;

public class ResidentAccess extends AppCompatActivity {
    //Need this for Database
    DbHandler resi;
    SQLiteDatabase mm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_access);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Start's database
        resi = new DbHandler(this);
        //get's and editable database to add parameters
        mm = resi.getWritableDatabase();

        //Inserts some fake data
        //testing for adding to database a different way
        //from a different screen
        //This also sends a toast message whenever a data is added
        resi.addToUsers(this,"Pepe", "Lepu", 812964812,
                "Lepu@gmail.com", "passpass", "RD", "North Village");

        //closes database connection
        resi.close();
    }

    public void buttonOnClick(View v) {
        Button button = (Button)v;
        if (v == findViewById(R.id.btnRegisterResidentAccess)) { //go to residentRegister
            startActivity(new Intent(getApplicationContext(), ResidentRegister.class));
        } else if(v == findViewById(R.id.btnLoginResidentAccess)) { //go to residentLogin
            startActivity(new Intent(getApplicationContext(), ResidentLogin.class));
        }
    }


}
