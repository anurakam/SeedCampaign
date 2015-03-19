package com.psu.seedcampaign;

/**
 * Created by อนุรักษ์ on 12/3/2558.
 */
public class PlantingTable {
    String userName,picName,nickName,flowerName,amount,locationName,region;
   // int id_User;

    public PlantingTable(String userName, String picName, String nickName, String flowerName,String amount, String locationName, String region) {
        super();
        this.userName = userName;
        this.picName = picName;
        this.nickName = nickName;
        this.flowerName = flowerName;
        this.amount = amount;
        this.locationName = locationName;
        this.region = region;
    }

    public PlantingTable() {
        super();
        this.userName = null;
        this.picName = null;
        this.nickName = null;
        this.flowerName = null;
        this.amount = null;
        this.locationName = null;
        this.region = null;

    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
