package com.example.bendenton.easypill;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPillController extends AppCompatActivity
{

    public void touchNewPill(View view) {
        EditText pillName = (EditText) findViewById(R.id.pillName);
        EditText pillStartDate =  (EditText) findViewById(R.id.pillStartDate);
        EditText pilldays = (EditText) findViewById(R.id.pillNumber);
        if (!pillName.getText().toString().matches("") || !pillStartDate.getText().toString().matches("") || !pilldays.getText().toString().matches("")) {
            PillModel pill = new PillModel(pillStartDate.getText().toString(), pillName.getText().toString(),0);
            DatabaseHelper db = new DatabaseHelper(this);
            Boolean added = db.insertPill(pill);
            if(added)
            {
                startActivity(new Intent(NewPillController.this, PillsController.class));
                Toast.makeText(this, "Pill Added", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "An Error Occurred", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Complete all fields", Toast.LENGTH_LONG).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_pill_register);
    }

}
