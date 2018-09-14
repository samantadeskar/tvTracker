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
import com.example.samanta.tvtrackermvp.listeners.TvShowSeasonClickListener;
import com.example.samanta.tvtrackermvp.pojo.TvShowSeasons;
import com.example.samanta.tvtrackermvp.presenters.TvShowSeasonsPresenter;
import com.example.samanta.tvtrackermvp.presenters.TvShowSeasonsPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons.adapters.TvShowSeasonsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowSeasonsActivity extends AppCompatActivity
        implements TvShowSeasonsView, TvShowSeasonClickListener {

    private TvShowSeasonsPresenter presenter = new TvShowSeasonsPresenterImpl();

    @BindView(R.id.imageViewTvShowSeasonsPoster)
    ImageView imageViewTvShowSeasonsPoster;
    @BindView(R.id.textViewTvShowSeasonsTitle)
    TextView textViewTvShowSeasonsTitle;
    @BindView(R.id.ratingBarTvShowSeasons)
    RatingBar ratingBarTvShowSeasons;
    @BindView(R.id.toolbarTvShowSeasons)
    Toolbar toolbarTvShowSeasons;
    @BindView(R.id.recyclerViewSeasons)
    RecyclerView recyclerViewSeasons;

    Intent intent;
    Bundle extras;

    String title, backdrop;
    int tvShowID;
    float rating;

    TvShowSeasonsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_seasons);
        ButterKnife.bind(this);

        intent = getIntent();
        extras = intent.getExtras();

        title = extras.getString(Constants.TV_SHOW_TITLE);
        backdrop = extras.getString(Constants.TV_SHOW_BACKDROP);
        tvShowID = extras.getInt(Constants.TV_SHOW_ID);
        rating = extras.getFloat(Constants.TV_SHOW_RATING);

        setUpToolbar();
        setData();
        presenter.setBaseVIew(this);


        adapter = new TvShowSeasonsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSeasons.setLayoutManager(linearLayoutManager);
        recyclerViewSeasons.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSeasons.setAdapter(adapter);
        presenter.getSeasons(tvShowID);

    }

    @Override
    public void setUpToolbar() {

        setSupportActionBar(toolbarTvShowSeasons);

        if(getSupportActionBar() != null){
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
        textViewTvShowSeasonsTitle.setText(title);
        ratingBarTvShowSeasons.setRating(rating/2);
        Glide.with(this)
                .load(backdrop)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(imageViewTvShowSeasonsPoster);

    }

    @Override
    public void setSeasons(List<TvShowSeasons> tvShowSeasonsList) {
        adapter.setTvShowSeasonsList(tvShowSeasonsList);
    }

    @Override
    public void onClick(TvShowSeasons tvShowSeasons) {

        Intent intent = new Intent(TvShowSeasonsActivity.this, TvShowEpisodesActivity.class);
        Bundle extras = new Bundle();

        extras.putInt(Constants.TV_SHOW_ID, tvShowID);
        extras.putInt(Constants.TV_SHOW_SEASON_NUMBER, tvShowSeasons.getSeasonNumber());
        extras.putString(Constants.TV_SHOW_TITLE, title);
        extras.putString(Constants.TV_SHOW_BACKDROP,backdrop);
        extras.putFloat(Constants.TV_SHOW_RATING, rating);

        intent.putExtras(extras);
        startActivity(intent);

    }
}
