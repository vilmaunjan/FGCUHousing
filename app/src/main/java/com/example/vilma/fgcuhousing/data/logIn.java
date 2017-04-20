package com.example.vilma.fgcuhousing.data;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.vilma.fgcuhousing.data.HousingContract.AttendedEventEntry;
import com.example.vilma.fgcuhousing.data.HousingContract.UserEntry;

/**
 * Created by Andre on 4/18/2017.
 */

public class logIn {

    private static DbHandler db;
    private static Cursor find;

    public logIn(){

    }

    //Returns the User logging in Basic Information
    public static CurrentUser LogIN(Context x, String e){
        db= new DbHandler(x);
        String Tt = "Tager";
        int id = 0;
        String name ="";
        String email="";
        String password="";
        String account="";
        String building="";

        String query = "Select * from "+ UserEntry.TABLE_NAME +" where " +
                UserEntry.Email +" = \"" + e + "\";";
           find = db.QueryData(query);

                if (find.moveToFirst()) {
                    do {
                        id = find.getInt(0);
                        name = find.getString(1);
                        email = find.getString(2);
                        password = find.getString(3);
                        account = find.getString(4);
                        building = find.getString(5);

                        Log.d(Tt, "The name is :" + name + " The email is : " + email
                                + " the password is : " + password + " building is : " + building);
                    }
                    while (find.moveToNext());
                }
                find.close();
            CurrentUser enter = new CurrentUser(name, email, password, building);
            enter.setID(id);
            enter.setAccountType(account);
            return enter;
    }

    //Still working on This but will populate all the information a person has in the database
    //At login
    public void setEvents(CurrentUser ep){
        //ratingid 0, residentid 1, eventid 2, checkin 3, timestayed 4
        //rating score 5, rating feedback 6
        String   CheckIN, CheckOut, feedback;
        String Tt = "LogIn";
        int RatingID, ResidentID, EventID, ratingScore;
        String query = "Select * from "+ AttendedEventEntry.TABLE_NAME +" where " +
                AttendedEventEntry.Resident_ID +" = \"" + ep.getID() + "\";";
        find = db.QueryData(query);

        if (find.moveToFirst()) {
            do {

                RatingID = find.getInt(0);
                ResidentID = find.getInt(1);
                EventID = find.getInt(2);
                CheckIN = find.getString(3);
                CheckOut = find.getString(4);
                ratingScore = find.getInt(5);
                feedback = find.getString(6);

//                UserEvents pop = new UserEvents(RatingID, ResidentID, EventID,
//                      CheckIN, CheckOut, ratingScore,
//                        feedback);

                Log.d(Tt, "Rating Id is : " + RatingID + " Resident Id is : " + ResidentID +
                        " Event ID is : " + EventID + " Check in time is : " + CheckIN +
                        " Check out time is : " + CheckOut +
                        "The rating score for said event is : +" + ratingScore +
                        " And The Feed Back is "+
                feedback);

            }
            while (find.moveToNext());
        }
        find.close();
    }

}
