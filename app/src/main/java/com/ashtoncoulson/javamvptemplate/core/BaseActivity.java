package com.ashtoncoulson.javamvptemplate.core;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract Intent getActivityIntent(Context context);

}
