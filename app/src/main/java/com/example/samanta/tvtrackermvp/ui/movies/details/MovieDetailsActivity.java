package com.example.samanta.tvtrackermvp.ui.movies.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.pojo.MovieComments;
import com.example.samanta.tvtrackermvp.presenters.MovieDetailsPresenter;
import com.example.samanta.tvtrackermvp.presenters.MovieDetailsPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailsActivity extends AppCompatActivity
        implements MovieDetailsView {

    private MovieDetailsPresenter presenter = new MovieDetailsPresenterImpl();

    @BindView(R.id.imageViewMovieDetailsPoster)
    ImageView imageViewMovieDetailsPoster;
    @BindView(R.id.textViewMovieDetailsTitle)
    TextView textViewMovieDetailsTitle;
    @BindView(R.id.textViewMovieDetailsDescription)
    TextView textViewMovieDetailsDescription;
    @BindView(R.id.textViewMovieDetailsReleaseDate)
    TextView textViewMovieDetailsReleaseDate;
    @BindView(R.id.ratingBarMovieDetails)
    RatingBar ratingBarMovieDetails;
    @BindView(R.id.toolbarMovieDetails)
    Toolbar toolbarMovieDetails;
    @BindView(R.id.textViewMovieDetailsSummaryLabel)
    TextView textViewSummaryLabel;
    @BindView(R.id.imageButtonMarkAsWatched)
    ImageButton imageButtonMarkAsWatched;
    @BindView(R.id.textViewWatchedMovieDetails)
    TextView watchedMovieDetails;

    Intent intent;
    Bundle extras;

    String title, description, releaseDate, poster, backdrop, watched;
    int movieID;
    float rating;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        presenter.setBaseView(this);


        intent = getIntent();
        extras = intent.getExtras();

        title = extras.getString(Constants.MOVIE_TITLE);
        description = extras.getString(Constants.MOVIE_DESCRIPTION);
        releaseDate = extras.getString(Constants.MOVIE_RELEASE_DATE);
        poster = extras.getString(Constants.MOVIE_POSTER);
        backdrop = extras.getString(Constants.MOVIE_BACKDROP);
        watched = extras.getString(Constants.MOVIE_WATCHED);
        movieID = extras.getInt(Constants.MOVIE_ID);
        rating = extras.getFloat(Constants.MOVIE_RATING);

        setData();

        setUpToolbar();
    }


    @Override
    public void setData() {
        textViewMovieDetailsTitle.setText(title);
        textViewMovieDetailsDescription.setText(description);
        textViewMovieDetailsReleaseDate.setVisibility(View.VISIBLE);
        textViewMovieDetailsReleaseDate.setText(releaseDate);
        ratingBarMovieDetails.setVisibility(View.VISIBLE);
        ratingBarMovieDetails.setRating(rating / 2);
        textViewSummaryLabel.setVisibility(View.VISIBLE);


        if(watched.equals("false")){
            imageButtonMarkAsWatched.setVisibility(View.VISIBLE);
            watchedMovieDetails.setVisibility(View.INVISIBLE);
        }else {
            imageButtonMarkAsWatched.setVisibility(View.INVISIBLE);
            watchedMovieDetails.setVisibility(View.VISIBLE);
        }

        Glide.with(MovieDetailsActivity.this)
                .load(backdrop)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(imageViewMovieDetailsPoster);

    }


    private void setUpToolbar() {

        setSupportActionBar(toolbarMovieDetails);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.imageButtonMarkAsWatched)
    public void setMarkAsWatched(){
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setWatched(watched);
        movie.setBackdrop(backdrop);
        movie.setDescription(description);
        movie.setId(movieID);
        movie.setPoster(poster);
        movie.setRating(rating);
        movie.setReleaseDate(releaseDate);

        presenter.markAsWatched(movie);
    }

    @OnClick(R.id.imageButtonSeeComments)
    public void seeComments(){

        Intent intent = new Intent(MovieDetailsActivity.this, MovieCommentsActivity.class);
        Bundle extras = new Bundle();
        extras.putFloat(Constants.MOVIE_RATING, rating);
        extras.putInt(Constants.MOVIE_ID, movieID);
        extras.putString(Constants.MOVIE_TITLE, title);
        extras.putString(Constants.MOVIE_BACKDROP, backdrop);
        extras.putString(Constants.MOVIE_WATCHED,watched);

        intent.putExtras(extras);

        startActivity(intent);

    }
}
