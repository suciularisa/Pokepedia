package com.lsuciu.pokepedia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapDialog extends DialogFragment implements OnMapReadyCallback {

    private View view;
    private GoogleMap mMap;
    private String imageUrl;
    private LatLng location;

    public MapDialog() {
        // Required empty public constructor
    }

    public static MapDialog getInstance(){
        MapDialog fragment = new MapDialog();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.popup, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment)
                this.getChildFragmentManager().findFragmentById(R.id.popup_map);
        mapFragment.getMapAsync(this);

        TextView closeButton = view.findViewById(R.id.popup_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        centerMapOnLocation();
    }


    public void centerMapOnLocation(){

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location).title("Pokemon");

        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        mMap.addMarker(markerOptions.icon(BitmapDescriptorFactory.fromBitmap(getBitmapDescriptorFromBitmap(resource))));
                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                    }
                });
    }

    private Bitmap getBitmapDescriptorFromBitmap(Bitmap image){
        View marker = getLayoutInflater().inflate(R.layout.custom_marker_layout, null);

        ImageView markerImage  = marker.findViewById(R.id.image);
        markerImage.setImageBitmap(image);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        Bitmap bitmap2 = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        marker.draw(new Canvas(bitmap2));

        return bitmap2;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public void setLocation(LatLng location) {
        this.location = location;
    }
}
