package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

public class PokemonTypeDetails {

    @SerializedName("name")
    private String type_name;

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }


}
