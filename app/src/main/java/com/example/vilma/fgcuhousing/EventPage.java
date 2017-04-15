package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.Event;
import com.example.vilma.fgcuhousing.data.HousingContract;

public class EventPage extends AppCompatActivity {

    //Need these whenever Connecting to database
    private SQLiteDatabase mDb;
    private DbHandler datCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //load database
        datCon = new DbHandler(this);

        String event_id = getIntent().getStringExtra("event_id");

        try{
            Cursor cursorEventInfo = datCon.QueryData("select * from "
                    + HousingContract.EventEntry.TABLE_NAME + " where "
                    + HousingContract.EventEntry.Event_ID + " = " + event_id);
            if(cursorEventInfo !=null){
                if(cursorEventInfo.moveToFirst()){
                    do{
                        Event event = new Event();
                        //Create event object and set the fields with data from database
                        //Event event = new Event();
                        event.setTitle(cursorEventInfo.getString(1));
                        event.setDescription(cursorEventInfo.getString(2));
                        event.setLocation(cursorEventInfo.getString(3));
                        event.setBuilding(cursorEventInfo.getString(6));
                        event.setTime(cursorEventInfo.getString(5));
                        event.setDate(cursorEventInfo.getString(4));
                        //event.setImage(cursorEventInfo.getString(7));

                        //create variables with the id of the text fields
                        TextView txt_event_title = (TextView) findViewById(R.id.txt_event_title);
                        TextView txt_event_description = (TextView) findViewById(R.id.txt_event_description);
                        TextView txt_event_location = (TextView) findViewById(R.id.txt_event_location);
                        TextView txt_event_building = (TextView) findViewById(R.id.txt_event_building);
                        TextView txt_event_time = (TextView) findViewById(R.id.txt_event_time);
                        TextView txt_event_date = (TextView) findViewById(R.id.txt_event_date);
                        //ImageView iv_image = (ImageView) findViewById(R.id.img_poster);

                        //Get event values from the object created before and set it to the text fields
                        txt_event_title.setText(event.getTitle());
                        txt_event_description.setText(event.getDescription());
                        txt_event_location.setText("Where: "+event.getLocation());
                        txt_event_building.setText("Village/building: "+event.getBuilding());
                        txt_event_time.setText("When: "+event.getTime());
                        txt_event_date.setText("What time: "+event.getDate());
                        //iv_image.setText(event.getTime());
                    }while (cursorEventInfo.moveToNext());
                }
            }
        }catch (SQLException e){}
    }

    public void buttonOnClick(View v) {
        Button button = (Button)v;
        if (v == findViewById(R.id.btnCheckIn)) { //when checkin button is pressed
            button.setVisibility(View.GONE); //changes text to check out
            button.setEnabled(false); //changes text to check out
            Button checkOut = (Button) findViewById(R.id.btnCheckout);
            checkOut.setVisibility(View.VISIBLE);
            checkOut.setEnabled(true);

            //timer should start and should stop when check out button is pressed
        }
        else if (v == findViewById(R.id.btnCheckout)){ //when 'checkout' button is pressed
            startActivity(new Intent(getApplicationContext(), RatingPage.class));
        }
    }

}
