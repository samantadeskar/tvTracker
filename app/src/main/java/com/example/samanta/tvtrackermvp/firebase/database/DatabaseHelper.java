package com.example.samanta.tvtrackermvp.firebase.database;

import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.pojo.TvShow;
import com.example.samanta.tvtrackermvp.pojo.TvShowEpisode;
import com.example.samanta.tvtrackermvp.pojo.User;

public interface DatabaseHelper {

    void saveUser(User user);

    void updateUserProfile(String username, String email, String status, String userID);

    void updateUserProfilePic(String uri, String userID);

    void getUserInfo(DatabaseUserCallback databaseUserCallback, String currentUser);

    void addMovieToWatchlist(WatchlistMoviesCallback moviesCallback,Movie movie, String userID);

    void addTvShowToWatchlist(WatchlistTvShowsCallback watchlistTvShowsCallback, TvShow tvShow, String userID);

    void getWatchedMovies(WatchedMoviesCallback watchedMoviesCallback, String userID);

    void markMovieAsWatched(Movie movie, String userID);

    void saveMovieComment(String comment, int movieID, String userID, String username, String profilePicture, MovieSendCommentCallback movieSendCommentCallback);

    void getMovieComments(int movieID, MovieCommentCallback movieCommentCallback);

    void getWatchlistMovies(String userID, WatchlistMovieCallback watchlistMovieCallback);

    void saveTvShowComment(String comment, int tvShowID, String userID, String username, String profilePicture, TvShowSendCommentCallback tvShowSendCommentCallback);

    void getTvShowComments(int tvShowID, TvShowCommentsCallback tvShowCommentsCallback);

    void markEpisodeAsWatched(TvShowEpisode tvShowEpisode, String userID, int SeasonsNumber,WatchedEpisodeCallback watchedEpisodeCallback);

    void getWatchedEpisodes(String userID, AllWatchedEpisodesCallback allWatchedEpisodesCallback);

    void getWatchlistTvShows(String userID, WatchlistTvShowCallback watchlistTvShowCallback);

    void getAllUsers(AllUsersCallback allUsersCallback);

    void getFollowedUsers(String userID, FollowedUsersCallback followedUsersCallback);

    void followUser(String currentUserID, User user, FollowUserCallback followUserCallback);

    void unfollowUser(User user, String userID, UnfollowUserCallback unfollowUserCallback);
}
