package com.ashtoncoulson.javamvptemplate.feature.main;

import com.ashtoncoulson.javamvptemplate.core.BasePresenterContract;

public interface MainContract {

    interface View {

        void setBlueFragment();

        void setOrangeFragment();
    }

    interface Presenter extends BasePresenterContract<View> {

        void toggleFragments();
    }
}
