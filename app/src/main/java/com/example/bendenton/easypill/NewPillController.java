package com.example.bendenton.easypill;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NewPillController extends AppCompatActivity
{
    DatabaseHelper db = new DatabaseHelper(this);
    private Button.OnClickListener submitNewPillListener
            = new Button.OnClickListener()
    {
        @Override
        public void onClick(@NonNull View v)
        {

        }
    };

}
