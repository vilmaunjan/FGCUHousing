package com.example.vilma.fgcuhousing.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;



/**
 * Created by Andrew on 4/17/2017.
 */

public class CurrentUser implements Parcelable {

    private String ID;
    private String name;
    private String email;
    private String password;
    private String building;
    private String AccountType;
    private HashMap<String, Integer> Events;
    private ArrayList<String> awarded = new ArrayList<>();




    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }


    public HashMap<String, Integer> getEvents() {
        return Events;
    }

    public void setEvents(HashMap<String, Integer> events) {
        Events = events;
    }

    public CurrentUser(){
        super();
    }

   public CurrentUser(String name, String Email, String pass, String Building){
        this.name = name;
        this.email = Email;
        this.password = pass;
        this.building = Building;
        Account();
    }

    public String getID() {
        return ID;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String ID) {

        this.ID = ID;
    }



    /**
     * Gets the rating score for the current event if rated already
     * @param RatingId
     * @return
     */
    public int getRating(int RatingId){

        return 0;
    }

    /**
     * sets the rating in the database
     * @param Rating
     */

    public void setRating(int Rating){

    }

    private void Account(){
        if(email.endsWith("@eagle.fgcu.edu")){
            AccountType = "R";
        }else if(email.endsWith("@fgcu.edu")){
            AccountType = "RA";
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.building);
        dest.writeString(this.AccountType);
        dest.writeSerializable(this.Events);
        dest.writeStringList(this.awarded);
    }

    protected CurrentUser(Parcel in) {
        this.ID = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.building = in.readString();
        this.AccountType = in.readString();
        this.Events = (HashMap<String, Integer>) in.readSerializable();
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
