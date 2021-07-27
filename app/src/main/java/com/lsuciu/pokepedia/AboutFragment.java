package com.lsuciu.pokepedia;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutFragment extends Fragment {

    public static final String title = "ABOUT";
    private static final String MY_SHARED_PREFERENCES = "MySharedPrefs" ;
    private int color, lighterColor;

    public AboutFragment() { }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        SharedPreferences preferences = getActivity().getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        color = preferences.getInt("currentColor",0);
        lighterColor = preferences.getInt("lighterColor",0);

        TextView id_tag = (TextView) view.findViewById(R.id.id_tag);
        id_tag.setTextColor(color);
        TextView height_tag = (TextView) view.findViewById(R.id.height_tag);
        height_tag.setTextColor(color);
        TextView weight_tag = (TextView) view.findViewById(R.id.weight_tag);
        weight_tag.setTextColor(color);
        TextView hapiness_tag = (TextView) view.findViewById(R.id.hapiness_tag);
        hapiness_tag.setTextColor(color);
        TextView capture_tag = (TextView) view.findViewById(R.id.capture_tag);
        capture_tag.setTextColor(color);

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


        return view;
    }
}