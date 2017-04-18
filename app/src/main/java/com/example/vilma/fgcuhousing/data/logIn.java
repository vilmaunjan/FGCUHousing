package com.example.vilma.fgcuhousing.data;

/**
 * Created by Andre on 4/18/2017.
 */

public class logIn {

    private String Email;
    private String password;

    logIn(String e, String p){
        this.Email = e;
        this.password= p;

        find();
    }

    private CurrentUser find() {
        /**
         * random code find user in database
         */
        CurrentUser enter = new CurrentUser("Andrew", "a@eagle.fgcu.edu", "north", "O");
        return enter;
    }

}
