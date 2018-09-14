package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.interactors.TvShowsInteractor;
import com.example.samanta.tvtrackermvp.networking.BackendFactory;
import com.example.samanta.tvtrackermvp.pojo.TvShowSeasons;
import com.example.samanta.tvtrackermvp.response.TvShowSeasonResponse;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons.TvShowSeasonsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowSeasonsPresenterImpl implements TvShowSeasonsPresenter {

    private TvShowSeasonsView view;
    private TvShowsInteractor interactor = BackendFactory.getTvShowInteractor();

    @Override
    public void setBaseVIew(TvShowSeasonsView view) {

        this.view = view;
    }

    @Override
    public void getSeasons(int tvShowID) {

        interactor.getTvShowSeasons(tvShowID, getTvShowSeasonsCallback());

    }

    private Callback<TvShowSeasonResponse> getTvShowSeasonsCallback() {

        return new Callback<TvShowSeasonResponse>() {
            @Override
            public void onResponse(Call<TvShowSeasonResponse> call, Response<TvShowSeasonResponse> response) {
                if (response.isSuccessful()) {
                    List<TvShowSeasons> tvShowSeasons = response.body().getShowSeasons();
                    view.setSeasons(tvShowSeasons);
                }
            }

            @Override
            public void onFailure(Call<TvShowSeasonResponse> call, Throwable t) {

            }
        };
    }
}
