package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class EventPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void buttonOnClick(View v) {
        Button button = (Button)v;
        if (v == findViewById(R.id.btnCheckIn)) { //when checkin button is pressed
            button.setVisibility(View.GONE); //changes text to check out
            button.setEnabled(false); //changes text to check out
            Button checkOut = (Button) findViewById(R.id.btnCheckout);
            checkOut.setVisibility(View.VISIBLE);
            checkOut.setEnabled(true);

            //timer should start and should stop when check out button is pressed
        }
        else if (v == findViewById(R.id.btnCheckout)){ //when 'checkout' button is pressed
            startActivity(new Intent(getApplicationContext(), RatingPage.class));
        }
    }

}
