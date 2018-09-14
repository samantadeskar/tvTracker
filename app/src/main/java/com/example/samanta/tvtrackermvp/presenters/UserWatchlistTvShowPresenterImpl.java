package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.WatchlistTvShowCallback;
import com.example.samanta.tvtrackermvp.pojo.WatchlistTvShows;
import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.fragments.UserWatchlistTvShowView;

import java.util.List;

public class UserWatchlistTvShowPresenterImpl implements UserWatchlistTvShowPresenter {

    private UserWatchlistTvShowView view;
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();

    @Override
    public void setBaseView(UserWatchlistTvShowView view) {
        this.view = view;
    }

    @Override
    public void getWatchlistTvShows(String userID) {

        databaseHelper.getWatchlistTvShows(userID, new WatchlistTvShowCallback() {
            @Override
            public void onWatchlistTvShowCallback(List<WatchlistTvShows> watchlistTvShows) {

                if (watchlistTvShows != null) {
                    view.setTvShows(watchlistTvShows);
                }

            }
        });

    }

}
