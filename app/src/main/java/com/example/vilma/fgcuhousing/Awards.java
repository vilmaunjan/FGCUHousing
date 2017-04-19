package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;


public class Awards extends AppCompatActivity {

    private CurrentUser CU;
    //Need these whenever Connecting to database
    private SQLiteDatabase sqlDb;
    private DbHandler datCon;
    private Cursor cursor=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle data = getIntent().getExtras();
        CU = data.getParcelable("CurrentUser");

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                int position, long id) {

                startActivity(new Intent(getApplicationContext(), AwardInfo.class));

                  Toast.makeText(Awards.this, "" + position +"id is " +id,
                        Toast.LENGTH_SHORT).show();
                }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
