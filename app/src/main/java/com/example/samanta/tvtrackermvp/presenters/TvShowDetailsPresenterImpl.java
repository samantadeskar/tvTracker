package com.example.samanta.tvtrackermvp.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.TvShowDetailsView;

import butterknife.BindView;

public class TvShowDetailsPresenterImpl implements TvShowDetailsPresenter{

    private TvShowDetailsView view;

    @Override
    public void setBaseView(TvShowDetailsView view) {
        this.view = view;
    }
}
