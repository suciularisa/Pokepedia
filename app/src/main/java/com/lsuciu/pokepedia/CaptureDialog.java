package com.lsuciu.pokepedia;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.lsuciu.pokepedia.data.CapturedPokemon;
import com.lsuciu.pokepedia.data.CapturedPokemonDatabase;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class CaptureDialog extends DialogFragment {


    private CapturedPokemon capturedPokemon;
   // CapturedPokemonDatabase capturedPokemonDB = CapturedPokemonDatabase.getInstance(this.getContext());


    public CaptureDialog() {
        // Required empty public constructor
    }


    public void setPokemonDetails(PokemonJson pokemonJson, LatLng coordinates){
        capturedPokemon = new CapturedPokemon();
        capturedPokemon.setId(pokemonJson.getId());
        capturedPokemon.setName(pokemonJson.getName());
        capturedPokemon.setImage(pokemonJson.getSprite().getSpriteDetails().getArtwork().getArtworkUrl());
        List<Type> types = new ArrayList<>();
        for (PokemonType pt: pokemonJson.getTypes()) {
            types.add(Type.valueOf(pt.getPokemonTypeDetails().getType_name().toUpperCase()));
        }
        capturedPokemon.setTypes(types);
        capturedPokemon.setLocation(new ArrayList<>(Arrays.asList(46.789, 46.889)));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        capturedPokemon.setDate(LocalDateTime.now());
        capturedPokemon.setLocation(new ArrayList<>(Arrays.asList(coordinates.latitude, coordinates.longitude)));
    }


    public static CaptureDialog getInstance(){
        CaptureDialog fragment = new CaptureDialog();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setCancelable(false);
        View view = inflater.inflate(R.layout.capture_pokemon_dialog, container, false);

        ImageView pokemonImage = view.findViewById(R.id.dialog_image);
        Glide.with(getContext())
                .load(capturedPokemon.getImage())
                .into(pokemonImage);

        TextView title = view.findViewById(R.id.dialog_text);
        title.setText("Catch " + capturedPokemon.getName() + " ?");


        ImageView gif = view.findViewById(R.id.pokeball_animation);

        //close window
        TextView closeButton = view.findViewById(R.id.dialog_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        GifDrawable gifDrawable = null;
        try {
            gifDrawable = new GifDrawable( getResources(), R.drawable.pokeball_animation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GifDrawable finalGifDrawable = gifDrawable;

        //catch pokemon
        TextView catchButton = view.findViewById(R.id.dialog_catch);


        catchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeButton.setEnabled(false);
                catchButton.setEnabled(false);

                Animation fadeout = new AlphaAnimation(1f, 1f);

                fadeout.setDuration(finalGifDrawable.getDuration());
                fadeout.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        gif.setBackgroundResource(R.drawable.pokeball_animation);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        FragmentManager manager = getChildFragmentManager();
                        ResultDialog resultDialog = ResultDialog.getInstance();
                        resultDialog.setCancelable(false);
                        resultDialog.setCapturedPokemon(capturedPokemon);
                        resultDialog.show(manager, null);
                        getDialog().hide();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                gif.startAnimation(fadeout);
            }
        });

        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

    }
}
