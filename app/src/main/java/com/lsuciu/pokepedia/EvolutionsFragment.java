package com.lsuciu.pokepedia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsuciu.pokepedia.data.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class EvolutionsFragment extends Fragment {

    public static final String title = "EVOLUTIONS";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterEvolutions adapter;
    PageViewModel2 pageViewModel2;
    List<PokemonData> evolutions = new ArrayList<>();
    CompositeDisposable compositeDisposable;



    public EvolutionsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel2 = new ViewModelProvider(requireActivity()).get(PageViewModel2.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evolutions, container, false);

        //Recycler View part
        recyclerView = view.findViewById(R.id.recyclerViewEvolutions);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        pageViewModel2.getPokemons().observe(requireActivity(), new Observer<List<PokemonData>>() {
            @Override
            public void onChanged(List<PokemonData> pokemonDataList) {
                updateData(pokemonDataList);
            }
        });

        adapter = new AdapterEvolutions(this.getContext(), evolutions);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void updateData(List<PokemonData> pokemonData){

        evolutions = pokemonData;
        evolutions.sort(Comparator.comparing(PokemonData::getId));
    }

}