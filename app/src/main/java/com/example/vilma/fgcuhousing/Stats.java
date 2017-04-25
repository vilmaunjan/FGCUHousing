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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.CurrentUser;
import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.EventAdapter;
import com.example.vilma.fgcuhousing.data.EventItem;
import com.example.vilma.fgcuhousing.data.HousingContract;
import com.example.vilma.fgcuhousing.data.RAItem;
import com.example.vilma.fgcuhousing.data.RaAdapter;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {

    //Need these whenever Connecting to database
    private SQLiteDatabase mDb;
    private DbHandler datCon = new DbHandler(this); //load database
    private CurrentUser CU;

    //variables declaration
    private ListView listView;
    private RaAdapter raAdapter;
    private ArrayList<RAItem> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        raSummary();
        raOfMonth();
        eventOfMonth();
        residentOfMonth();

    }

    public void raSummary() {
        try{
            //finds event in database
            Cursor raSum = datCon.QueryData("select r."+HousingContract.UserEntry.Name+
                    ", AVG(a."+HousingContract.AttendedEventEntry.Rating_Score+") rtg FROM "+
                    HousingContract.AttendedEventEntry.TABLE_NAME+" a INNER JOIN "+
                    HousingContract.OrganizedEvents.TABLE_NAME+" o ON o."+
                    HousingContract.OrganizedEvents.Event_ID+" = a."+
                    HousingContract.AttendedEventEntry.Event_ID+" INNER JOIN "+
                    HousingContract.UserEntry.TABLE_NAME+" r on r."+
                    HousingContract.UserEntry._ID+" = o."+
                    HousingContract.OrganizedEvents.RA_ID+" group by "+
                    HousingContract.OrganizedEvents.RA_ID);
            if(raSum !=null){
                //if event found
                if(raSum.moveToFirst()){
                    do{
                        RAItem item = new RAItem();
                        item.setName(raSum.getString(0));
                        item.setRating(raSum.getString(1));
                        arrayList.add(item);
                    }while (raSum.moveToNext());
                }
            }
        }catch (SQLException e){}

        raAdapter = new RaAdapter(this, R.layout.stats_list_item, arrayList);
        listView = (ListView) findViewById(R.id.list_ra_item);
        listView.setAdapter(raAdapter);
        raAdapter.notifyDataSetChanged();
    }

    public void raOfMonth() {

        try{
            //finds event in database
            Cursor bestRA = datCon.QueryData("select r."+HousingContract.UserEntry.Name+
                    ", AVG(a."+HousingContract.AttendedEventEntry.Rating_Score+") rtg FROM "+
                    HousingContract.AttendedEventEntry.TABLE_NAME+" a INNER JOIN "+
                    HousingContract.OrganizedEvents.TABLE_NAME+" o ON o."+
                    HousingContract.OrganizedEvents.Event_ID+" = a."+
                    HousingContract.AttendedEventEntry.Event_ID+" INNER JOIN "+
                    HousingContract.UserEntry.TABLE_NAME+" r on r."+
                    HousingContract.UserEntry._ID+" = o."+
                    HousingContract.OrganizedEvents.RA_ID+" group by "+
                    HousingContract.OrganizedEvents.RA_ID+" ORDER BY rtg DESC LIMIT 1");
            if(bestRA !=null){
                //if event found
                if(bestRA.moveToFirst()){
                    do{
                        TextView RAMonth = (TextView) findViewById(R.id.RAMonth);
                        RAMonth.setText("RA of the month: "+ bestRA.getString(0)+
                        " with a rating score of " + bestRA.getString(1));
                    }while (bestRA.moveToNext());
                }
            }
        }catch (SQLException e){}
    }

    public void eventOfMonth() {
        try{
            //finds event in database
            Cursor bestEvent = datCon.QueryData("select e." +
                    HousingContract.EventEntry.Event_Title+", AVG(a."+
                    HousingContract.AttendedEventEntry.Rating_Score+") rtg FROM "+
                    HousingContract.EventEntry.TABLE_NAME+" e INNER JOIN "+
                    HousingContract.AttendedEventEntry.TABLE_NAME+" a on e."+
                    HousingContract.EventEntry.Event_ID+" = a."+
                    HousingContract.AttendedEventEntry.Event_ID+" GROUP BY e."+
                    HousingContract.EventEntry.Event_Title+" ORDER BY rtg DESC LIMIT 1");
            if(bestEvent !=null){
                //if event found
                if(bestEvent.moveToFirst()){
                    do{
                        TextView EventMonth = (TextView) findViewById(R.id.EventMonth);
                        EventMonth.setText("Event of the month: "+ bestEvent.getString(0)+
                                " with a rating score of " + bestEvent.getString(1));
                    }while (bestEvent.moveToNext());
                }
            }
        }catch (SQLException e){}
    }

    public void residentOfMonth() {
        try{
            //finds event in database
            Cursor bestResident = datCon.QueryData("select r."+
                    HousingContract.UserEntry.Name+", count(a."+
                    HousingContract.AttendedEventEntry.Event_ID+") evCount, count(a."+
                    HousingContract.AttendedEventEntry.Rating_Score+") rtCount FROM "+
                    HousingContract.UserEntry.TABLE_NAME+" r INNER JOIN "+
                    HousingContract.AttendedEventEntry.TABLE_NAME+" a on r."+
                    HousingContract.UserEntry._ID+" = a."+
                    HousingContract.AttendedEventEntry.Resident_ID+" GROUP BY r."+
                    HousingContract.UserEntry.Name+" order by evCount DESC, rtCount DESC limit 1");
            if(bestResident !=null){
                //if event found
                if(bestResident.moveToFirst()){
                    do{
                        TextView ResidentMonth = (TextView) findViewById(R.id.ResidentMonth);
                        ResidentMonth.setText("Resident of the month: "+ bestResident.getString(0)+
                                " attended to " + bestResident.getString(1) +
                                " events and rated " + bestResident.getString(2) + " events.");
                    }while (bestResident.moveToNext());
                }
            }
        }catch (SQLException e){}
    }
}
