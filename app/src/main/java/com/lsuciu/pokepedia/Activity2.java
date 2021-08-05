package com.lsuciu.pokepedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.LottieTask;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lsuciu.pokepedia.data.Pokemon;
import com.lsuciu.pokepedia.data.PokemonDatabase;
import com.lsuciu.pokepedia.data.SavedPokemon;
import com.lsuciu.pokepedia.data.SavedPokemonDatabase;

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
    PokemonDatabase pokemonDB;
    List<PokemonData> pokemonDataList;
    SavedPokemonDatabase savedPokemonDatabase;
    Pokemon savePokemon;
    Retrofit retrofit;
    ApiServiceRX apiServiceRX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
         apiServiceRX = retrofit.create(ApiServiceRX.class);
        compositeDisposable = new CompositeDisposable();

        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        pokemonDataList = new ArrayList<>();
        savedPokemonDatabase = SavedPokemonDatabase.getInstance(this);
        pokemonDB = PokemonDatabase.getInstance(this);

        // Get the selected pokemon from the first activity
        intent = getIntent();
        pokemon = (PokemonData)intent.getSerializableExtra("selected_pokemon");
        pokemon2 = (PokemonData)intent.getSerializableExtra("selected_pokemon2");
        pokemonDetails = new PokemonDetails();
        initData();
        pageViewModel.setPokemonId(pokemonDetails.getId());

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
        StringBuilder sb = new StringBuilder();
        sb.append(pokemonDetails.getName().toUpperCase().charAt(0) + pokemonDetails.getName().substring(1));
        name.setText(sb.toString());
        Glide.with(this)
                .load(pokemonDetails.getImage())
                .into(image);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);

        MenuItem heart_button = menu.findItem(R.id.heart_button);

        if(pokemonDB.pokemonDao().getFromId(pokemonDetails.getId()) != null){
            heart_button.setIcon(R.drawable.heart_on);
        }

        heart_button.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(pokemonDB.pokemonDao().getFromId(pokemonDetails.getId()) == null){
                    savePokemon = new Pokemon();
                    savePokemon.setImage(pokemonDetails.getImage());
                    savePokemon.setId(pokemonDetails.getId());
                    savePokemon.setTypes(pokemonDetails.getTypes());
                    savePokemon.setName(pokemonDetails.getName());
                    savePokemon.setDescription(pokemonDetails.getDescription());
                    savePokemon.setBaseHappiness(pokemonDetails.getHappiness());
                    savePokemon.setCaptureRate(pokemonDetails.getCapture());
                    savePokemon.setHeight(pokemonDetails.getHeight());
                    savePokemon.setWeight(pokemonDetails.getWeight());
                    savePokemon.setStats(pokemonDetails.getStats());

                    pageViewModel.setSavePokemon(savePokemon);

                    //savedPokemonDatabase.SavedPokemonDao().savePokemon(new SavedPokemon(pokemonDetails.getId()));
                    heart_button.setIcon(R.drawable.heart_on);
                }else{
                    //savedPokemonDatabase.SavedPokemonDao().deleteFromId(pokemonDetails.getId());
                    pokemonDB.pokemonDao().deleteFromId(pokemonDetails.getId());
                    heart_button.setIcon(R.drawable.heart_off);
                }
                return false;
            }
        });

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(pokemon2 == null) {

            int id = pokemonDetails.getId();
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
                        return item;
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                        pageViewModel.setPokemon(pokemonDetails);
                    }, throwable -> Log.v("RETROFIT", throwable.getMessage())));

            pageViewModel.setLabel(1);
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(pokemon2 == null) compositeDisposable.clear();
    }

    private void initData(){
        if(pokemon2 == null){
            pokemonDetails.setId(pokemon.getId());
            pokemonDetails.setName(pokemon.getName());
            pokemonDetails.setTypes(pokemon.getTypes());
            pokemonDetails.setImage(pokemon.getImage());

        }else{
            // Get all the data from the database and send it to the fragments
            Pokemon pokemonDataBase = (Pokemon) pokemonDB.pokemonDao().getPokemon(pokemon2.getId());
            pokemonDetails.setId(pokemonDataBase.getId());
            pokemonDetails.setName(pokemonDataBase.getName());
            pokemonDetails.setWeight(pokemonDataBase.getWeight());
            pokemonDetails.setHeight(pokemonDataBase.getHeight());
            pokemonDetails.setHappiness(pokemonDataBase.getBaseHappiness());
            pokemonDetails.setCapture(pokemonDataBase.getCaptureRate());
            pokemonDetails.setDescription(pokemonDataBase.getDescription());
            pokemonDetails.setTypes(pokemonDataBase.getTypes());
            pokemonDetails.setImage(pokemonDataBase.getImage());
            pokemonDetails.setStats(pokemonDataBase.getStats());
            pokemonDetails.setEvolutionsId(pokemonDataBase.getEvolutions());

            for (Integer id: pokemonDetails.getEvolutionsId()) {
                if(pokemonDB.pokemonDao().getPokemon(id) != null){
                    pokemonDataBase = pokemonDB.pokemonDao().getPokemon(id);

                    PokemonData pokemonData = new PokemonData();
                    pokemonData.setId(pokemonDataBase.getId());
                    pokemonData.setName(pokemonDataBase.getName());
                    pokemonData.setImage(pokemonDataBase.getImage());
                    pokemonData.setTypes(pokemonDataBase.getTypes());

                    pokemonDataList.add(pokemonData);
                }else{
                    compositeDisposable.add(apiServiceRX.getPokemon(id).map(item -> {
                        PokemonData pokemonData = new PokemonData();
                        pokemonData.setId(item.getId());
                        pokemonData.setName(item.getName());
                        pokemonData.setImage(item.getSprite().getSpriteDetails().getArtwork().getArtworkUrl());
                        List<String> stringTypes = item.getStringTypes();
                        List<Type> types = new ArrayList<>();
                        for (String s : stringTypes) {
                            types.add(Type.valueOf(s.toUpperCase()));
                        }
                        pokemonData.setTypes(types);

                        return pokemonData;
                    }).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(item -> {
                                pokemonDataList.add(item);
                            }, throwable -> Log.d("RETROFIT", throwable.getMessage())));
                }

            }
            pageViewModel.setLabel(2);
            pageViewModel.setPokemons(pokemonDataList);
            pageViewModel.setPokemon(pokemonDetails);
        }

    }

}