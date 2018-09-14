package com.example.samanta.tvtrackermvp.ui.movies.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.samanta.tvtrackermvp.ui.movies.fragments.ComingSoonMoviesFragment;
import com.example.samanta.tvtrackermvp.ui.movies.fragments.NowPlayingMoviesFragment;
import com.example.samanta.tvtrackermvp.ui.movies.fragments.PopularMoviesFragment;
import com.example.samanta.tvtrackermvp.ui.movies.fragments.TopRatedMoviesFragment;

import java.util.ArrayList;
import java.util.List;

public class MoviesPagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;
    private final List<Fragment> fragmentList = new ArrayList<>();

    public MoviesPagerAdapter(FragmentManager fragmentManager, int numOfTabs){
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
                return new PopularMoviesFragment();
            case 1:
                return new TopRatedMoviesFragment();
            case 2:
                return new NowPlayingMoviesFragment();
            case 3:
                return new ComingSoonMoviesFragment();
        }

        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
