package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

public class PokemonType {

    @SerializedName("type")
    private PokemonTypeDetails pokemonTypeDetails;

    public PokemonTypeDetails getPokemonTypeDetails() {
        return pokemonTypeDetails;
    }

    public void setPokemonTypeDetails(PokemonTypeDetails pokemonTypeDetails) {
        this.pokemonTypeDetails = pokemonTypeDetails;
    }


    @Override
    public String toString() {
        return "PokemonType{" +
                "pokemonTypeDetails=" + pokemonTypeDetails.getType_name() +
                '}';
    }


}
