package com.example.samanta.tvtrackermvp.firebase.database;

import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.pojo.WatchlistMovies;

import java.util.List;

public interface WatchlistMovieCallback {

    void onCallbackWatchlistMovie(List<WatchlistMovies> watchlistMovieList);

}
