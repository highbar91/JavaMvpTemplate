package com.ashtoncoulson.javamvptemplate.dagger;

import com.ashtoncoulson.javamvptemplate.feature.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = {PresenterModule.class, FragmentModule.class, RetrofitApiModule.class})
	abstract MainActivity contributeMainActivityInjector();

}
