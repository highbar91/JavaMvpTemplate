package com.ashtoncoulson.javamvptemplate.fragment.bluesquare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashtoncoulson.javamvptemplate.R;
import com.ashtoncoulson.javamvptemplate.core.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BlueSquareFragment extends BaseFragment implements BlueSquareContract.View {

    @Inject
    BlueSquareContract.Presenter presenter;

    public static BlueSquareFragment newInstance() {
        return new BlueSquareFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blue_square, container, false);

        //Bind for View Injection
        ButterKnife.bind(this, view);

        //Register View with the Presenter
        presenter.registerView(this);

        return view;
    }
}
