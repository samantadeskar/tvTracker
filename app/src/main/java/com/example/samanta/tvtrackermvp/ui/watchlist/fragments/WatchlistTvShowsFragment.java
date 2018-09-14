package com.example.samanta.tvtrackermvp.ui.watchlist.fragments;

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
import com.example.samanta.tvtrackermvp.presenters.WatchlistTvShowsPresenter;
import com.example.samanta.tvtrackermvp.presenters.WatchlistTvShowsPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.TvShowDetailsActivity;
import com.example.samanta.tvtrackermvp.ui.watchlist.adapters.WatchlistTvShowsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WatchlistTvShowsFragment extends Fragment
        implements WatchlistTvShowsView,WatchlistTvShowClickListener{


    private WatchlistTvShowsPresenter presenter = new WatchlistTvShowsPresenterImpl();
    WatchlistTvShowsAdapter adapter;

    @BindView(R.id.recyclerViewWatchlistTvShows)
    RecyclerView recyclerviewWatchlistTvShows;

    public WatchlistTvShowsFragment(){}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_watchlist_tv_shows,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        presenter.setBaseView(this);

        adapter = new WatchlistTvShowsAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerviewWatchlistTvShows.setLayoutManager(linearLayoutManager);
        recyclerviewWatchlistTvShows.setItemAnimator(new DefaultItemAnimator());
        recyclerviewWatchlistTvShows.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getWatchlistTvShows();
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
