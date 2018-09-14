package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.TvShow;
import com.example.samanta.tvtrackermvp.ui.tvShows.fragments.OnTheAirTvShowsView;

public interface OnTheAirTvShowsPresenter {


    void setBaseView(OnTheAirTvShowsView view);

    void getOnTheAirTvShows(int page);

    void addOnTheAirTvShows(int page);

    void saveTvShowOnWatchlist(TvShow tvShow);

}
