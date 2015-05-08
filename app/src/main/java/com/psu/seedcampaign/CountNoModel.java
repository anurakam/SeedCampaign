package com.psu.seedcampaign;

/**
 * Created by อนุรักษ์ on 8/5/2558.
 */
public class CountNoModel {

    String UserDetailsID;

    public CountNoModel(String UserDetailsID) {

        //this.userName = userName;
        this.UserDetailsID = UserDetailsID;

    }

    /*public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
*/
    public String getCountNo() {
        return UserDetailsID;
    }
}


