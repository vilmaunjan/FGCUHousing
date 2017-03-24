package com.example.vilma.fgcuhousing.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
        List<ContentValues> Obtained = new ArrayList<>();
        List<ContentValues> Attended = new ArrayList<>();
        List<ContentValues> Orangi = new ArrayList<>();


        //create a list of fake guests
        ContentValues cv = new ContentValues();
        cv.put(UserEntry.Fname, "John");
        cv.put(UserEntry.Lname, "Bourne");
        cv.put(UserEntry.UIN, "894865412");
        cv.put(UserEntry.Email, "JohnBou@eagle.fgcu.edu");
        cv.put(UserEntry.Password, "north");
        cv.put(UserEntry.Type, "R");
        cv.put(UserEntry.Building, "North Village");

        User.add(cv);

        cv = new ContentValues();
        cv.put(UserEntry.Fname, "Kim");
        cv.put(UserEntry.Lname, "Bourne");
        cv.put(UserEntry.UIN, "894865214");
        cv.put(UserEntry.Email, "KimBou@eagle.fgcu.edu");
        cv.put(UserEntry.Password, "north");
        cv.put(UserEntry.Type, "RA");
        cv.put(UserEntry.Building, "South Village");

        User.add(cv);

        cv = new ContentValues();
        cv.put(UserEntry.Fname, "Johnny");
        cv.put(UserEntry.Lname, "BDepp");
        cv.put(UserEntry.UIN, "852456915");
        cv.put(UserEntry.Email, "JohnDep@fgcu.edu");
        cv.put(UserEntry.Password, "north");
        cv.put(UserEntry.Type, "RA");
        cv.put(UserEntry.Building, "South Village");

        User.add(cv);

        cv = new ContentValues();
        cv.put(UserEntry.Fname, "Bonnie");
        cv.put(UserEntry.Lname, "Pogan");
        cv.put(UserEntry.UIN, "894835712");
        cv.put(UserEntry.Email, "Bonnie@eagle.fgcu.edu");
        cv.put(UserEntry.Password, "north");
        cv.put(UserEntry.Type, "R");
        cv.put(UserEntry.Building, "North Village");

        User.add(cv);

        cv = new ContentValues();
        cv.put(UserEntry.Fname, "Belly");
        cv.put(UserEntry.Lname, "Cooper");
        cv.put(UserEntry.UIN, "357159852");
        cv.put(UserEntry.Email, "cooper@fgcu.edu");
        cv.put(UserEntry.Password, "north");
        cv.put(UserEntry.Type, "RD");
        cv.put(UserEntry.Building, "North Village");

        User.add(cv);

        //create a list of fake events
        cv = new ContentValues();
        cv.put(EventEntry.Event_Title, "LakesPark");
        cv.put(EventEntry.Event_Title, "LakesPark");
        cv.put(EventEntry.Event_Title, "LakesPark");
        cv.put(EventEntry.Event_Title, "LakesPark");
        cv.put(EventEntry.Event_Title, "LakesPark");

        Event.add(cv);


        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (UserEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:User){
                db.insert(UserEntry.TABLE_NAME, null, c);
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

    }
}
