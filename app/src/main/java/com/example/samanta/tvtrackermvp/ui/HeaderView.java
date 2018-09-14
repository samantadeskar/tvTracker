package com.example.samanta.tvtrackermvp.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderView {

    @BindView(R.id.textViewHeader)
    TextView textViewHeader;

    @BindView(R.id.imageViewProfilePictureHeader)
    ImageView imageViewProfilePicture;

    public HeaderView(View view, String username, String profilePicture) {
        ButterKnife.bind(this, view);
        textViewHeader.setText(username);

        Glide.with(view)
             .load(profilePicture)
             .apply(RequestOptions.placeholderOf(R.drawable.noimage))
             .into(imageViewProfilePicture);

    }

}
