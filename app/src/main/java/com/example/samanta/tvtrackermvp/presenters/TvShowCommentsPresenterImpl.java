package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseUserCallback;
import com.example.samanta.tvtrackermvp.firebase.database.TvShowCommentsCallback;
import com.example.samanta.tvtrackermvp.firebase.database.TvShowSendCommentCallback;
import com.example.samanta.tvtrackermvp.pojo.TvShowComments;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.tvShows.details.TvShowCommentsView;

import java.util.List;

public class TvShowCommentsPresenterImpl implements TvShowCommentsPresenter {

    private TvShowCommentsView view;
    AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    DatabaseHelper databaseHelper = new DatabaseHelperImpl();


    @Override
    public void setBaseView(TvShowCommentsView view) {
        this.view = view;
    }

    @Override
    public void saveComment(final String comment, final int tvShowID) {

        final String userID = authenticationHelper.getCurrentUserID();

        databaseHelper.getUserInfo(new DatabaseUserCallback() {
            @Override
            public void onCallback(User user) {
                String username = user.getUsername();
                String profilePicture = user.getImage();

                databaseHelper.saveTvShowComment
                        (comment, tvShowID, userID, username, profilePicture, new TvShowSendCommentCallback() {
                            @Override
                            public void onTvShowSendCallback(boolean isSend) {
                                if (isSend) {
                                    getComments(tvShowID);
                                    view.setToastCommentSent();
                                }
                            }
                        });
            }
        }, userID);

    }

    @Override
    public void getComments(int tvShowID) {

        databaseHelper.getTvShowComments(tvShowID, new TvShowCommentsCallback() {
            @Override
            public void onTvShowCommentCallback(List<TvShowComments> tvShowCommentsList){
                if(tvShowCommentsList!=null){
                    view.setData(tvShowCommentsList);
                }
                else {
                    view.setGetCommentsError();
                }
            }

        });

    }
}
