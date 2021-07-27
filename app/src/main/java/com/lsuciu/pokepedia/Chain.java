package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

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
}
