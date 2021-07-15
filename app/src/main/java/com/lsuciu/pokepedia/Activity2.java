package com.lsuciu.pokepedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RadioGroup;


public class Activity2 extends AppCompatActivity {
    private RadioGroup radioGroup;
    private Toolbar toolbar;
    private final String TAG = "Activity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.about: Log.v(TAG,"ABOUT");
                                     break;
                    case R.id.stats: Log.v(TAG, "STATS");
                                    break;
                    case R.id.evolution: Log.v(TAG, "EVOLUTION");
                                        break;
                    case R.id.moves: Log.v(TAG, "MOVES");
                                        break;
                }
            }
        });

    }

}