package com.lsuciu.pokepedia.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Pokemon.class, exportSchema = false, version = 1)
@TypeConverters({Converter.class})
public abstract class PokemonDatabase extends RoomDatabase {

    private static final String DB_NAME = "pokemon_db";
    private static PokemonDatabase instance;


    public static synchronized PokemonDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), PokemonDatabase.class, DB_NAME)
                        .allowMainThreadQueries()
                        .addTypeConverter(Converter.class)
                        .build();
        }
        return instance;
    }

    public abstract PokemonDao pokemonDao();
}
