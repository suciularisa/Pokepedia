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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class PokedexFragment extends Fragment {

    public static final String title = "Pokedex";

    List<PokemonData> pokemonDataList = new ArrayList<>();
    RecyclerView.Adapter adapter;
    CompositeDisposable compositeDisposable;

    public PokedexFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pokedex, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPokedex);

        adapter = new AdapterPokedex(getActivity(), pokemonDataList);
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
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(Observable.range(1,20)
                .flatMap(index -> apiServiceRX.getPokemon(index))
                .map(pokemonJson -> {
                    PokemonData pokemonData = new PokemonData();
                    pokemonData.setId(pokemonJson.getId());
                    pokemonData.setName(pokemonJson.getName());
                    pokemonData.setImage(pokemonJson.getSprite().getSpriteDetails().getArtwork().getArtworkUrl());
                    List<String> stringTypes = pokemonJson.getStringTypes();
                    List<Type> types = new ArrayList<>();
                    for (String s:stringTypes) {
                        types.add(Type.valueOf(s.toUpperCase()));
                    }
                    pokemonData.setTypes(types);

                    pokemonDataList.add(pokemonData);
                    return pokemonData;
                })
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pokemonDataList -> adapter.notifyDataSetChanged(),
                        throwable -> Log.v("RETROFIT", throwable.getMessage())));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }
}