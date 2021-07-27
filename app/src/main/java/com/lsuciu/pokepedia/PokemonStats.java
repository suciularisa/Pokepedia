package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

public class PokemonStats {

        @SerializedName("base_stat")
        private int base_stat;

        @SerializedName("stat")
        private PokemonStatsDetails pokemonStatsDetails;


    public PokemonStatsDetails getPokemonStatsDetails() {
        return pokemonStatsDetails;
    }

    public void setPokemonStatsDetails(PokemonStatsDetails pokemonStatsDetails) {
        this.pokemonStatsDetails = pokemonStatsDetails;
    }

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    @Override
    public String toString() {
        return "PokemonStats{" +
                "base_stat=" + base_stat +
                ", pokemonStatsDetails=" + pokemonStatsDetails.getName() +
                '}';
    }
}
