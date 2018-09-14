package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons.TvShowSeasonsView;

public interface TvShowSeasonsPresenter {

    void setBaseVIew(TvShowSeasonsView vIew);

    void getSeasons(int tvShowID);

}
