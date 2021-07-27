package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

public class PokemonStatsDetails {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
