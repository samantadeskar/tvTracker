package com.example.samanta.tvtrackermvp.pojo;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    @SerializedName("original_title")
    private String title;

    @SerializedName("id")
    private int id;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("overview")
    private String description;

    @SerializedName("backdrop_path")
    private String backdrop;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private float rating;

    private String watched = "false";

    public Movie(String title, int id, String poster, String description, String backdrop, String releaseDate, float rating, String watched) {
        this.title = title;
        this.id = id;
        this.poster = poster;
        this.description = description;
        this.backdrop = backdrop;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.watched = watched;
    }

    public Movie() {

    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getWatched() {
        return watched;
    }

    public void setWatched(String watched) {
        this.watched = watched;
    }


    public static class MovieResult {
        private List<Movie> results;

        public List<Movie> getResults() {
            return results;
        }
    }


}
