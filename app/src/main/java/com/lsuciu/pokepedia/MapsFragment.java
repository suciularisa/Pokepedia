package com.lsuciu.pokepedia;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private View view;

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location lastKnownLocation;
    private List<MarkerOptions> markers;

    private int MARKER_COUNT = 5;
    private float ZOOM = 18.0f;

    private Double MAX_LATITUDE = 0.0012;
    private Double MAX_LONGITUDE = 0.0009;
    List<Double> longitudes ;
    List<Double> latitudes ;
    boolean once = true;

    List<PokemonJson> pokemonJsonList;


    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        markers = new ArrayList<>();
        if(once) {
            longitudes = generateRandomNumbers(MARKER_COUNT, -MAX_LONGITUDE, MAX_LONGITUDE);
            latitudes = generateRandomNumbers(MARKER_COUNT, -MAX_LATITUDE, MAX_LATITUDE);
            once = false;
        }
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
      /*  mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);*/

        //initialize location manager and location listener
        locationManager = (LocationManager)this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // centerMapOnLocation(location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) { }

            @Override
            public void onProviderEnabled(String s) { }

            @Override
            public void onProviderDisabled(String s) { }
        };

        getLocation();

        generateCooridates(lastKnownLocation);

        //display markers
        while (pokemonJsonList == null){ }

        for (int i = 0; i < MARKER_COUNT; i++) {

            MarkerOptions currentMarker = markers.get(i);

            Glide.with(getContext()).asBitmap()
                    .load(pokemonJsonList.get(i).getSprite().getSpriteDetails().getArtwork().getArtworkUrl())
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                            mMap.addMarker(currentMarker.icon(BitmapDescriptorFactory.fromBitmap(getBitmapDescriptorFromBitmap(resource))));

                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }



        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                float[] results = new float[1];
                Location.distanceBetween(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(),
                                        marker.getPosition().latitude, marker.getPosition().longitude, results);

                Log.v("test3", String.valueOf(results[0]));

                return false;
            }
        });

    }



    private Bitmap getBitmapDescriptorFromBitmap(Bitmap res){
        View marker = getLayoutInflater().inflate(R.layout.custom_marker_layout, null);

        ImageView markerImage  = marker.findViewById(R.id.image);
        markerImage.setImageBitmap(res);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        Bitmap bitmap2 = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        marker.draw(new Canvas(bitmap2));

        return bitmap2;
    }


        private void generateCooridates(Location baseLocation){

        Double currentLatitude = baseLocation.getLatitude();
        Double currentLongitude = baseLocation.getLongitude();

        for(int i = 0; i < MARKER_COUNT; i++){
            MarkerOptions newMarker = new MarkerOptions().position(new LatLng(latitudes.get(i) + currentLatitude,
                                                                        longitudes.get(i) + currentLongitude));
            markers.add(newMarker);
        }

    }

    private List<Double> generateRandomNumbers(int size, double min, double max){

        List<Double> numbers = new ArrayList<>();

        while(numbers.size() < size){
            double number = new Random().doubles(min, max).limit(1).findFirst().getAsDouble();
            if(!numbers.contains(number)) numbers.add(number);
        }

        return numbers;
    }




    public void centerMapOnLocation(Location location){
        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, ZOOM));
        // mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude() + 0.0002, location.getLongitude())));
    }



    public void getLocation(){

        //if the permission is granted, get location
        if (ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
            lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            centerMapOnLocation(lastKnownLocation);
            mMap.setMyLocationEnabled(true);
        } else {
            //request permission
            ActivityCompat.requestPermissions(this.getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }
}
