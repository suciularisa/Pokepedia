package com.lsuciu.pokepedia.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = SavedPokemon.class, version = 1, exportSchema = false)
public abstract class SavedPokemonDatabase extends RoomDatabase {

    private static final String DB_NAME = "saved_pokemons_db";
    private static SavedPokemonDatabase instance;

    public abstract SavedPokemonDao SavedPokemonDao();

    public static synchronized SavedPokemonDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), SavedPokemonDatabase.class, DB_NAME)
                    //.fallbackToDestructiveMigration()
                     .allowMainThreadQueries()
                        .build();
            }
        return instance;
    }

}
