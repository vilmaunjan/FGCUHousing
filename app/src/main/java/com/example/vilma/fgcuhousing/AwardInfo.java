package com.example.vilma.fgcuhousing;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.HousingContract;


/**
 * Created by Andre on 4/13/2017.
 */

public class AwardInfo extends Activity{
    private TextView title;
    private TextView Description;
    private long LogoID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awardwindow);

        Bundle goodies = getIntent().getExtras();
        LogoID = goodies.getLong("logoid");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;//sets the width
        int height = dm.heightPixels;//sets the height

        getWindow().setLayout((int) (width * .9), (int)(height * .2));
        //initializing
        title = (TextView) findViewById(R.id.TitleBox);
        Description = (TextView) findViewById(R.id.AwardDescriptionBox);
        awardDescription();
        Log.d("Award", "is it making it here?");
    }

    private void awardDescription(){
        Cursor awardinfo;

            DbHandler getit = new DbHandler(getApplicationContext());
        //Find the Award
        String query = "Select * from "+ HousingContract.Awards.TABLE_NAME +" where " +
                HousingContract.Awards.COLUMN_Image +" = \"" + LogoID + "\";";

        awardinfo = getit.QueryData(query);//put in cursor and set the text and description

        try {
            if (awardinfo.moveToFirst()) {
                do {
                    title.setText(awardinfo.getString(1));
                    Description.setText(awardinfo.getString(2));

                }
                while (awardinfo.moveToNext());
            }
        }catch (Exception e){
            Log.d("CreatingEvent", "Somethign went wong");
        }


    }


}
