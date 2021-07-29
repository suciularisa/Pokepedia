package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

public class Entries {

    @SerializedName("flavor_text")
    private String entry;

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
