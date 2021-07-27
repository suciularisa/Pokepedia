package com.lsuciu.pokepedia;

import com.google.gson.annotations.SerializedName;

public class EvolutionJson {
    @SerializedName("chain")
    private Chain chain;

    public Chain getChain() {
        return chain;
    }

    public void setChain(Chain chain) {
        this.chain = chain;
    }
}
