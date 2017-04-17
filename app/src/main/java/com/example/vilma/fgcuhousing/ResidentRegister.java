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
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.HousingContract;
import com.example.vilma.fgcuhousing.data.User;
import com.example.vilma.fgcuhousing.data.Verify;

public class ResidentRegister extends AppCompatActivity {

    //Fields for input Andrew
    private EditText Name;
    private EditText Email;
    private EditText Password;
    private EditText PasswordVerify;
    private AutoCompleteTextView Building;

    DbHandler db = new DbHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,BUILDINGS);

        Name = (EditText) findViewById(R.id.txtName);
        Email = (EditText) findViewById(R.id.txtEmail);
        Password = (EditText) findViewById(R.id.txtPassword);
        PasswordVerify = (EditText) findViewById(R.id.txtPasswordVerify);
        Building = (AutoCompleteTextView) findViewById(R.id.buildings_list);

        Building.setAdapter(adapter);

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

            //gets the paramater from the android screen and convert to string
            String nameEntry = Name.getText().toString();
            String emailEntry = Email.getText().toString();
            String passwordEntry = Password.getText().toString();
            String passwordVerify = PasswordVerify.getText().toString();
            String buildingEntry = Building.getText().toString();

            Verify ver = new Verify(getApplicationContext(), nameEntry, emailEntry, passwordEntry, passwordVerify);

            if(ver.Verifier()) {
                //Inserts user information into user class.
                User usr = new User();
                usr.setName(nameEntry);
                usr.setEmail(emailEntry);
                usr.setPassword(passwordEntry);
                usr.setBuilding(buildingEntry);

                //Inserts New User into the database
                db.insertUser(this, usr);
                //Moves to next Activity
                startActivity(new Intent(getApplicationContext(), EventList.class));
            }
            else{
                //popup with "'feild' is invalid"
                Toast.makeText(getApplicationContext(), "Email Already Exists",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


}
