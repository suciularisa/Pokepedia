package com.lsuciu.pokepedia;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("pokemon/{id}")
    Call<PokemonJson> getPokemon(@Path("id") int id);


    @GET("pokemon-species/{id}")
    Call<SpeciesDetails> getSpeciesDetails(@Path("id") int id);

    @GET(".")
    Call<EvolutionJson> getEvolutionChain();
}
