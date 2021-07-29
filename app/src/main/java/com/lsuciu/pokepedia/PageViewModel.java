package com.lsuciu.pokepedia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private MutableLiveData<PokemonDetails> pokemon = new MutableLiveData<>();

    public void setPokemon(PokemonDetails name) {
        pokemon.setValue(name);
    }
    public LiveData<PokemonDetails> getPokemon() {
        return pokemon;
    }
}
