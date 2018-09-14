package com.example.samanta.tvtrackermvp.response;

import com.example.samanta.tvtrackermvp.pojo.TvShow;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {


    @SerializedName("results")
    private List<TvShow> tvShows;

    public List<TvShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<TvShow> tvShows) {
        this.tvShows = tvShows;
    }


}
