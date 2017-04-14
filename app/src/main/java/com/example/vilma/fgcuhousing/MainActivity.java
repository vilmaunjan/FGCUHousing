package com.example.vilma.fgcuhousing;


import android.content.Intent;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.database.Cursor;

import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.DbTest;


public class MainActivity extends AppCompatActivity {
    //Need these whenever Connecting to database
    private SQLiteDatabase mDb;
    private DbHandler datCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Start's database
        datCon = new DbHandler(this);
        //get's and editable database to add parameters
        mDb = datCon.getWritableDatabase();
        //Inserts some fake data
        DbTest.insertFakeData(mDb);

        //Closes database connection
        datCon.close();
    }


    public void buttonOnClick(View v) {
        Button button = (Button)v;
        if (v == findViewById(R.id.btnResident)) { //go to residentAccess
            startActivity(new Intent(getApplicationContext(), ResidentAccess.class));
        } else if(v == findViewById(R.id.btnStaff)) { //go to staffAccess
            startActivity(new Intent(getApplicationContext(), EventList.class));
        }
    }



}
