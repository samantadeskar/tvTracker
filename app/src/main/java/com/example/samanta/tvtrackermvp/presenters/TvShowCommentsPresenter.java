package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.ui.tvShows.details.TvShowCommentsView;

public interface TvShowCommentsPresenter {

    void setBaseView(TvShowCommentsView view);

    void saveComment(String comment, int tvShowID);

    void getComments(int tvShowID);

}
