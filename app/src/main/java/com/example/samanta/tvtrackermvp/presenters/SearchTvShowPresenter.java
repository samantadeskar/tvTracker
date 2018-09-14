package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.TvShow;
import com.example.samanta.tvtrackermvp.ui.search.SearchTvShowsView;

public interface SearchTvShowPresenter {

    void setBaseView(SearchTvShowsView view);

    void addSearchedTvShow(String query, int curentPage);

    void getSearchedTvShow(String query, int i);

    void saveTvShowOnWatchlist(TvShow tvShow);

}
