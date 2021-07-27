package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

public class Artwork {

    @SerializedName("front_default")
    private String artworkUrl;

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }
}
