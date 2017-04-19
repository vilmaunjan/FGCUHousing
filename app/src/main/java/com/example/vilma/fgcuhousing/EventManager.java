package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.vilma.fgcuhousing.data.CurrentUser;

public class EventManager extends AppCompatActivity {

    private CurrentUser CU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle data = getIntent().getExtras();
        CU = data.getParcelable("CurrentUser");


    }

    //Method used when clicking on buttons
    public void buttonOnClick(View v) {
        Button button = (Button)v;
        //This should be changed so that when any buttonEvent is pressed it goes to EventPage
        //with its info.
        if (v == findViewById(R.id.btnCreate)) { //go to EventPage
            Intent event = new Intent(getApplicationContext(), CreateEvent.class);
            event.putExtra("CurrentUser", CU);
            startActivity(event);
        }
        else if (v == findViewById(R.id.btnEdit)) { //go to EventEdit page
            String function = "edit";
            Intent event = new Intent(getApplicationContext(), EventEdit.class);
            event.putExtra("CurrentUser", CU);
            event.putExtra("function", function);
            startActivity(event);
        }
        else if (v == findViewById(R.id.btnDelete)) { //go to EventPage
            String function = "delete";
            Intent event = new Intent(getApplicationContext(), EventEdit.class);
            event.putExtra("CurrentUser", CU);
            event.putExtra("function", function);
            startActivity(event);
        }
        else if (v == findViewById(R.id.btnFeedback)) { //go to EventPage
           // startActivity(new Intent(getApplicationContext(), CreateEvent.class));
        }
    }

    @Override
    public void onBackPressed() {
        Intent e = new Intent(getApplicationContext(), EventList.class);
        e.putExtra("CurrentUser", CU);
        finish();
        startActivity(e);
    }
}
