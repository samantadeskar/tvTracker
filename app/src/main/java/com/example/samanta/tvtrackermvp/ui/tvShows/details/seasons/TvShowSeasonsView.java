package com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons;

import com.example.samanta.tvtrackermvp.pojo.TvShowSeasons;

import java.util.List;

public interface TvShowSeasonsView {

    void setUpToolbar();

    void setData();

    void setSeasons(List<TvShowSeasons> tvShowSeasonsList);

}
