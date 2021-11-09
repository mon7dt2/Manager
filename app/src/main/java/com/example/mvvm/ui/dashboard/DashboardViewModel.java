package com.example.mvvm.ui.dashboard;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.base.BaseViewModel;
import com.example.mvvm.model.response.StatisticResponse;
import com.example.mvvm.repository.Repository;
import com.example.mvvm.utils.Utils;

import javax.inject.Inject;

/**
 * Created by Mon7 on 9/30/2021.
 */
public class DashboardViewModel extends BaseViewModel{
    private Repository repository;

    private MutableLiveData<StatisticResponse> response = new MutableLiveData<>();
    private MutableLiveData<String> failure = new MutableLiveData<>();

    public MutableLiveData<StatisticResponse> getResponse() {
        return response;
    }

    public MutableLiveData<String> getFailure() {
        return failure;
    }

    @Inject
    public DashboardViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getStatistic(){
        compositeDisposable.add(repository.getStatistic()
                .doOnSubscribe(d -> loading.setValue(true))
                .doFinally(() -> loading.setValue(false))
                .subscribe(res -> response.setValue(res)
                        , throwable -> {
                            failure.setValue(throwable.getMessage());
                            Log.d(Utils.myLog, throwable.getMessage());
                        }));
    }
}
