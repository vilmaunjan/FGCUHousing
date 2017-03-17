package com.example.vilma.fgcuhousing.data;

import android.provider.BaseColumns;

/**
 * Created by Andrew on 3/10/2017.
 */

public class HousingContract {
    public static final class HousingEntry implements BaseColumns{
        public static final String TABLE_NAME = "housingAccounts";
        public static final String COLUMN_USER_NAME = "name";
        public static final String COLUMN_EMAIL = "emailAddress";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_BUILDING = "building";
    }
}
