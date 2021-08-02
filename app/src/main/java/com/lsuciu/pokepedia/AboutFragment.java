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

        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pageViewModel.getPokemon().observe(requireActivity(), new Observer<PokemonDetails>() {
            @Override
            public void onChanged(PokemonDetails pokemonDetails) {
                updateData(pokemonDetails);
            }
        });
    }


    public void updateData(PokemonDetails pokemonDetails) {
        this.pokemon = pokemonDetails;

        Log.d("test2", String.valueOf(pokemon.getId()));

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

    }


}