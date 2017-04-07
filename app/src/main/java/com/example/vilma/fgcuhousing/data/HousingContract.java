package com.example.vilma.fgcuhousing.data;

import android.provider.BaseColumns;

/**
 * Created by Andrew on 3/10/2017.
 */

public class HousingContract {
    public static final class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "Resident";
        public static final String Fname = "fname";
        public static final String Lname = "lname";
        public static final String Email = "emailAddress";
        public static final String Password = "password";
        public static final String Building = "building";
        public static final String UIN = "UIN";
        public static final String Type = "R_RA_RD";

    }

    public static final class EventEntry implements BaseColumns{
        public static final String TABLE_NAME = "Event";
        public static final String Event_ID = "Event_ID";
        public static final String Event_Title = "Title";
        public static final String Description = "Description";
        public static final String Location = "Location";
        public static final String Event_Date = "Created";
        public static final String Event_Time = "Time";
        public static final String BUILDING = "Building";
        public static final String IMAGE = "Image";
    }

    public static final class Awards implements BaseColumns{
        public static final String TABLE_NAME = "Awards";
        public static final String COLUMN_Award_ID = "Award_ID";
        public static final String COLUMN_Award_Name = "AwardName";
        public static final String COLUMN_Award_Description = "AwardDescription";
        public static final String COLUMN_Image = "Image";
    }

    public static final class OrganizedEvents implements BaseColumns{
        public static final String TABLE_NAME = "OrganizedEvents";
        public static final String OrganizedID = "OrganizedEventID";
        public static final String RA_ID = "RA_ID";
        public static final String Event_ID = "EventID";
        public static final String Date = "Date";
    }

    public static final class AttendedEventEntry implements BaseColumns{
        public static final String TABLE_NAME = "AttendedEvents";
        public static final String Rating_ID = "Rating_ID";
        public static final String Resident_ID = "Resident_ID";
        public static final String Event_ID = "Event_ID";
        public static final String Time_Stayed = "TimeStayed";
        public static final String Rating_Score = "RatingScore";
        public static final String Rating_FeedBack = "RatingFeedBack";
    }

    public static final class AwardObtained implements BaseColumns{
        public static final String TABLE_NAME = "AwardsObtained";
        public static final String AwardObtained_ID = "AwardObtainedID";
        public static final String AwardID = "AwardID";
        public static final String Resident_ID = "ResidentID";
    }




}
