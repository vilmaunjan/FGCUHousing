package com.example.vilma.fgcuhousing.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.vilma.fgcuhousing.data.HousingContract.*;
/**
 * Created by Andrew on 3/10/2017.
 * Used this to inser Fake data and test the database to make sure
 * a connection is able to be made and modified.
 */

public class DbTest {
    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> User = new ArrayList<>();
        List<ContentValues> Event = new ArrayList<>();
        List<ContentValues> awards = new ArrayList<>();



        //create a list of fake guests
        ContentValues cv = new ContentValues();
        cv.put(UserEntry.Name, "John");
        cv.put(UserEntry.Email, "JohnBou@eagle.fgcu.edu");
        cv.put(UserEntry.Password, "north");
        cv.put(UserEntry.Type, "R");
        cv.put(UserEntry.Building, "North Village");

        User.add(cv);

        cv = new ContentValues();
        cv.put(UserEntry.Name, "Kim");
        cv.put(UserEntry.Email, "KimBou@eagle.fgcu.edu");
        cv.put(UserEntry.Password, "north");
        cv.put(UserEntry.Type, "RA");
        cv.put(UserEntry.Building, "South Village");

        User.add(cv);

        cv = new ContentValues();
        cv.put(UserEntry.Name, "Johnny");
        cv.put(UserEntry.Email, "JohnDep@fgcu.edu");
        cv.put(UserEntry.Password, "north");
        cv.put(UserEntry.Type, "RA");
        cv.put(UserEntry.Building, "South Village");

        User.add(cv);

        cv = new ContentValues();
        cv.put(UserEntry.Name, "Bonnie");
        cv.put(UserEntry.Email, "Bonnie@eagle.fgcu.edu");
        cv.put(UserEntry.Password, "north");
        cv.put(UserEntry.Type, "R");
        cv.put(UserEntry.Building, "North Village");

        User.add(cv);

        cv = new ContentValues();
        cv.put(UserEntry.Name, "Belly");
        cv.put(UserEntry.Email, "cooper@fgcu.edu");
        cv.put(UserEntry.Password, "north");
        cv.put(UserEntry.Type, "RD");
        cv.put(UserEntry.Building, "North Village");

        User.add(cv);

        //create a list of fake events
        cv = new ContentValues();
        cv.put(EventEntry.Event_Title, "LakesPark");//title
        cv.put(EventEntry.Description, "FGCU Martial Arts");//description
        cv.put(EventEntry.Location, "LakesParkAddress");
        cv.put(EventEntry.Event_Date, "date('now')");//date
        cv.put(EventEntry.Event_Time, "time(\"15:05\")");//time
        cv.put(EventEntry.BUILDING, "All");//building
        cv.put(EventEntry.IMAGE, "LakesPark");//images

        Event.add(cv);

        cv = new ContentValues();
        cv.put(EventEntry.Event_Title, "PartyAtCindys");//title
        cv.put(EventEntry.Description, "Time to relax and mingle");//description
        cv.put(EventEntry.Location, "Cindy's House");
        cv.put(EventEntry.Event_Date, "date('now')");//date
        cv.put(EventEntry.Event_Time, "time(\"17:05\")");//time
        cv.put(EventEntry.BUILDING, "South Village");//building
        cv.put(EventEntry.IMAGE, "party");//images

        Event.add(cv);

        cv = new ContentValues();
        cv.put(EventEntry.Event_Title, "CampingOutside");//title
        cv.put(EventEntry.Description, "Camping adventures on nature Trail");//description
        cv.put(EventEntry.Location, "Front of South Village");
        cv.put(EventEntry.Event_Date, "date('now')");//date
        cv.put(EventEntry.Event_Time, "time(\"19:05\")");//time
        cv.put(EventEntry.BUILDING, "South Village");//building
        cv.put(EventEntry.IMAGE, "whatever");//images

        Event.add(cv);

        //create a list of fake awards
        cv = new ContentValues();
        cv.put(Awards.COLUMN_Award_Name, "MasteredKungFu");//AwardName
        cv.put(Awards.COLUMN_Award_Description, "Attended an FGCU Martial arts event");//description
        cv.put(Awards.COLUMN_Image, "FGCU Martial Arts Logo");//image

        awards.add(cv);

        cv = new ContentValues();
        cv.put(Awards.COLUMN_Award_Name, "Left The Coop");//awardName
        cv.put(Awards.COLUMN_Award_Description, "Left the room");//description
        cv.put(Awards.COLUMN_Image, "FGCU Martial Arts Logo");//image

        awards.add(cv);

        ArrayList<String> tables = new ArrayList();
        tables.add(UserEntry.TABLE_NAME);
        tables.add(EventEntry.TABLE_NAME);
        tables.add(Awards.TABLE_NAME);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();


            //clear the table first
            db.delete (UserEntry.TABLE_NAME,null,null);
            db.delete(EventEntry.TABLE_NAME, null,null);
            db.delete(Awards.TABLE_NAME, null,null);

            //go through the list and add one by one
            for(ContentValues c:User){
                db.insert(UserEntry.TABLE_NAME, null, c);
            }

            //go through the list and add one by one
            for(ContentValues c:Event){
                db.insert(EventEntry.TABLE_NAME, null, c);
            }

            //go through the list and add one by one
            for(ContentValues c:awards){
                db.insert(Awards.TABLE_NAME, null, c);
            }



            db.setTransactionSuccessful();


        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

        addOrganizedEvents(db);
    }


    /**
    *Using this method to add fake data to the rest of the tables
    *Had to create the first 3 tables first some values before i can call them
    *Here to get there id for the foreign key's
    *Mainly having problem getting the actual value of the field
     * to return right so far getting the column name as a return
     * which is weird
     **/
    public static void addOrganizedEvents(SQLiteDatabase db){
        Cursor ra;
        ContentValues cv;
        String KimId= "";

        List<ContentValues> Obtained = new ArrayList<>();
        List<ContentValues> Attended = new ArrayList<>();
        List<ContentValues> Orangi = new ArrayList<>();


        //Can do query like thi or just do a raw query
        String event1 = "Select "+ EventEntry._ID +
                " from "+ EventEntry.TABLE_NAME +
                " Where " + EventEntry.Event_Title + " = 'LakesPark'";

        String findKim = "Select "+ UserEntry._ID +
                " from "+ UserEntry.TABLE_NAME +
                " Where " + UserEntry.Name + " = 'Kim'";

        String p = "testing";
        //what I was last working on to try and get it to work, use the "Babe" keyword
        //to see in the log cat whats being printed out and the error
        try{
            ra = db.rawQuery("SELECT ? FROM Resident WHERE fname = ? ", new String[] {UserEntry._ID,"Kim"});

            if(ra.getCount() > 0) {
                ra.moveToFirst();

                Log.d(p,ra.getString(0));
                Log.d(p,ra.getColumnName(1));

                Log.d(p, (String.valueOf( ra.getColumnIndex("_id"))));
                //KimId =  ra.getString(ra.getColumnIndex(UserEntry._ID));
                //empName = cursor.getString(cursor.getColumnIndex("EmployeeName"));

            }


            Log.d("Babe", "succesful and KimId = " + KimId);
        } catch (Exception s){
            Log.d("Babe", "not successful");
            Log.d("Babe", s.toString());
        }

//        ra = db.rawQuery(event1, null);
//        ra.moveToNext();
//        String EventId = ra.getString(1);


        Log.d("Babe", "Think we found kim here? lets check  " + KimId);

        //create Organizing Events
        cv = new ContentValues();
        cv.put(OrganizedEvents.RA_ID, KimId);//title
        cv.put(OrganizedEvents.Event_ID, "Idk");//description
        cv.put(OrganizedEvents.Date, "date('now')");//date

        Orangi.add(cv);

//        //create a list of fake events
//        cv = new ContentValues();
//        cv.put(OrganizedEvents.RA_ID, KimId);//title
//        cv.put(OrganizedEvents.Event_ID, "FGCU Martial Arts");//description
//        cv.put(OrganizedEvents.Date, "date('now')");//date
//
//        Orangi.add(cv);
//
//        //create a list of fake events
//        cv = new ContentValues();
//        cv.put(OrganizedEvents.RA_ID, KimId);//title
//        cv.put(OrganizedEvents.Event_ID, "FGCU Martial Arts");//description
//        cv.put(OrganizedEvents.Date, "date('now')");//date
//
//        Orangi.add(cv);
//
//        try{
//            db.beginTransaction();
//
//            for(ContentValues a : Orangi){
//                db.insert(OrganizedEvents.TABLE_NAME, null, a);
//            }
//
//            db.setTransactionSuccessful();
//        }
//        catch (SQLException e) {
//        //too bad :(
//         }
//        finally
//         {
//        db.endTransaction();
//            }
    }


}
