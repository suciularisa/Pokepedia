package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvolutionJson {
    @SerializedName("chain")
    private Chain chain;

    public Chain getChain() {
        return chain;
    }

    public void setChain(Chain chain) {
        this.chain = chain;
    }

    public List<String> getSpeciesNames(){
        List<String> names = new ArrayList<String>();
        return chain.getSpeciesName(names);
    }

    public List<String> getSpeciesURLS(){
        List<String> urls = new ArrayList<String>();
        return chain.getSpeciesURL(urls);
    }




}
