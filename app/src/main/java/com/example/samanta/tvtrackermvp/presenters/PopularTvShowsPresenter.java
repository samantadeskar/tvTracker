package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.TvShow;
import com.example.samanta.tvtrackermvp.ui.tvShows.fragments.PopularTvShowsView;

public interface PopularTvShowsPresenter {

    void setBaseView(PopularTvShowsView view);

    void getPopularTvShows(int page);

    void addPopularTvShows(int page);

    void saveTvShowOnWatchlist(TvShow tvShow);

}
