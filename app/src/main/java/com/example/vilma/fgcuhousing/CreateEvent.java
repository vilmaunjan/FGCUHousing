package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.Event;
import com.example.vilma.fgcuhousing.data.HousingContract;

public class CreateEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private DbHandler datCon;
    private boolean edit = false;
    private String eveID;
    EditText title, description, time, date;
    String image;
    AutoCompleteTextView building;
    TextView location;
    Button btnCreateEvent;
    private CurrentUser CU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        CU = extras.getParcelable("CurrentUser");

        Spinner spinnerFilter = (Spinner) findViewById(R.id.spinnerLocation);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner_housing_options, android.R.layout.simple_spinner_item);

        ArrayAdapter<String> buildinglist = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,BUILDINGS);

        spinnerFilter.setAdapter(adapter);
        spinnerFilter.setOnItemSelectedListener(this);

        //Start's database
        datCon = new DbHandler(this);
        //get's and editable database to add parameters
        SQLiteDatabase mDb = datCon.getWritableDatabase();
        title = (EditText) findViewById(R.id.txtEventName);
        description = (EditText) findViewById(R.id.txtEventDescription);
        building = (AutoCompleteTextView) findViewById(R.id.txtBuilding);
        time = (EditText) findViewById(R.id.txtTime);
        date = (EditText) findViewById(R.id.txtDate);

        building.setAdapter(buildinglist);

        if (!extras.containsKey("event_id")) {
            edit=false;
        } else {
            edit = true;
            String event_id = getIntent().getStringExtra("event_id");  //look for the event by id and pull values from it to text feilds.
            Cursor cursorEventInfo = datCon.QueryData("select * from "
                    + HousingContract.EventEntry.TABLE_NAME + " where "
                    + HousingContract.EventEntry.Event_ID + " = " + event_id);
            if (cursorEventInfo != null) {
                if (cursorEventInfo.moveToFirst()) {
                    do {
                        eveID = event_id;
                        title.setText(cursorEventInfo.getString(1));
                        description.setText(cursorEventInfo.getString(2));
                        //location.setText(cursorEventInfo.getString(3));   going to leave like this, correct spinner value low priority.
                        building.setText(cursorEventInfo.getString(6));
                        time.setText(cursorEventInfo.getString(5));
                        date.setText(cursorEventInfo.getString(4));
                        image = cursorEventInfo.getString(7);
                        //****************************
                        //here the poster image would be loaded
                        //*****************************
                    } while (cursorEventInfo.moveToNext());
                }
            }
        }
    }

    private static final String[] BUILDINGS = new String[] {
            //All buildings need to be added
            "Eagle", "Osprey", "Palmetto", "Biscayne", "Everglades", //South Village
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", //North Lake
            "M", "N", "O", "P", "Q", "R", "S", "T", "Cypress", "Falcon",
            "Honors", "Pelican", "Oak", "Sandpiper", "Egret", "Mangrove",
            "Flamingo", "Manatee", "Pompano", "Marlin", "Panther", "Tarpon" //West Lake

    };

    public void buttonOnClick(View v){
        Button button = (Button) v;
        Event event = new Event();
        event.setTitle(title.getText().toString());
        event.setDescription(description.getText().toString());
        event.setBuilding(building.getText().toString());
        event.setTime(time.getText().toString());
        event.setDate(date.getText().toString());
        event.setLocation(location.getText().toString());
        event.setImage(image);
        boolean isInserted = false;
        if (v == findViewById(R.id.btnConfirm)) {
            if(!edit) {
                try {
                    Log.d("insertEvent", event.getDescription() + " " + event.getLocation() + "  " + event.getBuilding() + "  " + event.getTitle() + "  " + event.getDate() + "  " + event.getTime());
                    isInserted = datCon.insertEvent(event, CU);
                } catch (Exception e) {
                    Log.d("insertEvent", "so whats the problem");
                    Log.d("insertEvent", e.getMessage());
                }
                if (isInserted) {
                    Toast.makeText(CreateEvent.this, "Event created successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), EventManager.class);
                    intent.putExtra("CurrentUser", CU);
                    startActivity(intent);
                }
                else
                    Toast.makeText(CreateEvent.this, "Event was not created", Toast.LENGTH_LONG).show();
            }
            else{
                //**************************************************

                // edit event in database instead of creating event

                //*************************************************

                boolean upToDate = datCon.updateEvent(eveID, event);
                if(upToDate){

                    Toast.makeText(CreateEvent.this, "Event was Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CreateEvent.this, "Event was not Updated", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this, "you selected " + spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();
        location = spinnerDialogText;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {    }

    @Override
    public void onBackPressed() {
        Intent e = new Intent(getApplicationContext(), EventManager.class);
        e.putExtra("CurrentUser", CU);
        startActivity(e);
    }
}
