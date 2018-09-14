package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.ui.movies.details.MovieDetailsView;

public interface MovieDetailsPresenter {

    void setBaseView(MovieDetailsView view);

    void markAsWatched(Movie movie);
}
