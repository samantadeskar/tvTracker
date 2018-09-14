package com.example.samanta.tvtrackermvp.ui.movies.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.pojo.MovieComments;
import com.example.samanta.tvtrackermvp.presenters.MovieCommentsPresenter;
import com.example.samanta.tvtrackermvp.presenters.MovieCommentsPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.movies.details.adapters.MovieCommentsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieCommentsActivity extends AppCompatActivity implements MovieCommentsView {

    MovieCommentsPresenter presenter = new MovieCommentsPresenterImpl();

    @BindView(R.id.imageViewMovieCommentsPoster)
    ImageView imageViewMovieCommentsPoster;
    @BindView(R.id.textViewMovieCommentsTitle)
    TextView textViewMovieCommentsTitle;
    @BindView(R.id.ratingBarMovieComments)
    RatingBar ratingBarMovieComments;
    @BindView(R.id.editTextAddComment)
    EditText editTextAddComment;
    @BindView(R.id.recyclerViewMovieComments)
    RecyclerView recyclerViewMovieComments;
    @BindView(R.id.toolbarMovieComments)
    Toolbar toolbarComments;

    Intent intent;
    Bundle extras;

    String title, backdrop, watched;
    int movieID;
    float rating;

    MovieCommentsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_comments);
        ButterKnife.bind(this);
        presenter.setBaseView(this);

        intent = getIntent();
        extras = intent.getExtras();

        title = extras.getString(Constants.MOVIE_TITLE);
        backdrop = extras.getString(Constants.MOVIE_BACKDROP);
        movieID = extras.getInt(Constants.MOVIE_ID);
        rating = extras.getFloat(Constants.MOVIE_RATING);
        watched = extras.getString(Constants.MOVIE_WATCHED);

        presenter.getComments(movieID);

        setUpToolbar();

        adapter = new MovieCommentsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewMovieComments.setLayoutManager(linearLayoutManager);
        recyclerViewMovieComments.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMovieComments.setAdapter(adapter);
    }

    @Override
    public void setData(List<MovieComments> movieCommentsList) {


        textViewMovieCommentsTitle.setText(title);
        ratingBarMovieComments.setRating(rating / 2);

        Glide.with(this)
                .load(backdrop)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(imageViewMovieCommentsPoster);

        if (movieCommentsList != null) {
            adapter = new MovieCommentsAdapter(movieCommentsList);
            recyclerViewMovieComments.setAdapter(adapter);
        }
    }

    @Override
    public void setUpToolbar() {

        setSupportActionBar(toolbarComments);

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

    @Override
    public void setGetCommentsError() {
        Toast.makeText(MovieCommentsActivity.this,
                R.string.error_load_comments,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setEmptyFieldError() {
        Toast.makeText(MovieCommentsActivity.this,
                R.string.empty_comment_error,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setToastCommentSent() {
        Toast.makeText(MovieCommentsActivity.this,
                R.string.comment_is_sent,
                Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.imageButtonSendComment)
    public void sendComment() {
        String comment = editTextAddComment.getText().toString();

        if (!TextUtils.isEmpty(comment)) {
            presenter.saveComment(comment, movieID);
        } else {
            setEmptyFieldError();
        }
    }
}
