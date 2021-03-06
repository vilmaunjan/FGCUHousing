package com.example.vilma.fgcuhousing;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.Event;
import com.example.vilma.fgcuhousing.data.HousingContract;
import com.example.vilma.fgcuhousing.data.UserEvents;

public class EventPage extends AppCompatActivity {

    //Need these whenever Connecting to database
    private DbHandler datCon;
    private Event event;
    private CurrentUser CU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle data = getIntent().getExtras();
        CU = data.getParcelable("CurrentUser");

        //load database
        datCon = new DbHandler(this);
        String event_id = getIntent().getStringExtra("event_id");


        try{
            //finds event in database
            Cursor cursorEventInfo = datCon.QueryData("select * from "
                    + HousingContract.EventEntry.TABLE_NAME + " where "
                    + HousingContract.EventEntry.Event_ID + " = " + event_id);
            if(cursorEventInfo !=null){
                //if event found
                if(cursorEventInfo.moveToFirst()){
                    do{
                        //Create event object and set the fields with data from database
                        event = new Event();
                        try {
                            event.setId(Integer.parseInt(event_id));
                        }catch (NumberFormatException n){

                        }
                        event.setTitle(cursorEventInfo.getString(1));
                        event.setDescription(cursorEventInfo.getString(2));
                        event.setLocation(cursorEventInfo.getString(3));
                        event.setBuilding(cursorEventInfo.getString(6));
                        event.setTime(cursorEventInfo.getString(5));
                        event.setDate(cursorEventInfo.getString(4));
                        event.setImage(cursorEventInfo.getString(7));

                        //create variables with the id of the text fields
                        TextView txt_event_title = (TextView) findViewById(R.id.txt_event_title);
                        TextView txt_event_description = (TextView) findViewById(R.id.txt_event_description);
                        TextView txt_event_location = (TextView) findViewById(R.id.txt_event_location);
                        TextView txt_event_building = (TextView) findViewById(R.id.txt_event_building);
                        TextView txt_event_time = (TextView) findViewById(R.id.txt_event_time);
                        TextView txt_event_date = (TextView) findViewById(R.id.txt_event_date);
                        ImageView iv_image = (ImageView) findViewById(R.id.img_poster);

                        //Get event values from the object created before and set it to the text fields
                        txt_event_title.setText(event.getTitle());
                        txt_event_description.setText(event.getDescription());
                        txt_event_location.setText("Where: "+event.getLocation());
                        txt_event_building.setText("Village/building: "+event.getBuilding());
                        txt_event_time.setText("When: "+event.getTime());
                        txt_event_date.setText("What time: "+event.getDate());
                        iv_image.setImageResource(Integer.parseInt(event.getImage()));
                    }while (cursorEventInfo.moveToNext());
                }
            }
        }catch (SQLException e){}

        alreadyAttending();
    }

    private void alreadyAttending() {
        Button button = (Button) findViewById(R.id.btnCheckIn);

            if (CU.getEvents().containsKey(event.getTitle())) {
                if(CU.getEvents().get(event.getTitle()).isCheckedin()) {
                    button.setVisibility(View.GONE); //changes text to check out
                    button.setEnabled(false); //changes text to check out
                    Button checkOut = (Button) findViewById(R.id.btnCheckout);
                    checkOut.setVisibility(View.VISIBLE);
                    checkOut.setEnabled(true);
                }
            }

    }

    public void buttonOnClick(View v) {
        final Button button = (Button)v;
        if (v == findViewById(R.id.btnCheckIn)) { //when check in button is pressed

             new AlertDialog.Builder(this)
                    .setTitle("Check in")
                    .setMessage("Ready to join fun")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Button button = (Button) findViewById(R.id.btnCheckIn);
                            button.setVisibility(View.GONE); //changes text to check out
                            button.setEnabled(false); //changes text to check out
                            Button checkOut = (Button) findViewById(R.id.btnCheckout);
                            checkOut.setVisibility(View.VISIBLE);
                            checkOut.setEnabled(true);
                            datCon.insertEventAttended(event, CU);
                            int eventCounter = CU.getEventCounter()+1;
                            CU.setEventCounter(eventCounter);
                            //Checks if they qualify for any events
                            CU.getMyawards().checkEvent(CU.getEventCounter(), getApplicationContext());
                            //Checks if there able to get the complicated award
                            if(datCon.checkifComplicated(CU)){
                                //maybe toast or something saying there received the complicated award
                                Toast.makeText(getApplicationContext(), "You have Obtained the Complicated Award!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .create()
                    .show();

        }
        else if (v == findViewById(R.id.btnCheckout)){ //when 'checkout' button is pressed
            try {
                Intent Account = new Intent(getApplicationContext(), RatingPage.class);
                Account.putExtra("CurrentUser", CU);
                Account.putExtra("Event", event.getId());
                Account.putExtra("EventTitle", event.getTitle());
                startActivity(Account);
            }catch (Exception e){
                Log.d("UserErrors", e.getMessage());
                 Log.getStackTraceString(e);
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), EventList.class).putExtra("CurrentUser", CU));
    }
}
