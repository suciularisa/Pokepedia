package com.lsuciu.pokepedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private HomeFragment homeFragment;
    private MapsFragment mapsFragment;
    private CapturedPokemonsFragment capturedPokemonsFragment;

    List<Integer> ids;
    List<PokemonJson> pokemonJsonList;
    int MARKER_COUNT = 5;
    int MIN_POKEMON_ID = 1;
    int MAX_POKEMON_ID = 100;
    CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        homeFragment = new HomeFragment();
        mapsFragment = new MapsFragment();
        capturedPokemonsFragment = new CapturedPokemonsFragment();

        ids = generatePokemonIds();
        pokemonJsonList = new ArrayList<>();

        //change the status bar color
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.base_blue));

        //Button Navigation
        BottomNavigationView navigationView = findViewById(R.id.nav_bar);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment;

                switch (item.getItemId()){
                    case R.id.nav_home: selectedFragment = homeFragment;
                                        break;
                    case R.id.nav_map: selectedFragment = mapsFragment;
                                        break;
                    case R.id.nav_captured: selectedFragment =capturedPokemonsFragment;
                                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }
        });
        

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiServiceRX apiService = retrofit.create(ApiServiceRX.class);

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(Observable.range(0,MARKER_COUNT)
                .flatMap(index -> apiService.getPokemon(ids.get(index)))
                .map(pokemonJson -> {
                    pokemonJsonList.add(pokemonJson);
                    return pokemonJson;
                })
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pokemonJsonList -> mapsFragment.pokemonJsonList = pokemonJsonList
                        , throwable -> Log.v("RETROFIT", throwable.getMessage())));

    }


    private List<Integer> generatePokemonIds(){
        List<Integer> ids = new ArrayList<>();

        Random random = new Random();
        while(ids.size() < MARKER_COUNT)
            ids.add(random.nextInt(MAX_POKEMON_ID - MIN_POKEMON_ID) + MIN_POKEMON_ID);

        return ids;
    }



    @Override
    protected void onStart() {
        super.onStart();

        Log.v(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

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