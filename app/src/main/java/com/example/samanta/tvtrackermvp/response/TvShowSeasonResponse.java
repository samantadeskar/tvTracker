package com.example.samanta.tvtrackermvp.response;

import com.example.samanta.tvtrackermvp.pojo.TvShowSeasons;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowSeasonResponse {

    @SerializedName("seasons")
    private List<TvShowSeasons> showSeasons;

    public List<TvShowSeasons> getShowSeasons() {
        return showSeasons;
    }

    public void setShowSeasons(List<TvShowSeasons> showSeasons) {
        this.showSeasons = showSeasons;
    }


}
