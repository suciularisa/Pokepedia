package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

public class SpriteDetails {

    @SerializedName("official-artwork")
    private Artwork artwork;

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }
}
