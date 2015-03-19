package com.psu.seedcampaign;

/**
 * Created by อนุรักษ์ on 19/3/2558.
 */
public class CountPeopleModel {
    String countPeople;
    // int id_User;

    public CountPeopleModel(String countPeople) {

        this.countPeople = countPeople;

    }
    public String getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(String countPeople) {
        this.countPeople = countPeople;
    }
}
