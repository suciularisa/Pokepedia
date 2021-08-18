package com.lsuciu.pokepedia.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = CapturedPokemon.class, exportSchema = false, version = 1)

@TypeConverters(Converter.class)
public abstract class CapturedPokemonDatabase extends RoomDatabase {

    private static final String DB_NAME = "captured_pokemon_db";
    private static CapturedPokemonDatabase instance;

    public static synchronized CapturedPokemonDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CapturedPokemonDatabase.class, DB_NAME)
                    //.fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract CapturedPokemonDao capturedPokemonDao();
}
