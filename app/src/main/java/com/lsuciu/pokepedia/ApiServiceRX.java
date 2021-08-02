package com.lsuciu.pokepedia;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiServiceRX {
    @GET("pokemon/{id}")
    Observable<PokemonJson> getPokemon(@Path("id") int id);

    @GET("pokemon-species/{id}")
    Observable<SpeciesDetails> getSpeciesDetails(@Path("id") int id);

    @GET
    Observable<EvolutionJson> getEvolutionChain(@Url String url);

    @GET("pokemon/{name}")
    Observable<PokemonJson> getPokemon(@Path("name") String name);

}
