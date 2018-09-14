package com.example.samanta.tvtrackermvp.pojo;

public class TvShowComments {

    private String username;
    private String comment;
    private int tvShowID;
    private String profilePicture;
    private String userID;

    public TvShowComments() {
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTvShowID() {
        return tvShowID;
    }

    public void setTvShowID(int tvShowID) {
        this.tvShowID = tvShowID;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
