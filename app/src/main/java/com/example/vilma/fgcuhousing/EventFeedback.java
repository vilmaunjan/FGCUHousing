package com.example.vilma.fgcuhousing;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.Event;
import com.example.vilma.fgcuhousing.data.ExpandibleListAdapter;
import com.example.vilma.fgcuhousing.data.HousingContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventFeedback extends AppCompatActivity {

    //Need these whenever Connecting to database
    private DbHandler datCon;
    private Event event;
    private CurrentUser CU;

    private ExpandableListView listView;
    private ExpandibleListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandibleListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
    }

    private void initData(){
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        Bundle data = getIntent().getExtras();
        CU = data.getParcelable("CurrentUser"); //this is the line that gives me problem
/*      -------------------------------------This is that part that doesnt work----------------------
        datCon.insertEventAttended(event, CU);
        try{
            //finds event in database
            Cursor cursorFeedback = datCon.QueryData("select avg(" +
                    HousingContract.AttendedEventEntry.Rating_Score +
                    "), "+ HousingContract.AttendedEventEntry.Event_ID +
                    " from " + HousingContract.AttendedEventEntry.TABLE_NAME +
                    " where " + HousingContract.AttendedEventEntry.Event_ID +
                    " in (select " + HousingContract.OrganizedEvents.Event_ID +
                    " from " + HousingContract.OrganizedEvents.TABLE_NAME +
                    " where " + HousingContract.OrganizedEvents.RA_ID  + " = " +
                    CU.getID() + ")");

            if(cursorFeedback !=null){
                //if event found
                if(cursorFeedback.moveToFirst()){
                    int i = 0;
                    do{
                        listDataHeader.add(cursorFeedback.getString(0) + " Avg rating: " +
                                cursorFeedback.getString(1));

                        List<String> androidStudio = new ArrayList<>();
                        androidStudio.add("Expandible list view");
                        androidStudio.add("Google map");
                        androidStudio.add("CHat application");

                        listHash.put(listDataHeader.get(i),androidStudio);
                        i++;
                    }while (cursorFeedback.moveToNext());
                }
            }
        }catch (SQLException e){}
*/
        //This was part of a tutorial that i was following, it works
        listDataHeader.add("Dev");
        listDataHeader.add("Android");
        listDataHeader.add("Xamarin");
        listDataHeader.add("UMP");

        List<String> edmtDev = new ArrayList<>();
        edmtDev.add("This is expandible list view");

        List<String> androidStudio = new ArrayList<>();
        androidStudio.add("Expandible list view");
        androidStudio.add("Google map");
        androidStudio.add("CHat application");
        androidStudio.add("Firebase");

        List<String> xamarin = new ArrayList<>();
        xamarin.add("xamarin Expandible list view");
        xamarin.add("xamarin Google map");
        xamarin.add("xamarin CHat application");
        xamarin.add("xamarin Firebase");

        List<String> ump = new ArrayList<>();
        ump.add("ump Expandible list view");
        ump.add("ump Google map");
        ump.add("ump CHat application");
        ump.add("ump Firebase");

        listHash.put(listDataHeader.get(0),edmtDev);
        listHash.put(listDataHeader.get(1),androidStudio);
        listHash.put(listDataHeader.get(2),xamarin);
        listHash.put(listDataHeader.get(3),ump);

    }

}
