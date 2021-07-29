package com.lsuciu.pokepedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonDetails {

    private int id;
    private String name;
    private List<Type> types;
    private String image;
    private String description;
    private int height;
    private int weight;
    private int happiness;
    private int capture;
    Map<String, Integer> stats;
    List<String> evolutions;
    List<String> evolutionsUrl;


    public PokemonDetails() {
        evolutions = new ArrayList<String>();
        evolutionsUrl = new ArrayList<String>();
        stats = new HashMap<String, Integer>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getCapture() {
        return capture;
    }

    public void setCapture(int capture) {
        this.capture = capture;
    }

    public List<String> getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(List<String> evolutions) {
        this.evolutions = evolutions;
    }

    public List<String> getEvolutionsUrl() {
        return evolutionsUrl;
    }

    public void setEvolutionsUrl(List<String> evolutionsUrl) {
        this.evolutionsUrl = evolutionsUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }
}
