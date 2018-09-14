package com.example.samanta.tvtrackermvp.ui.users.details.watchlist.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.listeners.WatchlistTvShowClickListener;
import com.example.samanta.tvtrackermvp.pojo.WatchlistTvShows;
import com.example.samanta.tvtrackermvp.presenters.UserWatchlistTvShowPresenter;
import com.example.samanta.tvtrackermvp.presenters.UserWatchlistTvShowPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.TvShowDetailsActivity;
import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.adapters.UserWatchlistTvShowsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserWatchlistTvShowFragment extends Fragment
        implements UserWatchlistTvShowView, WatchlistTvShowClickListener {


    private UserWatchlistTvShowPresenter presenter = new UserWatchlistTvShowPresenterImpl();
    UserWatchlistTvShowsAdapter adapter;

    @BindView(R.id.recyclerViewWatchlistTvShows)
    RecyclerView recyclerviewWatchlistTvShows;

    Intent intent;
    Bundle extras;
    String userID;

    public UserWatchlistTvShowFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watchlist_tv_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);


        intent = getActivity().getIntent();
        extras = intent.getExtras();
        userID = extras.getString(Constants.USER_ID);

        presenter.setBaseView(this);

        adapter = new UserWatchlistTvShowsAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerviewWatchlistTvShows.setLayoutManager(linearLayoutManager);
        recyclerviewWatchlistTvShows.setItemAnimator(new DefaultItemAnimator());
        recyclerviewWatchlistTvShows.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getWatchlistTvShows(userID);
    }

    @Override
    public void onClick(WatchlistTvShows watchlistTvShows) {

        Intent intent = new Intent(getActivity(), TvShowDetailsActivity.class);
        Bundle extras = new Bundle();

        extras.putInt(Constants.TV_SHOW_ID, watchlistTvShows.getId());
        extras.putFloat(Constants.TV_SHOW_RATING, watchlistTvShows.getRating());
        extras.putString(Constants.TV_SHOW_TITLE, watchlistTvShows.getTitle());
        extras.putString(Constants.TV_SHOW_DESCRIPTION, watchlistTvShows.getDescription());
        extras.putString(Constants.TV_SHOW_BACKDROP, watchlistTvShows.getBackdrop());
        extras.putString(Constants.TV_SHOW_RELEASE_DATE, watchlistTvShows.getFirstAirDate());
        extras.putString(Constants.TV_SHOW_POSTER, watchlistTvShows.getPoster());

        intent.putExtras(extras);
        startActivity(intent);

    }

    @Override
    public void setTvShows(List<WatchlistTvShows> tvShowsList) {

        adapter.setTvShows(tvShowsList);
    }
}