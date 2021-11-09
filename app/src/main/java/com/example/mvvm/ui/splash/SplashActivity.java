package com.example.mvvm.ui.splash;

import android.content.Intent;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm.R;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivitysplashBinding;
import com.example.mvvm.ui.login.LoginActivity;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Mon7 on 9/29/2021.
 */
public class SplashActivity extends BaseActivity<SplashViewModel, ActivitysplashBinding>{
    private static final long SPLASH_TIME_OUT = 3000;

    @NonNull
    @NotNull
    @Override
    protected SplashViewModel createViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(SplashViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activitysplash;
    }

    @Override
    protected void initView() {
        new Handler().postDelayed(() -> {

            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(i);

            // close this activity
            finish();
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
