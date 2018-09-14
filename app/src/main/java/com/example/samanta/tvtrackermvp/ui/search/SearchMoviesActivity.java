package com.example.samanta.tvtrackermvp.ui.search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.listeners.AddToWatchlistClickListener;
import com.example.samanta.tvtrackermvp.listeners.EndlessScrollListener;
import com.example.samanta.tvtrackermvp.listeners.MovieClickListener;
import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.presenters.SearchMoviesPresenter;
import com.example.samanta.tvtrackermvp.presenters.SearchMoviesPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.movies.adapters.MoviesAdapter;
import com.example.samanta.tvtrackermvp.ui.movies.details.MovieDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMoviesActivity extends AppCompatActivity
        implements SearchMoviesView, MovieClickListener, AddToWatchlistClickListener {

    private MoviesAdapter adapter;
    @BindView(R.id.recyclerViewSearch)
    RecyclerView recyclerViewSearch;
    @BindView(R.id.toolbarSearch)
    Toolbar toolbarSearch;

    SearchMoviesPresenter presenter = new SearchMoviesPresenterImpl();

    Intent intent;
    Bundle extras;
    private String query;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        intent = getIntent();
        extras = intent.getExtras();

        query = extras.getString(Constants.SEARCH_QUERY);

        presenter.setBaseView(this);
        setUpNavigation();
        presenter.getSearchedMovies(query, 1);

        adapter = new MoviesAdapter(this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSearch.setLayoutManager(linearLayoutManager);
        recyclerViewSearch.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSearch.setAdapter(adapter);
        EndlessScrollListener scrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int curentPage, int newTotalItemCount, @Nullable RecyclerView newView) {
                presenter.addSearchedMovies(query, curentPage);
            }
        };
        recyclerViewSearch.addOnScrollListener(scrollListener);

    }

    @Override
    public void setUpNavigation() {

        setSupportActionBar(toolbarSearch);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getSearchedMovies(query, 1);
    }

    @Override
    public void setData(List<Movie> movieList, List<Movie> watchedMoviesList) {
        adapter.setMovies(movieList, watchedMoviesList);
    }


    @Override
    public void addData(List<Movie> movieList, List<Movie> watchedMoviesList) {
        adapter.addMovies(movieList, watchedMoviesList);
    }


    @Override
    public void onClick(Movie movie) {

        Intent intent = new Intent(SearchMoviesActivity.this, MovieDetailsActivity.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.action_bar, menu);

        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();

        SearchManager searchManager =
                (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchInput(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return true;
    }

    @Override
    public void getSearchInput(String query) {

        presenter.getSearchedMovies(query, 1);

    }

    @Override
    public void toastOnWatchlist(String title) {
        Toast.makeText(this, title + " is already on watchlist.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastAddedToWatchlist(String title) {
        Toast.makeText(this, title + " is added to watchlist.",
                Toast.LENGTH_SHORT).show();
    }
}
