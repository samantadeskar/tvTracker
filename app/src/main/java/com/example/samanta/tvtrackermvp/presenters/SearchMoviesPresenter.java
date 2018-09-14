package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.ui.search.SearchMoviesView;

import java.util.List;

public interface SearchMoviesPresenter {

    void setBaseView(SearchMoviesView view);

    void addSearchedMovies(String query, int curentPage);

    void getSearchedMovies(String query, int i);

    void saveMovieOnWatchlist(Movie movie);

    void getWatchedMovies(List<Movie> allMovieList, String callback);
}
