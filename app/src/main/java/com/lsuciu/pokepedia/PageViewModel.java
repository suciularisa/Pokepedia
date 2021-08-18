package com.lsuciu.pokepedia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lsuciu.pokepedia.data.Pokemon;

import java.util.List;

public class PageViewModel extends ViewModel {

    private MutableLiveData<PokemonDetails> pokemon = new MutableLiveData<>();
    private MutableLiveData<List<PokemonData>> pokemons = new MutableLiveData<>();
    private MutableLiveData<Pokemon> savePokemon = new MutableLiveData<>();
    private MutableLiveData<List<PokemonJson>> pokemonJsonList = new MutableLiveData<>();
    private Integer label;
    private Integer pokemonId;

    public void setPokemons(List<PokemonData> name) {
        pokemons.setValue(name);
    }
    public LiveData<List<PokemonData>> getPokemons() {
        return pokemons;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer id) {
        this.label = id;
    }

    public Integer getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Integer pokemonId) {
        this.pokemonId = pokemonId;
    }

    public void setPokemon(PokemonDetails name) {
        pokemon.setValue(name);
    }
    public LiveData<PokemonDetails> getPokemon() {
        return pokemon;
    }

    public void setSavePokemon(Pokemon pokemon){ this.savePokemon.setValue(pokemon);}
    public  LiveData<Pokemon> getSavePokemon(){return savePokemon;}


    public void setPokemonsJson(List<PokemonJson> pokemons) {
        pokemonJsonList.setValue(pokemons);
    }
    public LiveData<List<PokemonJson>> getPokemonsJson() {
        return pokemonJsonList;
    }
}
