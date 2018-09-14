package com.example.samanta.tvtrackermvp.firebase.database;

import com.example.samanta.tvtrackermvp.pojo.TvShowComments;

import java.util.List;

public interface TvShowCommentsCallback {

    void onTvShowCommentCallback(List<TvShowComments> commentsList);

}
