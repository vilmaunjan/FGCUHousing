package com.example.vilma.fgcuhousing;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.Event;
import com.example.vilma.fgcuhousing.data.ExpandibleListAdapter;
import com.example.vilma.fgcuhousing.data.HousingContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventFeedback extends AppCompatActivity {

    private Event event;

    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.lvExp);
        initData();
        ExpandibleListAdapter listAdapter = new ExpandibleListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);
    }

    private void initData(){
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        Bundle data = getIntent().getExtras();
        CurrentUser CU = data.getParcelable("CurrentUser");

        //load database
        DbHandler datCon = new DbHandler(this);
      //-------------------------------------This is that part that doesnt work----------------------
        //datCon.insertEventAttended(event, CU);
        try{
            //finds event in database
            Cursor cursorFeedback = datCon.QueryData("SELECT e."+HousingContract.EventEntry.Event_Title+
                    ", AVG(a."+HousingContract.AttendedEventEntry.Rating_Score+"), a."+
                    HousingContract.AttendedEventEntry.Event_ID+" FROM "+
                    HousingContract.AttendedEventEntry.TABLE_NAME+" a INNER JOIN "+
                    HousingContract.OrganizedEvents.TABLE_NAME+ " o ON a."+
                    HousingContract.AttendedEventEntry.Event_ID+" = o."+
                    HousingContract.OrganizedEvents.Event_ID+" INNER JOIN "+
                    HousingContract.EventEntry.TABLE_NAME+" e on e."+
                    HousingContract.EventEntry.Event_ID+" = o."+
                    HousingContract.OrganizedEvents.Event_ID+
                    " where o."+HousingContract.OrganizedEvents.RA_ID+
                    " = "+ CU.getID()+" GROUP BY o."+
                    HousingContract.OrganizedEvents.Event_ID);


            if(cursorFeedback !=null){
                //if event found
                if(cursorFeedback.moveToFirst()){
                    int i = 0;

                    do{
                        listDataHeader.add(cursorFeedback.getString(0) + "         Avg rating: " +
                                cursorFeedback.getString(1));
                        //Use this eventId to query the comments of each query
                        String eventId = cursorFeedback.getString(2);

                        Cursor cursor = datCon.QueryData("SELECT "+
                                HousingContract.AttendedEventEntry.Rating_FeedBack+
                                " FROM "+HousingContract.AttendedEventEntry.TABLE_NAME+
                                " WHERE "+HousingContract.AttendedEventEntry.Event_ID+
                                " = "+Integer.parseInt(eventId));

                        if(cursor !=null) {
                            List<String> comments = new ArrayList<>();
                            //if event found
                            if (cursor.moveToFirst()) {
                                do {
                                    String comment = cursor.getString(0);
                                    comments.add(comment); //add comment to a list

                                } while (cursor.moveToNext());
                            }
                            listHash.put(listDataHeader.get(i),comments);
                        }


                        i++;
                    }while (cursorFeedback.moveToNext());
                }
            }
        }catch (SQLException e){}
    }

}
