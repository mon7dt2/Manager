package com.example.mvvm.ui.customer;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.base.BaseViewModel;
import com.example.mvvm.model.response.CustomerResponse;
import com.example.mvvm.repository.Repository;

import javax.inject.Inject;

/**
 * Created by Mon7 on 10/3/2021.
 */
public class CustomerViewModel extends BaseViewModel{
    private Repository repository;

    private MutableLiveData<CustomerResponse> response = new MutableLiveData<>();
    private MutableLiveData<String> failure = new MutableLiveData<>();

    public MutableLiveData<CustomerResponse> getResponse() {
        return response;
    }

    public MutableLiveData<String> getFailure() {
        return failure;
    }

    @Inject
    public CustomerViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getAllCustomers(){
        compositeDisposable.add(repository.getAllCustomers()
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
