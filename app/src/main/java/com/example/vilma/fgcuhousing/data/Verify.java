package com.example.vilma.fgcuhousing.data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.vilma.fgcuhousing.EventList;

/**
 * Created by Andrew on 4/17/2017.
 */

public class Verify {
    private Context bleh;
    private String name, email, password, passwordVerify;
    private String ETag = "Verification";

    public Verify(Context bleh, String name, String email, String password, String passwordVerify){
        this.bleh = bleh;
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public boolean Verify() {

        if(nameVeri() && emailVeri() && passVeri()){
            return true;
        }else{
            return false;
        }

    }

    boolean nameVeri(){
       if(name.matches("[a-zA-Z\\s]+ ")){
           Log.d(ETag, "Did my name pass");
           return true;
       }else{
           Toast.makeText(bleh, "Invalid Name",
                   Toast.LENGTH_SHORT).show();
        return false;
        }
    }

    boolean emailVeri(){
        if(email.endsWith("@eagle.fgcu.edu")){
            Log.d(ETag, "Did my email pass");
            return true;
        }
        Toast.makeText(bleh, "Please enter an FGCU email",
                Toast.LENGTH_SHORT).show();
        return false;
    }

    boolean passVeri(){
        if(password.equals(passwordVerify)){
            Log.d(ETag, "Did my password pass");
            return true;
        }
        Toast.makeText(bleh, "passwords dont match",
                Toast.LENGTH_SHORT).show();
        return false;
    }
}
