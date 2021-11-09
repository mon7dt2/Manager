package com.example.mvvm.ui.product;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.base.BaseViewModel;
import com.example.mvvm.model.response.DefaultResponse;
import com.example.mvvm.model.response.ProductsResponse;
import com.example.mvvm.repository.Repository;
import com.example.mvvm.utils.Utils;

import javax.inject.Inject;

/**
 * Created by Mon7 on 10/8/2021.
 */
public class ListProductViewModel extends BaseViewModel{
    private Repository repository;
    private MutableLiveData<ProductsResponse> products = new MutableLiveData<>();
    private MutableLiveData<String> failure = new MutableLiveData<>();
    private MutableLiveData<DefaultResponse> response = new MutableLiveData<>();

    public MutableLiveData<DefaultResponse> getResponse() {
        return response;
    }

    public MutableLiveData<String> getFailure() {
        return failure;
    }

    public MutableLiveData<ProductsResponse> getListProducts() {
        return products;
    }

    @Inject
    public ListProductViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getProducts(){
        compositeDisposable.add(repository.getProducts()
                .doOnSubscribe(d -> {
                    loading.setValue(true);
                }).doFinally(()->{
                    loading.setValue(false);
                }).subscribe(productsResponse -> {
                    products.setValue(productsResponse);
                }, throwable -> {
                    Log.d(Utils.myLog, throwable.getMessage());
                    failure.setValue(throwable.getMessage());
                }));
    }

    public void deleteProduct(String id){
        compositeDisposable.add(repository.deleteProduct(id)
                .doOnSubscribe(d -> {
                    loading.setValue(true);
                }).doFinally(()->{
                    loading.setValue(false);
                }).subscribe(productsResponse -> {
                    response.setValue(productsResponse);
                }, throwable -> {
                    Log.d(Utils.myLog, throwable.getMessage());
                    failure.setValue(throwable.getMessage());
                }));
    }
}
