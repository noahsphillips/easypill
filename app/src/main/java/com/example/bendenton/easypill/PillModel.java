package com.example.bendenton.easypill;

import android.content.Context;
import android.util.Log;

import java.util.Date;

public class PillModel {
    private String pillName;
    private Date timeToTake;
    private int userId;
    private boolean isTaken;

    public String getPillName() {
        return pillName;
    }

    public Date getTimeToTake() {
        return timeToTake;
    }

    public boolean getIsTaken() {
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

    public void setIsTaken(Boolean isTaken) {
        this.isTaken = isTaken;
    }

    public void setTimeToTake(Date timeToTake) {
        this.timeToTake = timeToTake;
    }

    public PillModel() {

    }

    public PillModel(Date timeToTake, String pillName, boolean isTaken, int userId) {
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
