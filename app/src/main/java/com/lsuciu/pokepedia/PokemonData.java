package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;
import com.lsuciu.pokepedia.data.Pokemon;

import java.io.Serializable;
import java.util.List;

public class PokemonData implements Serializable {
    private int id;
    private String name;
    private List<Type> types;
    private String image;

    public PokemonData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        String result = (String) name.subSequence(0,1);
        result = result.toUpperCase();
        result = result.concat(name.substring(1));
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
