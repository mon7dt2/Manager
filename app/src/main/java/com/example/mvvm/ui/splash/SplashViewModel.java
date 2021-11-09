package com.example.mvvm.ui.splash;

import com.example.mvvm.base.BaseViewModel;
import com.example.mvvm.repository.Repository;

import javax.inject.Inject;

/**
 * Created by Mon7 on 9/29/2021.
 */
public class SplashViewModel extends BaseViewModel{
    private final Repository repository;

    @Inject
    public SplashViewModel(Repository repository) {
        this.repository = repository;
    }
}
