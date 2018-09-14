package com.example.samanta.tvtrackermvp.ui.movies.fragments;

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
import com.example.samanta.tvtrackermvp.listeners.AddToWatchlistClickListener;
import com.example.samanta.tvtrackermvp.listeners.EndlessScrollListener;
import com.example.samanta.tvtrackermvp.listeners.MovieClickListener;
import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.presenters.TopRatedMoviesPresenter;
import com.example.samanta.tvtrackermvp.presenters.TopRatedMoviesPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.movies.adapters.MoviesAdapter;
import com.example.samanta.tvtrackermvp.ui.movies.details.MovieDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopRatedMoviesFragment extends Fragment
        implements TopRatedMoviesView, MovieClickListener, AddToWatchlistClickListener{

    private TopRatedMoviesPresenter presenter = new TopRatedMoviesPresenterImpl();

    MoviesAdapter adapter;

    @BindView(R.id.recyclerTopRatedMovies)
    RecyclerView recyclerTopRatedMovies;

    public TopRatedMoviesFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_rated_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        presenter.setBaseView(this);

        adapter = new MoviesAdapter(this,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerTopRatedMovies.setLayoutManager(linearLayoutManager);
        recyclerTopRatedMovies.setItemAnimator(new DefaultItemAnimator());
        recyclerTopRatedMovies.setAdapter(adapter);

        EndlessScrollListener scrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int curentPage, int newTotalItemCount, @Nullable RecyclerView newView) {
                presenter.addTopRatedMovies(curentPage);
            }
        };
        recyclerTopRatedMovies.addOnScrollListener(scrollListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getTopRatedMovies(1);
    }




    @Override
    public void setMovies(List<Movie> movieList, List<Movie> watchedMoviesList) {

        adapter.setMovies(movieList, watchedMoviesList);

    }

    @Override
    public void addMovies(List<Movie> movieList, List<Movie> watchedMoviesList) {
        adapter.addMovies(movieList, watchedMoviesList);
    }

    @Override
    public void toastOnWatchlist(String title) {
        Toast.makeText(getActivity(),title + " is already on watchlist.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastAddedToWatchlist(String title) {
        Toast.makeText(getActivity(),title + " is added to watchlist",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(Movie movie) {

        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        Bundle extras = new Bundle();

        extras.putInt(Constants.MOVIE_ID, movie.getId());
        extras.putFloat(Constants.MOVIE_RATING, movie.getRating());
        extras.putString(Constants.MOVIE_TITLE, movie.getTitle());
        extras.putString(Constants.MOVIE_DESCRIPTION, movie.getDescription());
        extras.putString(Constants.MOVIE_BACKDROP, movie.getBackdrop());

        intent.putExtras(extras);

        startActivity(intent);

    }

    @Override
    public void onClickButton(Movie movie) {

        presenter.saveMovieOnWatchlist(movie);

    }
}
