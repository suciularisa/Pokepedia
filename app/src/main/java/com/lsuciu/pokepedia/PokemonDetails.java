package com.lsuciu.pokepedia;

import java.util.ArrayList;
import java.util.List;

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
    List<Integer> stats;
    List<String> evolutions;
    List<Integer> evolutionsId;


    public PokemonDetails() {
        evolutions = new ArrayList<String>();
        evolutionsId = new ArrayList<Integer>();
        stats = new ArrayList<Integer>();
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

    public List<Integer> getEvolutionsId() {
        return evolutionsId;
    }

    public void setEvolutionsId(List<Integer> evolutionsId) {
        this.evolutionsId = evolutionsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getStats() {
        return stats;
    }

    public void setStats(List<Integer> stats) {
        this.stats = stats;
    }
}
