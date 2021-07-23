package com.lsuciu.pokepedia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsuciu.pokepedia.data.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;


public class PokedexFragment extends Fragment {

    public static final String title = "Pokedex";


    ArrayList<Type> t1 = new ArrayList<Type>(Arrays.asList(Type.GRASS,Type.POISON));
    ArrayList<Type> t2 = new ArrayList<Type>(Arrays.asList(Type.FIRE));
    ArrayList<Type> t3 = new ArrayList<Type>(Arrays.asList(Type.FIRE,Type.FLYING));
    ArrayList<Type> t4 = new ArrayList<Type>(Arrays.asList(Type.WATER));
    ArrayList<Type> t5 = new ArrayList<Type>(Arrays.asList(Type.BUG));

    private Pokemon p1 = new Pokemon("Bulbasaur",1, t1);
    private Pokemon p2 = new Pokemon("Ivysaur",2, t1);
    private Pokemon p3 = new Pokemon("Venusaur",3, t1);
    private Pokemon p4 = new Pokemon("Charmander",4, t2);
    private Pokemon p5 = new Pokemon("Charmeleon",5, t2);
    private Pokemon p6 = new Pokemon("Charizard",6, t3);
    private Pokemon p7 = new Pokemon("Squirtle",7, t4);
    private Pokemon p8 = new Pokemon("Wartortle",8, t4);
    private Pokemon p9 = new Pokemon("Blastoise",9, t4);
    private Pokemon p10 = new Pokemon("Caterpie",10, t5);
    private Pokemon p11 = new Pokemon("Metapod",11, t5);
    private Pokemon p12 = new Pokemon("Butterfree",12, t5);
    private Pokemon p13 = new Pokemon("Weedle",13, t5);
    private Pokemon p14 = new Pokemon("Kakuna",14, t5);
    private Pokemon p15 = new Pokemon("Beedrill",15, t5);


    public PokedexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pokedex, container, false);

        ArrayList<Pokemon> pokemons = new ArrayList<>(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15));
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPokedex);

        RecyclerView.Adapter adapter = new AdapterPokedex(getActivity(), pokemons);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);

        return view;
    }


}