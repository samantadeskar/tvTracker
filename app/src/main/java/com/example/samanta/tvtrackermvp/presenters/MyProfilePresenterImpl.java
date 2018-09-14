package com.example.samanta.tvtrackermvp.presenters;

import android.content.Context;
import android.net.Uri;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.AllWatchedEpisodesCallback;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseUserCallback;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.WatchedMoviesCallback;
import com.example.samanta.tvtrackermvp.firebase.storage.StorageHelper;
import com.example.samanta.tvtrackermvp.firebase.storage.StorageHelperImpl;
import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.pojo.TvShowEpisode;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.myProfile.MyProfileView;

import java.util.List;

public class MyProfilePresenterImpl implements MyProfilePresenter {

    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();
    private StorageHelper storageHelper = new StorageHelperImpl();
    private AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();

    private MyProfileView view;

    @Override
    public void setBaseView(MyProfileView view) {
        this.view = view;
    }

    @Override
    public void getNumberOfWatchedEpisodesAndMovies() {

        final String userID = authenticationHelper.getCurrentUserID();
        databaseHelper.getWatchedEpisodes(userID, new AllWatchedEpisodesCallback() {
            @Override
            public void onAllWatchedEpisodesCallback(List<TvShowEpisode> watchedTvShowEpisodes) {
               final String episodes = String.valueOf(watchedTvShowEpisodes.size());
               databaseHelper.getWatchedMovies(new WatchedMoviesCallback() {
                   @Override
                   public void onWatchedMoviesCallback(List<Movie> movieList) {
                       String movies = String.valueOf(movieList.size());
                       view.setNumberOfEpisodesAndMovies(episodes,movies);
                   }
               },userID);
            }
        });
    }


    @Override
    public void saveProfilePicture(Uri profilePicUri) {

        String userID = authenticationHelper.getCurrentUserID();
        storageHelper.saveProfilePicture(profilePicUri, userID);
    }

    @Override
    public void saveUpdatedInfo
            (String username, String email, String status) {
        databaseHelper.updateUserProfile
                (username,email,status, authenticationHelper.getCurrentUserID());
        getData();
    }

    @Override
    public void getData() {
        databaseHelper.getUserInfo(new DatabaseUserCallback() {
            @Override
            public void onCallback(User user) {
                String username = user.getUsername();
                String email = user.getEmail();
                String status = user.getStatus();
                String image = user.getImage();
                getNumberOfWatchedEpisodesAndMovies();
                view.setData(username,email,status,image);
                view.setUpNavigationDrawer(username,image);
            }
        }, authenticationHelper.getCurrentUserID());
    }
}
