package com.mvp.travelhope.presenters;

import android.content.Context;
import android.util.Log;

import com.mvp.travelhope.contracts.SearchContract;
import com.mvp.travelhope.models.Address;
import com.mvp.travelhope.models.City;
import com.mvp.travelhope.network.NWHelper;
import com.mvp.travelhope.network.NetworkInterface;
import com.mvp.travelhope.network.NetworkListener;

import java.util.HashMap;
import java.util.List;

/**
 * Presenter to handle search fragment functionality
 */

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View mView;
    private NetworkInterface mNwHelper;
    private Context mContext;
    private static final String URL = "http://build2.ixigo" + "" +
            ".com/action/content/zeus/autocomplete?searchFor=tpAutoComplete&neCategories=City&query=%s";

    public SearchPresenter(SearchContract.View view, Context context) {
        mView = view;
        mNwHelper = new NWHelper<>();
        mContext = context;
    }

    public void searchForText(String text, final boolean isOrigin) {
        mNwHelper.get(mContext, new NetworkListener<Address>() {

            @Override
            public void onSuccess(Address response, int reqType) {
                List<City> cityList = response.getCityList();
                assert cityList!=null;
                if (isOrigin) {
                        mView.updateOriginCityList(cityList);
                } else {
                    mView.updateDestinationCityList(cityList);
                }
            }

            @Override
            public void onError(String response, int reqType) {

            }
        }, String.format(URL, text), Address.class, new HashMap<String, String>(), -1, null);

    }
}
