package com.example.vilma.fgcuhousing.data;

/**
 * Created by calebheinzman on 3/30/17.
 * This class is used to store the user information after registration to be inserted in the database.
 */

public class User {
    private String name;
    private String email;
    private String password;
    private String building;
    private String Type;
    private boolean validInput;

    public String getType() {
            if(email.endsWith("@eagle.fgcu.edu")){
                Type = "R";
            }else if(email.endsWith("@fgcu.edu")){
                Type = "RA";
            }
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public boolean isValidInput() {
        return validInput;
    }

    public void setValidInput(boolean validInput) {
        this.validInput = validInput;
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
}
