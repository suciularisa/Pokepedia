package com.lsuciu.pokepedia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.maps.model.LatLng;
import com.lsuciu.pokepedia.data.Pokemon;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    private static HashMap<Integer, PokemonJson> pokemonJsonList = new HashMap<>();
    private static HashMap<Integer, LatLng> coordinatesList = new HashMap<>();
    public static void addPokemon(PokemonJson pokemonJson, int index, LatLng coordinates){
        pokemonJsonList.put(index, pokemonJson);
        coordinatesList.put(index, coordinates);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        //Toast.makeText(context, "Geofence triggered...", Toast.LENGTH_SHORT).show();
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if(geofencingEvent.hasError()){
            Log.e("test3", String.valueOf(geofencingEvent.getErrorCode()));
            Toast.makeText(context, "Geofencing event has error", Toast.LENGTH_SHORT).show();
        }else{

            List <Geofence> triggerList = geofencingEvent.getTriggeringGeofences();

            String[] triggerIds = new String[triggerList.size()];

            for (int i = 0; i < triggerIds.length; i++) {
                triggerIds[i] = triggerList.get(i).getRequestId();

                PokemonJson pokemon = pokemonJsonList.get(Integer.valueOf(triggerIds[i]));
                LatLng coordinates = coordinatesList.get(Integer.valueOf(triggerIds[i]));
                PokemonInstance pokemonInstance = new PokemonInstance();
                pokemonInstance.setCoordinates(coordinates);
                pokemonInstance.setPokemonJson(pokemon);
                EventBus.getDefault().post(pokemonInstance);
            }
        }
    }
}