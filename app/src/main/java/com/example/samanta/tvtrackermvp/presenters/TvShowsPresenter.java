package com.example.samanta.tvtrackermvp.presenters;

import android.content.Context;

import com.example.samanta.tvtrackermvp.ui.tvShows.TvShowsView;

public interface TvShowsPresenter {

    void setBaseView(TvShowsView view);

    void signoutUser(Context context);

    void getUserInfo();
}
