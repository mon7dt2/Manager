package com.example.mvvm.ui.order.orderdetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.base.BaseViewModel;
import com.example.mvvm.model.response.DefaultResponse;
import com.example.mvvm.model.response.OrderCtmResponse;
import com.example.mvvm.model.response.OrderDetailResponse;
import com.example.mvvm.repository.Repository;

import javax.inject.Inject;

/**
 * Created by Mon7 on 10/1/2021.
 */
public class OrderDetailViewModel extends BaseViewModel{
    private Repository repository;

    private  MutableLiveData<OrderDetailResponse> details = new MutableLiveData<>();
    private  MutableLiveData<OrderCtmResponse> ctmResponse = new MutableLiveData<>();
    private  MutableLiveData<String> responses = new MutableLiveData<>();
    private MutableLiveData<String> failure = new MutableLiveData<>();

    public MutableLiveData<String> getFailure() {
        return failure;
    }

    public MutableLiveData<String> getResponses() {
        return responses;
    }

    public MutableLiveData<OrderDetailResponse> getDetails() {
        return details;
    }

    public MutableLiveData<OrderCtmResponse> getCtmResponse() {
        return ctmResponse;
    }

    @Inject
    public OrderDetailViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getOrderDetails(String id){
        compositeDisposable.add(repository.getDetail(id)
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                }).doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(res -> {
                    details.setValue(res);
                }, throwable -> {
                    failure.setValue(throwable.getMessage());
                }));
    }

    public void getCustomerInfo(String id){
        compositeDisposable.add(repository.getOrder(id)
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                }).doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(res -> {
                    ctmResponse.setValue(res);
                }, throwable -> {
                    failure.setValue(throwable.getMessage());
                }));
    }


    public void solveOrder(String id){
        compositeDisposable.add(repository.setOrderSuccess(id)
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                }).doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(res -> {
                    responses.setValue("update");
                }, throwable -> {
                    failure.setValue(throwable.getMessage());
                }));
    }

    public void cancelOrder(String id){
        compositeDisposable.add(repository.cancelOrder(id)
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                }).doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(res -> {
                    responses.setValue("cancel");
                }, throwable -> {
                    failure.setValue(throwable.getMessage());
                }));
    }
}
