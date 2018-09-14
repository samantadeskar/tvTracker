package com.example.samanta.tvtrackermvp.ui.tvShows.fragments;

import com.example.samanta.tvtrackermvp.pojo.TvShow;

import java.util.List;

public interface OnTheAirTvShowsView {

    void setTvShows(List<TvShow> tvShowList);

    void addTvShows(List<TvShow> tvShowList);

    void toastOnWatchlist(String title);

    void toastAddedToWatchlist(String title);
}
