package com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.listeners.TvShowEpisodeClickListener;
import com.example.samanta.tvtrackermvp.pojo.TvShowEpisode;
import com.example.samanta.tvtrackermvp.presenters.TvShowEpisodesPresenter;
import com.example.samanta.tvtrackermvp.presenters.TvShowEpisodesPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons.adapters.TvShowEpisodesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowEpisodesActivity extends AppCompatActivity
        implements TvShowEpisodesView, TvShowEpisodeClickListener{

    @BindView(R.id.imageViewTvShowEpisodesPoster)
    ImageView imageViewTvShowEpisodesPoster;
    @BindView(R.id.textViewTvShowEpisodesTitle)
    TextView textViewTvShowEpisodesTitle;
    @BindView(R.id.ratingBarTvShowEpisodes)
    RatingBar ratingBarTvShowEpisodes;
    @BindView(R.id.toolbarTvShowEpisodes)
    Toolbar toolbarTvShowEpisodes;
    @BindView(R.id.recyclerViewEpisodes)
    RecyclerView recyclerViewEpisodes;

    private TvShowEpisodesPresenter presenter = new TvShowEpisodesPresenterImpl();

    Intent intent;
    Bundle extras;

    String title, backdrop;
    int tvShowID, seasonNumber;
    float rating;

    TvShowEpisodesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_episodes);
        ButterKnife.bind(this);

        intent = getIntent();
        extras = intent.getExtras();

        title = extras.getString(Constants.TV_SHOW_TITLE);
        backdrop = extras.getString(Constants.TV_SHOW_BACKDROP);
        tvShowID = extras.getInt(Constants.TV_SHOW_ID);
        seasonNumber = extras.getInt(Constants.TV_SHOW_SEASON_NUMBER);
        rating = extras.getFloat(Constants.TV_SHOW_RATING);

        setUpToolbar();
        setData();
        presenter.setBaseView(this);

        adapter = new TvShowEpisodesAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewEpisodes.setLayoutManager(linearLayoutManager);
        recyclerViewEpisodes.setItemAnimator(new DefaultItemAnimator());
        recyclerViewEpisodes.setAdapter(adapter);

        presenter.getEpisodes(tvShowID,seasonNumber);
    }

    @Override
    public void setUpToolbar() {
        setSupportActionBar(toolbarTvShowEpisodes);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void setData() {

        textViewTvShowEpisodesTitle.setText(title);
        ratingBarTvShowEpisodes.setRating(rating/2);
        Glide.with(this)
                .load(backdrop)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(imageViewTvShowEpisodesPoster);

    }

    @Override
    public void setEpisodes(List<TvShowEpisode> tvShowEpisodeList, List<TvShowEpisode> watchedTvShowEpisodes) {
        adapter.setTvShowEpisodeList(tvShowEpisodeList, watchedTvShowEpisodes);
    }

    @Override
    public void setAllEpisodes(List<TvShowEpisode> tvShowEpisodeList) {
        adapter.setAllTvShowEpisodeList(tvShowEpisodeList);
    }

    @Override
    public void onClick(TvShowEpisode tvShowEpisode) {

        presenter.markEpisodeAsWatched(tvShowEpisode, tvShowID,seasonNumber);
    }
}
