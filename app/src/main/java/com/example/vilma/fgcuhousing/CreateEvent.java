package com.example.vilma.fgcuhousing;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.Event;
import com.example.vilma.fgcuhousing.data.HousingContract;

public class CreateEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //Need these whenever Connecting to database
    private SQLiteDatabase mDb;
    private DbHandler datCon;
    private boolean edit = false;
    EditText title, description, building, time, date;
    TextView location;
    Button btnCreateEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinnerFilter = (Spinner) findViewById(R.id.spinnerLocation);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner_housing_options, android.R.layout.simple_spinner_item);

        spinnerFilter.setAdapter(adapter);
        spinnerFilter.setOnItemSelectedListener(this);

        //Start's database
        datCon = new DbHandler(this);
        //get's and editable database to add parameters
        mDb = datCon.getWritableDatabase();
        title = (EditText) findViewById(R.id.txtEventName);
        description = (EditText) findViewById(R.id.txtEventDescription);
        building = (EditText) findViewById(R.id.txtBuilding);
        time = (EditText) findViewById(R.id.txtTime);
        date = (EditText) findViewById(R.id.txtDate);

        Bundle extras = getIntent().getExtras();   //extras will be the event ID if coming from edit event function
        if (extras == null) {
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
                        title.setText(cursorEventInfo.getString(1));
                        description.setText(cursorEventInfo.getString(2));
                        //location.setText(cursorEventInfo.getString(3));   going to leave like this, correct spinner value low priority.
                        building.setText(cursorEventInfo.getString(6));
                        time.setText(cursorEventInfo.getString(5));
                        date.setText(cursorEventInfo.getString(4));
                        //****************************
                        //here the poster image would be loaded
                        //*****************************
                    } while (cursorEventInfo.moveToNext());
                }
            }
        }
    }

    public void buttonOnClick(View v){
        Button button = (Button) v;
        Event event = new Event();
        event.setTitle(title.getText().toString());
        event.setDescription(description.getText().toString());
        event.setBuilding(building.getText().toString());
        event.setTime(time.getText().toString());
        event.setDate(date.getText().toString());
        event.setLocation(location.getText().toString());
        boolean isInserted = false;
        if (v == findViewById(R.id.btnConfirm)) {
            if(edit==false) {
                try {

                    Log.d("insertEvent", event.getDescription() + " " + event.getLocation() + "  " + event.getBuilding() + "  " + event.getTitle() + "  " + event.getDate() + "  " + event.getTime());
                    isInserted = datCon.insertEvent(event);
                } catch (Exception e) {
                    Log.d("insertEvent", "so whats the problem");
                    Log.d("insertEvent", e.getMessage());
                }
                if (isInserted)
                    Toast.makeText(CreateEvent.this, "Event created successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(CreateEvent.this, "Event was not created", Toast.LENGTH_LONG).show();
            }
            else{
                //**************************************************

                // edit event in database instead of creating event

                //*************************************************
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
}
