package com.lsuciu.pokepedia.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert
    void insertPokemon(Pokemon pokemon);

    @Update
    void updatePokemon(Pokemon pokemon);

    @Delete
    void deletePokemon(Pokemon pokemon);

    @Query("Select * from Pokemon")
    List<Pokemon> getPokemons();
}
