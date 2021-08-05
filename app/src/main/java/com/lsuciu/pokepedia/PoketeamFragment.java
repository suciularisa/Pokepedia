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
/*       Pokemon pokemon = new Pokemon();
        pokemon.setId(100);
        pokemon.setName("voltorb");
        pokemon.setBaseHappiness(70);
        pokemon.setCaptureRate(190 );
        pokemon.setDescription("VOLTORB is extremely sensitive - it\\nexplodes at the slightest of shocks.\\nIt is rumored that it was first created\\u000cwhen a POKé BALL was exposed to a\\npowerful pulse of energy.");
       pokemon.setHeight(5);
       pokemon.setWeight(104);
       pokemon.setImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/100.png");
       pokemon.setTypes(new ArrayList<>(Arrays.asList(Type.ELECTRIC)));
       pokemon.setEvolutions(new ArrayList<>(Arrays.asList(100,101)));
       pokemon.setStats(new ArrayList<>(Arrays.asList(40,30,50,55,55,100)));
       pokemonDB.pokemonDao().insertPokemon(pokemon);

        Pokemon pokemon2 = new Pokemon();
        pokemon2.setId(101);
        pokemon2.setName("electrode");
        pokemon2.setBaseHappiness(70);
        pokemon2.setCaptureRate(60);
        pokemon2.setDescription("ELECTRODE eats electricity in the\\natmosphere. On days when lightning\\nstrikes, you can see this POKéMON\\u000cexploding all over the place from\\neating too much electricity.");
        pokemon2.setHeight(12);
        pokemon2.setWeight(666);
        pokemon2.setImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/101.png");
        pokemon2.setTypes(new ArrayList<>(Arrays.asList(Type.ELECTRIC)));
        pokemon2.setEvolutions(new ArrayList<>(Arrays.asList(100,101)));
        pokemon2.setStats(new ArrayList<>(Arrays.asList(60,50,70,80,80,150)));
        pokemonDB.pokemonDao().insertPokemon(pokemon2);*/



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