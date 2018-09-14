package com.example.samanta.tvtrackermvp.response;

import com.example.samanta.tvtrackermvp.pojo.TvShowEpisode;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowEpisodeResponse {

    @SerializedName("episodes")
    private List<TvShowEpisode> showEpisodes;

    public List<TvShowEpisode> getShowEpisodes() {
        return showEpisodes;
    }

    public void setShowEpisodes(List<TvShowEpisode> showEpisodes) {
        this.showEpisodes = showEpisodes;
    }

}
