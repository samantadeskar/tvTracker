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
import com.example.samanta.tvtrackermvp.listeners.WatchlistMovieClickListener;
import com.example.samanta.tvtrackermvp.pojo.WatchlistMovies;
import com.example.samanta.tvtrackermvp.presenters.UserWatchlistMoviePresenter;
import com.example.samanta.tvtrackermvp.presenters.UserWatchlistMoviePresenterImpl;
import com.example.samanta.tvtrackermvp.ui.movies.details.MovieDetailsActivity;
import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.adapters.UserWatchlistMoviesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserWatchlistMovieFragment extends Fragment
        implements UserWatchlistMovieView, WatchlistMovieClickListener {


    private UserWatchlistMoviePresenter presenter = new UserWatchlistMoviePresenterImpl();
    UserWatchlistMoviesAdapter adapter;

    @BindView(R.id.recyclerViewWatchlistMovies)
    RecyclerView recyclerViewWatchlistMovies;

    Intent intent;
    Bundle extras;
    String userID;

    public UserWatchlistMovieFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watchlist_movies,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        intent = getActivity().getIntent();
        extras = intent.getExtras();

        userID = extras.getString(Constants.USER_ID);

        presenter.setBaseView(this);

        adapter = new UserWatchlistMoviesAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewWatchlistMovies.setLayoutManager(linearLayoutManager);
        recyclerViewWatchlistMovies.setItemAnimator(new DefaultItemAnimator());
        recyclerViewWatchlistMovies.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getWatchlistMovies(userID);
    }

    @Override
    public void onClick(WatchlistMovies movie) {

        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        Bundle extras = new Bundle();

        extras.putInt(Constants.MOVIE_ID, movie.getId());
        extras.putFloat(Constants.MOVIE_RATING, movie.getRating());
        extras.putString(Constants.MOVIE_TITLE, movie.getTitle());
        extras.putString(Constants.MOVIE_DESCRIPTION, movie.getDescription());
        extras.putString(Constants.MOVIE_BACKDROP, movie.getBackdrop());
        extras.putString(Constants.MOVIE_RELEASE_DATE, movie.getReleaseDate());
        extras.putString(Constants.MOVIE_POSTER, movie.getPoster());
        extras.putString(Constants.MOVIE_WATCHED, movie.getWatched());


        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void setMovies(List<WatchlistMovies> movieList) {
        adapter.setMovies(movieList);
    }
}