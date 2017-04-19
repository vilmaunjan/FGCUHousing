package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class EventManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    //Method used when clicking on buttons
    public void buttonOnClick(View v) {
        Button button = (Button)v;
        //This should be changed so that when any buttonEvent is pressed it goes to EventPage
        //with its info.
        if (v == findViewById(R.id.btnCreate)) { //go to EventPage
            startActivity(new Intent(getApplicationContext(), CreateEvent.class));
        }
        else if (v == findViewById(R.id.btnEdit)) { //go to EventEdit page
            startActivity(new Intent(getApplicationContext(), EventEdit.class));
        }
        else if (v == findViewById(R.id.btnDelete)) { //go to EventPage
           // startActivity(new Intent(getApplicationContext(), CreateEvent.class));
        }
        else if (v == findViewById(R.id.btnFeedback)) { //go to EventPage
           // startActivity(new Intent(getApplicationContext(), CreateEvent.class));
        }
    }

}
