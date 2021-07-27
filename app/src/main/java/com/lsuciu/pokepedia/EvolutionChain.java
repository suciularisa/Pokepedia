package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

public class EvolutionChain {

    @SerializedName("url")
    private String evolutionChainUrl;

    public String getEvolutionChainUrl() {
        return evolutionChainUrl;
    }

    public void setEvolutionChainUrl(String evolutionChainUrl) {
        this.evolutionChainUrl = evolutionChainUrl;
    }
}
