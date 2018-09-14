package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.ui.movies.details.MovieCommentsView;

public interface MovieCommentsPresenter {

    void setBaseView(MovieCommentsView view);

    void saveComment(String comment, int movieID);

    void getComments(int movieID);

}
