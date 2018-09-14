package com.example.samanta.tvtrackermvp.ui.movies.fragments;

import com.example.samanta.tvtrackermvp.pojo.Movie;

import java.util.List;

public interface TopRatedMoviesView {

    void setMovies(List<Movie> movieList, List<Movie> watchedMoviesList);

    void addMovies(List<Movie> movieList, List<Movie> watchedMoviesList);

    void toastOnWatchlist(String title);

    void toastAddedToWatchlist(String title);

}
