package com.example.samanta.tvtrackermvp.ui.tvShows.fragments;

import com.example.samanta.tvtrackermvp.pojo.TvShow;

import java.util.List;

public interface TopRatedTvShowsView {

    void setTvShow(List<TvShow> tvShowList);

    void addTvShow(List<TvShow> tvShowList);

    void toastOnWatchlist(String title);

    void toastAddedToWatchlist(String title);
}
