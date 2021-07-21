package com.lsuciu.pokepedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private RadioGroup radioGroup;
    private PokedexFragment pokedex;
    private PoketeamFragment poketeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       /* pokedex = new PokedexFragment();
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
        });*/

        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager2 viewPager = findViewById(R.id.view_pager);

        ArrayList<Fragment> fragments = new ArrayList<>(Arrays.asList(new PokedexFragment(), new PoketeamFragment()));
        ViewPagerAdapterMainActivity adapter = new ViewPagerAdapterMainActivity(this, fragments);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabs, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(adapter.getFragmentTitle(position));
                    }
                }).attach();


        //change the status bar color
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.base_blue));

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

}