package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.WatchlistMovieCallback;
import com.example.samanta.tvtrackermvp.pojo.WatchlistMovies;
import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.fragments.UserWatchlistMovieView;

import java.util.List;

public class UserWatchlistMoviePresenterImpl implements UserWatchlistMoviePresenter {

    private UserWatchlistMovieView view;
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();

    @Override
    public void setBaseView(UserWatchlistMovieView view) {
        this.view = view;
    }

    @Override
    public void getWatchlistMovies(String userID) {


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
