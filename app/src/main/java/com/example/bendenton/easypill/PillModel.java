package com.example.bendenton.easypill;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PillModel {
    private String pillName;
    private String timeToTake;
    private int pillID;
    private int userId;
    private int isTaken; // 0 for not, 1 for taken

    //SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public int getPillID() {
        return pillID;
    }

    public String getPillName() {
        return pillName;
    }

    public String getTimeToTake() {
        return timeToTake;
    }

    public int getIsTaken() {
        return isTaken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public void setIsTaken(Integer isTaken) {
        this.isTaken = isTaken;
    }

    public void setTimeToTakeString(String timeToTake) {
        this.timeToTake = timeToTake;
    }

    public PillModel() {

    }

    public PillModel(String timeToTake, String pillName, int isTaken, int userId) {
        this.timeToTake = timeToTake;
        this.pillName = pillName;
        this.isTaken = isTaken;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PillModel{" +
                "pillName='" + pillName + '\'' +
                ", isTaken='" + isTaken + '\'' +
                ", timeToTake='" + timeToTake + '\'' +
                ", userId=" + userId +
                '}';
    }
}
