package com.example.samanta.tvtrackermvp.interactors;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.networking.ApiService;
import com.example.samanta.tvtrackermvp.response.TvShowEpisodeResponse;
import com.example.samanta.tvtrackermvp.response.TvShowResponse;
import com.example.samanta.tvtrackermvp.response.TvShowSeasonResponse;

import retrofit2.Callback;

public class TvShowsInteractorImpl implements TvShowsInteractor {

    private final ApiService apiService;

    public TvShowsInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getPopularTvShow(int page, Callback<TvShowResponse> tvShowResponseCallback) {
        apiService.getPopularTvShow(page, Constants.API_KEY).enqueue(tvShowResponseCallback);
    }

    @Override
    public void getTopRatedTvShow(int page, Callback<TvShowResponse> tvShowResponseCallback) {
        apiService.getTopRatedTvShow(page, Constants.API_KEY).enqueue(tvShowResponseCallback);
    }

    @Override
    public void getOnTheAirTvShow(int page, Callback<TvShowResponse> tvShowResponseCallback) {
        apiService.getOnTheAirTvShow(page, Constants.API_KEY).enqueue(tvShowResponseCallback);
    }

    @Override
    public void getSearchedTvShow(int page, Callback<TvShowResponse> tvShowResponseCallback, String query) {
        apiService.getSearchedTvShow(page, Constants.API_KEY, query).enqueue(tvShowResponseCallback);
    }

    @Override
    public void getTvShowSeasons(int tv_id, Callback<TvShowSeasonResponse> tvShowSeasonResponseCallback) {
        apiService.getTvShowSeasons(tv_id,Constants.API_KEY).enqueue(tvShowSeasonResponseCallback);
    }

    @Override
    public void getTvShowEpisodes(int tv_id, int season_number, Callback<TvShowEpisodeResponse> tvShowEpisodeResponseCallback) {
        apiService.getTvShowEpisodes(tv_id, season_number, Constants.API_KEY).enqueue(tvShowEpisodeResponseCallback);
    }
}
