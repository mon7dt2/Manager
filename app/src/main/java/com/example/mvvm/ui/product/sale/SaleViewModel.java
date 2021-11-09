package com.example.mvvm.ui.product.sale;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.base.BaseViewModel;
import com.example.mvvm.repository.Repository;

import javax.inject.Inject;

/**
 * Created by Mon7 on 10/8/2021.
 */
public class SaleViewModel extends BaseViewModel{
    private Repository repository;
    private MutableLiveData<String> response = new MutableLiveData<>();
    private MutableLiveData<String> failure = new MutableLiveData<>();

    public MutableLiveData<String> getResponse() {
        return response;
    }

    public MutableLiveData<String> getFailure() {
        return failure;
    }

    @Inject
    public SaleViewModel(Repository repository) {
        this.repository = repository;
    }

    public void updateSale(String id, float sale){
        compositeDisposable.add(repository.updateSale(id, sale)
                .doOnSubscribe(d -> {
                    loading.setValue(true);
                })
                .doFinally(()->{
                    loading.setValue(false);
                })
                .subscribe(res -> {
                    response.setValue("update");
                }, throwable -> {
                    failure.setValue(throwable.getMessage());
                }));
    }

    public void removeSale(String id){
        compositeDisposable.add(repository.removeSale(id)
                .doOnSubscribe(d -> {
                    loading.setValue(true);
                })
                .doFinally(()->{
                    loading.setValue(false);
                })
                .subscribe(res -> {
                    response.setValue("remove");
                }, throwable -> {
                    failure.setValue(throwable.getMessage());
                }));
    }
}
