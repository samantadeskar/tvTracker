package com.example.samanta.tvtrackermvp.ui.myProfile;

public interface MyProfileView {

    void setUpNavigationDrawer(String username, String profilePicture);

    void setData
            (String username, String email, String status,
             String profilePic);

    void setNumberOfEpisodesAndMovies(String numberOfEpisodes, String numberOfMovies);
}
