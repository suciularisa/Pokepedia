package com.lsuciu.pokepedia;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.lsuciu.pokepedia.data.CapturedPokemon;
import com.lsuciu.pokepedia.data.CapturedPokemonDatabase;


import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class ResultDialog extends DialogFragment {


    private CapturedPokemon capturedPokemon;

    CapturedPokemonDatabase capturedPokemonDatabase;

    public ResultDialog() {
        // Required empty public constructor
    }

    public static ResultDialog getInstance(){
        ResultDialog fragment = new ResultDialog();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View view = inflater.inflate(R.layout.capture_result_dialog, container, false);

        //close window
        TextView closeButton = view.findViewById(R.id.dialog_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        TextView dialogText = view.findViewById(R.id.dialog_text);
        GifImageView gif = view.findViewById(R.id.dialog_gif);


        dialogText.setText("Captured " + capturedPokemon.getName() + " successfully");
        savePokemon();
        gif.setBackgroundResource(R.drawable.catch_animation);

        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        DialogFragment parent = (DialogFragment) getParentFragment();
        parent.dismiss();
    }

    public void setCapturedPokemon(CapturedPokemon capturedPokemon) {
        this.capturedPokemon = capturedPokemon;
    }


    private void savePokemon(){
        capturedPokemonDatabase = CapturedPokemonDatabase.getInstance(getContext());
        capturedPokemonDatabase.capturedPokemonDao().insertPokemon(capturedPokemon);
    }
}
