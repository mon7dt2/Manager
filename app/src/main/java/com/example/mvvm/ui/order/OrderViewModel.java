package com.example.mvvm.ui.order;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.base.BaseViewModel;
import com.example.mvvm.model.response.OrderResponse;
import com.example.mvvm.repository.Repository;

import javax.inject.Inject;

/**
 * Created by Mon7 on 10/1/2021.
 */
public class OrderViewModel extends BaseViewModel{
    private Repository repository;

    private MutableLiveData<OrderResponse> responses = new MutableLiveData<>();
    private MutableLiveData<String> failure = new MutableLiveData<>();

    public MutableLiveData<String> getFailure() {
        return failure;
    }

    public MutableLiveData<OrderResponse> getResponses() {
        return responses;
    }

    @Inject
    public OrderViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getOrders(){
        compositeDisposable.add(repository.getAllOrders()
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                }).doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(res -> {
                    responses.setValue(res);
                }, throwable -> {
                    failure.setValue(throwable.getMessage());
                }));
    }
    public void getOrdersChecked(){
        compositeDisposable.add(repository.getAllOrdersChecked()
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                }).doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(res -> {
                    responses.setValue(res);
                }, throwable -> {
                    failure.setValue(throwable.getMessage());
                }));
    }
    public void getOrdersUnchecked(){
        compositeDisposable.add(repository.getAllOrdersUnchecked()
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                }).doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(res -> {
                    responses.setValue(res);
                }, throwable -> {
                    failure.setValue(throwable.getMessage());
                }));
    }
    public void getOrdersCanceled(){
        compositeDisposable.add(repository.getAllOrdersCanceled()
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                }).doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(res -> {
                    responses.setValue(res);
                }, throwable -> {
                    failure.setValue(throwable.getMessage());
                }));
    }
}
