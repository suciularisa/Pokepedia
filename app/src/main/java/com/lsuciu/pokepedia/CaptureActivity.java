package com.lsuciu.pokepedia;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class CaptureActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.capture_activity);

        FragmentManager manager = getSupportFragmentManager();
        CaptureDialog captureDialog = CaptureDialog.getInstance();
       // captureDialog.setPokemonDetails(PokemonInstance.getPokemonJson(), PokemonInstance.getCoordinates());
        captureDialog.show(manager, "CaptureDialog");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
