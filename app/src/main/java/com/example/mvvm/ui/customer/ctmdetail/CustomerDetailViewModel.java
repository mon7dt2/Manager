package com.example.mvvm.ui.customer.ctmdetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.base.BaseViewModel;
import com.example.mvvm.model.response.ProfileResponse;
import com.example.mvvm.repository.Repository;

import javax.inject.Inject;

/**
 * Created by Mon7 on 10/3/2021.
 */
public class CustomerDetailViewModel extends BaseViewModel{
    private Repository repository;

    private MutableLiveData<ProfileResponse> response = new MutableLiveData<>();
    private MutableLiveData<String> failure = new MutableLiveData<>();

    public MutableLiveData<ProfileResponse> getResponse() {
        return response;
    }

    public MutableLiveData<String> getFailure() {
        return failure;
    }

    @Inject
    public CustomerDetailViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getCustomerProfile(String id){
        compositeDisposable.add(repository.getProfile(id)
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                }).doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(res -> {
                    response.setValue(res);
                }, throwable -> {
                    failure.setValue(throwable.getMessage());
                }));
    }
}
