package com.example.samanta.tvtrackermvp.interactors;

import com.example.samanta.tvtrackermvp.response.TvShowEpisodeResponse;
import com.example.samanta.tvtrackermvp.response.TvShowResponse;
import com.example.samanta.tvtrackermvp.response.TvShowSeasonResponse;

import retrofit2.Callback;


public interface TvShowsInteractor {

    void getPopularTvShow (int page, Callback<TvShowResponse> tvShowResponseCallback);

    void getTopRatedTvShow (int page, Callback<TvShowResponse> tvShowResponseCallback);

    void getOnTheAirTvShow (int page, Callback<TvShowResponse> tvShowResponseCallback);

    void getSearchedTvShow (int page, Callback<TvShowResponse> tvShowResponseCallback, String query);

    void getTvShowSeasons (int tv_id, Callback<TvShowSeasonResponse> tvShowSeasonResponseCallback);

    void getTvShowEpisodes (int tv_id, int season_number, Callback<TvShowEpisodeResponse> tvShowEpisodeResponseCallback);
}
