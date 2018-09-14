package com.example.samanta.tvtrackermvp.interactors;

import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.response.MovieResponse;

import retrofit2.Callback;

public interface MoviesInteractor {

    void getPopularMovies(int page, Callback<MovieResponse> movieResponseCallback);

    void getTopRatedMovies(int page, Callback<MovieResponse> movieResponseCallback);

    void getNowPlayingMovies(int page, Callback<MovieResponse> movieResponseCallback);

    void getComingSoonMoves(int page, Callback<MovieResponse> movieResponseCallback);

    void getSearchedMoves(int page, Callback<MovieResponse> movieResponseCallback, String query);

}









