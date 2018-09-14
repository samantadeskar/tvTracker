package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseUserCallback;
import com.example.samanta.tvtrackermvp.firebase.database.MovieCommentCallback;
import com.example.samanta.tvtrackermvp.firebase.database.MovieSendCommentCallback;
import com.example.samanta.tvtrackermvp.pojo.MovieComments;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.movies.details.MovieCommentsView;

import java.util.List;

public class MovieCommentsPresenterImpl implements MovieCommentsPresenter {

    private MovieCommentsView view;
    AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    DatabaseHelper databaseHelper = new DatabaseHelperImpl();

    @Override
    public void setBaseView(MovieCommentsView view) {

        this.view = view;

    }

    @Override
    public void saveComment(final String comment, final int movieID) {

        final String userID = authenticationHelper.getCurrentUserID();

        databaseHelper.getUserInfo(new DatabaseUserCallback() {
            @Override
            public void onCallback(User user) {
                String username = user.getUsername();
                String profilePicture = user.getImage();

                databaseHelper.saveMovieComment(comment, movieID, userID, username, profilePicture, new MovieSendCommentCallback() {
                    @Override
                    public void onMovieSendCallback(boolean isSend) {
                        if (isSend) {
                            getComments(movieID);
                            view.setToastCommentSent();
                        }
                    }
                });

            }
        },userID);
    }


    @Override
    public void getComments(int movieID) {

        databaseHelper.getMovieComments(movieID, new MovieCommentCallback() {
            @Override
            public void onMovieCommentCallback(List<MovieComments> commentList) {

                if(commentList!=null){
                    view.setData(commentList);
                }
                else {
                    view.setGetCommentsError();
                }
            }
        });

    }
}
