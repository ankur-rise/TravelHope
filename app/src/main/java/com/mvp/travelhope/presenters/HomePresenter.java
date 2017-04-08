package com.mvp.travelhope.presenters;

import com.mvp.travelhope.contracts.HomeContract;

/**
 * MVP architecture to inject UTC in depth of the code. All the functionality is written here for
 * {@link com.mvp.travelhope.fragments.HomeFragment}
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view) {
        mView = view;
    }

    public void gotoOption() {
        mView.moveToOption(mView.getRecommendation());
    }


}
