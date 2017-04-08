package com.mvp.travelhope.contracts;

/**
 * Created by ankurchaudhary on 08-04-2017.
 */

public class HomeContract {

    public interface View {
        boolean getRecommendation();

        void moveToOption(boolean recommendation);
    }

    public interface Presenter {

    }

}
