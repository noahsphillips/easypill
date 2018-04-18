package com.example.bendenton.easypill;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PillInfoController extends AppCompatActivity
{

    private Button.OnClickListener updatePillButton = new Button.OnClickListener()
    {
        @Override
        public void onClick(@NonNull View v)
        {
            if(isTaken.isChecked())
            {
                pill.setIsTaken(1);
            }
            else
            {
                pill.setIsTaken(0);
            }

            db.updatePill(pill);
        }
    };
    private Button.OnClickListener deletePillButton = new Button.OnClickListener()
    {
        @Override
        public void onClick(@NonNull View v)
        {
            db.deletePill(pill.getUserId());
        }
    };

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Pill Info");
        PillModel pill = new PillModel();
        DatabaseHelper db = new DatabaseHelper(this);
        TextView pillName = (TextView) findViewById(R.id.pillNameChange);
        TextView pillTime = (TextView) findViewById(R.id.pillTimeChange);
        CheckBox isTaken = (CheckBox) findViewById(R.id.isTakenCheckBox);

    }
    DatabaseHelper db = new DatabaseHelper(this);
    PillModel pill = new PillModel();
    TextView pillName = (TextView) findViewById(R.id.pillNameChange);
    TextView pillTime = (TextView) findViewById(R.id.pillTimeChange);
    CheckBox isTaken = (CheckBox) findViewById(R.id.isTakenCheckBox);
}

