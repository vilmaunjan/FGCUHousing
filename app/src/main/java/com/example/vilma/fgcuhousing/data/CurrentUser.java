package com.example.vilma.fgcuhousing.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

import static android.R.attr.rating;

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
    private HashMap<String, Boolean> Events;
    private ArrayList<String> awarded = new ArrayList<>();


    public CurrentUser(){
        super();
    }

   public CurrentUser(String name, String Email, String pass, String Building){
        this.name = name;
        this.email = Email;
        this.password = pass;
        this.building = Building;
    }

    public CurrentUser(Parcel par){
        this.name = par.readString();
        this.email = par.readString();
        this.password = par.readString();
        this.building = par.readString();
        this.awarded = par.readArrayList(null);

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
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.building);
        dest.writeList(this.awarded);
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
