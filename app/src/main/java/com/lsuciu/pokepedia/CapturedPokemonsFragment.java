package com.lsuciu.pokepedia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.type.Date;
import com.lsuciu.pokepedia.data.CapturedPokemon;
import com.lsuciu.pokepedia.data.CapturedPokemonDatabase;
import com.lsuciu.pokepedia.data.Pokemon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CapturedPokemonsFragment extends Fragment{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterCapturedPokemons adapter;
    List<PokemonData> pokemonDataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_captured_pokemons, container, false);

        CapturedPokemonDatabase capturedPokemonDB = CapturedPokemonDatabase.getInstance(this.getContext());

        //TESTING////////////////////////////
       // capturedPokemonDB.capturedPokemonDao().deleteCapturedPokemons();

       /* CapturedPokemon capturedPokemon = new CapturedPokemon();
        capturedPokemon.setName("Bulbasaur");
        capturedPokemon.setId(1);
        capturedPokemon.setImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png");
        capturedPokemon.setTypes(new ArrayList<>(Arrays.asList(Type.GRASS, Type.POISON)));
        capturedPokemon.setLocation(new ArrayList<>(Arrays.asList(46.789, 46.889)));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        capturedPokemon.setDate(LocalDateTime.now());*/

        //capturedPokemonDB.capturedPokemonDao().insertPokemon(capturedPokemon);
        /////////////////////////////////

        List<CapturedPokemon> pokemons = (ArrayList<CapturedPokemon>) capturedPokemonDB.capturedPokemonDao().getCapturedPokemons();

        recyclerView = view.findViewById(R.id.recyclerViewCapturedPokemons);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new AdapterCapturedPokemons(getActivity(), pokemons);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
