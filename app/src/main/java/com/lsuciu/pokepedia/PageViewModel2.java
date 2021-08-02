package com.lsuciu.pokepedia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class PageViewModel2 extends ViewModel {

    private MutableLiveData<List<PokemonData>> pokemon = new MutableLiveData<>();

    public void setPokemons(List<PokemonData> name) {
        pokemon.setValue(name);
    }
    public LiveData<List<PokemonData>> getPokemons() {
        return pokemon;
    }
}