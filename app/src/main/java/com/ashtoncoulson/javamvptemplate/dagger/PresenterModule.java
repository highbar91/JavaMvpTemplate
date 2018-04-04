package com.ashtoncoulson.javamvptemplate.dagger;

import com.ashtoncoulson.javamvptemplate.feature.main.MainContract;
import com.ashtoncoulson.javamvptemplate.feature.main.MainPresenter;
import com.ashtoncoulson.javamvptemplate.fragment.bluesquare.BlueSquareContract;
import com.ashtoncoulson.javamvptemplate.fragment.bluesquare.BlueSquarePresenter;
import com.ashtoncoulson.javamvptemplate.fragment.orangesquare.OrangeSquareContract;
import com.ashtoncoulson.javamvptemplate.fragment.orangesquare.OrangeSquarePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    //Break these out into Activity and Fragment presenter Modules if this grows.

    @Provides
    MainContract.Presenter provideMainContractPresenter() {
        return new MainPresenter();
    }

    @Provides
    BlueSquareContract.Presenter provideBlueSquareContractPresenter() {
        return new BlueSquarePresenter();
    }

    @Provides
    OrangeSquareContract.Presenter provideOrangeSquareContractPresenter() {
        return new OrangeSquarePresenter();
    }
}
