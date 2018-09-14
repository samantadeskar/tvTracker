package com.example.samanta.tvtrackermvp.firebase.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.pojo.MovieComments;
import com.example.samanta.tvtrackermvp.pojo.TvShow;
import com.example.samanta.tvtrackermvp.pojo.TvShowComments;
import com.example.samanta.tvtrackermvp.pojo.TvShowEpisode;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.pojo.WatchlistMovies;
import com.example.samanta.tvtrackermvp.pojo.WatchlistTvShows;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHelperImpl implements DatabaseHelper {

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    private User user;
    private Query watchlistMoviesMovie;
    private Query watchlistTvShowsTvShow;


    @Override
    public void saveUser(final User user) {


        final String userID = user.getUserID();
        final String username = user.getUsername();
        final String email = user.getEmail();
        final String password = user.getPassword();

        final DatabaseReference currentUserReference = reference.child("users").child(userID);

        currentUserReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Map<String, String> dataToSave = new HashMap<>();
                    dataToSave.put("username", username);
                    dataToSave.put("email", email);
                    dataToSave.put("password", password);
                    dataToSave.put("image", "https://blakemedical.ca/wp-content/uploads/noimage.png");
                    dataToSave.put("status", "No status");
                    dataToSave.put("userID", userID);
                    dataToSave.put("followed", "false");

                    currentUserReference
                            .setValue(dataToSave).addOnSuccessListener
                            (new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("REGISTER_USER", "Registered:"
                                            + user.getUsername() + ", " + user.getEmail());
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void updateUserProfile(String username, String email, String status, String userID) {

        reference.child("users").child(userID).child("username").setValue(username);
        reference.child("users").child(userID).child("email").setValue(email);
        reference.child("users").child(userID).child("status").setValue(status);

    }

    @Override
    public void updateUserProfilePic(String uri, String userID) {

        reference.child("users").child(userID).child("image").setValue(uri);

    }

    @Override
    public void getUserInfo(final DatabaseUserCallback databaseUserCallback, String currentUser) {

        user = new User();
        DatabaseReference userInfo = reference.child("users").child(currentUser);
        userInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    user = dataSnapshot.getValue(User.class);

                    databaseUserCallback.onCallback(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void addMovieToWatchlist(final WatchlistMoviesCallback moviesCallback,
                                    final Movie movie, final String userID) {


        final DatabaseReference watchlistReference = reference.child("watchlistMovies")
                .child(userID).child(String.valueOf(movie.getId()));

        watchlistReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    moviesCallback.onCallbackWatchlistMovies(true);
                } else {

                    watchlistReference.child("title").setValue(movie.getTitle());
                    watchlistReference.child("id").setValue(movie.getId());
                    watchlistReference.child("poster").setValue(movie.getPoster());
                    watchlistReference.child("backdrop").setValue(movie.getBackdrop());
                    watchlistReference.child("description").setValue(movie.getDescription());
                    watchlistReference.child("releaseDate").setValue(movie.getReleaseDate());
                    watchlistReference.child("rating").setValue(movie.getRating());
                    watchlistReference.child("watched").setValue("false");
                    moviesCallback.onCallbackWatchlistMovies(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void addTvShowToWatchlist(final WatchlistTvShowsCallback watchlistTvShowsCallback,
                                     final TvShow tvShow, final String userID) {


        final DatabaseReference watchlistReference = reference.child("watchlistTvShows")
                .child(userID).child(String.valueOf(tvShow.getId()));

        watchlistReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    watchlistTvShowsCallback.onCallbackWatchlistTvShows(true);
                } else {

                    watchlistReference.child("title").setValue(tvShow.getTitle());
                    watchlistReference.child("id").setValue(tvShow.getId());
                    watchlistReference.child("poster").setValue(tvShow.getPoster());
                    watchlistReference.child("backdrop").setValue(tvShow.getBackdrop());
                    watchlistReference.child("description").setValue(tvShow.getDescription());
                    watchlistReference.child("firstAirDate").setValue(tvShow.getFirstAirDate());
                    watchlistReference.child("rating").setValue(tvShow.getRating());
                    watchlistTvShowsCallback.onCallbackWatchlistTvShows(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void getWatchedMovies(final WatchedMoviesCallback watchedMoviesCallback, String userID) {

        final List<Movie> movieList = new ArrayList<>();
        final DatabaseReference watchedMoviesReference = reference.child("watchlistMovies")
                .child(userID);
        Query movies = watchedMoviesReference.orderByChild("watched").equalTo("true");
        movies.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Movie movie = new Movie();

                        movie.setTitle(ds.getValue(Movie.class).getTitle());
                        movie.setWatched(ds.getValue(Movie.class).getWatched());
                        movie.setReleaseDate(ds.getValue(Movie.class).getReleaseDate());
                        movie.setRating(ds.getValue(Movie.class).getRating());
                        movie.setPoster(ds.getValue(Movie.class).getPoster());
                        movie.setId(ds.getValue(Movie.class).getId());
                        movie.setDescription(ds.getValue(Movie.class).getDescription());
                        movie.setBackdrop(ds.getValue(Movie.class).getBackdrop());
                        movieList.add(movie);

                    }
                    watchedMoviesCallback.onWatchedMoviesCallback(movieList);
                } else {
                    watchedMoviesCallback.onWatchedMoviesCallback(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void markMovieAsWatched(final Movie movie, final String userID) {
        final DatabaseReference movieReference = reference.child("watchlistMovies")
                .child(userID).child(String.valueOf(movie.getId()));
        movieReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    movieReference.child("watched").setValue("true");

                } else {
                    movieReference.child("title").setValue(movie.getTitle());
                    movieReference.child("id").setValue(movie.getId());
                    movieReference.child("poster").setValue(movie.getPoster());
                    movieReference.child("backdrop").setValue(movie.getBackdrop());
                    movieReference.child("description").setValue(movie.getDescription());
                    movieReference.child("releaseDate").setValue(movie.getReleaseDate());
                    movieReference.child("rating").setValue(movie.getRating());
                    movieReference.child("watched").setValue("true");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void saveMovieComment(String comment, int movieID, String userID,
                                 String username, String profilePicture,
                                 MovieSendCommentCallback movieSendCommentCallback) {

        DatabaseReference commentsReference = reference.child("movieComments").child(String.valueOf(movieID));
        DatabaseReference newEntry = commentsReference.push();

        try {
            newEntry.child("comment").setValue(comment);
            newEntry.child("userID").setValue(userID);
            newEntry.child("username").setValue(username);
            newEntry.child("profilePicture").setValue(profilePicture);
            movieSendCommentCallback.onMovieSendCallback(true);
        } catch (DatabaseException e) {
            Log.d("COMMENT_EXEPCITION", e.toString());
            movieSendCommentCallback.onMovieSendCallback(false);
        }

    }

    @Override
    public void getMovieComments(int movieID, final MovieCommentCallback movieCommentCallback) {

        final List<MovieComments> commentsList = new ArrayList<>();
        DatabaseReference commentsReference = reference.child("movieComments").child(String.valueOf(movieID));
        commentsReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                commentsList.add(ds.getValue(MovieComments.class));
                                movieCommentCallback.onMovieCommentCallback(commentsList);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void getWatchlistMovies(String userID, final WatchlistMovieCallback watchlistMovieCallback) {

        final List<WatchlistMovies> movieList = new ArrayList<>();
        DatabaseReference watchlistMovies = reference.child("watchlistMovies").child(userID);
        watchlistMovies.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        movieList.add(ds.getValue(WatchlistMovies.class));

                    }
                    watchlistMovieCallback.onCallbackWatchlistMovie(movieList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void saveTvShowComment(String comment, int tvShowID, String userID,
                                  String username, String profilePicture,
                                  TvShowSendCommentCallback tvShowSendCommentCallback) {

        DatabaseReference commentsReference = reference.child("tvShowComments").child(String.valueOf(tvShowID));
        DatabaseReference newEntry = commentsReference.push();

        try {
            newEntry.child("comment").setValue(comment);
            newEntry.child("tvShowID").setValue(tvShowID);
            newEntry.child("userID").setValue(userID);
            newEntry.child("username").setValue(username);
            newEntry.child("profilePicture").setValue(profilePicture);
            tvShowSendCommentCallback.onTvShowSendCallback(true);
        } catch (DatabaseException e) {
            Log.d("COMMENT_EXCEPTION", e.toString());
            tvShowSendCommentCallback.onTvShowSendCallback(false);
        }

    }

    @Override
    public void getTvShowComments(int tvShowID, final TvShowCommentsCallback tvShowCommentsCallback) {

        final List<TvShowComments> commentsList = new ArrayList<>();
        DatabaseReference commentsReference = reference.child("tvShowComments").child(String.valueOf(tvShowID));
        commentsReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                commentsList.add(ds.getValue(TvShowComments.class));
                                tvShowCommentsCallback.onTvShowCommentCallback(commentsList);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void markEpisodeAsWatched(TvShowEpisode tvShowEpisode, String userID, int seasonNumber, WatchedEpisodeCallback watchedEpisodeCallback) {

        final DatabaseReference newEntry = reference.child("watchedEpisodes").child(userID).push();

        try {

            newEntry.child("episodeName").setValue(tvShowEpisode.getEpisodeName());
            newEntry.child("episodeAirDate").setValue(tvShowEpisode.getEpisodeAirDate());
            newEntry.child("episode_number").setValue(tvShowEpisode.getEpisode_number());
            newEntry.child("episode_id").setValue(tvShowEpisode.getEpisode_id());
            newEntry.child("watched").setValue("true");
            newEntry.child("userID").setValue(userID);
            watchedEpisodeCallback.onWatchedEpisodeCallback(true);

        } catch (DatabaseException e) {
            watchedEpisodeCallback.onWatchedEpisodeCallback(false);
        }
    }

    @Override
    public void getWatchedEpisodes(String userID, final AllWatchedEpisodesCallback allWatchedEpisodesCallback) {

        final List<TvShowEpisode> tvShowEpisodes = new ArrayList<>();
        final DatabaseReference watchedReference = reference.child("watchedEpisodes").child(userID);
        watchedReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        TvShowEpisode tvShowEpisode = new TvShowEpisode();

                        tvShowEpisode.setEpisodeName(ds.getValue(TvShowEpisode.class).getEpisodeName());
                        tvShowEpisode.setWatched(ds.getValue(TvShowEpisode.class).getWatched());
                        tvShowEpisode.setEpisode_id(ds.getValue(TvShowEpisode.class).getEpisode_id());
                        tvShowEpisode.setEpisode_number(ds.getValue(TvShowEpisode.class).getEpisode_number());
                        tvShowEpisode.setEpisodeAirDate(ds.getValue(TvShowEpisode.class).getEpisodeAirDate());

                        tvShowEpisodes.add(tvShowEpisode);

                    }
                    allWatchedEpisodesCallback.onAllWatchedEpisodesCallback(tvShowEpisodes);
                } else {
                    allWatchedEpisodesCallback.onAllWatchedEpisodesCallback(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void getWatchlistTvShows(String userID, final WatchlistTvShowCallback watchlistTvShowCallback) {

        final List<WatchlistTvShows> tvShowsList = new ArrayList<>();
        DatabaseReference watchlistTvShows = reference.child("watchlistTvShows").child(userID);
        watchlistTvShows.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        tvShowsList.add(ds.getValue(WatchlistTvShows.class));

                    }
                    watchlistTvShowCallback.onWatchlistTvShowCallback(tvShowsList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void getAllUsers(final AllUsersCallback allUsersCallback) {

        DatabaseReference allUsers = reference.child("users");
        allUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    List<User> userList = new ArrayList<>();
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        userList.add(ds.getValue(User.class));
                    }
                    allUsersCallback.onAllUsersCallback(userList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void getFollowedUsers(String userID, final FollowedUsersCallback followedUsersCallback) {

        final List<User> followedUsers= new ArrayList<>();
        final DatabaseReference followedUsersReference = reference.child("followedUsers").child(userID);
        followedUsersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        User followedUser = new User();

                        followedUser.setImage(ds.getValue(User.class).getImage());
                        followedUser.setStatus(ds.getValue(User.class).getStatus());
                        followedUser.setUsername(ds.getValue(User.class).getUsername());
                        followedUser.setFollowed(ds.getValue(User.class).getFollowed());
                        followedUser.setUserID(ds.getValue(User.class).getUserID());

                        followedUsers.add(followedUser);

                    }
                    followedUsersCallback.onFollowedUsersCallback(followedUsers);
                } else {
                    followedUsersCallback.onFollowedUsersCallback(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void followUser(String currentUserID, final User user, final FollowUserCallback followUserCallback) {

        final DatabaseReference followUserReference = reference.child("followedUsers")
                .child(currentUserID).child(user.getUserID());
        followUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    followUserCallback.onFollowUserCallback(true);
                }
                else {
                    followUserReference.child("username").setValue(user.getUsername());
                    followUserReference.child("image").setValue(user.getImage());
                    followUserReference.child("status").setValue(user.getStatus());
                    followUserReference.child("userID").setValue(user.getUserID());
                    followUserReference.child("followed").setValue("true");
                    followUserCallback.onFollowUserCallback(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void unfollowUser(User user, String userID, UnfollowUserCallback unfollowUserCallback) {

        DatabaseReference usersReference = reference.child("followedUsers")
                .child(userID)
                .child(user.getUserID());

        try {
            usersReference.removeValue();
            unfollowUserCallback.onUnfollowUserCallback(true);
        }catch (DatabaseException databaseExepction){
            unfollowUserCallback.onUnfollowUserCallback(false);
        }

    }
}

