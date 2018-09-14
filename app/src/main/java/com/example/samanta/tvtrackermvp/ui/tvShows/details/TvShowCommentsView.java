package com.example.samanta.tvtrackermvp.ui.tvShows.details;

import com.example.samanta.tvtrackermvp.pojo.TvShowComments;

import java.util.List;

public interface TvShowCommentsView {

    void setData(List<TvShowComments> tvShowComments);

    void setUpToolbar();

    void setGetCommentsError();

    void setEmptyFieldError();

    void setToastCommentSent();

}
