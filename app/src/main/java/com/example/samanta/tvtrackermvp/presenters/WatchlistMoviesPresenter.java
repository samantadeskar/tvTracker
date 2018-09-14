package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.ui.watchlist.fragments.WatchlistMoviesView;

public interface WatchlistMoviesPresenter {

    void setBaseView(WatchlistMoviesView view);

    void getWatchlistMovies();

}
