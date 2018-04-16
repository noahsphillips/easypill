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
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tabegaz on 3/23/18.
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
    private static final String CREATE_TABLE_PILL = "CREATE TABLE "  + TABLE_PILL+ " (PILLID INTEGER PRIMARY KEY AUTOINCREMENT, PILLNAME TEXT, USERID INTEGER, TIMETOTAKE DATE, ISTAKEN BOOLEAN)";
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
        values.put("TIMETOTAKE", dateFormat.format(pill.getTimeToTake()));
        values.put("ISTAKEN",pill.getIsTaken());


        // insert row
        long resultSet = db.insert(TABLE_PILL, null, values);
        if(resultSet== -1)
            return false;
        else
            return true;

    }

    // get single candidate record from poll table
    public PollModel getPoll(long candidateId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_POLL + " WHERE  CANDIDATEID =" + candidateId;

        Cursor resultSet = db.rawQuery(selectQuery, null);

        if (resultSet != null)
            resultSet.moveToFirst();


        PollModel poll = new PollModel();
        poll.setFirstName((resultSet.getString(resultSet.getColumnIndex("FIRSTNAME"))));
        poll.setLastName((resultSet.getString(resultSet.getColumnIndex("LASTNAME"))));
        poll.setParty((resultSet.getString(resultSet.getColumnIndex("PARTY"))));
        poll.setState((resultSet.getString(resultSet.getColumnIndex("STATE"))));
        poll.setElectionYear((resultSet.getInt(resultSet.getColumnIndex("ELECTIONYEAR"))));
        return poll;
    }

    // GET POLL LIST
    public List<PollModel> getAllCadidates() {
        List<PollModel> pollList = new ArrayList<PollModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_POLL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (resultSet.moveToFirst()) {
            do {
                PollModel poll = new PollModel();
                poll.setCandidateId((resultSet.getInt(resultSet.getColumnIndex("CANDIDATEID"))));
                poll.setFirstName((resultSet.getString(resultSet.getColumnIndex("FIRSTNAME"))));
                poll.setLastName((resultSet.getString(resultSet.getColumnIndex("LASTNAME"))));
                poll.setParty((resultSet.getString(resultSet.getColumnIndex("PARTY"))));
                poll.setState((resultSet.getString(resultSet.getColumnIndex("STATE"))));
                poll.setElectionYear((resultSet.getInt(resultSet.getColumnIndex("ELECTIONYEAR"))));

                // adding to poll list
                pollList.add(poll);
            } while (resultSet.moveToNext());
        }

        return pollList;
    }

    public int updatePoll(PollModel poll) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("FIRSTNAME", poll.getFirstName());
        values.put("LASTNAME", poll.getLastName());
        values.put("PARTY", poll.getParty());
        values.put("STATE", poll.getState());
        values.put("ELECTIONYEAR", poll.getState());
        // updating row
        return db.update(TABLE_POLL, values, "CANDIDATEID" + " = ?",
                new String[] { String.valueOf(poll.getCandidateId()) });
    }

    /**
     * Deleting a poll
     */
    public void deletePoll(long candidateId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POLL, "CANDIDATEID" + " = ?",
                new String[] { String.valueOf(candidateId) });
    }


    // ------------------------ "VOTER" table methods ----------------//

    public Boolean insertVoter(VoterModel voter) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("FIRSTNAME", voter.getFirstName());
        values.put("LASTNAME", voter.getLastName());
        values.put("STATE", voter.getState());
        values.put("ADDRESS", voter.getAddress());
        values.put("ZIPCODE",voter.getZipCode());


        // insert row
        long resultSet = db.insert(TABLE_VOTER, null, values);
        if(resultSet== -1)
            return false;
        else
            return true;

    }

    // get single candidate record from poll table
    public VoterModel getVoter(long voterId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_VOTER + " WHERE  VOTERID =" + voterId;

        Cursor resultSet = db.rawQuery(selectQuery, null);

        if (resultSet != null)
            resultSet.moveToFirst();

        VoterModel voter = new VoterModel();
        voter.setFirstName((resultSet.getString(resultSet.getColumnIndex("FIRSTNAME"))));
        voter.setLastName((resultSet.getString(resultSet.getColumnIndex("LASTNAME"))));
        voter.setState((resultSet.getString(resultSet.getColumnIndex("STATE"))));
        voter.setAddress((resultSet.getString(resultSet.getColumnIndex("ADDRESS"))));
        voter.setZipCode((resultSet.getString(resultSet.getColumnIndex("ZIPCODE"))));
        return voter;
    }

    // GET Voter LIST
    public List<VoterModel> getAllVoters() {
        List<VoterModel> voterList = new ArrayList<VoterModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_VOTER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (resultSet.moveToFirst()) {
            do {
                VoterModel voter = new VoterModel();
                voter.setVoterId((resultSet.getInt(resultSet.getColumnIndex("VOTERID"))));
                voter.setFirstName((resultSet.getString(resultSet.getColumnIndex("FIRSTNAME"))));
                voter.setLastName((resultSet.getString(resultSet.getColumnIndex("LASTNAME"))));
                voter.setState((resultSet.getString(resultSet.getColumnIndex("STATE"))));
                voter.setAddress((resultSet.getString(resultSet.getColumnIndex("ADDRESS"))));
                voter.setZipCode((resultSet.getString(resultSet.getColumnIndex("ZIPCODE"))));

                // adding to voter list
                voterList.add(voter);
            } while (resultSet.moveToNext());
        }

        return voterList;
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

    public boolean insertUser(UserModel user)
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

    }
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