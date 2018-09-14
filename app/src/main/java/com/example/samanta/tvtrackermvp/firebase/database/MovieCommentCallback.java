package com.example.samanta.tvtrackermvp.firebase.database;

import com.example.samanta.tvtrackermvp.pojo.MovieComments;

import java.util.List;

public interface MovieCommentCallback {

    void onMovieCommentCallback(List<MovieComments> commentList);

}
