package com.example.vilma.fgcuhousing.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.vilma.fgcuhousing.data.HousingContract.*;


/**
 * Created by Andrew on 3/10/2017.
 * Handles the dataBase
 */

public class DbHandler extends SQLiteOpenHelper {
    //Used for the name ad database version whenever updating schema
    private static final String DATABASE_NAME = "HousingDB";
    private static final int DATABASE_VERSION = 1;

    public DbHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqldb){
        //Create statement for table will rework later just wanted something working for now
        final String CreateUserTable = "CREATE TABLE " +
                UserEntry.TABLE_NAME + "( "+
                UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                UserEntry.Fname + " TEXT NOT NULL, " +
                UserEntry.Lname + " TEXT NOT NULL, " +
                UserEntry.UIN + " INTEGER NOT NULL UNIQUE," +
                UserEntry.Email + " TEXT NOT NULL, " +
                UserEntry.Password + " TEXT NOT NULL," +
                UserEntry.Type + " TEXT NOT NULL, " +
                UserEntry.Building+ " TEXT NOT NULL" +
                ")";

        final String CreateEvent = "CREATE TABLE " +
                EventEntry.TABLE_NAME + "( "+
                EventEntry.Event_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                EventEntry.Event_Title + " TEXT NOT NULL, " +
                EventEntry.Description + " TEXT NOT NULL, " +
                EventEntry.CreatedDate + " TEXT NOT NULL, " +
                EventEntry.BUILDING + " TEXT NOT NULL, " +
                EventEntry.IMAGE + " TEXT NOT NULL " +
                ")";

        final String CreateAwards = "CREATE TABLE " +
                Awards.TABLE_NAME + "( "+
                Awards.COLUMN_Award_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Awards.COLUMN_Image + " TEXT NOT NULL " +
                ")";

        //Needs foreign Key
        final String CreateAttendedEvent = "CREATE TABLE " +
                AttendedEventEntry.TABLE_NAME + "( "+
                AttendedEventEntry.Rating_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                AttendedEventEntry.Resident_ID + " INTEGER, " +
                AttendedEventEntry.Event_ID + " INTEGER, " +
                AttendedEventEntry.Time_Stayed + " TEXT NOT NULL, " +
                AttendedEventEntry.Rating_Score + " TEXT NOT NULL, " +
                AttendedEventEntry.Rating_FeedBack + " TEXT NOT NULL, " +
                " FOREIGN KEY ("+ AttendedEventEntry.Resident_ID +") REFERENCES "+UserEntry.TABLE_NAME+"("+UserEntry._ID+")," +
                " FOREIGN KEY ("+ AttendedEventEntry.Event_ID +") REFERENCES "+EventEntry.TABLE_NAME+"("+EventEntry._ID+")" +
                ")";


        //Needs Foreign key
        final String CreateAwardObtained = "CREATE TABLE " +
                AwardObtained.TABLE_NAME + "( "+
                AwardObtained.AwardObtained_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                AwardObtained.AwardID + " TEXT NOT NULL, " +
                AwardObtained.Resident_ID + " TEXT NOT NULL " +
                ")";

        //Need foregin keys
        final String CreateOrganizedEvents = "CREATE TABLE " +
                OrganizedEvents.TABLE_NAME + "( "+
                OrganizedEvents.OrganizedID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                OrganizedEvents.RA_ID+ " TEXT NOT NULL, " +
                OrganizedEvents.Event_ID + " TEXT NOT NULL, " +
                OrganizedEvents.Date + " TEXT NOT NULL " +
                ")";

        sqldb.execSQL(CreateUserTable);
        sqldb.execSQL(CreateEvent);
        sqldb.execSQL(CreateAwards);

        Log.d("dbHandler", "If it made it this far, database was given a error");
    }

    //i'll get back to fixing the rest of the updates and inserts
    public void onUpgrade(SQLiteDatabase sqldb, int i, int i1){
        sqldb.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        sqldb.execSQL("DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME);
        sqldb.execSQL("DROP TABLE IF EXISTS " + Awards.TABLE_NAME);
        onCreate(sqldb);
    }

}
