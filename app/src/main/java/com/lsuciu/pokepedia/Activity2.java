package com.lsuciu.pokepedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lsuciu.pokepedia.data.Pokemon;
import com.lsuciu.pokepedia.data.PokemonDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class Activity2 extends AppCompatActivity  {
    private Toolbar toolbar;
    private Intent intent;
    private PokemonDetails pokemonDetails;
    CompositeDisposable compositeDisposable;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerAdapterActivity2 adapter;
    PageViewModel pageViewModel;
    PokemonData pokemon, pokemon2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);

        // Get the selected pokemon from the first activity
        intent = getIntent();
        pokemon = (PokemonData)intent.getSerializableExtra("selected_pokemon");
        pokemon2 = (PokemonData)intent.getSerializableExtra("selected_pokemon2");
        pokemonDetails = new PokemonDetails();
        initData();

        setTheme(ThemeUtils.getThemeId(pokemonDetails.getTypes().get(0).getName()));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(null);
        actionBar.setHomeAsUpIndicator(R.drawable.back_arrow);


        // Viewpager and tabs
         tabLayout = findViewById(R.id.tabs);
         viewPager2 = findViewById(R.id.view_pager);
         adapter = new ViewPagerAdapterActivity2(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(adapter.getFragmentTitle(position));
                    }
                }).attach();
        //////




        //set the activity visible fields
        TextView type1 = findViewById(R.id.pokemon_type_1);
        TextView type2 = findViewById(R.id.pokemon_type_2);
        TextView name = findViewById(R.id.pokemon_name);
        ImageView image = findViewById(R.id.pokemon_image);

        type1.setText(pokemonDetails.getTypes().get(0).getName());
        if(pokemonDetails.getTypes().size() == 2) {
            type2.setText(pokemonDetails.getTypes().get(1).getName());
            type2.setBackgroundResource(R.drawable.transparent_placeholder);
        }
        name.setText(pokemonDetails.getName());
        Glide.with(this)
                .load(pokemonDetails.getImage())
                .into(image);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(pokemon2 == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            ApiServiceRX apiServiceRX = retrofit.create(ApiServiceRX.class);

            int id = pokemonDetails.getId();
            compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(apiServiceRX.getPokemon(id)
                    .map(pokemonJson -> {
                        pokemonDetails.setHeight(pokemonJson.getHeight());
                        pokemonDetails.setWeight(pokemonJson.getWeight());
                        pokemonDetails.setStats(pokemonJson.getStatsMap());
                        return pokemonDetails;
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());

            compositeDisposable.add(apiServiceRX.getSpeciesDetails(id)
                    .map(item -> {
                        pokemonDetails.setHappiness(item.getHappiness());
                        pokemonDetails.setCapture(item.getCaptureRate());
                        pokemonDetails.setDescription(item.getEntries().get(7).getEntry());
                        return item;
                    })
                    .flatMap(item -> apiServiceRX.getEvolutionChain(item.getEvolutionChain().getEvolutionChainUrl()))
                    .map(item -> {
                        pokemonDetails.setEvolutions(item.getSpeciesNames());
                        pokemonDetails.setEvolutionsUrl(item.getSpeciesURLS());
                        return pokemonDetails;
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                        pageViewModel.setPokemon(pokemonDetails);
                    }, throwable -> Log.v("RETROFIT", throwable.getMessage())));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }

    private void initData(){
        if(pokemon2 == null){
            pokemonDetails.setId(pokemon.getId());
            pokemonDetails.setName(pokemon.getName());
            pokemonDetails.setTypes(pokemon.getTypes());
            pokemonDetails.setImage(pokemon.getImage());
        }else{
          //  PokemonDatabase pokemonDB = PokemonDatabase.getInstance(this);
           // Pokemon pokemonDataBase = (Pokemon) pokemonDB.pokemonDao().getPokemon(pokemon2.getId());

        }

    }

}