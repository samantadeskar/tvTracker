package com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons;

import com.example.samanta.tvtrackermvp.pojo.TvShowEpisode;

import java.util.List;

public interface TvShowEpisodesView {

    void setUpToolbar();

    void setData();

    void setEpisodes(List<TvShowEpisode> tvShowEpisodeList, List<TvShowEpisode> watchedTvShowEpisodes);

    void setAllEpisodes(List<TvShowEpisode> tvShowEpisodeList);

}
