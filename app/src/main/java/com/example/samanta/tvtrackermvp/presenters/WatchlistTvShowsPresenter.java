package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.ui.watchlist.fragments.WatchlistTvShowsView;

public interface WatchlistTvShowsPresenter {

    void setBaseView(WatchlistTvShowsView view);

    void getWatchlistTvShows();

}
