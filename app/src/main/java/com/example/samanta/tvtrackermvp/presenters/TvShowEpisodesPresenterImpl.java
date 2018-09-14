package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.AllWatchedEpisodesCallback;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.WatchedEpisodeCallback;
import com.example.samanta.tvtrackermvp.interactors.TvShowsInteractor;
import com.example.samanta.tvtrackermvp.networking.BackendFactory;
import com.example.samanta.tvtrackermvp.pojo.TvShowEpisode;
import com.example.samanta.tvtrackermvp.response.TvShowEpisodeResponse;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons.TvShowEpisodesView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowEpisodesPresenterImpl implements TvShowEpisodesPresenter {

    private TvShowEpisodesView view;
    private TvShowsInteractor interactor = BackendFactory.getTvShowInteractor();
    private AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();


    @Override
    public void setBaseView(TvShowEpisodesView view) {

        this.view = view;

    }

    @Override
    public void getEpisodes(int tvShowID, int seasonNumber) {
        interactor.getTvShowEpisodes(tvShowID, seasonNumber, tvShowEpisodeResponseCallback());
    }

    @Override
    public void markEpisodeAsWatched(final TvShowEpisode tvShowEpisode, final int tvShowID, final int seasonNumber) {

        String userID = authenticationHelper.getCurrentUserID();
        databaseHelper.markEpisodeAsWatched(tvShowEpisode, userID, seasonNumber, new WatchedEpisodeCallback() {
            @Override
            public void onWatchedEpisodeCallback(boolean isMarkedAsWatched) {
                if (isMarkedAsWatched) {
                    getEpisodes(tvShowID, seasonNumber);
                }
            }
        });


    }

    @Override
    public void getWatchedEpisodes(final List<TvShowEpisode> allTvShowEpisodes) {

        String userID = authenticationHelper.getCurrentUserID();

        databaseHelper.getWatchedEpisodes(userID, new AllWatchedEpisodesCallback() {
            @Override
            public void onAllWatchedEpisodesCallback(List<TvShowEpisode> watchedTvShowEpisodes) {

                    view.setEpisodes(allTvShowEpisodes, watchedTvShowEpisodes);

            }
        });

    }

    private Callback<TvShowEpisodeResponse> tvShowEpisodeResponseCallback() {

        return new Callback<TvShowEpisodeResponse>() {
            @Override
            public void onResponse(Call<TvShowEpisodeResponse> call,
                                   Response<TvShowEpisodeResponse> response) {
                if (response.isSuccessful()) {
                    getWatchedEpisodes(response.body().getShowEpisodes());
                }
            }

            @Override
            public void onFailure(Call<TvShowEpisodeResponse> call, Throwable t) {

            }
        };

    }
}
