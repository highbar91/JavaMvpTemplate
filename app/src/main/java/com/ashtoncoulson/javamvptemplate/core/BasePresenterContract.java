package com.ashtoncoulson.javamvptemplate.core;

public interface BasePresenterContract<ViewT> {

    void registerView(ViewT view);
    void deregister();
}