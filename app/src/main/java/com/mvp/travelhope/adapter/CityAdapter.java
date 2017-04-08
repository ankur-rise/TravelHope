package com.mvp.travelhope.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.mvp.travelhope.R;
import com.mvp.travelhope.models.City;

import java.util.List;

/**
 * Adapter for citiies in autocompleteview
 */

public class CityAdapter extends BaseAdapter implements Filterable {
    private List<City> mCityList;
    private LayoutInflater mLayInflator;

    public CityAdapter(List<City> cityList, Context context) {
        this.mCityList = cityList;
        mLayInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mCityList.size();
    }

    @Override
    public City getItem(int position) {
        return mCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = mLayInflator.inflate(R.layout.simple_dropdown_item_2line, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.tv_name)).setText(getItem(position).getAddress());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
