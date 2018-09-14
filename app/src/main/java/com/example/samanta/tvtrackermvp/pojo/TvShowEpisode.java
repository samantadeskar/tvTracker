package com.example.samanta.tvtrackermvp.pojo;

import com.google.gson.annotations.SerializedName;

public class TvShowEpisode {

    @SerializedName("air_date")
    private String episodeAirDate;
    @SerializedName("episode_number")
    private int episode_number;
    @SerializedName("id")
    private int episode_id;
    @SerializedName("name")
    private String episodeName;

    private String watched = "false";

    public String getWatched() {
        return watched;
    }

    public void setWatched(String watched) {
        this.watched = watched;
    }

    public TvShowEpisode() {
    }

    public String getEpisodeAirDate() {
        return episodeAirDate;
    }

    public void setEpisodeAirDate(String episodeAirDate) {
        this.episodeAirDate = episodeAirDate;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(int episode_number) {
        this.episode_number = episode_number;
    }

    public int getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(int episode_id) {
        this.episode_id = episode_id;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }
}
