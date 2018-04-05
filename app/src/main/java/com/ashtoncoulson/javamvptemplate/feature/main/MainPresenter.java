package com.ashtoncoulson.javamvptemplate.feature.main;

import com.ashtoncoulson.javamvptemplate.api.ApiService;
import com.ashtoncoulson.javamvptemplate.core.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private ApiService apiService;
    private CompositeDisposable disposables = new CompositeDisposable();

    private boolean isBlueFragmentShowing = true;

    public MainPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void deregister() {
        super.deregister();
        disposables.dispose();
    }

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
