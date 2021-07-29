package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpeciesDetails {

    @SerializedName("base_happiness")
    private int happiness;

    @SerializedName("capture_rate")
    private int captureRate;

    @SerializedName("evolution_chain")
    private EvolutionChain evolutionChain;

    @SerializedName("flavor_text_entries")
    private List<Entries> entries;


    public List<Entries> getEntries() {
        return entries;
    }

    public void setEntries(List<Entries> entries) {
        this.entries = entries;
    }

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
