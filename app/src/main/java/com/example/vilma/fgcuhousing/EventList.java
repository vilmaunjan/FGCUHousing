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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.DbHandler;
import com.example.vilma.fgcuhousing.data.Event;
import com.example.vilma.fgcuhousing.data.EventAdapter;
import com.example.vilma.fgcuhousing.data.EventItem;
import com.example.vilma.fgcuhousing.data.HousingContract;

import java.util.ArrayList;

import static com.example.vilma.fgcuhousing.R.id.spinnerFilter;

public class EventList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Need these whenever Connecting to database
    private SQLiteDatabase mDb;
    private DbHandler datCon;

    //variables declaration
    ListView listView;
    ArrayList<EventItem> arrayList=new ArrayList<EventItem>();
    EventAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinnerFilter = (Spinner) findViewById(R.id.spinnerFilter);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner_housing_options, android.R.layout.simple_spinner_item);

        spinnerFilter.setAdapter(adapter);
        spinnerFilter.setOnItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionAwards);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), Awards.class));
            }
        });

        //load database
        datCon = new DbHandler(this);

        //this try catch queries all the events in the database and displays it in EventList page
        try{
            Cursor cursor=datCon.QueryData("select * from " + HousingContract.EventEntry.TABLE_NAME);
            if(cursor !=null){
                if(cursor.moveToFirst()){
                    do{
                        EventItem item=new EventItem();
                        item.setId(cursor.getString(0));
                        item.setTitle(cursor.getString(1));
                        item.setTime(cursor.getString(5));
                        item.setDate(cursor.getString(4));
                      //  item.setPoster(R.drawable.movie_night); //doesnt work, need to load posters for events
                        arrayList.add(item);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLException e){}
        adapter = new EventAdapter(this, R.layout.custom_list_item,arrayList);
        listView = (ListView) findViewById(R.id.list_event_item);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //When clicking on an events, leads to the event info page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);

                String event_id = ((TextView) view.findViewById(R.id.tv_id)).getText().toString();
                Toast.makeText(EventList.this, event_id, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), EventPage.class).putExtra("event_id", event_id));
            }
        });

    }

    // This method is used for dropdown spinner when it filters by housing options(north, south, west)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this, "you selected " + spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();
    }

    //Does nothing but required by implementing interface
    @Override
    public void onNothingSelected(AdapterView<?> parent) {    }

    //Method used when clicking on buttons
    public void buttonOnClick(View v) {
        Button button = (Button)v;
        //use if statement if you want to open something when clicking a button
        if (v == findViewById(R.id.btnSearch)) { //go to EventPage
            //startActivity(new Intent(getApplicationContext(), EventPage.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), ResidentAccount.class));
        } else if (id == R.id.event_manager){
            startActivity(new Intent(getApplicationContext(), EventManager.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
