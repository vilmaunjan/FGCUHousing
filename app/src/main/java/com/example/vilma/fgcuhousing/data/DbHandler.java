package com.example.vilma.fgcuhousing.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.HousingContract.*;


/**
 * Created by Andrew on 3/10/2017.
 * Handles the dataBase
 */

public class DbHandler extends SQLiteOpenHelper {
    //Used for the name ad database version whenever updating schema
    private static final String DATABASE_NAME = "HousingDB";
    private static final int DATABASE_VERSION = 1;
    private static final String Tag = "DataBaseHelper";
    private static SQLiteDatabase db = null;

    public DbHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqldb){
        //Creates the tables
        final String CreateUserTable = "CREATE TABLE " +
                UserEntry.TABLE_NAME + "( "+
                UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                UserEntry.Name + " TEXT NOT NULL, " +
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
                EventEntry.Location + " TEXT NOT NULL, " +
                EventEntry.Event_Date + " DATE, " +
                EventEntry.Event_Time + " TIME, " +
                EventEntry.BUILDING + " TEXT NOT NULL, " +
                EventEntry.IMAGE + " TEXT " +
                ")";

        final String CreateAwards = "CREATE TABLE " +
                Awards.TABLE_NAME + "( "+
                Awards.COLUMN_Award_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Awards.COLUMN_Award_Name + " TEXT NOT NULL, "+
                Awards.COLUMN_Award_Description + " TEXT, "+
                Awards.COLUMN_Image + " TEXT " +
                ")";

        final String CreateOrganizedEvents = "CREATE TABLE " +
                OrganizedEvents.TABLE_NAME + "( "+
                OrganizedEvents.OrganizedID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                OrganizedEvents.RA_ID+ " TEXT NOT NULL, " +
                OrganizedEvents.Event_ID + " TEXT NOT NULL, " +
                OrganizedEvents.Date + " TEXT NOT NULL, " +
                " FOREIGN KEY ("+ OrganizedEvents.RA_ID +") REFERENCES "+
                UserEntry.TABLE_NAME+"("+UserEntry._ID+")," +
                " FOREIGN KEY ("+ OrganizedEvents.Event_ID +") REFERENCES "+
                EventEntry.TABLE_NAME+"("+EventEntry._ID+")" +
                ")";

        final String CreateAttendedEvent = "CREATE TABLE " +
                AttendedEventEntry.TABLE_NAME + "( "+
                AttendedEventEntry.Rating_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                AttendedEventEntry.Resident_ID + " INTEGER, " +
                AttendedEventEntry.Event_ID + " INTEGER, " +
                AttendedEventEntry.Time_Stayed + " TEXT NOT NULL, " +
                AttendedEventEntry.Rating_Score + " TEXT NOT NULL, " +
                AttendedEventEntry.Rating_FeedBack + " TEXT NOT NULL, " +
                " FOREIGN KEY ("+
                AttendedEventEntry.Resident_ID +
                ") REFERENCES "+UserEntry.TABLE_NAME+"("+UserEntry._ID+")," +
                " FOREIGN KEY ("+ AttendedEventEntry.Event_ID +") REFERENCES "+
                EventEntry.TABLE_NAME+"("+EventEntry._ID+")" +
                ")";

        final String CreateAwardObtained = "CREATE TABLE " +
                AwardObtained.TABLE_NAME + "( "+
                AwardObtained.AwardObtained_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                AwardObtained.AwardID + " TEXT NOT NULL, " +
                AwardObtained.Resident_ID + " TEXT NOT NULL, " +
                " FOREIGN KEY ("+ AwardObtained.Resident_ID +") REFERENCES "+
                UserEntry.TABLE_NAME+"("+UserEntry._ID+")," +
                " FOREIGN KEY ("+ AwardObtained.AwardID +") REFERENCES "+
                Awards.TABLE_NAME+"("+Awards.COLUMN_Award_ID+")" +
                ")";



        //Execute the commands in proper order
        sqldb.execSQL(CreateUserTable);
        sqldb.execSQL(CreateEvent);
        sqldb.execSQL(CreateAwards);
        sqldb.execSQL(CreateAttendedEvent);
        sqldb.execSQL(CreateAwardObtained);
        sqldb.execSQL(CreateOrganizedEvents);

        //Log is used like system.out.println. You have to click on android monitor on the bottom
        //click log cat and then where you see verbose just type the name of the Tag
        //I declared to see it's specific log only
        Log.d(Tag, "If it made it this far, database was created successfully");
        db = sqldb;
    }

    //i'll get back to fixing the rest of the updates and inserts
    public void onUpgrade(SQLiteDatabase sqldb, int i, int i1){
        sqldb.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        sqldb.execSQL("DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME);
        sqldb.execSQL("DROP TABLE IF EXISTS " + Awards.TABLE_NAME);
        sqldb.execSQL("DROP TABLE IF EXISTS " + AwardObtained.TABLE_NAME);
        sqldb.execSQL("DROP TABLE IF EXISTS " + AttendedEventEntry.TABLE_NAME);
        sqldb.execSQL("DROP TABLE IF EXISTS " + OrganizedEvents.TABLE_NAME);
        onCreate(sqldb);
    }
    /**
     * customize-able Toast Message
     * Message Pops up on android screen
     * @param message Toast
     */
    private void toastMessage(Context c,String message){
        Toast.makeText(c,message, Toast.LENGTH_SHORT).show();
    }


    //Uses this to get everything from ResidentTable
    //planning on working on more of these for now to make it easier later
    //Should be able to get started with using the database right now though

    public Cursor getData(){
        //use getReadableDatabase whenever you just want to read valuse
        //from database
        SQLiteDatabase db = this.getReadableDatabase();
        //String for Query
        String query = "select *" +
                "From " + UserEntry.TABLE_NAME;
        //inputs query and the return is always a cursor
        Cursor data = db.rawQuery(query, null);
        //returns the data
        return data;
    }

    //Used to insert a user into the database
    public void insertUser(Context c, User usr){
        Log.d("insertUser", usr.getEmail());
        SQLiteDatabase homebase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserEntry.Name, usr.getName());
        values.put(UserEntry.Email, usr.getEmail());
        values.put(UserEntry.Password, usr.getPassword());
        values.put(UserEntry.Type, "r");
        values.put(UserEntry.Building, usr.getBuilding());
        long result = homebase.insert(UserEntry.TABLE_NAME, null, values);
    }

    public boolean insertEvent(Event evt){

            Log.d("insertEvent", evt.getTitle());
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(EventEntry.Event_Title, evt.getTitle());
            values.put(EventEntry.Description, evt.getDescription());
            values.put(EventEntry.Location, evt.getLocation());
            values.put(EventEntry.Event_Time, evt.getTime());
            values.put(EventEntry.Event_Date, evt.getDate());
            values.put(EventEntry.BUILDING, evt.getBuilding());
            values.put(EventEntry.IMAGE, evt.getImage());
            long result = db.insert(EventEntry.TABLE_NAME, null, values);
            if(result == -1)
                return false;
            else
                return true;
    }

    public Cursor QueryData(String query) throws SQLException{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Search for email and return corresponding password.
    public String searchPassword(String emailEntry){
        Log.d("696969", "Reached set password");
        String password = "";
        String email = "Not found";
        db = this.getReadableDatabase();
        String query = "select " + UserEntry.Email + ", " + UserEntry.Password + " from "
                + UserEntry.TABLE_NAME + "";
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    email = cursor.getString(0);
                    Log.d("searchPassword", email);
                    if (email.equals(emailEntry)) {
                        password = cursor.getString(1);
                        Log.d("searchPassword", password);
                        break;
                    }
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            Log.d("DataBase", e.getMessage());
        }
        return password;
    }

    //Get awards info
    public Cursor getAwardsInfo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        String [] projections = {Awards.COLUMN_Award_ID, Awards.COLUMN_Award_Description,
                Awards.COLUMN_Award_Name, Awards.COLUMN_Image};

        cursor = db.query(Awards.TABLE_NAME, projections, null, null, null, null, null);

        return cursor;
    }

}
