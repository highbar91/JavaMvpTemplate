package com.ashtoncoulson.javamvptemplate.dagger;

import com.ashtoncoulson.javamvptemplate.MainApplication;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {AndroidInjectionModule.class, ActivityModule.class, ApplicationModule.class, RetrofitApiModule.class})
public interface ApplicationComponent {

    void inject(MainApplication mainApplication);
}
