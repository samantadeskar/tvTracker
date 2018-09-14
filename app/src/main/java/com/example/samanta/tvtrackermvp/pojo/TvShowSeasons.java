package com.example.samanta.tvtrackermvp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowSeasons {

    @SerializedName("id")
    private int seasonID;
    @SerializedName("name")
    private String seasonName;
    @SerializedName("episode_count")
    private int episodeNumber;
    @SerializedName("season_number")
    private int seasonNumber;
    @SerializedName("air_date")
    private String seasonAirDate;

    public String getSeasonAirDate() {
        return seasonAirDate;
    }

    public void setSeasonAirDate(String seasonAirDate) {
        this.seasonAirDate = seasonAirDate;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }



    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public TvShowSeasons() {
    }

    public int getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public static class TvShowSeasonResult{

        private List<TvShowSeasons> tvShowSeasonsResult;

        public List<TvShowSeasons> getTvShowSeasonsResult() {
            return tvShowSeasonsResult;
        }
    }
}
