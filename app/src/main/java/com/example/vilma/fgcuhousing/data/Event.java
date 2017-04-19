package com.example.vilma.fgcuhousing.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.vilma.fgcuhousing.R;


/**
 * Created by Vilma on 4/6/2017.
 */

public class Event {

    private String title, description, location, building, time, date, image;
    private int id;
    private boolean validInput;

    //Getters and setters
    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        if(image == null){
            setImage(String.valueOf((R.drawable.defaulposter)));
        }
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isValidInput() {
        return validInput;
    }

    public void setValidInput(boolean validInput) {
        this.validInput = validInput;
    }

}
