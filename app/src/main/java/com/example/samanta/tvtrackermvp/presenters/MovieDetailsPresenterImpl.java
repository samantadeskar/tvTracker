package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.interactors.MoviesInteractor;
import com.example.samanta.tvtrackermvp.networking.BackendFactory;
import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.ui.movies.details.MovieDetailsView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsPresenterImpl implements MovieDetailsPresenter {

    private MovieDetailsView view;
    AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    DatabaseHelper databaseHelper = new DatabaseHelperImpl();


    @Override
    public void setBaseView(MovieDetailsView view) {
        this.view = view;
    }

    @Override
    public void markAsWatched(Movie movie) {

        String userID = authenticationHelper.getCurrentUserID();
        databaseHelper.markMovieAsWatched(movie,userID);

    }

}
