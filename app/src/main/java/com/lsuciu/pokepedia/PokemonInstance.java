package com.lsuciu.pokepedia;

import com.google.android.gms.maps.model.LatLng;

public class PokemonInstance {

     PokemonJson pokemonJson;

     LatLng coordinates;

    public  PokemonJson getPokemonJson() {
        return pokemonJson;
    }

    public void setPokemonJson(PokemonJson pokemonJson) {
        this.pokemonJson = pokemonJson;
    }

    public  LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }
}
