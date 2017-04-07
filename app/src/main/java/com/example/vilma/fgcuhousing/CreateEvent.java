package com.example.vilma.fgcuhousing;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class CreateEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //Need these whenever Connecting to database
    private SQLiteDatabase mDb;
    private DbHandler datCon;
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
    }

    public void buttonOnClick(View v) {
        Button button = (Button) v;
        Event event = null;
        event.setTitle(title.getText().toString());
        event.setDescription(description.getText().toString());
        event.setBuilding(building.getText().toString());
        event.setTime(time.getText().toString());
        event.setDate(date.getText().toString());
        event.setLocation(location.getText().toString());
        if (v == findViewById(R.id.btnCreateEvent)) {
            boolean isInserted;
            try{
             isInserted = datCon.insertEvent(event);
        }catch(Exception e){
            Log.d("insertEvent", "so whats the problem");
            Log.d("insertEvent",e.getMessage());
        }
            if(isInserted = true)
                Toast.makeText(CreateEvent.this,"Event created successfully",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(CreateEvent.this,"Event was not created",Toast.LENGTH_LONG).show();
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
