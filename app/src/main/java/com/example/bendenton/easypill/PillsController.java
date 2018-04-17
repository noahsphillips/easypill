package com.example.bendenton.easypill;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PillsController extends AppCompatActivity
{

    private FloatingActionButton.OnClickListener newPillListener
            = new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(@NonNull View v)
        {
            startActivity(new Intent(PillsController.this, NewPillController.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PillModel pill = new PillModel();
        DatabaseHelper db = new DatabaseHelper(this);
        final ArrayList<String> pillNames = new ArrayList<>();
        final List<PillModel> pillList = db.getAllPills();
        for(PillModel aPill: pillList) {
            pillNames.add(aPill.getPillName());
        }
        setContentView(R.layout.pill_layout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, pillNames);
        ListView listView = (ListView) findViewById(R.id.futurePillListView);
        listView.setAdapter(adapter);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FloatingActionButton newPill = (FloatingActionButton) findViewById(R.id.newPillRegister);
        newPill.setOnClickListener(newPillListener);
    }
}
