package com.mvp.travelhope.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ankurchaudhary on 08-04-2017.
 */

public class Address {
    @SerializedName("value")
    private List<City> cityList;

    public void Address() {
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }



}
