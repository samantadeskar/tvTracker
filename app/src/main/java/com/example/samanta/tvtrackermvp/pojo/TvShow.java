package com.example.samanta.tvtrackermvp.pojo;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShow {

    @SerializedName("original_name")
    private String title;

    @SerializedName("id")
    private int id;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("overview")
    private String description;

    @SerializedName("backdrop_path")
    private String backdrop;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("vote_average")
    private float rating;


    public TvShow() {
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return Constants.TMDB_IMAGE_FILE_PATH + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackdrop() {
        return Constants.TMDB_IMAGE_FILE_PATH + backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public static class TvShowResult {
        private List<TvShow> tvShowResults;

        public List<TvShow> getTvShowResults() {
            return tvShowResults;
        }
    }
}
