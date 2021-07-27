package com.lsuciu.pokepedia;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import org.w3c.dom.Text;


public class StatsFragment extends Fragment {

    public static final String title = "STATS";
    private static final String MY_SHARED_PREFERENCES = "MySharedPrefs" ;
    private int color, lighterColor;



    public StatsFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stats, container, false);


        SharedPreferences preferences = getActivity().getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        this.color = preferences.getInt("currentColor",0);
        this.lighterColor = preferences.getInt("lighterColor",0);

        LinearProgressIndicator progress_hp = view.findViewById(R.id.progress_hp);
        progress_hp.setMax(100);
        progress_hp.setProgress(44);

        /*Drawable initialProgressBar = AppCompatResources.getDrawable(this.getContext(), R.drawable.progress_bar);
        Drawable progressBar = DrawableCompat.wrap(initialProgressBar);
        DrawableCompat.setTint(progressBar, color);
        */
        progress_hp.setIndicatorColor(color);



        View line1 = view.findViewById(R.id.line1);
        line1.setBackgroundColor(lighterColor);
        View line2 = view.findViewById(R.id.line2);
        line2.setBackgroundColor(lighterColor);
        View line3 = view.findViewById(R.id.line3);
        line3.setBackgroundColor(lighterColor);
        View line4 = view.findViewById(R.id.line4);
        line4.setBackgroundColor(lighterColor);
        View line5 = view.findViewById(R.id.line5);
        line5.setBackgroundColor(lighterColor);
        View line6 = view.findViewById(R.id.line6);
        line6.setBackgroundColor(lighterColor);

        TextView total_tg = view.findViewById(R.id.total_tag);
        total_tg.setTextColor(color);


        return view;
    }
}