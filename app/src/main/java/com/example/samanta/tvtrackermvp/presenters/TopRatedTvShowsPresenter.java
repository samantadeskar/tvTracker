package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.TvShow;
import com.example.samanta.tvtrackermvp.ui.tvShows.fragments.TopRatedTvShowsFragment;
import com.example.samanta.tvtrackermvp.ui.tvShows.fragments.TopRatedTvShowsView;

public interface TopRatedTvShowsPresenter {

    void setBaseView(TopRatedTvShowsView view);

    void getTopRatedTvShows(int page);

    void addTopRatedTvShows(int page);

    void saveTvShowOnWatchlist(TvShow tvShow);

}
