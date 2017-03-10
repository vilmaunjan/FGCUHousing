package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class ResidentRegister extends AppCompatActivity {

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
        if (v == findViewById(R.id.btnSubmit)) { //validate entry values and go to residentLogin
            //Still need to write validation code
            startActivity(new Intent(getApplicationContext(), ResidentLogin.class));
        }
    }

}
