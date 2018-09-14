package com.example.samanta.tvtrackermvp.ui.tvShows.details.seasons.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.listeners.TvShowSeasonClickListener;
import com.example.samanta.tvtrackermvp.pojo.TvShowSeasons;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TvShowSeasonsAdapter extends RecyclerView.Adapter<TvShowSeasonsAdapter.TvShowSeasonsViewHolder> {


    private TvShowSeasonClickListener tvShowSeasonsClickListener;
    private final List<TvShowSeasons> tvShowSeasonsList;

    public TvShowSeasonsAdapter() {
        tvShowSeasonsList = new ArrayList<>();
    }

    public TvShowSeasonsAdapter(TvShowSeasonClickListener clickListener) {
        this.tvShowSeasonsClickListener = clickListener;
        this.tvShowSeasonsList = new ArrayList<>();
    }

    public void setTvShowSeasonsList(List<TvShowSeasons> tvShowSeasons) {
        if (tvShowSeasons != null) {
            this.tvShowSeasonsList.clear();
            this.tvShowSeasonsList.addAll(tvShowSeasons);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public TvShowSeasonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seasons_item, parent, false);
        return new TvShowSeasonsViewHolder(view, tvShowSeasonsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowSeasonsViewHolder holder, int position) {

        holder.bind(tvShowSeasonsList.get(position));

    }

    @Override
    public int getItemCount() {
        return tvShowSeasonsList.size();
    }

    public class TvShowSeasonsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewSeasonName)
        TextView textViewSeasonName;
        @BindView(R.id.textViewNumberOfEpisodes)
        TextView textViewNumberOfEpisodes;
        @BindView(R.id.textViewReleaseDate)
        TextView textViewReleaseDate;


        public TvShowSeasonsViewHolder(View itemView, TvShowSeasonClickListener clickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(TvShowSeasons tvShowSeasons) {

            String date = tvShowSeasons.getSeasonAirDate();

            if (date != null) {
                String[] splitDate = date.split("-");
                String year = splitDate[0];
                textViewReleaseDate.setText(year);
            } else {
                textViewReleaseDate.setText(date);
            }
            textViewSeasonName.setText(tvShowSeasons.getSeasonName());
            textViewNumberOfEpisodes.setText(String.valueOf(tvShowSeasons.getEpisodeNumber()));
        }

        @OnClick
        public void onSeasonClick() {
            tvShowSeasonsClickListener.onClick(tvShowSeasonsList.get(getAdapterPosition()));
        }

    }
}
