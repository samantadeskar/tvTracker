package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.ui.movies.fragments.NowPlayingMoviesView;

import java.util.List;

public interface NowPlayingMoviesPresenter {

    void setBaseView (NowPlayingMoviesView view);

    void getNowPlayingMovies(int page);

    void addNowPlayingMovies(int page);

    void saveMovieOnWatchlist(Movie movie);

    void getWatchedMovies(List<Movie> allMovieList, String callback);

}
