package com.example.samanta.tvtrackermvp.presenters;

import android.util.Log;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.WatchlistTvShowsCallback;
import com.example.samanta.tvtrackermvp.interactors.TvShowsInteractor;
import com.example.samanta.tvtrackermvp.networking.BackendFactory;
import com.example.samanta.tvtrackermvp.pojo.TvShow;
import com.example.samanta.tvtrackermvp.response.TvShowResponse;
import com.example.samanta.tvtrackermvp.ui.tvShows.fragments.PopularTvShowsView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularTvShowsPresenterImpl implements PopularTvShowsPresenter{

    private PopularTvShowsView view;
    private TvShowsInteractor interactor = BackendFactory.getTvShowInteractor();
    AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    DatabaseHelper databaseHelper = new DatabaseHelperImpl();

    @Override
    public void setBaseView(PopularTvShowsView view) {
        this.view = view;
    }

    @Override
    public void getPopularTvShows(int page) {
        interactor.getPopularTvShow(page, getPopularTvShowsCallback());
    }

    @Override
    public void addPopularTvShows(int page) {
        interactor.getPopularTvShow(page, addPopularTvShowsCallback());
    }

    @Override
    public void saveTvShowOnWatchlist(final TvShow tvShow) {

        final String userID = authenticationHelper.getCurrentUserID();

        databaseHelper.addTvShowToWatchlist(new WatchlistTvShowsCallback() {
            @Override
            public void onCallbackWatchlistTvShows(boolean isOnWatchlist) {
                if(isOnWatchlist){
                    view.toastOnWatchlist(tvShow.getTitle());
                }else {
                    view.toastAddedToWatchlist(tvShow.getTitle());
                }
            }
        }, tvShow, userID);

    }

    private Callback<TvShowResponse> getPopularTvShowsCallback(){
        return new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if(response.isSuccessful()){
                    view.setTvShows(response.body().getTvShows());
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.d("TAG", "Fail");
            }
        };
    }

    private Callback<TvShowResponse> addPopularTvShowsCallback(){
        return new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if(response.isSuccessful()){
                    view.addTvShows(response.body().getTvShows());
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.d("TAG", "Fail");
            }
        };
    }
}
