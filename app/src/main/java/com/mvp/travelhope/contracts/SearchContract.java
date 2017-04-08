package com.mvp.travelhope.contracts;

import com.mvp.travelhope.models.City;

import java.util.List;

/**
 * Created by ankurchaudhary on 08-04-2017.
 */

public class SearchContract {
    public interface View {

        void updateOriginCityList(List<City> cityList);

        void updateDestinationCityList(List<City> cityList);
    }

    public interface Presenter {

    }

}
