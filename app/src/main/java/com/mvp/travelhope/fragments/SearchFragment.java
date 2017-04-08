package com.mvp.travelhope.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.mvp.travelhope.R;
import com.mvp.travelhope.adapter.CityAdapter;
import com.mvp.travelhope.contracts.SearchContract;
import com.mvp.travelhope.models.City;
import com.mvp.travelhope.presenters.SearchPresenter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass. Avail user search options
 */
public class SearchFragment extends Fragment implements SearchContract.View {
    private static final String KEY_RECOMMENDATION = "recommendations";
    private OnFragmentInteractionListener mListener;
    private AutoCompleteTextView mOrigin, mDestination;
    private SearchPresenter mPresenter;
    private CityAdapter mOriginCityAdapter, mDestinationCityAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param recommendations .
     * @return A new instance of fragment SearchFragment.
     */
    public static SearchFragment newInstance(boolean recommendations) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putBoolean(KEY_RECOMMENDATION, recommendations);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SearchPresenter(this, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            boolean recommendation = bundle.getBoolean(KEY_RECOMMENDATION);
            if (recommendation) {
                view.findViewById(R.id.iv_nav).setVisibility(View.GONE);
                view.findViewById(R.id.et_destination).setVisibility(View.GONE);
                Button btnText = (Button) view.findViewById(R.id.btn_search);
                btnText.setText(getString(R.string.show_option));
            }
        }

        mOrigin = (AutoCompleteTextView) view.findViewById(R.id.et_origin);
        mOrigin.addTextChangedListener(mOriginTextWatcher);
        mDestination = (AutoCompleteTextView) view.findViewById(R.id.et_destination);
        mDestination.addTextChangedListener(mDestinationTextWatcher);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private TextWatcher mOriginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mPresenter.searchForText(s.toString(), true);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher mDestinationTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mPresenter.searchForText(s.toString(), false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void updateOriginCityList(List<City> cityList) {
        if (mOriginCityAdapter == null) {
            mOriginCityAdapter = new CityAdapter(cityList, getActivity());
            mOrigin.setAdapter(mOriginCityAdapter);
        }else{
            mOriginCityAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void updateDestinationCityList(List<City> cityList) {
        if (mDestinationCityAdapter == null) {
            mDestinationCityAdapter = new CityAdapter(cityList, getActivity());
            mOrigin.setAdapter(mDestinationCityAdapter);
        }else{
            mDestinationCityAdapter.notifyDataSetChanged();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
