package com.lsuciu.pokepedia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsuciu.pokepedia.data.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class PokedexFragment extends Fragment {

    public static final String title = "Pokedex";

    List<PokemonData> pokemonJsonList = new ArrayList<>();
    RecyclerView.Adapter adapter;

    ArrayList<Type> t1 = new ArrayList<Type>(Arrays.asList(Type.GRASS,Type.POISON));
    ArrayList<Type> t2 = new ArrayList<Type>(Arrays.asList(Type.FIRE));
    ArrayList<Type> t3 = new ArrayList<Type>(Arrays.asList(Type.FIRE,Type.FLYING));
    ArrayList<Type> t4 = new ArrayList<Type>(Arrays.asList(Type.WATER));
    ArrayList<Type> t5 = new ArrayList<Type>(Arrays.asList(Type.BUG));

    private Pokemon p1 = new Pokemon("Bulbasaur",1, t1);
    private Pokemon p2 = new Pokemon("Ivysaur",2, t1);
    private Pokemon p3 = new Pokemon("Venusaur",3, t1);
    private Pokemon p4 = new Pokemon("Charmander",4, t2);
    private Pokemon p5 = new Pokemon("Charmeleon",5, t2);
    private Pokemon p6 = new Pokemon("Charizard",6, t3);
    private Pokemon p7 = new Pokemon("Squirtle",7, t4);
    private Pokemon p8 = new Pokemon("Wartortle",8, t4);
    private Pokemon p9 = new Pokemon("Blastoise",9, t4);
    private Pokemon p10 = new Pokemon("Caterpie",10, t5);
    private Pokemon p11 = new Pokemon("Metapod",11, t5);
    private Pokemon p12 = new Pokemon("Butterfree",12, t5);
    private Pokemon p13 = new Pokemon("Weedle",13, t5);
    private Pokemon p14 = new Pokemon("Kakuna",14, t5);
    private Pokemon p15 = new Pokemon("Beedrill",15, t5);


    public PokedexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pokedex, container, false);

        //ArrayList<Pokemon> pokemons = new ArrayList<>(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15));
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPokedex);

        adapter = new AdapterPokedex(getActivity(), pokemonJsonList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiServiceRX apiServiceRX = retrofit.create(ApiServiceRX.class);
        Observable.range(1,3)
                .flatMap(index -> apiServiceRX.getPokemon(index))
                .toList()
                .toObservable()
                .map(list -> {
                    for (PokemonJson pj: list) {
                        PokemonData pokemonData = new PokemonData();
                        pokemonData.setId(pj.getId());
                        pokemonData.setName(pj.getName());
                        pokemonData.setImage(pj.getSprite().getSpriteDetails().getArtwork().getArtworkUrl());
                        List<String> stringTypes = pj.getStringTypes();
                        List<Type> types = new ArrayList<>();
                        for (String s:stringTypes) {
                            types.add(Type.valueOf(s.toUpperCase()));
                        }
                        pokemonData.setTypes(types);

                        pokemonJsonList.add(pokemonData);
                    }
                    return list;
                })
                .flatMap(list -> {
                    return Observable.fromIterable(list).flatMap(item -> apiServiceRX.getSpeciesDetails(item.getId())).toList().toObservable();
                })
                .flatMap(list -> {
                    return Observable.fromIterable(list).flatMap(item -> {
                        return apiServiceRX.getEvolutionChain(item.getEvolutionChain().getEvolutionChainUrl());
                    });
                }).toList().toObservable()
                .map(list -> {
                    for(int i = 0; i < list.size(); i++){
                        //pokemonJsonList.get(i).set
                    }
                    return list;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pokemonJsons -> {
                    adapter.notifyDataSetChanged();
                   // pokemonJsonList.clear();
                    Log.v("RETROFIT", pokemonJsons.toString());
                }, throwable -> Log.v("RETROFIT", throwable.getMessage()));

    }
}