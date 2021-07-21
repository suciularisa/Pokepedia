package com.lsuciu.pokepedia;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapterMainActivity extends FragmentStateAdapter {

    private int NR_FRAGMENTS = 2;
    private ArrayList<Fragment> fragments;

    public ViewPagerAdapterMainActivity(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragments) {
        super(fragmentActivity);
        this.fragments = fragments;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return fragments.get(position);
       /* Log.v("POS",position + " ");
        switch (position){
            case 0 : return new PokedexFragment();
            default: return new PoketeamFragment();
        }*/
    }

    public String getFragmentTitle(int position){
        switch (position){
            case 0 : return PokedexFragment.title;
            default : return PoketeamFragment.title;
        }
    }

    @Override
    public int getItemCount() {
        return NR_FRAGMENTS;
    }
}
