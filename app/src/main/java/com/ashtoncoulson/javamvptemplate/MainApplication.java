package com.ashtoncoulson.javamvptemplate;

import android.app.Application;

public class MainApplication extends Application {

    private static MainApplication instance;

    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MainApplication getApp() {
        return instance;
    }
}
