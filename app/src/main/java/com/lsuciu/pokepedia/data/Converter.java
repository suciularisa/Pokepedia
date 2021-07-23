package com.lsuciu.pokepedia.data;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lsuciu.pokepedia.Type;

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


/*
    @TypeConverter
    public static List<String> fromTypeToString(List<Type> types){

        ArrayList<String> strings = new ArrayList<>();
        for (Type t: types
             ) {
            strings.add(new Gson().toJson(t));
        }
        return strings;
    }

    @TypeConverter
    public static List<Type> fromStringToType(List<String> names){
        ArrayList<Type> types = new ArrayList<>();
        for (String n: names
        ) {
            types.add(new Gson().fromJson(n,Type.class));
        }
        return types;
    }*/
}
