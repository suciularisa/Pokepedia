package com.lsuciu.pokepedia;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterActivity2 extends FragmentStateAdapter {

    private int NR_FRAGMENTS = 3;

    public ViewPagerAdapterActivity2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new AboutFragment();
            case 1 : return new StatsFragment();
            default: return new EvolutionsFragment();
        }
    }

    public String getFragmentTitle(int position){
        switch (position){
            case 0 : return AboutFragment.title;
            case 1 : return StatsFragment.title;
            default: return EvolutionsFragment.title;
        }
    }

    @Override
    public int getItemCount() {
        return NR_FRAGMENTS;
    }
}
