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

import com.lsuciu.pokepedia.data.CapturedPokemon;
import com.lsuciu.pokepedia.data.CapturedPokemonDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CapturedPokemonsFragment extends Fragment{

    RecyclerView recyclerView;
    AdapterCapturedPokemons adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_captured_pokemons, container, false);

        CapturedPokemonDatabase capturedPokemonDB = CapturedPokemonDatabase.getInstance(this.getContext());


        List<CapturedPokemon> pokemons = (ArrayList<CapturedPokemon>) capturedPokemonDB.capturedPokemonDao().getCapturedPokemons();
        Collections.reverse(pokemons);

        recyclerView = view.findViewById(R.id.recyclerViewCapturedPokemons);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new AdapterCapturedPokemons(getActivity(), pokemons);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
