package com.lsuciu.pokepedia;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


public class AboutFragment extends Fragment {

    public static final String title = "ABOUT";
    PokemonDetails pokemon;
    TextView id;
    TextView height;
    TextView weight;
    TextView happiness;
    TextView capture;
    TextView description;
    PageViewModel pageViewModel;
    StringBuilder sb;
    CompositeDisposable compositeDisposable;
    List<PokemonData> pokemonDataList;
    PokemonDatabase pokemonDatabase;

    public AboutFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        id = view.findViewById(R.id.id_pokemon);
        height = view.findViewById(R.id.height_pokemon);
        weight = view.findViewById(R.id.weight_pokemon);
        happiness = view.findViewById(R.id.hapiness_pokemon);
        capture = view.findViewById(R.id.capture_pokemon);
        description = view.findViewById(R.id.pokemon_description);

        pokemonDataList = new ArrayList<>();
        pokemonDatabase = PokemonDatabase.getInstance(this.getContext());
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pageViewModel.getPokemon().observe(requireActivity(), new Observer<PokemonDetails>() {
            @Override
            public void onChanged(PokemonDetails pokemonDetails) {
                compositeDisposable = new CompositeDisposable();
                updateData(pokemonDetails);
            }
        });

        pageViewModel.getSavePokemon().observe(requireActivity(), new Observer<Pokemon>() {
            @Override
            public void onChanged(Pokemon pokemon) {
                savePokemon(pokemon);
            }
        });

    }


    public void updateData(PokemonDetails pokemonDetails) {
        this.pokemon = pokemonDetails;
        Pokemon pokemonEntity = pageViewModel.getSavePokemon().getValue();
        if(pokemonEntity != null) pokemonEntity.setEvolutions(new ArrayList<>());

        description.setText(pokemon.getDescription());

        sb = new StringBuilder();
        int id2 = pokemon.getId();

        if(id2 / 10 == 0){
            sb.append("00" + id2);
        }else if(id2 / 100 == 0){
            sb.append("0" + id2);
        }else sb.append(id2);
        id.setText(sb.toString());

        sb = new StringBuilder();
        sb.append(((float)pokemon.getHeight() / 10) + " m");
        height.setText(sb.toString());

        sb = new StringBuilder();
        sb.append(((float)pokemon.getWeight() / 10) + " kg");
        weight.setText(sb.toString());

        happiness.setText(Integer.toString(pokemon.getHappiness()));
        capture.setText(Integer.toString(pokemon.getCapture()));


        if(pageViewModel.getLabel() == 1){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            ApiServiceRX apiServiceRX = retrofit.create(ApiServiceRX.class);

            for (String name : pokemon.getEvolutions()) {

                compositeDisposable.add(apiServiceRX.getPokemon(name)
                        .map(item -> {
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
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(item -> {
                            if(pokemonEntity != null){
                                pokemonEntity.getEvolutions().add(item.getId());
                            }
                            pokemonDataList.add(item);
                        }, throwable -> Log.d("RETROFIT", throwable.getMessage())));
            }
            pageViewModel.setPokemons(pokemonDataList);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }

    public void savePokemon(Pokemon pokemon){
        pokemon.setEvolutions(new ArrayList<>());

        for (PokemonData pd: pokemonDataList) {
            pokemon.getEvolutions().add(pd.getId());
        }

        pokemonDatabase.pokemonDao().insertPokemon(pokemon);
    }
}