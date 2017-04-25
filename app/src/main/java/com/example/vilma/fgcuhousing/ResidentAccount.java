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

public class ResidentAccount extends AppCompatActivity {

    private CurrentUser CU;
    private EditText Name, Email, Password;
    private AutoCompleteTextView Building;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**
         * Holds all the user information in CU
         */
        Bundle data = getIntent().getExtras();
        CU = data.getParcelable("CurrentUser");

        //Adapter for building list drop down on autocomplete
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,BUILDINGS);

        Name = (EditText) findViewById(R.id.txtName);
        Email = (EditText) findViewById(R.id.txtEmail);
        Password = (EditText) findViewById(R.id.txtPassword);
        Building = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        Building.setAdapter(adapter);

        setProfile();
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

    public void setProfile(){
        Name.setText(CU.getName());
        Email.setText(CU.getEmail());
        Password.setText(CU.getPassword());
        Building.setText(CU.getBuilding());
    }

    //Method used when clicking on buttons
    public void buttonOnClick(View v) {
        Button button = (Button)v;
        //This should be changed so that when any buttonEvent is pressed it goes to EventPage
        //with its info.
        if (v == findViewById(R.id.btnAwards)) { //go to EventPage
            Intent awards = new Intent(getApplicationContext(), Awards.class);
            awards.putExtra("CurrentUser", CU);
            startActivity(awards);
        }else if(v == findViewById(R.id.btnUpdate)){
            DbHandler user = new DbHandler(this);
            CU.setName(Name.getText().toString());
            CU.setEmail(Email.getText().toString());
            CU.setPassword(Password.getText().toString());
            CU.setBuilding(Building.getText().toString());
            boolean status = user.updateProfile(CU);
            if(status){

                Toast.makeText(getApplicationContext(), "UserAccount Updated!",
                        Toast.LENGTH_SHORT).show();
            }else{

                Toast.makeText(getApplicationContext(), "UserAccount UpdateFailed",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    public void onBackPressed() {
        Intent Account = new Intent(getApplicationContext(), EventList.class);
        Account.putExtra("CurrentUser", CU);
        startActivity(Account);
    }
}
