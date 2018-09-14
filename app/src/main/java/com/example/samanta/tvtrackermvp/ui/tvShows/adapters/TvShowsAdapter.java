package com.example.samanta.tvtrackermvp.ui.tvShows.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.listeners.AddTvShowToWatchlistClickListener;
import com.example.samanta.tvtrackermvp.listeners.TvShowClickListener;
import com.example.samanta.tvtrackermvp.pojo.TvShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder> {

    private TvShowClickListener tvShowClickListener;
    private final List<TvShow> tvShowList;
    private AddTvShowToWatchlistClickListener addTvShowToWatchlistClickListener;

    public TvShowsAdapter() {
        tvShowList = new ArrayList<>();
    }

    public TvShowsAdapter(TvShowClickListener listener,
                          AddTvShowToWatchlistClickListener addTvShowToWatchlistClickListener) {
        this.tvShowClickListener = listener;
        this.tvShowList = new ArrayList<>();
        this.addTvShowToWatchlistClickListener = addTvShowToWatchlistClickListener;
    }

    public void setTvShows(List<TvShow> tvShows) {
        if (tvShows != null) {
            this.tvShowList.clear();
            this.tvShowList.addAll(tvShows);
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public TvShowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tv_show_item, parent, false);
        return new TvShowsViewHolder(view, tvShowClickListener, addTvShowToWatchlistClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsViewHolder holder,
                                 int position) {

        holder.bind(tvShowList.get(position));

    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    public void addTvShows(List<TvShow> tvShows) {
        this.tvShowList.addAll(tvShows);
        notifyDataSetChanged();
    }

    class TvShowsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewTvShowPoster)
        ImageView imageViewTvShowPoster;
        @BindView(R.id.textViewTvShowTitle)
        TextView textViewTvShowTitle;
        @BindView(R.id.textViewTvShowRating)
        TextView textViewTvShowRating;
        @BindView(R.id.textViewTvShowReleaseDate)
        TextView textViewTvShowReleaseDate;


        public TvShowsViewHolder(View itemView, TvShowClickListener tvShowClickListener,
                                 AddTvShowToWatchlistClickListener addTvShowToWatchlistClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        public void onTvShowClick() {

            tvShowClickListener.onClick(tvShowList.get(getAdapterPosition()));

        }

        @OnClick(R.id.buttonAddToWatchlistTvShow)
        public void onButtonClick(){
            addTvShowToWatchlistClickListener.onClickButton(tvShowList.get(getAdapterPosition()));
        }

        public void bind(TvShow tvShow) {

            String date = tvShow.getFirstAirDate();

            if(date!=null){
                String[] splitDate = date.split("-");
                String year = splitDate[0];
                textViewTvShowReleaseDate.setText(year);
            }
            else {
                textViewTvShowReleaseDate.setText(date);
            }

            textViewTvShowTitle.setText(tvShow.getTitle());
            textViewTvShowRating.setText(String.valueOf(tvShow.getRating()));

            Glide.with(itemView)
                    .load(tvShow.getPoster())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(imageViewTvShowPoster);

        }
    }
}
