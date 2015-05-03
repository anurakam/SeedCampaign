package com.psu.seedcampaign;

/**
 * Created by อนุรักษ์ on 4/5/2558.
 */
public class ImageProfileTable {
    String userName,imageProfile;

    public ImageProfileTable(String userName, String imageProfile) {
        super();
        this.userName = userName;
        this.imageProfile = imageProfile;

    }

    public ImageProfileTable() {
        super();
        this.userName = null;
        this.imageProfile = null;


    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }
}
