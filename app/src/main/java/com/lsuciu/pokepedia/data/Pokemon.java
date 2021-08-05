package com.lsuciu.pokepedia.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.lsuciu.pokepedia.Type;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "Pokemon")
@TypeConverters({Converter.class})
public class Pokemon implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Id")
    private int id;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Types")
    private List<Type> types;
    @ColumnInfo(name = "Image")
    private  String image;
    @ColumnInfo(name = "Description")
    private String description;
    @ColumnInfo(name = "Height")
    private int height;
    @ColumnInfo(name = "Weight")
    private int weight;
    @ColumnInfo(name = "Base_Happiness")
    private int baseHappiness;
    @ColumnInfo(name = "Capture_Rate")
    private int captureRate;
    @ColumnInfo(name = "Evolutions")
    private List<Integer> evolutions;
    @ColumnInfo(name = "Stats")
    private List<Integer> stats;


    public  Pokemon(){}

    public Pokemon(String name, int id, List<Type> types) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Integer> getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(List<Integer> evolutions) {
        this.evolutions = evolutions;
    }

    public List<Integer> getStats() {
        return stats;
    }

    public void setStats(List<Integer> stats) {
        this.stats = stats;
    }
}
