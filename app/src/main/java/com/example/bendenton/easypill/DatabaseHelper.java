package com.example.bendenton.easypill;

import android.content.Context;
import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tabegaz on 3/23/18. Changed by bendenton and noahphillips on 04/16/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "EasyPillDB";
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Table Names
    private static final String TABLE_PILL = "Pill";
    private static final String TABLE_USER = "user";
    //  create  Tables for poll, voter, and user
    private static final String CREATE_TABLE_PILL = "CREATE TABLE "  + TABLE_PILL+ " (PILLID INTEGER PRIMARY KEY AUTOINCREMENT, PILLNAME TEXT, USERID INTEGER, TIMETOTAKE DATE, ISTAKEN INTEGER)";
    private static final String CREATE_TABLE_USER = "CREATE TABLE "  + TABLE_USER+ " (USERID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // creating required tables
        sqLiteDatabase.execSQL(CREATE_TABLE_PILL);
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PILL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // create new tables
        onCreate(sqLiteDatabase);
    }

    // ------------------------ "POLL" table methods ----------------//

    public Boolean insertPill(PillModel pill) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("PILLNAME", pill.getPillName());
        values.put("PILLID", pill.getPillID());
        values.put("USERID", pill.getUserId());
        values.put("TIMETOTAKE", pill.getTimeToTake());
        values.put("ISTAKEN",pill.getIsTaken());


        // insert row
        long resultSet = db.insert(TABLE_PILL, null, values);
        if(resultSet== -1)
            return false;
        else
            return true;

    }

    // get single user record from pill table
    public PillModel getPill(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PILL + " WHERE  CANDIDATEID =" + userId;

        Cursor resultSet = db.rawQuery(selectQuery, null);

        if (resultSet != null)
            resultSet.moveToFirst();


        PillModel pill = new PillModel();
        pill.setPillName((resultSet.getString(resultSet.getColumnIndex("PILLNAME"))));
        pill.setTimeToTakeString((resultSet.getString(resultSet.getColumnIndex("TIMETOTAKE"))));
        pill.setIsTaken((resultSet.getInt(resultSet.getColumnIndex("ISTAKEN"))));
        return pill;
    }

    // GET PILL LIST
    public List<PillModel> getAllPills() {
        List<PillModel> pillList = new ArrayList<PillModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_PILL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (resultSet.moveToFirst()) {
            do {
                PillModel pill = new PillModel();
                pill.setPillName((resultSet.getString(resultSet.getColumnIndex("PILLNAME"))));
                pill.setTimeToTakeString((resultSet.getString(resultSet.getColumnIndex("TIMETOTAKE"))));
                pill.setIsTaken((resultSet.getInt(resultSet.getColumnIndex("ISTAKEN"))));

                // adding to poll list
                pillList.add(pill);
            } while (resultSet.moveToNext());
        }

        return pillList;
    }

    public int updatePoll(PillModel pill) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("PILLNAME", pill.getPillName());
        values.put("PILLID", pill.getPillID());
        values.put("USERID", pill.getUserId());
        values.put("TIMETOTAKE", pill.getTimeToTake());
        values.put("ISTAKEN",pill.getIsTaken());
        // updating row
        return db.update(TABLE_PILL, values, "USERID" + " = ?",
                new String[] { String.valueOf(pill.getUserId()) });
    }

    /**
     * Deleting a poll
     */
    public void deletePill(long userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PILL, "USERID" + " = ?",
                new String[] { String.valueOf(userId) });
    }


    // ------------------------ "VOTER" table methods ----------------//

    public Boolean insertUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("USERNAME", user.getUsername());
        values.put("USERID", user.getUserId());
        values.put("PASSWORD", user.getPassword());
        values.put("EMAIL", user.getEmail());]


        // insert row
        long resultSet = db.insert(TABLE_USER, null, values);
        if(resultSet== -1)
            return false;
        else
            return true;

    }

    // get single candidate record from poll table
    public UserModel getVoter(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE  VOTERID =" + userId;

        Cursor resultSet = db.rawQuery(selectQuery, null);

        if (resultSet != null)
            resultSet.moveToFirst();

        UserModel user = new UserModel();
        user.setUsername((resultSet.getString(resultSet.getColumnIndex("USERNAME"))));
        user.setUserId((resultSet.getInt(resultSet.getColumnIndex("USERID"))));
        user.setPassword((resultSet.getString(resultSet.getColumnIndex("PASSWORD"))));
        user.setEmail((resultSet.getString(resultSet.getColumnIndex("EMAIL"))));
        return voter;
    }

    // GET Voter LIST
    public List<UserModel> getAllUsers() {
        List<UserModel> userList = new ArrayList<UserModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (resultSet.moveToFirst()) {
            do {
                UserModel user = new UserModel();
                user.setUsername((resultSet.getString(resultSet.getColumnIndex("USERNAME"))));
                user.setUserId((resultSet.getInt(resultSet.getColumnIndex("USERID"))));
                user.setPassword((resultSet.getString(resultSet.getColumnIndex("PASSWORD"))));
                user.setEmail((resultSet.getString(resultSet.getColumnIndex("EMAIL"))));

                // adding to voter list
                userList.add(user);
            } while (resultSet.moveToNext());
        }

        return userList;
    }

    public String getSecurePassword(String passwordToHash, String messageSalt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(messageSalt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    /*public boolean insertUser(UserModel user)
    {
        String password;
        password= getSecurePassword(user.getPassword(),    "CSCI3660, Mobile App Development");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("USERNAME", user.getUsername());
        values.put("EMAIL", user.getEmail());
        values.put("PASSWORD", password);
        long result  = db.insert(TABLE_USER, null, values );
        if(result == -1)
            return false;
        else
            return true;

    }*/

    public Boolean getLoginInfo(UserModel user){
        SQLiteDatabase db = this.getReadableDatabase();
        String password;
        password= getSecurePassword(user.getPassword(),    "CSCI3660, Mobile App Development");
        String query = "Select USERNAME, PASSWORD FROM " + TABLE_USER + " WHERE USERNAME = '"+user.getUsername() +"' AND PASSWORD= '"+password+"'";
        Cursor resultSet = db.rawQuery(query, null);
        if(resultSet.getCount()== 0)
            return false;
        else
            return true;
        //resultSet.close();


    }


}