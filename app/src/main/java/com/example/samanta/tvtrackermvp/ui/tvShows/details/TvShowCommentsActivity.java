package com.example.samanta.tvtrackermvp.ui.tvShows.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.pojo.TvShowComments;
import com.example.samanta.tvtrackermvp.presenters.TvShowCommentsPresenter;
import com.example.samanta.tvtrackermvp.presenters.TvShowCommentsPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.adapter.TvShowCommentsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TvShowCommentsActivity extends AppCompatActivity
        implements TvShowCommentsView {


    TvShowCommentsPresenter presenter = new TvShowCommentsPresenterImpl();

    @BindView(R.id.imageViewTvShowCommentsPoster)
    ImageView imageViewTvshowCommentsPoster;
    @BindView(R.id.textViewTvShowCommentsTitle)
    TextView textViewTvShowCommentsTitle;
    @BindView(R.id.ratingBarTvShowComments)
    RatingBar ratingBarTvShowComments;
    @BindView(R.id.editTextAddTvShowComment)
    EditText editTextAddTvShowComment;
    @BindView(R.id.recyclerViewTvShowComments)
    RecyclerView recyclerViewTvShowComments;
    @BindView(R.id.toolbarTvShowComments)
    Toolbar toolbar;

    Intent intent;
    Bundle extras;

    String title, backdrop;
    int tvShowID;
    float rating;

    TvShowCommentsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_comments);
        ButterKnife.bind(this);
        presenter.setBaseView(this);

        intent = getIntent();
        extras = intent.getExtras();

        title = extras.getString(Constants.TV_SHOW_TITLE);
        backdrop = extras.getString(Constants.TV_SHOW_BACKDROP);
        tvShowID = extras.getInt(Constants.TV_SHOW_ID);
        rating = extras.getFloat(Constants.TV_SHOW_RATING);

        presenter.getComments(tvShowID);

        setUpToolbar();

        adapter = new TvShowCommentsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewTvShowComments.setLayoutManager(linearLayoutManager);
        recyclerViewTvShowComments.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTvShowComments.setAdapter(adapter);
    }

    @Override
    public void setData(List<TvShowComments> tvShowComments) {

        textViewTvShowCommentsTitle.setText(title);
        ratingBarTvShowComments.setRating(rating / 2);
        Glide.with(this)
                .load(backdrop)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(imageViewTvshowCommentsPoster);

        if(tvShowComments != null){
            adapter = new TvShowCommentsAdapter(tvShowComments);
            recyclerViewTvShowComments.setAdapter(adapter);
        }

    }

    @Override
    public void setUpToolbar() {

        setSupportActionBar(toolbar);

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
    public void setGetCommentsError() {
        Toast.makeText(TvShowCommentsActivity.this,
                R.string.error_load_comments,
                Toast.LENGTH_SHORT).show();


    }

    @Override
    public void setEmptyFieldError() {
        Toast.makeText(TvShowCommentsActivity.this,
                R.string.empty_comment_error,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setToastCommentSent() {
        Toast.makeText(TvShowCommentsActivity.this,
                R.string.comment_is_sent,
                Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.imageButtonSendTvShowComment)
    public void sendComment(){

        String comment = editTextAddTvShowComment.getText().toString();

        if(!TextUtils.isEmpty(comment)){
            presenter.saveComment(comment,tvShowID);
        }else {
            setEmptyFieldError();
        }

    }
}
