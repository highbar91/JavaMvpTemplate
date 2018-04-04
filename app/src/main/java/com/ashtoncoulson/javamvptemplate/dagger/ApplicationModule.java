package com.ashtoncoulson.javamvptemplate.dagger;

import com.ashtoncoulson.javamvptemplate.MainApplication;

import dagger.Module;

@Module
public class ApplicationModule {

	private MainApplication mainApplication;

	public ApplicationModule(MainApplication mainApplication) {
		this.mainApplication = mainApplication;
	}

	// Put items to inject in the application class here with @Provides
}
