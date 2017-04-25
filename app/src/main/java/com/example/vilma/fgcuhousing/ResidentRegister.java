package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.User;
import com.example.vilma.fgcuhousing.data.Verify;

import static com.example.vilma.fgcuhousing.data.logIn.LogIN;

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

        //Adapter for building list drop down on autocomplete
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,BUILDINGS);

        //Assigning inputted values
        Name = (EditText) findViewById(R.id.accountName);
        Email = (EditText) findViewById(R.id.txtEmail);
        Password = (EditText) findViewById(R.id.txtPassword);
        PasswordVerify = (EditText) findViewById(R.id.txtPasswordVerify);
        Building = (AutoCompleteTextView) findViewById(R.id.buildings_list);

        Building.setAdapter(adapter);

    }

    /**
     * Building List
     */
    private static final String[] BUILDINGS = new String[] {
            //All buildings need to be added
            "Eagle", "Osprey", "Palmetto", "Biscayne", "Everglades", //South Village
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", //North Lake
            "M", "N", "O", "P", "Q", "R", "S", "T", "Cypress", "Falcon",
            "Honors", "Pelican", "Oak", "Sandpiper", "Egret", "Mangrove",
            "Flamingo", "Manatee", "Pompano", "Marlin", "Panther", "Tarpon" //West Lake

    };

    @Override
    public void onBackPressed() {
        Intent residentChoice = new Intent(getApplicationContext(), ResidentAccess.class);
        startActivity(residentChoice);
    }

    public void buttonOnClick(View v) {
        Button button = (Button)v;
        if (v == findViewById(R.id.btnSubmit)) {

            //gets the paramater from the android screen and convert to string
            String nameEntry = Name.getText().toString();
            String emailEntry = Email.getText().toString();
            String passwordEntry = Password.getText().toString();
            String passwordVerify = PasswordVerify.getText().toString();
            String buildingEntry = Building.getText().toString();

            /**
             * code to verify registration parameters
             */
            Verify ver = new Verify(getApplicationContext(), nameEntry, emailEntry, passwordEntry, passwordVerify);

            //if verified
            if(ver.Verifier() && !db.emailCheck(emailEntry)) {
                if(passwordEntry.length() > 6 && passwordEntry.length() < 14) {
                    //Inserts user information into user class.
                    User usr = new User();
                    usr.setName(nameEntry);
                    usr.setEmail(emailEntry);
                    usr.setPassword(passwordEntry);
                    usr.setBuilding(buildingEntry);

                    //Inserts New User into the database
                    db.insertUser(this, usr);
                    //Creates intent to go to next activity
                    Intent CU = new Intent(getApplicationContext(), ResidentLogin.class);
                    CurrentUser Current = LogIN(getApplicationContext(), emailEntry);
                    //add's a new Current user to be passed
                    CU.putExtra("CurrentUser", Current);
                    //Moves to next Activity
                    startActivity(CU);
                }else{
                    Toast.makeText(getApplicationContext(), "Password should be between 6-14 " +
                                    "characters",
                            Toast.LENGTH_SHORT).show();
                }
            }
            else{
                //popup with "'feild' is invalid"
                Toast.makeText(getApplicationContext(), "Email Already Exists",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


}
