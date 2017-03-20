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
        cv.put(HousingContract.ResidentEntry.COLUMN_USER_NAME, "John Bourne");
        cv.put(HousingContract.ResidentEntry.COLUMN_EMAIL, "John@gmail.com");
        cv.put(HousingContract.ResidentEntry.COLUMN_PASSWORD, "John");
        cv.put(HousingContract.ResidentEntry.COLUMN_BUILDING, "North Village");

        list.add(cv);

        cv = new ContentValues();
        cv.put(HousingContract.ResidentEntry.COLUMN_USER_NAME, "Kim Bourne");
        cv.put(HousingContract.ResidentEntry.COLUMN_EMAIL, "Kim@gmail.com");
        cv.put(HousingContract.ResidentEntry.COLUMN_PASSWORD, "kim");
        cv.put(HousingContract.ResidentEntry.COLUMN_BUILDING, "South Village");

        list.add(cv);

        cv = new ContentValues();
        cv.put(HousingContract.ResidentEntry.COLUMN_USER_NAME, "Joe Cena");
        cv.put(HousingContract.ResidentEntry.COLUMN_EMAIL, "Joe@gmail.com");
        cv.put(HousingContract.ResidentEntry.COLUMN_PASSWORD, "joe");
        cv.put(HousingContract.ResidentEntry.COLUMN_BUILDING, "West Village");

        list.add(cv);

        cv = new ContentValues();
        cv.put(HousingContract.ResidentEntry.COLUMN_USER_NAME, "Bandon Booughe");
        cv.put(HousingContract.ResidentEntry.COLUMN_EMAIL, "Bandon@gmail.com");
        cv.put(HousingContract.ResidentEntry.COLUMN_PASSWORD, "bandon");
        cv.put(HousingContract.ResidentEntry.COLUMN_BUILDING, "East Village");

        list.add(cv);

        cv = new ContentValues();
        cv.put(HousingContract.ResidentEntry.COLUMN_USER_NAME, "Plan Bo");
        cv.put(HousingContract.ResidentEntry.COLUMN_EMAIL, "plan@gmail.com");
        cv.put(HousingContract.ResidentEntry.COLUMN_PASSWORD, "bo");
        cv.put(HousingContract.ResidentEntry.COLUMN_BUILDING, "West Village");

        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (HousingContract.ResidentEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(HousingContract.ResidentEntry.TABLE_NAME, null, c);
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
