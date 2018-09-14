package com.example.samanta.tvtrackermvp.ui.search;

import com.example.samanta.tvtrackermvp.pojo.Movie;

import java.util.List;

public interface SearchMoviesView {

    void setUpNavigation();

    void setData(List<Movie> movieList, List<Movie> watchedMoviesList);

    void addData(List<Movie> movieList, List<Movie> watchedMoviesList);

    void getSearchInput(String query);

    void toastOnWatchlist(String title);

    void toastAddedToWatchlist(String title);

}
