package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.ui.movies.fragments.TopRatedMoviesView;

import java.util.List;

public interface TopRatedMoviesPresenter {

    void setBaseView(TopRatedMoviesView view);

    void getTopRatedMovies(int page);

    void addTopRatedMovies(int page);

    void saveMovieOnWatchlist(Movie movie);

    void getWatchedMovies(List<Movie> allMovieList, String callback);

}
