package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.ui.movies.fragments.PopularMoviesView;
import com.example.samanta.tvtrackermvp.pojo.Movie;

import java.util.List;

public interface PopularMoviesPresenter {

    void setBaseView(PopularMoviesView view);

    void getPopularMovies(int page);

    void addPopularMovies(int page);

    void saveMovieOnWatchlist(Movie movie);

    void getWatchedMovies(List<Movie> allMovieList, String callback);
}
