package com.example.vilma.fgcuhousing;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;
import static com.example.vilma.fgcuhousing.data.logIn.LogIN;


public class MainActivity extends AppCompatActivity {
    /**
     * Needed to make sure the database is created and the fake values
     * inserted
     */
    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Database Created with Fake Info
         */
        db = new DbHandler(getApplicationContext());

    }


    public void buttonOnClick(View v) {
        Button button = (Button)v;
        if (v == findViewById(R.id.btnResident)) { //go to residentAccess
            startActivity(new Intent(getApplicationContext(), ResidentAccess.class));
        } else if(v == findViewById(R.id.btnStaff)) { //go to staffAccess
            //Current Staff login not up so this sends you to event list with an auto login feature
            CurrentUser admin = LogIN(getApplicationContext(),"cooper@fgcu.edu");
            Intent supa = new Intent(getApplicationContext(), ResidentAccess.class);
            supa.putExtra("CurrentUser", admin);
            startActivity(supa);
        }
    }


    public void imageOnClick(View i){
        if(i == findViewById(R.id.StarMode)) {
            try {
                /**
                 * Currently being used by me just to get awards up and running
                 */
                CurrentUser admin = LogIN(getApplicationContext(),"cooper@fgcu.edu");
                Intent supa = new Intent(getApplicationContext(), Awards.class);
                supa.putExtra("CurrentUser", admin);
                startActivity(supa);
            }catch (Exception e){
                Log.d("Admin", e.getMessage());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }
}
