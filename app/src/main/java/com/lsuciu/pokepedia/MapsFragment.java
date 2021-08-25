package com.lsuciu.pokepedia;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.lsuciu.pokepedia.data.Pokemon;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private View view;

    private GeofencingClient geofencingClient;
    private GeofenceHelper geofenceHelper;
    private float GEOFENCE_RADIUS = 20.0f;

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location lastKnownLocation;
    private List<MarkerOptions> markerOptionsList;
    private List<Marker> markersList;

    private int MARKER_COUNT = 5;
    private float ZOOM = 18.0f;

    private Double MAX_LATITUDE = 0.0012;
    private Double MAX_LONGITUDE = 0.0009;
    List<Double> longitudes;
    List<Double> latitudes;
    boolean once = true;

    List<PokemonJson> pokemonJsonList;


    private LocationRequest locationRequest;
    private FusedLocationProviderClient client;
    private LocationCallback locationCallback;
    Marker currentLocation;

    List<Geofence> geofenceList;
    private PokemonJson testPokemon;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        markerOptionsList = new ArrayList<>();
        markersList = new ArrayList<>();
        if (once) {
            longitudes = generateRandomNumbers(MARKER_COUNT, -MAX_LONGITUDE, MAX_LONGITUDE);
            latitudes = generateRandomNumbers(MARKER_COUNT, -MAX_LATITUDE, MAX_LATITUDE);
            once = false;
        }

        geofencingClient = LocationServices.getGeofencingClient(this.getContext());
        geofenceHelper = new GeofenceHelper(this.getContext());

        init();
    }

    private void init(){
        testPokemon = new PokemonJson();
        testPokemon.setName("Venusaur");
        testPokemon.setId(3);
        testPokemon.setImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png");
        PokemonTypeDetails type1 = new PokemonTypeDetails();
        type1.setType_name(Type.GRASS.getName());
        PokemonTypeDetails type2 = new PokemonTypeDetails();
        type2.setType_name(Type.POISON.getName());
        PokemonType pt1 = new PokemonType();
        pt1.setPokemonTypeDetails(type1);
        PokemonType pt2 = new PokemonType();
        pt2.setPokemonTypeDetails(type2);
        testPokemon.setTypes(new ArrayList<>(Arrays.asList(pt1,pt2)));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_maps, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;

        //initialize location manager and location listener
        getLocation();

        generateCooridates(lastKnownLocation);

        //display markers
        while (pokemonJsonList == null) {
        }

        for (int i = 0; i < MARKER_COUNT; i++) {

            MarkerOptions currentMarker = markerOptionsList.get(i);

            int aux = i;
            Glide.with(getContext()).asBitmap()
                    .load(pokemonJsonList.get(i).getSprite().getSpriteDetails().getArtwork().getArtworkUrl())
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            Marker newMarker;
                            newMarker = mMap.addMarker(currentMarker.icon(BitmapDescriptorFactory.fromBitmap(getBitmapDescriptorFromBitmap(resource)))
                                    .title(String.valueOf(aux)));
                            mMap.addCircle(new CircleOptions().center(currentMarker.getPosition()).radius(GEOFENCE_RADIUS)
                                    .strokeColor(Color.argb(255, 255, 0, 0))
                                    .fillColor(Color.argb(64, 255, 0, 0)));

                           addGeofence(newMarker, pokemonJsonList.get(aux), aux, markerOptionsList.get(aux).getPosition());
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });
        }

        //TestMarker
        LatLng testCoordinates = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude() + 0.000013);
        MarkerOptions testMarker = new MarkerOptions().title("5").position(testCoordinates);
        Marker testMarkerr = mMap.addMarker(testMarker);
        mMap.addCircle(new CircleOptions().center(testMarker.getPosition()).radius(GEOFENCE_RADIUS)
                .strokeColor(Color.argb(255, 255, 0, 0))
                .fillColor(Color.argb(64, 255, 0, 0)));
        markerOptionsList.add(testMarker);
        markersList.add(testMarkerr);
        pokemonJsonList.add(testPokemon);
        addGeofence(testMarkerr, testPokemon, 5, testCoordinates);




       /* mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                float[] distance = new float[1];
                Location.distanceBetween(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(),
                        marker.getPosition().latitude, marker.getPosition().longitude, distance);
                Toast.makeText(getContext(), "Distance: " + String.valueOf(distance[0]), Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PokemonInstance pokemonInstance){
        FragmentManager manager = getChildFragmentManager();
        CaptureDialog captureDialog = CaptureDialog.getInstance();
         captureDialog.setPokemonDetails(pokemonInstance.getPokemonJson(), pokemonInstance.getCoordinates());
        captureDialog.show(manager, "CaptureDialog");

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void addGeofence(Marker marker, PokemonJson pokemonJson, int index, LatLng coordinates) {
        Geofence geofence = geofenceHelper.getGeofence(marker.getTitle(), marker.getPosition(), GEOFENCE_RADIUS,  Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_DWELL);
        GeofencingRequest geofencingRequest = geofenceHelper.getGeofencingRequest(geofence);
        PendingIntent pendingIntent = geofenceHelper.getPendingIntent();

        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("test3", "Permission not granted");
            return;
        }
        geofencingClient.addGeofences(geofencingRequest, pendingIntent).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.e("test3", "Geofence Added");
                geofenceHelper.addPokemon(pokemonJson,  index, coordinates);
                //Toast.makeText(getContext(), "Geofence " + marker.getTitle() + " added", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("test3", geofenceHelper.getError(e));
                    }
                });

    }

    private Bitmap getBitmapDescriptorFromBitmap(Bitmap res) {
        View marker = getLayoutInflater().inflate(R.layout.custom_marker_layout, null);

        ImageView markerImage = marker.findViewById(R.id.image);
        markerImage.setImageBitmap(res);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        Bitmap bitmap2 = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        marker.draw(new Canvas(bitmap2));

        return bitmap2;
    }







    private void generateCooridates(Location baseLocation) {

        Double currentLatitude = baseLocation.getLatitude();
        Double currentLongitude = baseLocation.getLongitude();

        for (int i = 0; i < MARKER_COUNT; i++) {
            MarkerOptions newMarker = new MarkerOptions().position(new LatLng(latitudes.get(i) + currentLatitude,
                    longitudes.get(i) + currentLongitude));
            markerOptionsList.add(newMarker);
        }

    }



    private List<Double> generateRandomNumbers(int size, double min, double max) {

        List<Double> numbers = new ArrayList<>();

        while (numbers.size() < size) {
            double number = new Random().doubles(min, max).limit(1).findFirst().getAsDouble();
            if (!numbers.contains(number)) numbers.add(number);
        }

        return numbers;
    }


    public void centerMapOnLocation(Location location) {
        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, ZOOM));
    }


    public void getLocation(){

        //if the permission is granted, get location
        if (ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager = (LocationManager)this.getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    lastKnownLocation = location;
                }
            });

            lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            centerMapOnLocation(lastKnownLocation);
            mMap.setMyLocationEnabled(true);
        } else {
            //request permission
            ActivityCompat.requestPermissions(this.getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            while(ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            }
            getLocation();
        }
    }


}
