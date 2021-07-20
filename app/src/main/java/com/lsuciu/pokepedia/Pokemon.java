package com.lsuciu.pokepedia;

import java.util.List;

public class Pokemon {

    private String name;
    private String id;
    private List<Type> types;
    private double height;
    private double weight;

    //from the pokemon's JSON, go to the link from species section for these 3
    private int baseHappiness;
    private int captureRate;

    private String description;


    public Pokemon(String name, String id, List<Type> types) {
        this.name = name;
        this.id = id;
        this.types = types;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getBaseHappiness() {
        return baseHappiness;
    }

    public void setBaseHappiness(int baseHappiness) {
        this.baseHappiness = baseHappiness;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
