package com.example.mvvm.repository;

import com.example.mvvm.api.ApiService;
import com.example.mvvm.model.Post;
import com.example.mvvm.model.body.UpdateCatBody;
import com.example.mvvm.model.response.CategoryResponse;
import com.example.mvvm.model.response.CustomerResponse;
import com.example.mvvm.model.response.DefaultResponse;
import com.example.mvvm.model.response.LoginResponse;
import com.example.mvvm.model.response.OrderCtmResponse;
import com.example.mvvm.model.response.OrderDetailResponse;
import com.example.mvvm.model.response.OrderResponse;
import com.example.mvvm.model.response.ProductDetailResponse;
import com.example.mvvm.model.response.ProductsResponse;
import com.example.mvvm.model.response.ProfileResponse;
import com.example.mvvm.model.response.StatisticResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class Repository {
    private ApiService apiService;

    @Inject
    public Repository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<LoginResponse> login(String encodedString){
        return apiService.login(encodedString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //start repo category
    public Single<CategoryResponse> getCategories (){
        return apiService.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<DefaultResponse> addCategory(String title){
        return apiService.addCategory(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<DefaultResponse>  updateCategory(UpdateCatBody body){
        return apiService.updateCategory(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<DefaultResponse> deleteCategory(String id){
        return apiService.deleteCategory(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //end repo category

    //start repo customer
    //end repo customer

    //start repo product

    public Single<ProductsResponse> getProducts(){
        return apiService.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<DefaultResponse> addProduct(String id,
                                              String name,
                                              String description,
                                              double price,
                                              String size,
                                              int quantity,
                                              MultipartBody.Part avatar,
                                              MultipartBody.Part cover){
        return apiService.addProduct(id, name, description, price, size, quantity, avatar, cover)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<DefaultResponse> updateProduct(String id,
                                              String name,
                                              String description,
                                              double price,
                                              String size,
                                              int quantity,
                                              MultipartBody.Part avatar,
                                              MultipartBody.Part cover){
        return apiService.updateProduct(id, name, description, price, size, quantity, avatar, cover)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<DefaultResponse> deleteProduct(String productId){
        return apiService.deleteProduct(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ProductDetailResponse> getProductDetail(String id){
        return apiService.getProductDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<StatisticResponse> getStatistic(){
        return apiService.getStatistic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<DefaultResponse> updateSale(String productId, float salePercent){
        return apiService.updateSale(productId, salePercent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<DefaultResponse> removeSale(String productId){
        return apiService.removeSale(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //end repo product

    //order
    public Single<OrderResponse> getAllOrders(){
        return apiService.getAllOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<OrderResponse> getAllOrdersChecked(){
        return apiService.getAllOrdersChecked()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<OrderResponse> getAllOrdersUnchecked(){
        return apiService.getAllOrdersUnchecked()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<OrderResponse> getAllOrdersCanceled(){
        return apiService.getAllOrdersCanceled()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<OrderDetailResponse> getDetail(String id){
        return apiService.getDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<OrderCtmResponse> getOrder(String id){
        return apiService.getOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<DefaultResponse> setOrderSuccess(String id){
        return apiService.setOrderSuccess(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<DefaultResponse> cancelOrder(String id){
        return apiService.cancelOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //customer
    public Single<CustomerResponse> getAllCustomers(){
        return apiService.getAllCustomers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ProfileResponse> getProfile(String id){
        return apiService.getProfile(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
