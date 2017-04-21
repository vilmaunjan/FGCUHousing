package com.example.vilma.fgcuhousing.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;



/**
 * Created by Andrew on 4/17/2017.
 * Parcelable class that can be passed through activities
 */

public class CurrentUser implements Parcelable {


    private int ID;//Gets the users ID in database when added
    private String name;//user name
    private String email;//email
    private String password;//password
    private String building;//building
    private String AccountType;//account Type
    private int EventCounter=0;
    //This hash map holds the title of the event and a class of infromation needed
    private HashMap<String, UserEvents> Events = new HashMap<>();
    //Working on the awards still but this is here for now
    private ArrayList<String> awarded = new ArrayList<>();

    //Anything passed here is getter and setters
    //as well as the parcelable default required methods
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getEventCounter() {
        return EventCounter;
    }

    public void setEventCounter(int eventCounter) {
        EventCounter = eventCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public HashMap<String, UserEvents> getEvents() {
        return Events;
    }

    public void setEvents(HashMap<String, UserEvents> events) {
        Events = events;
    }

    public ArrayList<String> getAwarded() {
        return awarded;
    }

    public void setAwarded(ArrayList<String> awarded) {
        this.awarded = awarded;
    }

    public CurrentUser(){
        super();
    }

   public CurrentUser(String name, String Email, String pass, String Building){
        this.name = name;
        this.email = Email;
        this.password = pass;
        this.building = Building;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ID);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.building);
        dest.writeString(this.AccountType);
        dest.writeInt(this.EventCounter);
        dest.writeMap(this.Events);
        dest.writeStringList(this.awarded);
    }

    protected CurrentUser(Parcel in) {
        this.ID = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.building = in.readString();
        this.AccountType = in.readString();
        this.EventCounter = in.readInt();
        in.readMap(Events, UserEvents.class.getClassLoader());
        this.awarded = in.createStringArrayList();
    }

    public static final Creator<CurrentUser> CREATOR = new Creator<CurrentUser>() {
        @Override
        public CurrentUser createFromParcel(Parcel source) {
            return new CurrentUser(source);
        }

        @Override
        public CurrentUser[] newArray(int size) {
            return new CurrentUser[size];
        }
    };
}
