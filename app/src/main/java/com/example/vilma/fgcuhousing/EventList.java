package com.example.vilma.fgcuhousing;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.EventAdapter;
import com.example.vilma.fgcuhousing.data.EventItem;
import com.example.vilma.fgcuhousing.data.HousingContract;

import java.util.ArrayList;

public class EventList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Need these whenever Connecting to database
    private SQLiteDatabase mDb;
    private DbHandler datCon;
    private CurrentUser CU;

    //variables declaration
    private ListView listView;
    private EventAdapter eventAdapter;
    private ArrayList<EventItem> arrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setups Current User
        Bundle data = getIntent().getExtras();
        CU = data.getParcelable("CurrentUser");

        Log.d("CurrentUser", "Name : " + CU.getName()+ " Password: " +CU.getPassword()+ " Account type :" + CU.getAccountType() );

        Spinner spinnerFilter = (Spinner) findViewById(R.id.spinnerFilter);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner_housing_options, android.R.layout.simple_spinner_item);

        spinnerFilter.setAdapter(adapter);
        spinnerFilter.setOnItemSelectedListener(this);

        //Code for the award button on bottom
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionAwards);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent awards = new Intent(getApplicationContext(), Awards.class);
                awards.putExtra("CurrentUser", CU);
                startActivity(awards);
            }
        });

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

        //When clicking on an events, leads to the event info page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Not sure if this is used
                Object listItem = listView.getItemAtPosition(position);

                //gets the event id
                String event_id = ((TextView) view.findViewById(R.id.tv_id)).getText().toString();
                Toast.makeText(EventList.this, event_id, Toast.LENGTH_SHORT).show();

                //Passes it onto event page where even id is used to bring up the event
                Intent event = new Intent(getApplicationContext(), EventPage.class);
                event.putExtra("CurrentUser", CU);
                event.putExtra("event_id", event_id);
                startActivity(event);
            }
        });

    }

    //protected Adapter initializedAdapter=null;
    // This method is used for dropdown spinner when it filters by housing options(north, south, west)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView spinnerDialogText = (TextView) view;
        String building = "";
        Toast.makeText(this, "You selected " + spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();

        String selected = parent.getItemAtPosition(position).toString();

        //If i dont create this new arrayList, and use the existing arrayList, it duplicates the events
        ArrayList<EventItem> newArrayList=new ArrayList<>();
        if(spinnerDialogText.getText().equals("South Lake Village")){
            building = "South Village";
        } else if(spinnerDialogText.getText().equals("North Lake Village")){
            building = "North Village";
        } else if(spinnerDialogText.getText().equals("West Lake Village")){
            building = "West Village";
        }

        eventAdapter.clear(); //cleans adapter so that new data can be loaded
        Cursor cursor = datCon.QueryData("select * from " +
                HousingContract.EventEntry.TABLE_NAME );
        if(!building.equals("")) {

            try {
                cursor = datCon.QueryData("select * from " +
                        HousingContract.EventEntry.TABLE_NAME + " where " +
                        HousingContract.EventEntry.BUILDING + " = '" + building + "'");
            } catch (SQLException e) {
            }
        }

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

        eventAdapter.addAll(newArrayList); //adds new arrayList of events
        eventAdapter.notifyDataSetChanged(); //notify changes to the adapter
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

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        /**
         * If user is a Resident, Can not see event manager
         */
        MenuItem EventManager = menu.findItem(R.id.event_manager);
        if(CU.getAccountType().equals("R"))
        {
            EventManager.setVisible(false);
        }
        else
        {
            EventManager.setVisible(true);
        }
        return true;
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
        Log.d("Menu",item.getTitle().toString());

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent Account = new Intent(getApplicationContext(), ResidentAccount.class);
            Account.putExtra("CurrentUser", CU);
            startActivity(Account);
        } else if (id == R.id.event_manager){

            Intent Account = new Intent(getApplicationContext(), EventManager.class);
            Account.putExtra("CurrentUser", CU);
            startActivity(Account);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * prompt user to make sure they want to log out
     *
     */
    @Override
    public void onBackPressed() {
        /**
         * Pop up Dialog
         */
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent goback = new Intent(getApplicationContext(), ResidentLogin.class);
                        startActivity(goback);
                    }
                })
                .create()
                .show();
    }

}
