package com.example.vilma.fgcuhousing.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(HousingContract.UserEntry.Fname, "John");
        cv.put(HousingContract.UserEntry.Lname, "Bourne");
        cv.put(HousingContract.UserEntry.UIN, "894865412");
        cv.put(HousingContract.UserEntry.Email, "JohnBou@eagle.fgcu.edu");
        cv.put(HousingContract.UserEntry.Password, "north");
        cv.put(HousingContract.UserEntry.Type, "R");
        cv.put(HousingContract.UserEntry.Building, "North Village");

        list.add(cv);

        cv = new ContentValues();
        cv.put(HousingContract.UserEntry.Fname, "Kim");
        cv.put(HousingContract.UserEntry.Lname, "Bourne");
        cv.put(HousingContract.UserEntry.UIN, "894865214");
        cv.put(HousingContract.UserEntry.Email, "KimBou@eagle.fgcu.edu");
        cv.put(HousingContract.UserEntry.Password, "north");
        cv.put(HousingContract.UserEntry.Type, "RA");
        cv.put(HousingContract.UserEntry.Building, "South Village");

        list.add(cv);

        cv = new ContentValues();
        cv.put(HousingContract.UserEntry.Fname, "Johnny");
        cv.put(HousingContract.UserEntry.Lname, "BDepp");
        cv.put(HousingContract.UserEntry.UIN, "852456915");
        cv.put(HousingContract.UserEntry.Email, "JohnDep@fgcu.edu");
        cv.put(HousingContract.UserEntry.Password, "north");
        cv.put(HousingContract.UserEntry.Type, "RA");
        cv.put(HousingContract.UserEntry.Building, "South Village");

        list.add(cv);

        cv = new ContentValues();
        cv.put(HousingContract.UserEntry.Fname, "Bonnie");
        cv.put(HousingContract.UserEntry.Lname, "Pogan");
        cv.put(HousingContract.UserEntry.UIN, "894835712");
        cv.put(HousingContract.UserEntry.Email, "Bonnie@eagle.fgcu.edu");
        cv.put(HousingContract.UserEntry.Password, "north");
        cv.put(HousingContract.UserEntry.Type, "R");
        cv.put(HousingContract.UserEntry.Building, "North Village");

        list.add(cv);

        cv = new ContentValues();
        cv.put(HousingContract.UserEntry.Fname, "Belly");
        cv.put(HousingContract.UserEntry.Lname, "Cooper");
        cv.put(HousingContract.UserEntry.UIN, "357159852");
        cv.put(HousingContract.UserEntry.Email, "cooper@fgcu.edu");
        cv.put(HousingContract.UserEntry.Password, "north");
        cv.put(HousingContract.UserEntry.Type, "RD");
        cv.put(HousingContract.UserEntry.Building, "North Village");

        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (HousingContract.UserEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(HousingContract.UserEntry.TABLE_NAME, null, c);
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
