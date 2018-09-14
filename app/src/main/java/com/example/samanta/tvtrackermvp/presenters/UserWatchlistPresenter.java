package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.UserWatchlistView;

public interface UserWatchlistPresenter {

    void setBaseView(UserWatchlistView view);

    void getData(String userID);

}
