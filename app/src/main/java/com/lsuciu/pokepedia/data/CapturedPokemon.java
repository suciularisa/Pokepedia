package com.lsuciu.pokepedia.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.lsuciu.pokepedia.Type;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Entity(tableName = "Captured Pokemons")
@TypeConverters({Converter.class})
public class CapturedPokemon {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "DatabaseId")
    private int database_id;
    @NonNull
    @ColumnInfo(name = "Id")
    private int id;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Types")
    private List<Type> types;
    @ColumnInfo(name = "Image")
    private  String image;
    @ColumnInfo(name = "Location")
    private List<Double> location;
    @ColumnInfo(name = "Date")
    private LocalDateTime date;

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

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDateFormated(){
        StringBuilder sb = new StringBuilder();

        sb.append(date.getYear() + "/" + date.getMonthValue() + "/" + date.getDayOfMonth() + " ");
        if(date.getHour() < 10){
            sb.append("0" + date.getHour() + ":");
        }else sb.append(date.getHour() + ":");

        if(date.getMinute() < 10){
            sb.append("0" + date.getMinute());
        }else sb.append(date.getMinute());

        return sb.toString();
    }

    public int getDatabase_id() {
        return database_id;
    }

    public void setDatabase_id(int database_id) {
        this.database_id = database_id;
    }
}
