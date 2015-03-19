package com.psu.seedcampaign;

/**
 * Created by อนุรักษ์ on 12/3/2558.
 */
public class PlantingDetailsTable {
    String userName,picName,nickName,flowerName,locationName,region;
   // int id_User;

    public PlantingDetailsTable(String picName, String nickName, String flowerName, String locationName, String region) {
        super();

        this.picName = picName;
        this.nickName = nickName;
        this.flowerName = flowerName;
        this.locationName = locationName;
        this.region = region;
    }

    public PlantingDetailsTable() {
        super();

        this.picName = null;
        this.nickName = null;
        this.flowerName = null;
        this.locationName = null;
        this.region = null;

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
