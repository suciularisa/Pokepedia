package com.lsuciu.pokepedia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsuciu.pokepedia.data.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;


public class EvolutionsFragment extends Fragment {

    public static final String title = "EVOLUTIONS";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterPoketeam adapter;

    ArrayList<Pokemon> evolutions;

    //For testing only
    ArrayList<Type> t1 = new ArrayList<>(Arrays.asList(Type.GRASS,Type.POISON));
    private Pokemon p1 = new Pokemon("Bulbasaur",1, t1);


    public EvolutionsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evolutions, container, false);


        evolutions = new ArrayList<>(Arrays.asList(p1));



        //Recycler View part
        recyclerView = view.findViewById(R.id.recyclerViewEvolutions);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterPoketeam(this.getContext(), evolutions);
        recyclerView.setAdapter(adapter);

        return view;
    }
}