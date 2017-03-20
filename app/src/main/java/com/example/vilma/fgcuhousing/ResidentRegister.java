package com.example.vilma.fgcuhousing;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.HousingContract;

public class ResidentRegister extends AppCompatActivity {

    //Fields for input Andrew
    private EditText Name;
    private EditText Email;
    private EditText Password;
    private AutoCompleteTextView Building;
    private SQLiteDatabase mDb;
    private DbHandler datCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,BUILDINGS);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.buildings_list);
        textView.setAdapter(adapter);

    }

    private static final String[] BUILDINGS = new String[] {
            //All buildings need to be added
            "Eagle", "Osprey", "Palmetto", "Biscayne", "Everglades", //South Village
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", //North Lake
            "M", "N", "O", "P", "Q", "R", "S", "T", "Cypress", "Falcon",
            "Honors", "Pelican", "Oak", "Sandpiper", "Egret", "Mangrove",
            "Flamingo", "Manatee", "Pompano", "Marlin", "Panther", "Tarpon" //West Lake

    };

    public void buttonOnClick(View v) {
        Button button = (Button)v;
        if (v == findViewById(R.id.btnSubmit)) {
            //validate entry values and go to residentLogin
            //Still working on validating the entry's
            Name = (EditText) findViewById(R.id.txtName);
            Email = (EditText) findViewById(R.id.txtEmail);
            Password = (EditText) findViewById(R.id.txtPassword);
            Building = (AutoCompleteTextView) findViewById(R.id.buildings_list);

            //gets the paramater from the android screen and convert to string
            String nameEntry = Name.getText().toString();
            String emailEntry = Email.getText().toString();
            String passwordEntry = Password.getText().toString();
            String buildingEntry = Building.getText().toString();

            Log.d("Reg" , "Ther name entered is " + nameEntry);
            Log.d("Reg" , "Ther Email entered is " + emailEntry);
            Log.d("Reg" , "Ther Password entered is " + passwordEntry);
            Log.d("Reg" , "Ther Building entered is " + buildingEntry);

            //Sends to RegisterDataMethod to input into database
            RegisterData(nameEntry, emailEntry, passwordEntry, buildingEntry);

            //Still need to write validation code
            startActivity(new Intent(getApplicationContext(), ResidentLogin.class));
        }
    }

    //Void Method to insert data into database
    public void RegisterData(String nameInput, String emailInput, String passwordInput, String buildingInput){
        //How to get data from data base
        datCon = new DbHandler(this);
        mDb = datCon.getWritableDatabase();

        //Need content value to insert input into data base nicely
        ContentValues cv = new ContentValues();
        cv.put(HousingContract.ResidentEntry.COLUMN_USER_NAME, nameInput);
        cv.put(HousingContract.ResidentEntry.COLUMN_EMAIL, emailInput);
        cv.put(HousingContract.ResidentEntry.COLUMN_PASSWORD, passwordInput);
        cv.put(HousingContract.ResidentEntry.COLUMN_BUILDING, buildingInput);
        //Try's the insert into the database
        try{
            mDb.insert(HousingContract.ResidentEntry.TABLE_NAME, null, cv);
            Log.d("reg", "Insert Successful");
        }catch (SQLException e){
            Log.d("reg", "Insert Failedd");
        };

        /*
        Code to test DataBase
        DbTest.insertFakeData(mDb);

        Cursor cursor = getallData();
        Log.d("Cursor stuff" , "Cursor count is equal to " + cursor.getCount());
        cursor.moveToPosition(3);
        Log.d("cursor stuff", "At crusor index 0 we have " + cursor.getString(cursor.getColumnIndex(HousingContract.ResidentEntry.COLUMN_USER_NAME)));
        cursor.close();
        */


    }

    private Cursor getallData(){
        //Adds all the data from a database and adds it to a cursor
        return mDb.query(
                HousingContract.ResidentEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                HousingContract.ResidentEntry.COLUMN_USER_NAME
        );
    }

}
