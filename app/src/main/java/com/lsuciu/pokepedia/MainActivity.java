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
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lsuciu.pokepedia.data.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private final String TAG2 = "RETROFIT";


    private RadioGroup radioGroup;
    private PokedexFragment pokedex;
    private PoketeamFragment poketeam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LinearLayout tabLayout = (LinearLayout)((ViewGroup) tabs.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LinearLayout tabLayout = (LinearLayout)((ViewGroup) tabs.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
                tabTextView.setTypeface(Typeface.DEFAULT);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        //change the status bar color
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.base_blue));







       /* ApiService apiService = retrofit.create(ApiService.class);
        Call<PokemonJson> call = apiService.getPokemon(1);
        call.enqueue(new Callback<PokemonJson>() {
            @Override
            public void onResponse(Call<PokemonJson> call, Response<PokemonJson> response) {
                if(!response.isSuccessful()) {
                    Log.v(TAG2, "Code: " + response.code());
                    return;
                }
                PokemonJson pokemon = response.body();
                Log.v(TAG2, pokemon.toString());
                Log.v(TAG2, "Artwork url: " + pokemon.getSprite().getSpriteDetails().getArtwork().getArtworkUrl());


                apiService.getSpeciesDetails(pokemon.getId()).enqueue(new Callback<SpeciesDetails>() {
                    @Override
                    public void onResponse(Call<SpeciesDetails> call, Response<SpeciesDetails> response) {
                        SpeciesDetails speciesDetails = response.body();
                        String evolutionUrl = speciesDetails.getEvolutionChain().getEvolutionChainUrl();
                        Log.v(TAG2, evolutionUrl);

                        Retrofit retrofit2 = new Retrofit.Builder()
                                .baseUrl(evolutionUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build();

                        ApiService apiService2 = retrofit2.create(ApiService.class);
                        Call<EvolutionJson> evolutionChainCall = apiService2.getEvolutionChain();

                        evolutionChainCall.enqueue(new Callback<EvolutionJson>() {
                            @Override
                            public void onResponse(Call<EvolutionJson> call, Response<EvolutionJson> response) {
                                if(!response.isSuccessful()){
                                    Log.v(TAG2, "EVOLUTIONS: RESPONDSE NOT SUCCESSFUL");
                                    return;
                                }
                                EvolutionJson evolutionJson = response.body();
                                Log.v(TAG2, "EVOLUTIONS: " + evolutionJson.getChain().getChain2().get(0).getChain2().get(0).getSpecies().getName());
                            }

                            @Override
                            public void onFailure(Call<EvolutionJson> call, Throwable t) {
                                Log.v(TAG2, "EVOLUTIONS FAILED");
                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<SpeciesDetails> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<PokemonJson> call, Throwable t) {
                    Log.v("RETROFIT","ERROR : " + t.getMessage());
            }
        });*/

    }
    @Override
    protected void onStart() {
        super.onStart();

        Log.v(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        TabLayout tabs = findViewById(R.id.tabs);
        LinearLayout tabLayout = (LinearLayout)((ViewGroup) tabs.getChildAt(0)).getChildAt(0);
        TextView tabTextView = (TextView) tabLayout.getChildAt(1);
        tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);

        tabs.setBackgroundResource(R.color.base_blue);
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