package com.lsuciu.pokepedia.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import retrofit2.http.Path;

@Dao
public interface SavedPokemonDao {

    @Insert
    void savePokemon(SavedPokemon savedPokemon);

    @Query("Select * from SavedPokemon where id = :idPokemon")
    SavedPokemon getFromId(int idPokemon);

    @Query("Delete from SavedPokemon where id = :idPokemon")
    void deleteFromId(int idPokemon);
}
