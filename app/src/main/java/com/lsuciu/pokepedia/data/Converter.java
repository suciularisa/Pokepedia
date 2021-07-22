package com.lsuciu.pokepedia.data;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.lsuciu.pokepedia.Type;

import java.util.ArrayList;
import java.util.List;

@ProvidedTypeConverter
public class Converter {

    @TypeConverter
    public static List<String> TypeToString(List<Type> types){

        return new ArrayList<>();
    }

    @TypeConverter
    public static List<Type> StringToType(List<String> name){
        return new ArrayList<>();
    }
}
