package com.lsuciu.pokepedia;

import android.widget.LinearLayout;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonJson {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private int height;

    @SerializedName("weight")
    private int weight;

    @SerializedName("species")
    private Species species;

    @SerializedName("types")
    private List<PokemonType> types;

    @SerializedName("stats")
    private List<PokemonStats> stats;

    @SerializedName("sprites")
    private Sprite sprite;

    public List<Integer> getStatsMap(){
        List<Integer> statsMap = new ArrayList<Integer>();

        for (PokemonStats ps: stats) {
            statsMap.add(ps.getBase_stat());
        }
        return statsMap;
    }

    @Override
    public String toString() {
        String s;
        s = "PokemonJson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", types=" + types.size() +
                ", stats=" + stats.size() +
                '}';
        return s;
    }

    // Getter and Setter

    public List<String> getStringTypes(){
        List<String> result = new ArrayList<>();
        for (PokemonType pj: this.types) {
            result.add(pj.getPokemonTypeDetails().getType_name());
        }
        return result;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public List<PokemonStats> getStats() {
        return stats;
    }

    public void setStats(List<PokemonStats> stats) {
        this.stats = stats;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setImage(String url){
        this.sprite = new Sprite();
        sprite.setImage(url);
    }

}
