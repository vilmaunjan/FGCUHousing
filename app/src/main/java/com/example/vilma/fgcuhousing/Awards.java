package com.example.vilma.fgcuhousing;

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

import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.DbTest;

public class Awards extends AppCompatActivity {

    //Need these whenever Connecting to database
    private SQLiteDatabase sqlDb;
    private DbHandler datCon;
    private Cursor cursor=null;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Start's database
        datCon = new DbHandler(this);
        //get's and editable database to add parameters
        sqlDb = datCon.getWritableDatabase();
        //Inserts some fake data
        DbTest.insertFakeData(sqlDb);

        datCon = new DbHandler(getApplicationContext());
        sqlDb = datCon.getReadableDatabase();

        //Getting all awards data from database
        cursor = datCon.getAwardsInfo();

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                int position, long id) {
                  Toast.makeText(Awards.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                }
        });

        while(cursor.moveToNext()){
            String s = "awards";
            // String id = cursor.getString(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            String image = cursor.getString(3);

            //    Button currentButton = new Button(name);

            Log.d(s," name: " + name
                    + " description : "+ description +" image : "+ image );

            //    currentButton.setText(name);
        }




        //Closes database connection
        datCon.close();
    }

}
