package com.example.vilma.fgcuhousing.data;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

/**
 * Created by Andrew on 4/21/2017.
 */

public class awardChecker implements Parcelable {
    /*
     * For Testing About 9 logos so 9 awards
     * First Time checking In
     * Third Time checking in
     * LuckySeven time Checking in
     * OutGoing Time Checking In
     * MakeLifeSimple Created an Account
     * TheHallPatrol Joined as an RA account
     * KingPin Joined as a RD Account
     * Complicated Checked in into 2 Events without checking out
     * BetaTester Joined one of the First 100 to join
     *
     * Events Checked with Event Counter in CurrentUserClass
     *  which will also be populated with a database query when
     *  user logs in to keep it up to date
     *
     *  First Time register's whether RA or RD or R
     *      will be awarded on successful registration
     */
    private boolean FirstTime=false;
    private boolean ThirdTimer=false;
    private boolean LuckySeven=false;
    private boolean OutGoing=false;
    private boolean MakeLifeSimple=false;
    private boolean TheHallPatrol=false;
    private boolean KingPin=false;
    private boolean Complicated=false;
    private boolean BetaTester=false;

    public awardChecker() {

    }



    public awardChecker(int Number, String ActType){

        //Sets event awards
        if(Number > 0){
            setFirstTime(true);
        }else if(Number > 2){
            setThirdTimer(true);
        } else if(Number > 6){
            setLuckySeven(true);
        }else if(Number > 10){
            setOutGoing(true);
        }


        //sets Award for account creation
        if(ActType.equals("R")){
            setMakeLifeSimple(true);
        }else if(ActType.equals("RA")){
            setTheHallPatrol(true);
        }else if(ActType.equals("RD")){
            setKingPin(true);
        }

        //BetaTester assigned in Different Method
        //Same for Complicated

    }

    public void checkEvent(int Number, Context c){
        //Sets event awards
        if(Number == 1){
            setFirstTime(true);
            Toast.makeText(c, "You Obtained First Time award!", Toast.LENGTH_SHORT).show();
        }else if(Number == 3){
            Toast.makeText(c, "You Obtained Third Time award!", Toast.LENGTH_SHORT).show();
            setThirdTimer(true);
        } else if(Number == 7){
            Toast.makeText(c, "You Obtained Lucky Seven award!", Toast.LENGTH_SHORT).show();
            setLuckySeven(true);
        }else if(Number == 10){
            Toast.makeText(c, "You Obtained OutGoing award!", Toast.LENGTH_SHORT).show();
            setOutGoing(true);
        }
    }


    public boolean isFirstTime() {
        return FirstTime;
    }

    public void setFirstTime(boolean firstTime) {
        FirstTime = firstTime;
    }

    public boolean isThirdTimer() {
        return ThirdTimer;
    }

    public void setThirdTimer(boolean thirdTimer) {
        ThirdTimer = thirdTimer;
    }

    public boolean isLuckySeven() {
        return LuckySeven;
    }

    public void setLuckySeven(boolean luckySeven) {
        LuckySeven = luckySeven;
    }

    public boolean isOutGoing() {
        return OutGoing;
    }

    public void setOutGoing(boolean outGoing) {
        OutGoing = outGoing;
    }

    public boolean isMakeLifeSimple() {
        return MakeLifeSimple;
    }

    public void setMakeLifeSimple(boolean makeLifeSimple) {
        MakeLifeSimple = makeLifeSimple;
    }

    public boolean isTheHallPatrol() {
        return TheHallPatrol;
    }

    public void setTheHallPatrol(boolean theHallPatrol) {
        TheHallPatrol = theHallPatrol;
    }

    public boolean isKingPin() {
        return KingPin;
    }

    public void setKingPin(boolean kingPin) {
        KingPin = kingPin;
    }

    public boolean isComplicated() {
        return Complicated;
    }

    public void setComplicated(boolean complicated) {
        Complicated = complicated;
    }

    public boolean isBetaTester() {
        return BetaTester;
    }

    public void setBetaTester(boolean betaTester) {
        BetaTester = betaTester;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.FirstTime ? (byte) 1 : (byte) 0);
        dest.writeByte(this.ThirdTimer ? (byte) 1 : (byte) 0);
        dest.writeByte(this.LuckySeven ? (byte) 1 : (byte) 0);
        dest.writeByte(this.OutGoing ? (byte) 1 : (byte) 0);
        dest.writeByte(this.MakeLifeSimple ? (byte) 1 : (byte) 0);
        dest.writeByte(this.TheHallPatrol ? (byte) 1 : (byte) 0);
        dest.writeByte(this.KingPin ? (byte) 1 : (byte) 0);
        dest.writeByte(this.Complicated ? (byte) 1 : (byte) 0);
        dest.writeByte(this.BetaTester ? (byte) 1 : (byte) 0);
    }

    protected awardChecker(Parcel in) {
        this.FirstTime = in.readByte() != 0;
        this.ThirdTimer = in.readByte() != 0;
        this.LuckySeven = in.readByte() != 0;
        this.OutGoing = in.readByte() != 0;
        this.MakeLifeSimple = in.readByte() != 0;
        this.TheHallPatrol = in.readByte() != 0;
        this.KingPin = in.readByte() != 0;
        this.Complicated = in.readByte() != 0;
        this.BetaTester = in.readByte() != 0;
    }

    public static final Creator<awardChecker> CREATOR = new Creator<awardChecker>() {
        @Override
        public awardChecker createFromParcel(Parcel source) {
            return new awardChecker(source);
        }

        @Override
        public awardChecker[] newArray(int size) {
            return new awardChecker[size];
        }
    };
}
