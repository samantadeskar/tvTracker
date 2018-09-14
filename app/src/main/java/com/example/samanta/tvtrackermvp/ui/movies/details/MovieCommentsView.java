package com.example.samanta.tvtrackermvp.ui.movies.details;

import com.example.samanta.tvtrackermvp.pojo.MovieComments;

import java.util.List;

public interface MovieCommentsView {

    void setData(List<MovieComments> movieCommentsList);

    void setUpToolbar();

    void setGetCommentsError();

    void setEmptyFieldError();

    void setToastCommentSent();
}
