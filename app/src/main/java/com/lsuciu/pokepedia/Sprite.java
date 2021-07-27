package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

public class Sprite {

    @SerializedName("other")
    private SpriteDetails spriteDetails;

    public SpriteDetails getSpriteDetails() {
        return spriteDetails;
    }

    public void setSpriteDetails(SpriteDetails spriteDetails) {
        this.spriteDetails = spriteDetails;
    }
}
