package com.example.samanta.tvtrackermvp.presenters;

import android.content.Context;

import com.example.samanta.tvtrackermvp.ui.movies.MoviesView;

public interface MoviesPresenter {

    void setBaseView(MoviesView view);

    void getData();

}
