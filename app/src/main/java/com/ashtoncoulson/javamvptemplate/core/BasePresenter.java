package com.ashtoncoulson.javamvptemplate.core;

public class BasePresenter<ViewT> implements BasePresenterContract<ViewT> {

    public ViewT view = null;

    @Override
    public void registerView(ViewT view) {
        this.view = view;
    }

    @Override
    public void deregister() {
        view = null;
    }
}
