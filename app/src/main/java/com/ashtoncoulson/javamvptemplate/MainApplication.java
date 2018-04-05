package com.ashtoncoulson.javamvptemplate;

import android.app.Activity;
import android.app.Application;

import com.ashtoncoulson.javamvptemplate.dagger.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

public class MainApplication extends Application implements HasActivityInjector {

    public static final int BUILD_NUMBER = BuildConfig.VERSION_CODE;
    public static final String VERSION_NAME = BuildConfig.VERSION_NAME;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeDagger();
        initializeTimber();
    }

    private void initializeDagger() {
        DaggerApplicationComponent.create()
                .inject(this);
    }
    private void initializeTimber() {
        if (isDebuggable()) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static boolean isDebuggable() { // Could be moved into an application state utility
        return BuildConfig.DEBUG;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

}
