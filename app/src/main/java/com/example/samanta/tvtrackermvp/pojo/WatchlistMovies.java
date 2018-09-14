package com.example.samanta.tvtrackermvp.pojo;

public class WatchlistMovies {

    private int id;
    private String userID;
    private String title;
    private String poster;
    private String description;
    private String releaseDate;
    private String watched;
    private String backdrop;
    private float rating;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public WatchlistMovies() {
    }

    public WatchlistMovies(String description, int id, String poster,
                           String releaseDate, String title,
                           String userEmail, String userID, String watched) {
        this.id = id;
        this.userID = userID;
        this.title = title;
        this.poster = poster;
        this.description = description;
        this.releaseDate = releaseDate;
        this.watched = watched;
    }

    public int getId() {
        return id;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
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
}
