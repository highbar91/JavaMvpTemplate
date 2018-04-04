package com.ashtoncoulson.javamvptemplate.feature.main;

import com.ashtoncoulson.javamvptemplate.core.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private boolean isBlueFragmentShowing = true;

    @Override
    public void toggleFragments() {
        if(isBlueFragmentShowing) {
            view.setOrangeFragment();
            isBlueFragmentShowing = false;
        }
        else {
            view.setBlueFragment();
            isBlueFragmentShowing = true;
        }
    }
}
