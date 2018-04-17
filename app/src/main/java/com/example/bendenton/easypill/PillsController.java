package com.example.bendenton.easypill;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class PillsController extends AppCompatActivity
{

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {


        ListView futurePills = (ListView) findViewById(R.id.futurePillListView);

        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId()) {
                case R.id.navigation_pastPills:
                    setTitle("Pills Taken");
                    setContentView(R.layout.fragment_past_pill);
                    //futurePills.add
                    return true;
                case R.id.navigation_futurePills:
                    setTitle("Upcoming Pills");
                    setContentView(R.layout.pill_layout);
                    return true;
            }
            return false;
        }

    };
    private FloatingActionButton.OnClickListener newPillListener
            = new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(@NonNull View v)
        {
            setContentView(R.layout.new_pill_register);
            setTitle("New Pill");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pill_layout);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FloatingActionButton newPill = (FloatingActionButton) findViewById(R.id.newPillRegister);
        newPill.setOnClickListener(newPillListener);

    }
}
