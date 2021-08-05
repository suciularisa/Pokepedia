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
import com.lsuciu.pokepedia.data.PokemonDatabase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class EvolutionsFragment extends Fragment {

    public static final String title = "EVOLUTIONS";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterPoketeam adapter;
    PageViewModel pageViewModel;
    List<PokemonData> evolutions = new ArrayList<>();
    Integer label, pokemonId;



    public EvolutionsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);
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

        pageViewModel.getPokemons().observe(requireActivity(), new Observer<List<PokemonData>>() {
            @Override
            public void onChanged(List<PokemonData> pokemonDataList) {
                updateData(pokemonDataList);
            }
        });


        adapter = new AdapterPoketeam(this.getContext(), evolutions, label, pokemonId);

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
        label = pageViewModel.getLabel();
        pokemonId = pageViewModel.getPokemonId();
    }


}