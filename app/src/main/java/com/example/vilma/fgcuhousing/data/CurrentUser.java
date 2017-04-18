package com.example.vilma.fgcuhousing.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Andrew on 4/17/2017.
 */

public class CurrentUser implements Parcelable {

    private String Name;
    private String Email;
    private String Password;
    private String Building;
    private ArrayList<String> awarded = new ArrayList<>();


    public CurrentUser(){
        super();
    }

    CurrentUser(String name, String Email, String pass, String Building){
        this.Name = name;
        this.Email = Email;
        this.Password = pass;
        this.Building = Building;
    }

    public CurrentUser(Parcel par){
        this.Name = par.readString();
        this.Email = par.readString();
        this.Password = par.readString();
        this.Building = par.readString();
        this.awarded = par.readArrayList(null);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeString(this.Email);
        dest.writeString(this.Password);
        dest.writeString(this.Building);
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
