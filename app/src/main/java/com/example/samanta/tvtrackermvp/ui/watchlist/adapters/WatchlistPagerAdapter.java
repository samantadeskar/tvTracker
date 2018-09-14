package com.example.samanta.tvtrackermvp.ui.watchlist.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.samanta.tvtrackermvp.ui.watchlist.fragments.WatchlistMoviesFragment;
import com.example.samanta.tvtrackermvp.ui.watchlist.fragments.WatchlistTvShowsFragment;

import java.util.ArrayList;
import java.util.List;

public class WatchlistPagerAdapter extends FragmentStatePagerAdapter{

    int numOfTabs;
    private final List<Fragment> fragmentList = new ArrayList<>();

    public WatchlistPagerAdapter(FragmentManager fragmentManager, int numOfTabs){
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
                return new WatchlistMoviesFragment();
            case 1:
                return new WatchlistTvShowsFragment();
        }
        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
