package com.example.samanta.tvtrackermvp.firebase.database;

import com.example.samanta.tvtrackermvp.pojo.WatchlistTvShows;

import java.util.List;

public interface WatchlistTvShowCallback {

    void onWatchlistTvShowCallback (List<WatchlistTvShows> watchlistTvShows);

}
