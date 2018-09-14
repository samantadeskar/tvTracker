package com.example.samanta.tvtrackermvp.firebase.database;

import com.example.samanta.tvtrackermvp.pojo.TvShowEpisode;

import java.util.List;

public interface AllWatchedEpisodesCallback {

    void onAllWatchedEpisodesCallback(List<TvShowEpisode> watchedTvShowEpisodes);

}
