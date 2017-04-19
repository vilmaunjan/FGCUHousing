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

public class RatingPage extends AppCompatActivity {
    private CurrentUser CU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle data = getIntent().getExtras();
        CU = data.getParcelable("CurrentUser");

    }

    public void buttonOnClick(View v) {
        Button button = (Button)v;
        if (v == findViewById(R.id.btnRate)) { //go to residentAccess
            Intent Account = new Intent(getApplicationContext(), EventList.class);
            Account.putExtra("CurrentUser", CU);
            startActivity(Account);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
