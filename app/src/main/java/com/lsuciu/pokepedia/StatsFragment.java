package com.lsuciu.pokepedia;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.progressindicator.LinearProgressIndicator;


public class StatsFragment extends Fragment {

    public static final String title = "STATS";



    public StatsFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        LinearProgressIndicator progress_hp = view.findViewById(R.id.progress_hp);
        progress_hp.setMax(100);
        progress_hp.setProgress(44);
        int color = ContextCompat.getColor(this.getContext(), R.color.fire);
        progress_hp.setIndicatorColor(color);
        return view;
    }
}