package com.example.mvvm.ui.category;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.base.BaseViewModel;
import com.example.mvvm.model.body.UpdateCatBody;
import com.example.mvvm.model.response.CategoryResponse;
import com.example.mvvm.model.response.DefaultResponse;
import com.example.mvvm.repository.Repository;
import com.example.mvvm.utils.Utils;

import javax.inject.Inject;

/**
 * Created by Mon7 on 9/30/2021.
 */
public class CategoryViewModel extends BaseViewModel{
    private Repository repository;
    private MutableLiveData<CategoryResponse> categories = new MutableLiveData<>();
    private MutableLiveData<DefaultResponse> defaultResponse = new MutableLiveData<>();
    private MutableLiveData<String> failure = new MutableLiveData<>();

    public MutableLiveData<String> getFailure() {
        return failure;
    }

    public MutableLiveData<DefaultResponse> getDefaultResponse() {
        return defaultResponse;
    }

    public MutableLiveData<CategoryResponse> getCategories() {
        return categories;
    }

    @Inject
    public CategoryViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getAllCategories(){
        compositeDisposable.add(repository.getCategories()
                .doOnSubscribe(d -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(categoryResponse -> {
                    Log.d(Utils.myLog, categoryResponse.getMsg());
                    categories.setValue(categoryResponse);
                }, throwable -> {
                    Log.d(Utils.myLog, throwable.getMessage());
                    failure.setValue(throwable.getMessage());
                }));
    }

    public void addCategory(String title){
        compositeDisposable.add(repository.addCategory(title)
                .doOnSubscribe(d -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(Response -> {
                    Log.d(Utils.myLog, Response.getMsg());
                    defaultResponse.setValue(Response);
                }, throwable -> {
                    Log.d(Utils.myLog, throwable.getMessage());
                    failure.setValue(throwable.getMessage());
                }));
    }

    public void updateCategory(UpdateCatBody body){
        compositeDisposable.add(repository.updateCategory(body)
                .doOnSubscribe(d -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(Response -> {
                    Log.d(Utils.myLog, Response.getMsg());
                    defaultResponse.setValue(Response);
                }, throwable -> {
                    Log.d(Utils.myLog, throwable.getMessage());
                    failure.setValue(throwable.getMessage());
                }));
    }

    public void deleteCategory(String id){
        compositeDisposable.add(repository.deleteCategory(id)
                .doOnSubscribe(d -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(Response -> {
                    Log.d(Utils.myLog, Response.getMsg());
                    defaultResponse.setValue(Response);
                }, throwable -> {
                    Log.d(Utils.myLog, throwable.getMessage());
                    failure.setValue(throwable.getMessage());
                }));
    }
}
