package com.example.vilma.fgcuhousing;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.Event;
import com.example.vilma.fgcuhousing.data.EventAdapter;
import com.example.vilma.fgcuhousing.data.EventItem;
import com.example.vilma.fgcuhousing.data.HousingContract;

import java.util.ArrayList;

public class EventEdit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    //Need these whenever Connecting to database
    private SQLiteDatabase mDb;
    private DbHandler datCon;
    private CurrentUser CU;

    //variables declaration
    ListView listView;
    ArrayList<EventItem> arrayList=new ArrayList<>();
    EventAdapter eventAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setup Current User
        // Bundle data = getIntent().getExtras();
        //CU = data.getParcelable("CurrentUser");

        Spinner spinnerFilter = (Spinner) findViewById(R.id.spinnerFilter);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner_housing_options, android.R.layout.simple_spinner_item);

        spinnerFilter.setAdapter(adapter);
        spinnerFilter.setOnItemSelectedListener(this);


        //load database
        datCon = new DbHandler(this);

        //this try catch queries all the events in the database and displays it in EventList page
        try {
            Cursor cursor = datCon.QueryData("select * from " + HousingContract.EventEntry.TABLE_NAME);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        EventItem item = new EventItem();
                        item.setId(cursor.getString(0));
                        item.setTitle(cursor.getString(1));
                        item.setTime(cursor.getString(5));
                        item.setDate(cursor.getString(4));
                        item.setPoster(cursor.getString(7)); //doesnt work, need to load posters for events
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException e) {
        }
        eventAdapter = new EventAdapter(this, R.layout.custom_list_item, arrayList);
        listView = (ListView) findViewById(R.id.list_event_item);
        listView.setAdapter(eventAdapter);
        eventAdapter.notifyDataSetChanged();

        Bundle extras = getIntent().getExtras();
        final String function = getIntent().getStringExtra("function");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);

                String event_id = ((TextView) view.findViewById(R.id.tv_id)).getText().toString();

                if(function.equals("edit")){
                    startActivity(new Intent(getApplicationContext(), CreateEvent.class).putExtra("event_id", event_id));
                }
                else if(function.equals("delete")){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EventEdit.this);
                    alertDialogBuilder.setTitle("Really Delete?");
                    alertDialogBuilder.setMessage("Are you sure you want to delete this event?");
                    alertDialogBuilder.setNegativeButton(android.R.string.no, null);
                    alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                 @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    //*************************
                                    //here the event will be deleted from the database
                                    //use the String event_id to select correct item to delete
                                    //***********************************
                                    finish();
                                    startActivity(getIntent().putExtra("function", "delete"));

                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });

        eventAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Deleted",Toast.LENGTH_SHORT).show();

    }


    //protected Adapter initializedAdapter=null;
    // This method is used for dropdown spinner when it filters by housing options(north, south, west)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView spinnerDialogText = (TextView) view;
        String building = "";


        String selected = parent.getItemAtPosition(position).toString();

        //If i dont create this new arrayList, and use the existing arrayList, it duplicates the events
        ArrayList<EventItem> newArrayList=new ArrayList<>();
        if(spinnerDialogText.getText().equals("South Lake Village")){
            building = "South Village";
            Toast.makeText(this, "You selected " + spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();
        } else if(spinnerDialogText.getText().equals("North Lake Village")){
            building = "North Village";
            Toast.makeText(this, "You selected " + spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();
        } else if(spinnerDialogText.getText().equals("West Lake Village")){
            building = "West Village";
            Toast.makeText(this, "You selected " + spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();
        }

        if(!building.equals("")) {
            eventAdapter.clear(); //cleans adapter so that new data can be loaded
            try {
                Cursor cursor = datCon.QueryData("select * from " +
                        HousingContract.EventEntry.TABLE_NAME + " where " +
                        HousingContract.EventEntry.BUILDING + " = '" + building + "'");
                if (cursor != null) {
                    newArrayList.clear();
                    if (cursor.moveToFirst()) {
                        do {
                            EventItem newItem = new EventItem();
                            newItem.setId(cursor.getString(0));
                            newItem.setTitle(cursor.getString(1));
                            newItem.setTime(cursor.getString(5));
                            newItem.setDate(cursor.getString(4));
                            newItem.setPoster(cursor.getString(7));
                            newArrayList.add(newItem);
                        } while (cursor.moveToNext());
                    }
                }
            } catch (SQLException e) {
            }

            eventAdapter.addAll(newArrayList); //adds new arrayList of events
            eventAdapter.notifyDataSetChanged(); //notify changes to the adapter
        }
    }

    //Does nothing but required by implementing interface
    @Override
    public void onNothingSelected(AdapterView<?> parent) {    }

    //Method used when clicking on buttons
    public void buttonOnClick(View v) {
        Button button = (Button)v;
        //use if statement if you want to open something when clicking a button
        if (v == findViewById(R.id.btnSearch)) { //go to EventPage
            //startActivity(new Intent(getApplicationContext(), EventPage.class));
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
            startActivity(new Intent(getApplicationContext(), ResidentAccount.class));
        } else if (id == R.id.event_manager){
            startActivity(new Intent(getApplicationContext(), EventManager.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        EventEdit.super.onBackPressed();
                    }
                }).create().show();
    }

}
