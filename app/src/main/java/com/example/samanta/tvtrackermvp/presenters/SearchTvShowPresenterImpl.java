package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.WatchlistTvShowsCallback;
import com.example.samanta.tvtrackermvp.interactors.TvShowsInteractor;
import com.example.samanta.tvtrackermvp.networking.BackendFactory;
import com.example.samanta.tvtrackermvp.pojo.TvShow;
import com.example.samanta.tvtrackermvp.response.TvShowResponse;
import com.example.samanta.tvtrackermvp.ui.search.SearchTvShowsView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTvShowPresenterImpl implements SearchTvShowPresenter {

    private SearchTvShowsView view;
    private TvShowsInteractor interactor = BackendFactory.getTvShowInteractor();
    private AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();



    @Override
    public void setBaseView(SearchTvShowsView view) {

        this.view = view;

    }

    @Override
    public void addSearchedTvShow(String query, int curentPage) {
        interactor.getSearchedTvShow(curentPage,addTvShowResponseCallback(), query);
    }

    @Override
    public void getSearchedTvShow(String query, int i) {

        interactor.getSearchedTvShow(1,getTvShowResponseCallback(),query);

    }

    @Override
    public void saveTvShowOnWatchlist(final TvShow tvShow) {

        String userID = authenticationHelper.getCurrentUserID();
        databaseHelper.addTvShowToWatchlist(new WatchlistTvShowsCallback() {
            @Override
            public void onCallbackWatchlistTvShows(boolean isOnWatchlist) {
                if(isOnWatchlist){
                    view.toastOnWatchlist(tvShow.getTitle());
                }
                else {
                    view.toastAddedToWatchlist(tvShow.getTitle());
                }
            }
        },tvShow,userID);
    }

    private Callback<TvShowResponse> getTvShowResponseCallback() {

        return new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if(response.isSuccessful()){
                    view.setData(response.body().getTvShows());
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {

            }
        };

    }


    private Callback<TvShowResponse> addTvShowResponseCallback() {

        return  new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if(response.isSuccessful()){
                    view.addData(response.body().getTvShows());
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {

            }
        };
    }

}
