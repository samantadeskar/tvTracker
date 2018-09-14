package com.example.samanta.tvtrackermvp.ui.tvShows.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.samanta.tvtrackermvp.ui.tvShows.fragments.OnTheAirTvShowsFragment;
import com.example.samanta.tvtrackermvp.ui.tvShows.fragments.PopularTvShowsFragment;
import com.example.samanta.tvtrackermvp.ui.tvShows.fragments.TopRatedTvShowsFragment;

import java.util.ArrayList;
import java.util.List;

public class TvShowsPagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;
    private final List<Fragment> fragmentList = new ArrayList<>();

    public TvShowsPagerAdapter(FragmentManager fragmentManager, int numOfTabs){
        super(fragmentManager);
        this.numOfTabs = numOfTabs;
    }

    public void setItems(List<Fragment> fragments){
        this.fragmentList.clear();
        this.fragmentList.addAll(fragments);
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PopularTvShowsFragment();
            case 1:
                return new TopRatedTvShowsFragment();
            case 2:
                return new OnTheAirTvShowsFragment();
        }
        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
