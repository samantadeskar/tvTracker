package com.example.samanta.tvtrackermvp.presenters;

import android.net.Uri;

import com.example.samanta.tvtrackermvp.ui.myProfile.MyProfileView;

public interface MyProfilePresenter {

    void saveProfilePicture(Uri profilePicUri);

    void saveUpdatedInfo(String username, String email, String status);

    void getData();

    void setBaseView(MyProfileView view);

    void getNumberOfWatchedEpisodesAndMovies();


}
