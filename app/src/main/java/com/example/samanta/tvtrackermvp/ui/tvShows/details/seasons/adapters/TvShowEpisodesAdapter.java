package com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.listeners.TvShowEpisodeClickListener;
import com.example.samanta.tvtrackermvp.pojo.TvShowEpisode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TvShowEpisodesAdapter extends RecyclerView.Adapter<TvShowEpisodesAdapter.TvShowEpisodesViewHolder> {


    private TvShowEpisodeClickListener tvShowEpisodeClickListener;
    private List<TvShowEpisode> tvShowEpisodeList;

    public TvShowEpisodesAdapter() {
        tvShowEpisodeList = new ArrayList<>();
    }

    public TvShowEpisodesAdapter(TvShowEpisodeClickListener clickListener) {
        this.tvShowEpisodeClickListener = clickListener;
        tvShowEpisodeList = new ArrayList<>();
    }

    public void setTvShowEpisodeList(List<TvShowEpisode> episodeList, List<TvShowEpisode> watchedTvShowEpisodes) {
        if (episodeList != null) {
            this.tvShowEpisodeList.clear();
            this.tvShowEpisodeList.addAll(episodeList);

            if (watchedTvShowEpisodes != null) {
                for (TvShowEpisode tvShowEpisode : tvShowEpisodeList) {
                    for (TvShowEpisode watchedTvShowEpisode : watchedTvShowEpisodes) {
                        if (tvShowEpisode.getEpisode_id() == watchedTvShowEpisode.getEpisode_id()) {
                            tvShowEpisode.setWatched("true");
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public TvShowEpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.episode_item, parent, false);
        return new TvShowEpisodesViewHolder(view, tvShowEpisodeClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowEpisodesViewHolder holder, int position) {

        holder.bind(tvShowEpisodeList.get(position));

    }

    @Override
    public int getItemCount() {
        return tvShowEpisodeList.size();
    }

    public void setAllTvShowEpisodeList(List<TvShowEpisode> episodeList) {

        if (episodeList != null) {
            this.tvShowEpisodeList.clear();
            this.tvShowEpisodeList.addAll(episodeList);
            }
            notifyDataSetChanged();
        }



    public class TvShowEpisodesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewEpisodeNumber)
        TextView textViewEpisodeNumber;
        @BindView(R.id.textViewEpisodeName)
        TextView textViewEpisodeName;
        @BindView(R.id.textViewEpisodeReleaseDate)
        TextView textViewEpisodeReleaseDate;
        @BindView(R.id.textViewEpisodeWatched)
        TextView textViewEpisodeWatched;
        @BindView(R.id.imageButtonMarkEpisodeAsWatched)
        ImageButton imageButtonMarkEpisodeAsWatched;


        public TvShowEpisodesViewHolder(View itemView, TvShowEpisodeClickListener clickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(TvShowEpisode tvShowEpisode) {

            textViewEpisodeNumber.setText(String.valueOf(tvShowEpisode.getEpisode_number()));
            textViewEpisodeName.setText(tvShowEpisode.getEpisodeName());
            textViewEpisodeReleaseDate.setText(tvShowEpisode.getEpisodeAirDate());

            if (tvShowEpisode.getWatched().equals("false")) {
                textViewEpisodeWatched.setVisibility(View.INVISIBLE);
                imageButtonMarkEpisodeAsWatched.setVisibility(View.VISIBLE);
            } else {
                textViewEpisodeWatched.setVisibility(View.VISIBLE);
                imageButtonMarkEpisodeAsWatched.setVisibility(View.INVISIBLE);
            }
        }

        @OnClick(R.id.imageButtonMarkEpisodeAsWatched)
        public void onButtonClick() {
            tvShowEpisodeClickListener.onClick(tvShowEpisodeList.get(getAdapterPosition()));
        }
    }
}
