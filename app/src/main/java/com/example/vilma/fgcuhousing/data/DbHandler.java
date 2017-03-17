package com.example.vilma.fgcuhousing.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.vilma.fgcuhousing.data.HousingContract.*;


/**
 * Created by Andrew on 3/10/2017.
 * Handles the dataBase
 */

public class DbHandler extends SQLiteOpenHelper {
    //Used for the name ad database version whenever updating schema
    private static final String DATABASE_NAME = "HousingDB";
    private static final int DATABASE_VERSION = 1;

    public DbHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqldb){
        //Create statement for table will rework later just wanted something working for now
        final String SQL_Create_HousingLogin_TABLE = "CREATE TABLE " +
                HousingEntry.TABLE_NAME + "( "+
                HousingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                HousingEntry.COLUMN_USER_NAME + " TEXT NOT NULL, " +
                HousingEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                HousingEntry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                HousingEntry.COLUMN_BUILDING + " TEXT" +
                ")";
        sqldb.execSQL(SQL_Create_HousingLogin_TABLE);
        Log.d("dbHandler", "If it made it this far, database was given a error");
    }

    //i'll get back to fixing the rest of the updates and inserts
    public void onUpgrade(SQLiteDatabase sqldb, int i, int i1){
        sqldb.execSQL("DROP TABLE IF EXISTS " + HousingContract.HousingEntry.TABLE_NAME);
        onCreate(sqldb);
    }

}
