package com.example.vilma.fgcuhousing.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vilma.fgcuhousing.data.HousingContract.UserEntry;

/**
 * Created by Andre on 4/18/2017.
 */

public class logIn {

    private static DbHandler db;
    private static SQLiteDatabase s;
    private static Cursor find;

    public logIn(){

    }


    public static CurrentUser LogIN(String e){
        String Tt = "Tager";
        String name ="";
        String email="";
        String password="";
        String building="";
        String query = "Select * from "+ UserEntry.TABLE_NAME +" where " + UserEntry.Email +" = " + e;
       find = db.QueryData(query);
        if(find.moveToFirst()){
            do{
                name = find.getString(0);
                email = find.getString(1);
                password = find.getString(2);
                building = find.getString(3);

                Log.d(Tt, "The name is :" + name + " The email is : " + email
                + " the password is : " + password + " building is : " + building);
            }
            while(find.moveToNext());
        }


        CurrentUser enter = new CurrentUser(name, email, password, building);
        return enter;
    }

}
