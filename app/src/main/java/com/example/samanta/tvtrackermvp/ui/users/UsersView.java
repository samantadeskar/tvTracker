package com.example.samanta.tvtrackermvp.ui.users;

public interface UsersView {

    void setUpFragments();

    void setUpNavigationDrawer(String username, String profilePicture);

    void getSearchInput(String query);

}
