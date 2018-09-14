package com.example.samanta.tvtrackermvp.ui.search;

import com.example.samanta.tvtrackermvp.pojo.TvShow;

import java.util.List;

public interface SearchTvShowsView {

    void setUpNavigation();

    void setData(List<TvShow> tvShowList);

    void addData(List<TvShow> tvShowList);

    void getSearchInput(String query);

    void toastOnWatchlist(String title);

    void toastAddedToWatchlist(String title);

}
