package com.ashtoncoulson.javamvptemplate.dagger;

import com.ashtoncoulson.javamvptemplate.fragment.bluesquare.BlueSquareFragment;
import com.ashtoncoulson.javamvptemplate.fragment.orangesquare.OrangeSquareFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract BlueSquareFragment contributeBlueSquareFragment();

    @ContributesAndroidInjector
    abstract OrangeSquareFragment contributeOrangeSquareFragment();

}
