package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.WatchlistTvShowCallback;
import com.example.samanta.tvtrackermvp.pojo.WatchlistTvShows;
import com.example.samanta.tvtrackermvp.ui.watchlist.fragments.WatchlistTvShowsView;

import java.util.List;

public class WatchlistTvShowsPresenterImpl implements WatchlistTvShowsPresenter {

    private WatchlistTvShowsView view;
    private AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();

    @Override
    public void setBaseView(WatchlistTvShowsView view) {
        this.view = view;
    }

    @Override
    public void getWatchlistTvShows() {

        String userID = authenticationHelper.getCurrentUserID();
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
