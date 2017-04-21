package com.example.vilma.fgcuhousing.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Andrew on 4/19/2017.
 */

public class UserEvents implements Parcelable {

    private boolean createdEvent=false;//used if they created the event
    private boolean Attended = false;//if there attending the event
    private boolean checkedin = false;//if they hit check in
    //Event title, feedback from user, time they checked in and out
    private String title, feedBack, CheckIn, CheckOut;
    //Id generated from when they attended the event
    private int EventID, AttendedId;//Contain the event id and attended id
    private float rating;//what rating they put for the event

    public UserEvents() {
    }

    //Constructor to easily instantiate the class
    public UserEvents(String title, String feedBack, String checkIn,
                      String checkOut, int eventID, int attendedId, float rating) {
        this.title = title;
        this.feedBack = feedBack;
        this.CheckIn = checkIn;
        this.CheckOut = checkOut;
        this.EventID = eventID;
        this.AttendedId = attendedId;
        this.rating = rating;
    }

    //everything below this is just getter and setters, as well as the defaults required for parcelabel
    public boolean isAttended() {
        return Attended;
    }

    public boolean isCheckedin() {
        return checkedin;
    }

    public void setCheckedin(boolean checkedin) {
        this.checkedin = checkedin;
    }

    public void setAttended(boolean attended) {
        Attended = attended;
    }

    public boolean isCreatedEvent() {
        return createdEvent;
    }

    public void setCreatedEvent(boolean createdEvent) {
        this.createdEvent = createdEvent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeedBack() {
        if(feedBack == null){
            setFeedBack("Awesome");
        }
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public String getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(String checkIn) {
        CheckIn = checkIn;
    }

    public String getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(String checkOut) {
        CheckOut = checkOut;
    }

    public int getEventID() {
        return EventID;
    }

    public void setEventID(int eventID) {
        EventID = eventID;
    }

    public int getAttendedId() {
        return AttendedId;
    }

    public void setAttendedId(int attendedId) {
        AttendedId = attendedId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.createdEvent ? (byte) 1 : (byte) 0);
        dest.writeByte(this.Attended ? (byte) 1 : (byte) 0);
        dest.writeByte(this.checkedin ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeString(this.feedBack);
        dest.writeString(this.CheckIn);
        dest.writeString(this.CheckOut);
        dest.writeInt(this.EventID);
        dest.writeInt(this.AttendedId);
        dest.writeFloat(this.rating);
    }

    protected UserEvents(Parcel in) {
        this.createdEvent = in.readByte() != 0;
        this.Attended = in.readByte() != 0;
        this.checkedin = in.readByte() != 0;
        this.title = in.readString();
        this.feedBack = in.readString();
        this.CheckIn = in.readString();
        this.CheckOut = in.readString();
        this.EventID = in.readInt();
        this.AttendedId = in.readInt();
        this.rating = in.readFloat();
    }

    public static final Creator<UserEvents> CREATOR = new Creator<UserEvents>() {
        @Override
        public UserEvents createFromParcel(Parcel source) {
            return new UserEvents(source);
        }

        @Override
        public UserEvents[] newArray(int size) {
            return new UserEvents[size];
        }
    };
}
