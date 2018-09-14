package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.fragments.UserWatchlistTvShowView;

public interface UserWatchlistTvShowPresenter {

    void setBaseView(UserWatchlistTvShowView view);

    void getWatchlistTvShows(String userID);

}
