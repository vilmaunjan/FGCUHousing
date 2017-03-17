package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
    private SQLiteDatabase mdb;

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
            //Log is like sysout
            Log.d("reg", "Is it reaching here??");
            //Starts the database helper
            DbHandler housedp = new DbHandler(this);

            EditText Email = (EditText) findViewById(R.id.resEmail);
            EditText Password = (EditText) findViewById(R.id.resPassword);

            //gets the paramater from the android screen and convert to string
            String emailEntry = Email.getText().toString();
            String passwordEntry = Password.getText().toString();

            //Lets you reads whats in the database
            mdb = housedp.getReadableDatabase();
            //Cursors are used to go through the data can make code shorter but just testing at this point
            Cursor cusor = getallData();
            cusor.moveToFirst();
            //Trying to get some validation going right now but minor problems searching through the cursor
            while(cusor != null){
                String e = cusor.getString(cusor.getColumnIndex(HousingContract.HousingEntry.COLUMN_EMAIL));
                String p = cusor.getString(cusor.getColumnIndex(HousingContract.HousingEntry.COLUMN_PASSWORD));
                if(e == emailEntry){
                    Log.d("reg", "Found a Match");
                    if(p == passwordEntry){
                        Log.d("reg", "Password is Correct");
                        break;
                    }
                }
                cusor.moveToNext();
                if(cusor.isLast()){
                    break;
                }
            }

            //Log.d("reg","What is at this point? " +cusor.getColumnIndex(emailEntry));

            //Log.d("reg", "the amount of tables returned is " + cusor.getCount());

            //Still need to write validation code
            //startActivity(new Intent(getApplicationContext(), ResidentAccess.class));

        }
    }

    //Can get rid of this later to amke shroter code but here for testing reasons
    private Cursor getallData(){
        //Adds all the data from a database and adds it to a cursor
        return mdb.query(
                HousingContract.HousingEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                HousingContract.HousingEntry.COLUMN_USER_NAME
        );
    }

}
