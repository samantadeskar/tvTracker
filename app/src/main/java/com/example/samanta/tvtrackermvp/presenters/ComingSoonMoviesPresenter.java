package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.ui.movies.fragments.ComingSoonMoviesView;

import java.util.List;

public interface ComingSoonMoviesPresenter {

    void setBaseView(ComingSoonMoviesView view);

    void getComingSoonMovies(int page);

    void addComingSoonMovies(int page);

    void saveMovieOnWatchlist(Movie movie);

    void getWatchedMovies(List<Movie> allMovieList, String callback);

}
