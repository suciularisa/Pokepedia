package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

public class SpeciesDetails {

    @SerializedName("base_happiness")
    private int happiness;

    @SerializedName("capture_rate")
    private int captureRate;

    @SerializedName("evolution_chain")
    private EvolutionChain evolutionChain;



    public EvolutionChain getEvolutionChain() {
        return evolutionChain;
    }

    public void setEvolutionChain(EvolutionChain evolutionUrl) {
        this.evolutionChain = evolutionUrl;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }


}
