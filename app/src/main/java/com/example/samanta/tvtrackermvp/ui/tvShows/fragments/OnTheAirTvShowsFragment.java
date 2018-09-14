package com.example.samanta.tvtrackermvp.ui.tvShows.fragments;

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
import android.widget.Toast;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.listeners.AddTvShowToWatchlistClickListener;
import com.example.samanta.tvtrackermvp.listeners.EndlessScrollListener;
import com.example.samanta.tvtrackermvp.listeners.TvShowClickListener;
import com.example.samanta.tvtrackermvp.pojo.TvShow;
import com.example.samanta.tvtrackermvp.presenters.OnTheAirTvShowsPresenter;
import com.example.samanta.tvtrackermvp.presenters.OnTheAirTvShowsPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.tvShows.adapters.TvShowsAdapter;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.TvShowDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnTheAirTvShowsFragment extends Fragment
        implements OnTheAirTvShowsView, TvShowClickListener,AddTvShowToWatchlistClickListener {

    private OnTheAirTvShowsPresenter presenter = new OnTheAirTvShowsPresenterImpl();

    TvShowsAdapter adapter;

    @BindView(R.id.recyclerOnTheAirTvShows)
    RecyclerView recyclerOnTheAirTvShows;

    public OnTheAirTvShowsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_on_the_air_tv_shows,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter.setBaseView(this);

        adapter = new TvShowsAdapter(this,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerOnTheAirTvShows.setLayoutManager(linearLayoutManager);
        recyclerOnTheAirTvShows.setItemAnimator(new DefaultItemAnimator());
        recyclerOnTheAirTvShows.setAdapter(adapter);

        EndlessScrollListener scrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int curentPage,
                                   int newTotalItemCount,
                                   @Nullable RecyclerView newView) {
                presenter.addOnTheAirTvShows(curentPage);
            }
        };
        recyclerOnTheAirTvShows.addOnScrollListener(scrollListener);

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getOnTheAirTvShows(1);
    }

    @Override
    public void setTvShows(List<TvShow> tvShowList) {
        adapter.setTvShows(tvShowList);
    }

    @Override
    public void addTvShows(List<TvShow> tvShowList) {
        adapter.addTvShows(tvShowList);
    }

    @Override
    public void toastOnWatchlist(String title) {
        Toast.makeText(getActivity(),
                title + " is already on watchlist.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastAddedToWatchlist(String title) {
        Toast.makeText(getActivity(),
                title + " is added to watchlist",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(TvShow tvShow) {

        Intent intent = new Intent(getActivity(), TvShowDetailsActivity.class);
        Bundle extras = new Bundle();

        extras.putInt(Constants.TV_SHOW_ID, tvShow.getId());
        extras.putFloat(Constants.TV_SHOW_RATING, tvShow.getRating());
        extras.putString(Constants.TV_SHOW_TITLE, tvShow.getTitle());
        extras.putString(Constants.TV_SHOW_DESCRIPTION, tvShow.getDescription());
        extras.putString(Constants.TV_SHOW_BACKDROP, tvShow.getBackdrop());
        extras.putString(Constants.TV_SHOW_RELEASE_DATE, tvShow.getFirstAirDate());
        extras.putString(Constants.TV_SHOW_POSTER, tvShow.getPoster());

        intent.putExtras(extras);
        startActivity(intent);
    }


    @Override
    public void onClickButton(TvShow tvShow) {

        presenter.saveTvShowOnWatchlist(tvShow);
    }
}
