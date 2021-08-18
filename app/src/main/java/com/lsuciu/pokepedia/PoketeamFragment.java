package com.lsuciu.pokepedia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsuciu.pokepedia.data.Pokemon;
import com.lsuciu.pokepedia.data.PokemonDao;
import com.lsuciu.pokepedia.data.PokemonDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PoketeamFragment extends Fragment{

    public static final String title = "PokeTeam";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterPoketeam adapter;
    List<PokemonData> pokemonDataList;

    public PoketeamFragment(){ }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poketeam, container, false);



        PokemonDatabase pokemonDB = PokemonDatabase.getInstance(this.getContext());

         List<Pokemon> pokemons = (ArrayList<Pokemon>) pokemonDB.pokemonDao().getPokemons();
        pokemonDataList = new ArrayList<>();

        for (Pokemon pokemon1: pokemons) {
            PokemonData pokemonData = new PokemonData();
            pokemonData.setId(pokemon1.getId());
            pokemonData.setName(pokemon1.getName());
            pokemonData.setTypes(pokemon1.getTypes());
            pokemonData.setImage(pokemon1.getImage());

            pokemonDataList.add(pokemonData);
        }

        // For the Recycler View
        recyclerView = view.findViewById(R.id.recyclerViewPoketeam);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterPoketeam(getActivity(), pokemonDataList, 2, 0);
        recyclerView.setAdapter(adapter);

        return view;
    }

}