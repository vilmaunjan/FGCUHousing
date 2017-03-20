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
        final String SQL_Create_HousingLogin_TABLE = "CREATE TABLE " +
                ResidentEntry.TABLE_NAME + "( "+
                ResidentEntry.COLUMN_R_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ResidentEntry.COLUMN_USER_NAME + " TEXT NOT NULL, " +
                ResidentEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                ResidentEntry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                ResidentEntry.COLUMN_BUILDING + " TEXT" +
                ")";

        final String CreateResidentAssistant = "CREATE TABLE" +
                ResidentAssistantEntry.TABLE_NAME + "( "+
                ResidentAssistantEntry.COLUMN_RA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ResidentAssistantEntry.COLUMN_UIN+ " INTEGER NOT NULL,"+
                ResidentAssistantEntry.COLUMN_USER_NAME + " TEXT NOT NULL, " +
                ResidentAssistantEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                ResidentAssistantEntry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                ResidentAssistantEntry.COLUMN_BUILDING + " TEXT" +
                ")";

        final String CreateResidentDirector = "CREATE TABLE" +
                ResidentDirectorEntry.TABLE_NAME + "( "+
                ResidentDirectorEntry.COLUMN_RD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ResidentDirectorEntry.COLUMN_USER_NAME + " TEXT NOT NULL, " +
                ResidentDirectorEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                ResidentDirectorEntry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                ")";

        final String CreateAttendedEvent = "CREATE TABLE" +
                AttendedEventEntry.TABLE_NAME + "( "+
                AttendedEventEntry.COLUMN_Rating_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                AttendedEventEntry.COLUMN_Resident_ID + " TEXT NOT NULL, " +
                AttendedEventEntry.COLUMN_Event_ID + " TEXT NOT NULL, " +
                AttendedEventEntry.COLUMN_Time_Stayed + " TEXT NOT NULL, " +
                AttendedEventEntry.COLUMN_Rating_Score + " TEXT NOT NULL, " +
                AttendedEventEntry.COLUMN_Ratomg_FeedBack + " TEXT NOT NULL, " +
                ")";

        final String CreateAwards = "CREATE TABLE" +
                Awards.TABLE_NAME + "( "+
                Awards.COLUMN_Award_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Awards.COLUMN_Attended_Num + " TEXT NOT NULL, " +
                Awards.COLUMN_Image + " TEXT NOT NULL, " +
                ")";

        final String CreateAwardObtained = "CREATE TABLE" +
                AwardObtained.TABLE_NAME + "( "+
                AwardObtained.COLUMN_AwardObtained_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                AwardObtained.COLUMN_AwardID + " TEXT NOT NULL, " +
                AwardObtained.COLUMN_Resident_ID + " TEXT NOT NULL, " +
                ")";

        final String CreateOrganizedEvents = "CREATE TABLE" +
                OrganizedEvents.TABLE_NAME + "( "+
                OrganizedEvents.COLUMN_OrganizedID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                OrganizedEvents.COLUMN_RA_ID+ " TEXT NOT NULL, " +
                OrganizedEvents.COLUMN_Event_ID + " TEXT NOT NULL, " +
                OrganizedEvents.COLUMN_Date + " TEXT NOT NULL, " +
                ")";

        sqldb.execSQL(SQL_Create_HousingLogin_TABLE);
        Log.d("dbHandler", "If it made it this far, database was given a error");
    }

    //i'll get back to fixing the rest of the updates and inserts
    public void onUpgrade(SQLiteDatabase sqldb, int i, int i1){
        sqldb.execSQL("DROP TABLE IF EXISTS " + ResidentEntry.TABLE_NAME);
        onCreate(sqldb);
    }

}
