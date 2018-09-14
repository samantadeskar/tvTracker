package com.example.samanta.tvtrackermvp.ui.movies;


public interface MoviesView {

    void setUpFragments();
    void setUpNavigationDrawer(String username, String profilePicture);
    void getSearchInput(String query);
}
