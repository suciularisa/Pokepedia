package com.lsuciu.pokepedia.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SavedPokemon")
public class SavedPokemon {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;


    public SavedPokemon(){}

    public SavedPokemon(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
