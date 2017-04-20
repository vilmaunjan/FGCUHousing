package com.example.vilma.fgcuhousing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;

public class RatingPage extends AppCompatActivity {
    private CurrentUser CU;
    private int eventid;
    private String eveTitle;
    private RatingBar rating;
    private EditText Comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Gets Parcelabel objects and strings
        Bundle data = getIntent().getExtras();
        CU = data.getParcelable("CurrentUser");
        eventid = data.getInt("Event");
        eveTitle = data.getString("EventTitle");
        Comments = (EditText) findViewById(R.id.txtEventComments);
        rating = (RatingBar) findViewById(R.id.ratingBar);
        //here just to be here
        addListenerOnRatingBar();

        //If the user already checked in and out of the event
        //Change the button
        if(CU.getEvents().get(eveTitle).isAttended()){
            rating.setClickable(false);
            rating.setRating(CU.getEvents().get(eveTitle).getRating());
            Comments.setText(CU.getEvents().get(eveTitle).getFeedBack());
            //change the buttons
            Button button = (Button) findViewById(R.id.btnRate);
            button.setVisibility(View.GONE); //changes text to check out
            button.setEnabled(false); //changes text to check out
            Button checkOut = (Button) findViewById(R.id.btnOnward);
            checkOut.setVisibility(View.VISIBLE);
            checkOut.setEnabled(true);

            Toast.makeText(getApplicationContext(),"We rated here already", Toast.LENGTH_SHORT).show();
        }

    }

    public void addListenerOnRatingBar() {
        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

               // Toast.makeText(RatingPage.this, "The rating bar number is " + String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();

            }
        });
    }




    public void buttonOnClick(View v) {
        Button button = (Button)v;

        if (v == findViewById(R.id.btnRate)) { //go to residentAccess
            //Set the rating and comments
            CU.getEvents().get(eveTitle).setRating(rating.getRating());
            if(!((Comments.getText().toString()) == null)) {
                CU.getEvents().get(eveTitle).setFeedBack(Comments.getText().toString());
            }else{
                CU.getEvents().get(eveTitle).setFeedBack("Awesome");
            }

            DbHandler datCon = new DbHandler(this);
            //puts into database
            datCon.EventCheckOut(eveTitle, eventid, CU);
            //They have succefully attended the event
            CU.getEvents().get(eveTitle).setAttended(true);
            Intent Account = new Intent(getApplicationContext(), EventList.class);
            Account.putExtra("CurrentUser", CU);
            startActivity(Account);
        }else if(v == findViewById(R.id.btnOnward)){
            Intent Account = new Intent(getApplicationContext(), EventList.class);
            Account.putExtra("CurrentUser", CU);
            startActivity(Account);
        }


    }



    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
