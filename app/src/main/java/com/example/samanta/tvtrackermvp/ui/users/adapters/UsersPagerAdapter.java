package com.example.samanta.tvtrackermvp.ui.users.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.samanta.tvtrackermvp.ui.users.fragments.AllUsersFragment;
import com.example.samanta.tvtrackermvp.ui.users.fragments.FollowedUsersFragment;

import java.util.ArrayList;
import java.util.List;

public class UsersPagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;
    private final List<Fragment> fragmentList = new ArrayList<>();

    public UsersPagerAdapter(FragmentManager fragmentManager, int numOfTabs) {
        super(fragmentManager);
        this.numOfTabs = numOfTabs;
    }

    public void setItems(List<Fragment> fragments) {
        this.fragmentList.clear();
        this.fragmentList.addAll(fragments);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new AllUsersFragment();
            case 1:
                return new FollowedUsersFragment();
        }

        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


}
