package com.example.vilma.fgcuhousing.data;

import android.provider.BaseColumns;

/**
 * Created by Andrew on 3/10/2017.
 */

public class HousingContract {
    public static final class ResidentEntry implements BaseColumns{
        public static final String TABLE_NAME = "Resident";
        public static final String COLUMN_R_ID = "R_ID";
        public static final String COLUMN_USER_NAME = "name";
        public static final String COLUMN_EMAIL = "emailAddress";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_BUILDING = "building";

    }

    public static final class ResidentAssistantEntry implements BaseColumns{
        public static final String TABLE_NAME = "ResidentAssistant";
        public static final String COLUMN_RA_ID = "RA_ID";
        public static final String COLUMN_USER_NAME = "Name";
        public static final String COLUMN_EMAIL = "Email";
        public static final String COLUMN_UIN = "UIN";
        public static final String COLUMN_PASSWORD = "Password";
        public static final String COLUMN_BUILDING = "Building";

    }

    public static final class ResidentDirectorEntry implements BaseColumns{
        public static final String TABLE_NAME = "ResidientDirector";
        public static final String COLUMN_RD_ID = "RD_ID";
        public static final String COLUMN_USER_NAME = "name";
        public static final String COLUMN_EMAIL = "emailAddress";
        public static final String COLUMN_PASSWORD = "password";
    }

    public static final class EventEntry implements BaseColumns{
        public static final String TABLE_NAME = "Event";
        public static final String COLUMN_Event_ID = "Event_ID";
        public static final String COLUMN_Event_Title = "Title";
        public static final String COLUMN_Description = "Description";
        public static final String COLUMN_DateTime = "DateTime";
        public static final String COLUMN_Attending = "Attending";
        public static final String COLUMN_BUILDING = "Building";
        public static final String COLUMN_IMAGE = "Image";
    }

    public static final class AttendedEventEntry implements BaseColumns{
        public static final String TABLE_NAME = "AttendedEvents";
        public static final String COLUMN_Rating_ID = "Rating_ID";
        public static final String COLUMN_Resident_ID = "Resident_ID";
        public static final String COLUMN_Event_ID = "Event_ID";
        public static final String COLUMN_Time_Stayed = "TimeStayed";
        public static final String COLUMN_Rating_Score = "RatingScore";
        public static final String COLUMN_Ratomg_FeedBack = "RatingFeedBack";
    }

    public static final class Awards implements BaseColumns{
        public static final String TABLE_NAME = "Awards";
        public static final String COLUMN_Award_ID = "Award_ID";
        public static final String COLUMN_Attended_Num = "Attended";
        public static final String COLUMN_Image = "Image";
    }

    public static final class AwardObtained implements BaseColumns{
        public static final String TABLE_NAME = "AwardsObtained";
        public static final String COLUMN_AwardObtained_ID = "AwardObtainedID";
        public static final String COLUMN_AwardID = "AwardID";
        public static final String COLUMN_Resident_ID = "ResidentID";
    }

    public static final class OrganizedEvents implements BaseColumns{
        public static final String TABLE_NAME = "OrganizedEvents";
        public static final String COLUMN_OrganizedID = "OrganizedEventID";
        public static final String COLUMN_RA_ID = "RA_ID";
        public static final String COLUMN_Event_ID = "EventID";
        public static final String COLUMN_Date = "Date";
    }


}
