package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.fragments.UserWatchlistMovieView;

public interface UserWatchlistMoviePresenter {

    void setBaseView(UserWatchlistMovieView view);

    void getWatchlistMovies(String userID);

}
