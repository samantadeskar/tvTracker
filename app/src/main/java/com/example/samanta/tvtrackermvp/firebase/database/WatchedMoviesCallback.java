package com.example.samanta.tvtrackermvp.firebase.database;

import com.example.samanta.tvtrackermvp.pojo.Movie;

import java.util.List;

public interface WatchedMoviesCallback {

    void onWatchedMoviesCallback(List<Movie> movieList);

}
