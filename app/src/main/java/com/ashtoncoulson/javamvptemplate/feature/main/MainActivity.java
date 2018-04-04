package com.ashtoncoulson.javamvptemplate.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.ashtoncoulson.javamvptemplate.R;
import com.ashtoncoulson.javamvptemplate.core.BaseActivity;
import com.ashtoncoulson.javamvptemplate.fragment.bluesquare.BlueSquareFragment;
import com.ashtoncoulson.javamvptemplate.fragment.orangesquare.OrangeSquareFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @BindView(R.id.fragment_container)
    RelativeLayout fragmentContainer;

    @Override
    public Intent getActivityIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.registerView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deregister();
    }

    @OnClick(R.id.swap_button)
    public void onSwapButtonClick() {
        presenter.toggleFragments();
    }

    private void initialize() {
        setBlueFragment();
    }

    public void setBlueFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(fragmentContainer.getId(), BlueSquareFragment.newInstance())
                .commit();
    }
    public void setOrangeFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(fragmentContainer.getId(), OrangeSquareFragment.newInstance())
                .commit();
    }
}
