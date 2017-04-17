package com.example.vilma.fgcuhousing.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.vilma.fgcuhousing.R;
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
        cv.put(EventEntry.Event_Title, "Sovivor");//title
        cv.put(EventEntry.Description, "Kick off summer B in the best way possible.. with an" +
                "ultimate water balloon fight, an inflatable water slide, a fire truck and more!" );//description
        cv.put(EventEntry.Location, "SOVI Green");
        cv.put(EventEntry.Event_Date, "06/21/2017");//date
        cv.put(EventEntry.Event_Time, "16:00");//time
        cv.put(EventEntry.BUILDING, "South Village");//building
        cv.put(EventEntry.IMAGE, R.drawable.event1);//images

        Event.add(cv);

        cv = new ContentValues();
        cv.put(EventEntry.Event_Title, "Apartments block party");//title
        cv.put(EventEntry.Description, "We are excited to kick off the Apartments Block Party" +
                "tomorrow night with all of our NLV & WLV residents! Join us for food, trivia," +
                "&even a VIP room for overflow students.");//description
        cv.put(EventEntry.Location, "Eagles' landing");
        cv.put(EventEntry.Event_Date, "04/23/2017");//date
        cv.put(EventEntry.Event_Time, "19:00");//time
        cv.put(EventEntry.BUILDING, "North Village");//building
        cv.put(EventEntry.IMAGE, R.drawable.event2);//images

        Event.add(cv);

        cv = new ContentValues();
        cv.put(EventEntry.Event_Title, "Gardens kick back");//title
        cv.put(EventEntry.Description, "Gardens kick back BBQ & Pool party");//description
        cv.put(EventEntry.Location, "Commons Pool Area");
        cv.put(EventEntry.Event_Date, "03/16/2017");//date
        cv.put(EventEntry.Event_Time, "17:00");//time
        cv.put(EventEntry.BUILDING, "South Village");//building
        cv.put(EventEntry.IMAGE, R.drawable.event3);//images

        Event.add(cv);

        cv = new ContentValues();
        cv.put(EventEntry.Event_Title, "Glades Neighborhood night");//title
        cv.put(EventEntry.Description, "Neighborhood night event. Come join us in eagle's landing. Free food");//description
        cv.put(EventEntry.Location, "Eagle's landing");
        cv.put(EventEntry.Event_Date, "04/14/2017");//date
        cv.put(EventEntry.Event_Time, "18:00");//time
        cv.put(EventEntry.BUILDING, "North Village");//building
        cv.put(EventEntry.IMAGE, R.drawable.event4);//images

        Event.add(cv);

        cv = new ContentValues();
        cv.put(EventEntry.Event_Title, "St. Pattys Day");//title
        cv.put(EventEntry.Description, "Food, drinks, & more. Come out and have fun! If you have any questions, contact your RA");//description
        cv.put(EventEntry.Location, "Chicke Hut");
        cv.put(EventEntry.Event_Date, "03/17/2017");//date
        cv.put(EventEntry.Event_Time, "19:00");//time
        cv.put(EventEntry.BUILDING, "North Village");//building
        cv.put(EventEntry.IMAGE, R.drawable.event5);//images

        Event.add(cv);

        cv = new ContentValues();
        cv.put(EventEntry.Event_Title, "Sham Rock n' Roll");//title
        cv.put(EventEntry.Description, "Is St. Patrick's more than a binge fest? Pool Party. " +
                "Come out and enjoy. Free food, drinks & mocktails. Sunglasses giveaways & " +
                "interactive Posters. Remote control car with obstacle courses");//description
        cv.put(EventEntry.Location, "SoVi Pool");
        cv.put(EventEntry.Event_Date, "03/17/2017");//date
        cv.put(EventEntry.Event_Time, "17:00");//time
        cv.put(EventEntry.BUILDING, "South Village");//building
        cv.put(EventEntry.IMAGE, R.drawable.event6);//images

        Event.add(cv);

        cv = new ContentValues();
        cv.put(EventEntry.Event_Title, "Late Night Breakfast");//title
        cv.put(EventEntry.Description, "Come join us to the Late Night Breakfast. ");//description
        cv.put(EventEntry.Location, "WLV community center");
        cv.put(EventEntry.Event_Date, "12/04/2017");//date
        cv.put(EventEntry.Event_Time, "21:00");//time
        cv.put(EventEntry.BUILDING, "West Village");//building
        cv.put(EventEntry.IMAGE, R.drawable.event7);//images

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

       // addOrganizedEvents(db);
    }


    /**
    *Using this method to add fake data to the rest of the tables
    *Had to create the first 3 tables first some values before i can call them
    *Here to get there id for the foreign key's
    *Mainly having problem getting the actual value of the field
     * to return right so far getting the column name as a return
     * which is weird
     **/
//    public static void addOrganizedEvents(SQLiteDatabase db){
//        Cursor ra;
//        ContentValues cv;
//        String KimId= "";
//
//        List<ContentValues> Obtained = new ArrayList<>();
//        List<ContentValues> Attended = new ArrayList<>();
//        List<ContentValues> Orangi = new ArrayList<>();
//
//
//        //Can do query like thi or just do a raw query
//        String event1 = "Select "+ EventEntry._ID +
//                " from "+ EventEntry.TABLE_NAME +
//                " Where " + EventEntry.Event_Title + " = 'LakesPark'";
//
//        String findKim = "Select "+ UserEntry._ID +
//                " from "+ UserEntry.TABLE_NAME +
//                " Where " + UserEntry.Name + " = 'Kim'";
//
//        //what I was last working on to try and get it to work, use the "Babe" keyword
//        //to see in the log cat whats being printed out and the error
//        try{
//            ra = db.rawQuery("SELECT ? FROM Resident WHERE fname = ? ", new String[] {UserEntry._ID,"Kim"});
//
//            if (ra != null) {
//                ra.moveToFirst();
//            }
//
//            KimId = ra.getString(0);
//            Log.d("Babe", "succesful and KimId = " + KimId);
//        } catch (Exception s){
//            Log.d("Babe", "not successful");
//            Log.d("Babe", s.toString());
//        }
//
////        ra = db.rawQuery(event1, null);
////        ra.moveToNext();
////        String EventId = ra.getString(1);
//
//
//        Log.d("Babe", "Think we found kim here? lets check  " + KimId);
//
//        //create Organizing Events
//        cv = new ContentValues();
//        cv.put(OrganizedEvents.RA_ID, KimId);//title
//        cv.put(OrganizedEvents.Event_ID, "Idk");//description
//        cv.put(OrganizedEvents.Date, "date('now')");//date
//
//        Orangi.add(cv);
//
////        //create a list of fake events
////        cv = new ContentValues();
////        cv.put(OrganizedEvents.RA_ID, KimId);//title
////        cv.put(OrganizedEvents.Event_ID, "FGCU Martial Arts");//description
////        cv.put(OrganizedEvents.Date, "date('now')");//date
////
////        Orangi.add(cv);
////
////        //create a list of fake events
////        cv = new ContentValues();
////        cv.put(OrganizedEvents.RA_ID, KimId);//title
////        cv.put(OrganizedEvents.Event_ID, "FGCU Martial Arts");//description
////        cv.put(OrganizedEvents.Date, "date('now')");//date
////
////        Orangi.add(cv);
////
////        try{
////            db.beginTransaction();
////
////            for(ContentValues a : Orangi){
////                db.insert(OrganizedEvents.TABLE_NAME, null, a);
////            }
////
////            db.setTransactionSuccessful();
////        }
////        catch (SQLException e) {
////        //too bad :(
////         }
////        finally
////         {
////        db.endTransaction();
////            }
//    }


}
