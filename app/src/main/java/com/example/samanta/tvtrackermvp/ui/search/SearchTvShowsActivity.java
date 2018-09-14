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
import com.example.samanta.tvtrackermvp.listeners.AddTvShowToWatchlistClickListener;
import com.example.samanta.tvtrackermvp.listeners.EndlessScrollListener;
import com.example.samanta.tvtrackermvp.listeners.TvShowClickListener;
import com.example.samanta.tvtrackermvp.pojo.TvShow;
import com.example.samanta.tvtrackermvp.presenters.SearchTvShowPresenter;
import com.example.samanta.tvtrackermvp.presenters.SearchTvShowPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.tvShows.adapters.TvShowsAdapter;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.TvShowDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchTvShowsActivity extends AppCompatActivity
        implements SearchTvShowsView, TvShowClickListener, AddTvShowToWatchlistClickListener {

    private TvShowsAdapter adapter;
    @BindView(R.id.recyclerViewSearch)
    RecyclerView recyclerViewSearch;
    @BindView(R.id.toolbarSearch)
    Toolbar toolbarSearch;

    SearchTvShowPresenter presenter = new SearchTvShowPresenterImpl();

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
        presenter.getSearchedTvShow(query, 1);

        adapter = new TvShowsAdapter(this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSearch.setLayoutManager(linearLayoutManager);
        recyclerViewSearch.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSearch.setAdapter(adapter);
        EndlessScrollListener scrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int curentPage, int newTotalItemCount, @Nullable RecyclerView newView) {
                presenter.addSearchedTvShow(query, curentPage);
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
    public void setData(List<TvShow> tvShowList) {

        adapter.setTvShows(tvShowList);

    }

    @Override
    public void addData(List<TvShow> tvShowList) {
        adapter.addTvShows(tvShowList);
    }

    @Override
    public void onClickButton(TvShow tvShow) {

        presenter.saveTvShowOnWatchlist(tvShow);

    }

    @Override
    public void onClick(TvShow tvShow) {

        Intent intent = new Intent(SearchTvShowsActivity.this, TvShowDetailsActivity.class);
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

        presenter.getSearchedTvShow(query, 1);
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
