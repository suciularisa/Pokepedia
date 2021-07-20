package com.lsuciu.pokepedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private RadioGroup radioGroup;
    private PokedexFragment pokedex;
    private PoketeamFragment poketeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pokedex = new PokedexFragment();
        poketeam = new PoketeamFragment();
        setFragment(pokedex);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rb = findViewById(checkedId);

                switch (rb.getId()){
                    case R.id.pokedex: Log.v(TAG, "Selected POKEDEX");
                                        setFragment(pokedex);
                                         rb.setTypeface(null, Typeface.BOLD);
                                        break;
                    case R.id.poketeam: Log.v(TAG, "Selected POKETEAM");
                                        rb.setTypeface(null, Typeface.BOLD);
                                        setFragment(poketeam);
                                        break;
                }
            }
        });


        //change the status bar color
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.base_blue));

    }
    @Override
    protected void onStart() {
        super.onStart();

        Log.v(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();


        Log.v(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG,"onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG,"onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"onDestroy");
    }

    private void setFragment(Fragment f){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, f);
        ft.commit();
    }


}