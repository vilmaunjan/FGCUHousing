package com.example.vilma.fgcuhousing.data;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.vilma.fgcuhousing.data.HousingContract.*;

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
            enter.setID(id);//Sets there id
            enter.setAccountType(account);//Sets there accountType
            setEvents(enter);//Sets the events they previously attended
            awardChecker trophies = new awardChecker(enter.getEventCounter(),account);//Passes
            //Number of Events and the account type
            setAwards(enter, trophies);
            enter.setMyawards(trophies);//Puts in for current user

            return enter;
    }

    //Sets the Awards they Obtained
    public static void setAwards(CurrentUser ep, awardChecker tp) {
        //If they don't have the beta Tester Title
        if(!db.betaTester(ep)){
            //check if they qualify
            //logic might be flawed for local reasons but thinking in terms of
            //having a sql database on the net later
            if(ep.getID() < 100) {
                tp.setBetaTester(true);
                db.insertAward(ep, db.getAwardID("BetaTester"));
            }
        }
        //Populates all the awards Obtained Such As Complicated
        //Only award that can't be auto populated at the moment
        if(db.complicated(ep)){
            //If they got the complicated Award set true on login
                tp.setComplicated(true);
        }

    }
    //Still working on This but will populate all the information a person has in the database
    //At login
    //Things to populate, EventsAttended
    public static void setEvents(CurrentUser ep){
        //Title 0, feedback 1, checkin 2, check out 3, event 4
        //attended_id 5, ratingscore 6
        //Rating_id is Attended Id
        String   CheckIN, CheckOut, feedback, title;
        String Tt = "LogIn";
        int RatingID, EventID, ratingScore;
        //returns cursor with all the events a user attended
        find = db.getEvents(ep);
        //Add's them to there current user object so
        //We know events they have attended already in the past after logging
        //Out
        ep.setEventCounter(find.getCount());//Sets the number of events user has gone too
        Log.d("LogIn", "the number of Events this user attended is: "  + find.getCount());
        if (find.moveToFirst()) {
            do {

                title = find.getString(0);
                feedback = find.getString(1);
                CheckIN = find.getString(2);
                CheckOut = find.getString(3);
                EventID = find.getInt(4);
                RatingID = find.getInt(5);
                ratingScore = find.getInt(6);

                UserEvents pop = new UserEvents(title, feedback, CheckIN,
                      CheckOut, EventID, RatingID,
                       ratingScore);
                ep.getEvents().put(title, pop);
                ep.getEvents().get(title).setAttended(true);
                ep.getEvents().get(title).setCheckedin(true);

                Log.d(Tt, "Rating Id is : " + RatingID + " Title of event is : " + title +
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
