package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.TvShowEpisode;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons.TvShowEpisodesView;

import java.util.List;

public interface TvShowEpisodesPresenter {

    void setBaseView(TvShowEpisodesView view);

    void getEpisodes(int tvShowID, int seasonNumber);

    void markEpisodeAsWatched(TvShowEpisode tvShowEpisode, int tvShowID, int seasonNumber);

    void getWatchedEpisodes(List<TvShowEpisode> allTvShowEpisodes);
}
