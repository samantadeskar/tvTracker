package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.WatchlistMovieCallback;
import com.example.samanta.tvtrackermvp.pojo.WatchlistMovies;
import com.example.samanta.tvtrackermvp.ui.watchlist.fragments.WatchlistMoviesView;

import java.util.List;

public class WatchlistMoviesPresenterImpl implements WatchlistMoviesPresenter {

    private WatchlistMoviesView view;
    private AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();

    @Override
    public void setBaseView(WatchlistMoviesView view) {
        this.view = view;
    }

    @Override
    public void getWatchlistMovies() {

        String userID = authenticationHelper.getCurrentUserID();

        databaseHelper.getWatchlistMovies(userID, new WatchlistMovieCallback() {
            @Override
            public void onCallbackWatchlistMovie(List<WatchlistMovies> watchlistMovieList) {

                if (watchlistMovieList != null) {
                    view.setMovies(watchlistMovieList);
                }

            }
        });
    }
}
