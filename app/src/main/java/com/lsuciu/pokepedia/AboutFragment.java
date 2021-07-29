package com.lsuciu.pokepedia;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutFragment extends Fragment implements Activity2.Callback {

    public static final String title = "ABOUT";
    PokemonDetails pokemon;
    TextView id;
    TextView height;
    TextView weight;
    TextView happiness;
    TextView capture;
    PageViewModel pageViewModel;

    public AboutFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Activity2)getActivity()).receiveDataListener(this);
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

        return view;
    }


    @Override
    public void sendData(PokemonDetails pokemonDetails) {
        this.pokemon = pokemonDetails;

        Log.d("test2", String.valueOf(pokemon.getId()));

        id.setText(Integer.toString(pokemon.getId()));
        height.setText(Integer.toString(pokemon.getHeight()));
        weight.setText(Integer.toString(pokemon.getWeight()));
        happiness.setText(Integer.toString(pokemon.getHappiness()));
        capture.setText(Integer.toString(pokemon.getCapture()));

        pageViewModel.setPokemon(pokemon);
    }


}