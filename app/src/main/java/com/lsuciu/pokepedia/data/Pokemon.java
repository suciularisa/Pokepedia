package com.lsuciu.pokepedia.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.lsuciu.pokepedia.Type;

import java.util.List;

@Entity(tableName = "Pokemon")
@TypeConverters({Converter.class})
public class Pokemon {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Id")
    private String id;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Types")
    private List<Type> types;

    //private double height;
   // private double weight;

    //from the pokemon's JSON, go to the link from species section for these 3
   /* private int baseHappiness;
    private int captureRate;

    private String description;*/


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

   /* public double getHeight() {
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
    }*/
}
