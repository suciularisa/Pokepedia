package com.lsuciu.pokepedia;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;


public class PokedexFragment extends Fragment {
    private Button button;


    ArrayList<Type> t1 = new ArrayList<Type>(Arrays.asList(Type.GRASS,Type.POISON));
    ArrayList<Type> t2 = new ArrayList<Type>(Arrays.asList(Type.FIRE));
    ArrayList<Type> t3 = new ArrayList<Type>(Arrays.asList(Type.FIRE,Type.FLYING));
    ArrayList<Type> t4 = new ArrayList<Type>(Arrays.asList(Type.WATER));
    ArrayList<Type> t5 = new ArrayList<Type>(Arrays.asList(Type.BUG));

    private Pokemon p1 = new Pokemon("Bulbasaur","#1", t1);
    private Pokemon p2 = new Pokemon("Ivysaur","#2", t1);
    private Pokemon p3 = new Pokemon("Venusaur","#3", t1);
    private Pokemon p4 = new Pokemon("Charmander","#4", t2);
    private Pokemon p5 = new Pokemon("Charmeleon","#5", t2);
    private Pokemon p6 = new Pokemon("Charizard","#6", t3);
    private Pokemon p7 = new Pokemon("Squirtle","#7", t4);
    private Pokemon p8 = new Pokemon("Wartortle","#8", t4);
    private Pokemon p9 = new Pokemon("Blastoise","#9", t4);
    private Pokemon p10 = new Pokemon("Caterpie","#10", t5);
    private Pokemon p11 = new Pokemon("Metapod","#11", t5);
    private Pokemon p12 = new Pokemon("Butterfree","#12", t5);
    private Pokemon p13 = new Pokemon("Weedle","#13", t5);
    private Pokemon p14 = new Pokemon("Kakuna","#14", t5);
    private Pokemon p15 = new Pokemon("Beedrill","#15", t5);
    private Pokemon p16 = new Pokemon("Beedrill2","#16", t5);
    private Pokemon p17 = new Pokemon("Beedrill3","#17", t5);
    private Pokemon p18 = new Pokemon("Beedrill4","#18", t5);
    private Pokemon p19 = new Pokemon("Beedrill5","#19", t5);
    private Pokemon p20 = new Pokemon("Beedrill6","#20", t5);
    private Pokemon p21 = new Pokemon("Beedrill7","#20", t5);
    private Pokemon p22 = new Pokemon("Beedrill8","#20", t5);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pokedex, container, false);


        Pokemon[] pokemons = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16, p17, p18, p19, p20, p21, p22};

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPokedex);

        RecyclerView.Adapter adapter = new AdapterPokedex(getActivity(), pokemons);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);

     /*   button = view.findViewById(R.id.button);
        button.setText("Mew");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
            }
        });*/




        //GLIDE TEST
        /*ImageView image = view.findViewById(R.id.testGlide);

        Glide.with(this)
                .load("https://i.waifu.pics/V_kQGSb.jpg")
                .into(image);*/


        return view;
    }

    private void openSecondActivity(){
        Intent i = new Intent(getActivity(), Activity2.class);
        startActivity(i);
    }

}