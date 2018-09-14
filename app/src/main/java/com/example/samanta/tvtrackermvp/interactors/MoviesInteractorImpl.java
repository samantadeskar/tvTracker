package com.example.samanta.tvtrackermvp.interactors;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.networking.ApiService;
import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.response.MovieResponse;

import retrofit2.Callback;

public class MoviesInteractorImpl implements MoviesInteractor {

    private final ApiService apiService;

    public MoviesInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getPopularMovies(int page, Callback<MovieResponse> movieResponseCallback) {
        apiService.getPopularMovies(page, Constants.API_KEY).enqueue(movieResponseCallback);
    }

    @Override
    public void getTopRatedMovies(int page, Callback<MovieResponse> movieResponseCallback) {
        apiService.getTopRatedMovies(page, Constants.API_KEY).enqueue(movieResponseCallback);
    }

    @Override
    public void getNowPlayingMovies(int page, Callback<MovieResponse> movieResponseCallback) {
        apiService.getNowPlayingMovies(page, Constants.API_KEY).enqueue(movieResponseCallback);
    }

    @Override
    public void getComingSoonMoves(int page, Callback<MovieResponse> movieResponseCallback) {
        apiService.getCommingSoonMovies(page,Constants.API_KEY).enqueue(movieResponseCallback);
    }

    @Override
    public void getSearchedMoves(int page, Callback<MovieResponse> movieResponseCallback,
                                 String query) {
        apiService.getSearchedMovie(page,Constants.API_KEY, query).enqueue(movieResponseCallback);
    }
}



