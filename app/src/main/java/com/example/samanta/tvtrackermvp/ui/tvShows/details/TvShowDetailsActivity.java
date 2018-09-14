package com.example.samanta.tvtrackermvp.ui.tvShows.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.presenters.TvShowDetailsPresenter;
import com.example.samanta.tvtrackermvp.presenters.TvShowDetailsPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons.TvShowSeasonsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TvShowDetailsActivity extends AppCompatActivity
        implements TvShowDetailsView{

    private TvShowDetailsPresenter presenter = new TvShowDetailsPresenterImpl();

    @BindView(R.id.imageViewTvShowDetailsPoster)
    ImageView imageViewTvShowDetailsPoster;
    @BindView(R.id.textViewTvShowDetailsTitle)
    TextView textViewTvShowDetailsTitle;
    @BindView(R.id.textViewTvShowDetailsDescription)
    TextView textViewTvShowDetailsDescription;
    @BindView(R.id.textViewTvShowDetailsReleaseDate)
    TextView textViewTvShowDetailsReleaseDate;
    @BindView(R.id.ratingBarTvShowDetails)
    RatingBar ratingBarTvShowDetails;
    @BindView(R.id.toolbarTvShowDetails)
    Toolbar toolbarTvShowDetails;
    @BindView(R.id.textViewTvShowDetailsSummaryLabel)
    TextView textViewSummaryLabel;

    Intent intent;
    Bundle extras;

    String title, description, releaseDate, poster, backdrop;
    int tvShowID;
    float rating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_details);
        ButterKnife.bind(this);
        presenter.setBaseView(this);

        intent = getIntent();
        extras = intent.getExtras();

        title = extras.getString(Constants.TV_SHOW_TITLE);
        description = extras.getString(Constants.TV_SHOW_DESCRIPTION);
        releaseDate = extras.getString(Constants.TV_SHOW_RELEASE_DATE);
        poster = extras.getString(Constants.TV_SHOW_POSTER);
        backdrop = extras.getString(Constants.TV_SHOW_BACKDROP);
        tvShowID = extras.getInt(Constants.TV_SHOW_ID);
        rating = extras.getFloat(Constants.TV_SHOW_RATING);


        setData();
        setUpToolbar();
    }

    @Override
    public void setData() {
        textViewTvShowDetailsTitle.setText(title);
        textViewTvShowDetailsDescription.setText(description);
        textViewTvShowDetailsReleaseDate.setVisibility(View.VISIBLE);
        textViewTvShowDetailsReleaseDate.setText(releaseDate);
        ratingBarTvShowDetails.setVisibility(View.VISIBLE);
        ratingBarTvShowDetails.setRating(rating/2);
        textViewSummaryLabel.setVisibility(View.VISIBLE);
        Glide.with(TvShowDetailsActivity.this)
                .load(backdrop)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(imageViewTvShowDetailsPoster);
    }

    private void setUpToolbar(){
        setSupportActionBar(toolbarTvShowDetails);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.imageButtonSeeTvShowComments)
    public void seeComments(){

        Intent intent = new Intent(TvShowDetailsActivity.this, TvShowCommentsActivity.class);
        Bundle extras = new Bundle();

        extras.putFloat(Constants.TV_SHOW_RATING, rating);
        extras.putInt(Constants.TV_SHOW_ID, tvShowID);
        extras.putString(Constants.TV_SHOW_TITLE, title);
        extras.putString(Constants.TV_SHOW_BACKDROP, backdrop);

        intent.putExtras(extras);
        startActivity(intent);

    }

    @OnClick(R.id.imageButtonSeeTvShowSeasons)
    public void seeSeasons(){

        Intent intent = new Intent(TvShowDetailsActivity.this, TvShowSeasonsActivity.class);
        Bundle extras = new Bundle();

        extras.putFloat(Constants.TV_SHOW_RATING, rating);
        extras.putInt(Constants.TV_SHOW_ID, tvShowID);
        extras.putString(Constants.TV_SHOW_TITLE, title);
        extras.putString(Constants.TV_SHOW_BACKDROP, backdrop);

        intent.putExtras(extras);
        startActivity(intent);

    }
}
