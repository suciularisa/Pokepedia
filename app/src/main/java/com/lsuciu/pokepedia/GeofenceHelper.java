package com.lsuciu.pokepedia;

import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

public class GeofenceHelper extends ContextWrapper {

    PendingIntent pendingIntent;
    private HashMap<Integer, PokemonJson> pokemonJsonList;
    private HashMap<Integer, LatLng> coordinatesList;

    public void addPokemon(PokemonJson pokemonJson, int index, LatLng coordinates){
        pokemonJsonList.put(index, pokemonJson);
        coordinatesList.put(index, coordinates);
        GeofenceBroadcastReceiver.addPokemon(pokemonJson, index, coordinates);
    }

    public GeofenceHelper(Context base) {
        super(base);
        pokemonJsonList = new HashMap<>();
        coordinatesList = new HashMap<>();
    }


    public GeofencingRequest getGeofencingRequest(Geofence geofence){
        return new GeofencingRequest.Builder()
                .addGeofence(geofence)
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_DWELL)
                .build();
    }


    public Geofence getGeofence(String id, LatLng latLng, float radius, int trnasitionTypes){
        return new Geofence.Builder()
                .setCircularRegion(latLng.latitude, latLng.longitude, radius)
                .setRequestId(id)
                .setTransitionTypes(trnasitionTypes)
                .setLoiteringDelay(1000)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();
    }

    public PendingIntent getPendingIntent(){
        if(pendingIntent == null){
            Intent intent = new Intent(this, GeofenceBroadcastReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 2021, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        return pendingIntent;

    }

    public String getError(Exception e){
        return e.getLocalizedMessage();
    }


}
