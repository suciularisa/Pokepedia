package com.lsuciu.pokepedia.data;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lsuciu.pokepedia.Type;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ProvidedTypeConverter
public class Converter {

    @TypeConverter
    public static List<Type> fromStringToTypes(String field){

        List<String> dbValues = new ArrayList<>(Arrays.asList(field.split(",")));
        List<Type> types = new ArrayList<>();

        for (String value: dbValues
             ) {
            types.add(Type.valueOf(value.toUpperCase()));
        }
        return types;
    }


    @TypeConverter
    public static String fromTypesToString(List<Type> types) {
        StringBuilder sb = new StringBuilder();

        for (Type t: types
             ) {
            sb.append(t.getName() + ",");
        }
        return sb.toString();
    }


    @TypeConverter
    public static List<Integer> fromStringToStats(String statsString){
        List<Integer> stats = new ArrayList<>();
        List<String> tokens = Arrays.asList(statsString.split(","));

        for (String stat: tokens) {
            if(stat.equals("")) continue;
            stats.add(Integer.valueOf(stat));
        }
        return stats;
    }

    @TypeConverter
    public static String fromStatsToString(List<Integer> stats){
        StringBuilder sb = new StringBuilder();

        for (Integer stat: stats) {
            sb.append(stat + ",");
        }

        return sb.toString();
    }



    @TypeConverter
    public static List<Double> fromStringToLocation(String locationString){
        List<Double> location = new ArrayList<>();
        List<String> tokens = Arrays.asList(locationString.split(","));

        for (String stat: tokens) {
            location.add(Double.valueOf(stat));
        }
        return location;
    }

    @TypeConverter
    public static String fromLocationToString(List<Double> location){
        StringBuilder sb = new StringBuilder();

        for (Double coordinate: location) {
            sb.append(coordinate + ",");
        }

        return sb.toString();
    }

    @TypeConverter
    public static LocalDateTime toDate(String dateString) {
        if (dateString == null) {
            return null;
        } else {
            return LocalDateTime.parse(dateString);
        }
    }

    @TypeConverter
    public static String toDateString(LocalDateTime date) {
        if (date == null) {
            return null;
        } else {
            return date.toString();
        }
    }

}
