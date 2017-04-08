package com.mvp.travelhope.presenters;

import com.mvp.travelhope.contracts.SearchContract;

/**
 * Presenter to handle search fragment functionality
 */

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View mView;

    public SearchPresenter(SearchContract.View view) {
        mView = view;
    }

    public void searchForText(final boolean isOrigin) {




    }
}
