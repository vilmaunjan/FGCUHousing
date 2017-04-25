package com.example.vilma.fgcuhousing.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.data.HousingContract.AttendedEventEntry;
import com.example.vilma.fgcuhousing.data.HousingContract.AwardObtained;
import com.example.vilma.fgcuhousing.data.HousingContract.Awards;
import com.example.vilma.fgcuhousing.data.HousingContract.EventEntry;
import com.example.vilma.fgcuhousing.data.HousingContract.OrganizedEvents;
import com.example.vilma.fgcuhousing.data.HousingContract.UserEntry;


/**
 * Created by Andrew on 3/10/2017.
 * Handles the dataBase
 */

public class DbHandler extends SQLiteOpenHelper {
    //Used for the name ad database version whenever updating schema
    private static final String DATABASE_NAME = "HousingDB";
    private static final int DATABASE_VERSION = 9;
    private static final String Tag = "DataBaseHelper";
    private static SQLiteDatabase db = null;

    public DbHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqldb){
            //create db
            //Creates the tables
            final String CreateUserTable = "CREATE TABLE " +
                    UserEntry.TABLE_NAME + "( " +
                    UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    UserEntry.Name + " TEXT NOT NULL, " +
                    UserEntry.Email + " TEXT NOT NULL, " +
                    UserEntry.Password + " TEXT NOT NULL," +
                    UserEntry.Type + " TEXT NOT NULL, " +
                    UserEntry.Building + " TEXT NOT NULL" +
                    ")";

            final String CreateEvent = "CREATE TABLE " +
                    EventEntry.TABLE_NAME + "( " +
                    EventEntry.Event_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EventEntry.Event_Title + " TEXT NOT NULL, " +
                    EventEntry.Description + " TEXT NOT NULL, " +
                    EventEntry.Location + " TEXT NOT NULL, " +
                    EventEntry.Event_Date + " DATE, " +
                    EventEntry.Event_Time + " TIME, " +
                    EventEntry.BUILDING + " TEXT NOT NULL, " +
                    EventEntry.IMAGE + " TEXT " +
                    ")";

            final String CreateAwards = "CREATE TABLE " +
                    Awards.TABLE_NAME + "( " +
                    Awards.COLUMN_Award_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Awards.COLUMN_Award_Name + " TEXT NOT NULL, " +
                    Awards.COLUMN_Award_Description + " TEXT, " +
                    Awards.COLUMN_Image + " TEXT " +
                    ")";

            final String CreateOrganizedEvents = "CREATE TABLE " +
                    OrganizedEvents.TABLE_NAME + "( " +
                    OrganizedEvents.OrganizedID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    OrganizedEvents.RA_ID + " TEXT NOT NULL, " +
                    OrganizedEvents.Event_ID + " TEXT NOT NULL, " +
                    OrganizedEvents.Date + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    " FOREIGN KEY (" + OrganizedEvents.RA_ID + ") REFERENCES " +
                    UserEntry.TABLE_NAME + "(" + UserEntry._ID + ")," +
                    " FOREIGN KEY (" + OrganizedEvents.Event_ID + ") REFERENCES " +
                    EventEntry.TABLE_NAME + "(" + EventEntry._ID + ")" +
                    ")";

            final String CreateAttendedEvent = "CREATE TABLE " +
                    AttendedEventEntry.TABLE_NAME + "( " +
                    AttendedEventEntry.Rating_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AttendedEventEntry.Resident_ID + " INTEGER NOT NULL, " +
                    AttendedEventEntry.Event_ID + " INTEGER NOT NULL, " +
                    AttendedEventEntry.Checked_IN + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    AttendedEventEntry.Time_Stayed + " TEXT, " +
                    AttendedEventEntry.Rating_Score + " INTEGER, " +
                    AttendedEventEntry.Rating_FeedBack + " TEXT, " +
                    " FOREIGN KEY (" +
                    AttendedEventEntry.Resident_ID +
                    ") REFERENCES " + UserEntry.TABLE_NAME + "(" + UserEntry._ID + ")," +
                    " FOREIGN KEY (" + AttendedEventEntry.Event_ID + ") REFERENCES " +
                    EventEntry.TABLE_NAME + "(" + EventEntry._ID + ")" +
                    ")";

            final String CreateAwardObtained = "CREATE TABLE " +
                    AwardObtained.TABLE_NAME + "( " +
                    AwardObtained.AwardObtained_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AwardObtained.AwardID + " TEXT NOT NULL, " +
                    AwardObtained.Resident_ID + " TEXT NOT NULL, " +
                    " FOREIGN KEY (" + AwardObtained.Resident_ID + ") REFERENCES " +
                    UserEntry.TABLE_NAME + "(" + UserEntry._ID + ")," +
                    " FOREIGN KEY (" + AwardObtained.AwardID + ") REFERENCES " +
                    Awards.TABLE_NAME + "(" + Awards.COLUMN_Award_ID + ")" +
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
        insertDaFakes(db);
    }

    //Inserts the fake data into the database
    private void insertDaFakes(SQLiteDatabase db) {
        DbTest.insertFakeData(db);
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

    //Gets all data from a table, not sure if we need this just yet
    public Cursor getEvents(CurrentUser ep){
        db = this.getReadableDatabase();
        Cursor UserSpecificEvents = null;


        String table = EventEntry.TABLE_NAME +" e  inner join " + AttendedEventEntry.TABLE_NAME +
                " a"+
                " on e. "+ EventEntry.Event_ID +" = a." + AttendedEventEntry.Event_ID +";";

        String [] columns = {"e." + EventEntry.Event_Title, "a." +
                AttendedEventEntry.Rating_FeedBack, "a." +
                AttendedEventEntry.Checked_IN, "a." + AttendedEventEntry.Time_Stayed, "a." +
                AttendedEventEntry.Event_ID, "a."+ AttendedEventEntry.Rating_ID, "a." +
                AttendedEventEntry.Rating_Score};

        String where = AttendedEventEntry.Resident_ID + " = " + ep.getID();

//                SELECT e.Title, a.RatingFeedBack, a.CheckedIn, a.TimeStayed,
//                a.Event_ID, a.Rating_ID, a.RatingScore
//                   FROM AttendedEvents a inner join Event e
//                   on a.Event_ID = e.Event_ID
//                  Where a.Resident_ID = 5

        try {
            //table is the inner join clause
            //colums is which colums to select
            //after that is the where claus
            // null for the rest which is order by group by, etc
            UserSpecificEvents = db.query(table, columns, where,
                    null, null, null, null);

        }catch (Exception e){
            Log.d("LogIn", "There was a error here's the message");
            e.getMessage();
        }

        return UserSpecificEvents;
    }


    //Used to insert a user into the database
    public void insertUser(Context c, User usr){
        Log.d("insertUser", usr.getEmail());
         db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserEntry.Name, usr.getName());
        values.put(UserEntry.Email, usr.getEmail());
        values.put(UserEntry.Password, usr.getPassword());
        values.put(UserEntry.Type, usr.getType());
        values.put(UserEntry.Building, usr.getBuilding());
        long result = db.insert(UserEntry.TABLE_NAME, null, values);
        db.close();
    }

    //This returns a CurrentUser with information from database based on email
    //Depreciated
    public CurrentUser loginAS(String email){
        CurrentUser admin = new CurrentUser();
        String query = "Select * from "+ UserEntry.TABLE_NAME +" where " +
                UserEntry.Email +" = \"" + email + "\";";
        Cursor find = QueryData(query);

        if (find.moveToFirst()) {
            do {
               admin.setID(find.getInt(0));
                admin.setName(find.getString(1));
                admin.setEmail(find.getString(2));
                admin.setPassword(find.getString(3));
                admin.setAccountType(find.getString(4));
                admin.setBuilding(find.getString(5));
            }
            while (find.moveToNext());
        }

        db.close();
        return admin;
    }

    //Creates Event in database
    public boolean insertEvent(Event evt, CurrentUser creator) {
        boolean TheReturner;
        //If event does not exist
        if(!Eventcheck(evt.getTitle())) {
            Log.d("insertEvent", evt.getTitle());

             db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(EventEntry.Event_Title, evt.getTitle());
            values.put(EventEntry.Description, evt.getDescription());
            values.put(EventEntry.Location, evt.getLocation());
            values.put(EventEntry.Event_Time, evt.getTime());
            values.put(EventEntry.Event_Date, evt.getDate());
            values.put(EventEntry.BUILDING, evt.getBuilding());
            values.put(EventEntry.IMAGE, evt.getImage());

            long result = db.insert(EventEntry.TABLE_NAME, null, values);
            TheReturner = result != -1;
            AddCreator(evt, creator);
        }else{
            TheReturner = false;
        }
        return TheReturner;
    }

    //Adds the user who created the event
    private void AddCreator(Event evt, CurrentUser creator) {

        String query = "Select * from "+ EventEntry.TABLE_NAME +" where " +
                EventEntry.Event_Title +" = \"" + evt.getTitle() + "\";";
        Cursor find = QueryData(query);
        try {
            if (find.moveToFirst()) {
                do {
                    evt.setId(find.getInt(0));
                    Log.d("CreatingEvent", "The name is :" + find.getString(1) +
                            " The id for this is : " + evt.getId());
                }
                while (find.moveToNext());
            }
        }catch (Exception e){
            Log.d("CreatingEvent", "Somethign went wong");
        }


        ContentValues Manager = new ContentValues();
        Manager.put(OrganizedEvents.RA_ID, creator.getID());
        Manager.put(OrganizedEvents.Event_ID, evt.getId());



        try {
            db = this.getWritableDatabase();
            db.insert(OrganizedEvents.TABLE_NAME, null, Manager);
            db.close();
        } catch (Exception e) {
            Log.d("CreatingEvent", e.getMessage() );
        }
    }

    //Checks if the event was created already
    private boolean Eventcheck(String EventTitle){
        db = this.getReadableDatabase();
        boolean Here= false;
        String query = "Select * from "+ EventEntry.TABLE_NAME +" where " +
                EventEntry.Event_Title +" = \"" + EventTitle + "\";";
        Cursor find = QueryData(query);
        try {
            if (find.moveToFirst()) {
                do {
                    find.getString(1);
                    Here = true;
                    Log.d("CreatingEvent", "The name is :" + find.getString(1) +
                            " The id for this is : " + find.getInt(0));
                }
                while (find.moveToNext());
            }
        }catch (Exception e){
            Log.d("CreatingEvent", "Somethign went wong");
        }
        db.close();
        return Here;
    }

    //Updates the Event with new values
    public boolean updateEvent(String id, Event event){
        boolean TheReturner;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        try {
            cv.put(EventEntry.Event_Title, event.getTitle()); //These Fields should be
            // your String values of actual column names
            cv.put(EventEntry.Description, event.getDescription());
            cv.put(EventEntry.Location, event.getLocation());
            cv.put(EventEntry.BUILDING, event.getBuilding());
            cv.put(EventEntry.Event_Time, event.getTime());
            cv.put(EventEntry.Event_Date, event.getDate());
            //cv.put(EventEntry.IMAGE, event.getImage()); //Commented out untill we have
            // importing images up and running

            int update = db.update(EventEntry.TABLE_NAME, cv, EventEntry.Event_ID +
                    " = ? ", new String[]{id});
            TheReturner = update != -1;
        }catch (Exception e){
            Log.d("UpdateEvent", "The event failed to update its settings oh no!");
            TheReturner = false;
        }
        return TheReturner;
    }

    //Simple method to pass a query string
    public Cursor QueryData(String query) throws SQLException{
        db = this.getReadableDatabase();
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Search for email and return corresponding password.
    public boolean emailCheck(String emailEntry){
        Log.d("696969", "Reached set password");
        boolean exists = false;
        String email = "";
        db = this.getReadableDatabase();
        String query = "select " + UserEntry.Email + " from "
                + UserEntry.TABLE_NAME ;
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    email = cursor.getString(0);

                    Log.d("EmailCheck", email);

                    if (email.equals(emailEntry)) {
                        exists = true;
                        Log.d("searchPassword", "I guess it lives!");
                    }
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }catch (Exception e){

            Log.d("EmailCheck", e.getMessage());
        }
        return exists;
    }

    //Search for email and return corresponding password.
    public boolean searchPassword(String emailEntry, String pass){
        boolean Match=false;
        String email;
        String password;
        String query = "Select * from "+ UserEntry.TABLE_NAME +" where " +
                UserEntry.Email +" = \"" + emailEntry + "\";";
        try {
            Cursor cursor = QueryData(query);
            if (cursor.moveToFirst()) {
                do {
                    email = cursor.getString(2);
                    password = cursor.getString(3);
                    Log.d("AccountSetUp", "The given email is : " + email);
                    if (email.equals(emailEntry)) {
                        if(password.equals(pass)){
                            Match = true;
                        }
                    }
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            Log.d("DataBase", e.getMessage());
        }
        return Match;
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

    //Used to set the id when creating the row for attended events
    private boolean setEventid(CurrentUser attendee, Event evt) {
        db = this.getReadableDatabase();
        boolean Here= false;
        //The usual sql stuff
        String query = "Select * from "+ AttendedEventEntry.TABLE_NAME +" where " +
                AttendedEventEntry.Resident_ID +" = \"" + attendee.getID() + "\" " +
                " AND "+ AttendedEventEntry.Event_ID +" = " +
                attendee.getEvents().get(evt.getTitle()).getEventID() +
                " ;";
        try {
        Cursor find = QueryData(query);

            if (find.moveToFirst()) {
                do {
                    attendee.getEvents().get(evt.getTitle()).setAttendedId(find.getInt(0));
                    Here = true;
                    Log.d("CreatingEvent", "The Resident ID is :" + find.getInt(1) +
                            " The ID  for this is : " + find.getInt(0));
                }
                while (find.moveToNext());
            }
        }catch (Exception e){
            Log.d("CreatingattendedEvent", "Somethign went wong");
            Log.d("CreatingAttendedEvent", e.getMessage());
        }
        db.close();
        return Here;

    }
    //Used to insert the rest of the values in EventAttended Table when they Check Out
    public void EventCheckOut(String evt,int evtId, CurrentUser attendee){

        db = this.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + AttendedEventEntry.TABLE_NAME + " SET " +
                    AttendedEventEntry.Time_Stayed
                    + " = datetime()  WHERE " + AttendedEventEntry.Event_ID + " = " +
                    evtId + " AND " +
                    AttendedEventEntry.Resident_ID + " = " + attendee.getID());
            Log.d("Updating", "Is it even making it over here?");
            ContentValues cv = new ContentValues();
            cv.put(AttendedEventEntry.Rating_Score, attendee.getEvents().get(evt).getRating());
            cv.put(AttendedEventEntry.Rating_FeedBack, attendee.getEvents().get(evt).getFeedBack());

            db.update(AttendedEventEntry.TABLE_NAME, cv, AttendedEventEntry.Event_ID +
                            " = ? AND " + AttendedEventEntry.Resident_ID + " = ? ",
                    new String[]{String.valueOf(evtId), String.valueOf(attendee.getID())});
        }catch (SQLException s){
            Log.d("UPDATE", s.getMessage());
        }

    }
    //Inserts a row in AttendedTable of events
    public boolean insertEventAttended(Event evt, CurrentUser creator) {
        boolean TheReturner;
            //If they have not attended the event

        if(!(creator.getEvents().containsKey(evt.getTitle()))) {
            Log.d("insertEvent", evt.getTitle());
            //Create a instance of UserEvents and inserts the ID's of the event and Ra for now
            //Another method add's the rest
            UserEvents Up = new UserEvents();
            Up.setTitle(evt.getTitle());
            Up.setEventID(evt.getId());
            Up.setCheckedin(true);
            creator.getEvents().put(evt.getTitle(), Up);//Sets it in the hashmap

            //This inputs it into the attended database
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(AttendedEventEntry.Event_ID, evt.getId());
            values.put(AttendedEventEntry.Resident_ID, creator.getID());

            long result = db.insert(AttendedEventEntry.TABLE_NAME, null, values);
            //This sets the id in the event it was called from in the current user id
            setEventid(creator, evt);
            creator.getEvents().get(evt.getTitle()).setCheckedin(true);
            Log.d("CreatingAttendedEvent", "The id of the attended event is : " +
                    creator.getEvents().get(evt.getTitle()).getAttendedId());
            TheReturner = result != -1;
        } else {
            TheReturner = false;
        }


        return TheReturner;
    }

    //Deletes an Event from database
    public void deleteEvent(String event_id) {
        //Have to delete from Event, OrganizedEvent, and AttendedEvent Tables
        //Which ever has this event ID, also have to make sure any user that previously
        // had this event. attended Also has this value removed or not accessible
        //Delete order All entries from AttendedEvent, all entry from OrganizedEvent
        // and then the event entry itself
        db = this.getWritableDatabase();
        try {
            db.delete(AttendedEventEntry.TABLE_NAME, AttendedEventEntry.Event_ID +
                    " = ?", new String[]{event_id});
            db.delete(OrganizedEvents.TABLE_NAME, OrganizedEvents.Event_ID + " = ?",
                    new String[]{event_id});
            db.delete(EventEntry.TABLE_NAME, EventEntry.Event_ID + " = ?", new String[]{event_id});
        }catch (Exception e){
            Log.d("DeleteOperation", "If you see this then there is an error in deleting");
        }

    }

    public Boolean updateProfile(CurrentUser CU) {
        db = this.getWritableDatabase();
        boolean TheReturner;
        ContentValues cv = new ContentValues();
        try {
            cv.put(UserEntry.Name, CU.getName());
            cv.put(UserEntry.Email, CU.getEmail());
            cv.put(UserEntry.Password, CU.getPassword());
            cv.put(UserEntry.Building, CU.getBuilding());

            int update = db.update(UserEntry.TABLE_NAME, cv, UserEntry._ID + " = ? ",
                    new String[]{String.valueOf(CU.getID())});
            TheReturner = update != -1;
        }catch (Exception e){
            Log.d("UpdateUser", "Oh No The user file wasnt updated ErrorError");
            TheReturner = false;
        }
        return TheReturner;
    }


    public Cursor UserEvents(int id) {

        db = this.getReadableDatabase();
        Cursor UserSpecificEvents = null;
        String table = EventEntry.TABLE_NAME +" e  inner join " + OrganizedEvents.TABLE_NAME +" o"+
                " on e. "+ EventEntry.Event_ID +" = o." + OrganizedEvents.Event_ID +";";

        String [] columns = {"e." + EventEntry.Event_ID, "e." + EventEntry.Event_Title, "e." +
                EventEntry.Description, "e." + EventEntry.Location, "e." +
                EventEntry.Event_Date, "e."+ EventEntry.Event_Time, "e." +
                EventEntry.BUILDING, "e." + EventEntry.IMAGE};

/*
        Select all the events created by a user

        Select e.Event_ID, e.Title, e.Description, e.Location, e.Created, e.Time,
        e.Building, e.Image

        FROM Event e  inner join OrganizedEvent o
        on e.Event_ID = o.RA_ID*/

        try {
            //table is the inner join clause
            //colums is which colums to select
            //after that is the where claus
            // null for the rest which is order by group by, etc
            UserSpecificEvents = db.query(table, columns, OrganizedEvents.RA_ID + " = " + id,
                    null, null, null, null);

        }catch (Exception e){
            Log.d("FindingUserEvents", "There was a error here's the message");
            e.getMessage();
        }

        return UserSpecificEvents;
    }
}
