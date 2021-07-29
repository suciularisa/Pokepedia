package com.lsuciu.pokepedia;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chain {

    @SerializedName("evolves_to")
    private List<Chain> chain2;

    @SerializedName("species")
    private Species species;


    public List<Chain> getChain2() {
        return chain2;
    }

    public void setChain2(List<Chain> chain2) {
        this.chain2 = chain2;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }



    public List<String> getSpeciesName(List<String> names){

        if(chain2.isEmpty()){
            names.add(species.getName());
            return names;
        }
        names.add(species.getName());
        return chain2.get(0).getSpeciesName(names);
    }


    public List<String> getSpeciesURL(List<String> urls){

        if(chain2.isEmpty()){
            urls.add(species.getUrl());
            return urls;
        }
        urls.add(species.getUrl());
        return chain2.get(0).getSpeciesURL(urls);
    }

}
