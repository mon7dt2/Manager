package com.example.mvvm.api;

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

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //auth
    @POST("/api/auths/admin/login")
    Single<LoginResponse> login(@Header("Authorization") String encodedString);

    //category
    @GET("/api/category/categories")
    Single<CategoryResponse> getCategories();

    @POST("/api/category/addcategory")
    Single<DefaultResponse> addCategory(@Query("body") String body);

    @PUT("/api/category/updcategory")
    Single<DefaultResponse> updateCategory(@Body UpdateCatBody body);

    @POST("/api/category/delcategory")
    Single<DefaultResponse> deleteCategory(@Query("id") String id);

    //product
    @GET("/api/products/categorys/0")
    Single<ProductsResponse> getProducts();

    @Multipart
    @POST("/api/products/clothes/{id}")
    Single<DefaultResponse> addProduct(@Path("id") String id,
                                       @Query("name") String name,
                                       @Query("description") String description,
                                       @Query("price") double price,
                                       @Query("size") String size,
                                       @Query("quantity") int quantity,
                                       @Part MultipartBody.Part avatar,
                                       @Part MultipartBody.Part cover);

    @Multipart
    @PUT("/api/products/updateclothes/{productID}")
    Single<DefaultResponse> updateProduct(@Path("productID") String productID,
                                       @Query("name") String name,
                                       @Query("description") String description,
                                       @Query("price") double price,
                                       @Query("size") String size,
                                       @Query("quantity") int quantity,
                                       @Part MultipartBody.Part avatar,
                                       @Part MultipartBody.Part cover);

    @POST("/api/products/deleteclothes/")
    Single<DefaultResponse> deleteProduct(@Query("id") String id);

    @GET("/api/products/clothes/{id}")
    Single<ProductDetailResponse> getProductDetail(@Path("id") String id);

    @GET("/api/products/statistic/")
    Single<StatisticResponse> getStatistic();

    @PUT("/api/products/updatesale/{id}")
    Single<DefaultResponse> updateSale(@Path("id") String id,
                                       @Query("salePercent") float salePercent);

    @PUT("/api/products/removesale/{id}")
    Single<DefaultResponse> removeSale(@Path("id") String id);

    //order
    @PUT("/api/order/ordersuccess")
    Single<DefaultResponse> setOrderSuccess(@Query("id") String id);

    @PUT("/api/order/cancelorder")
    Single<DefaultResponse> cancelOrder(@Query("id") String id);

    @GET("/api/order/orders")
    Single<OrderResponse> getAllOrders();

    @GET("/api/order/orderscanceled")
    Single<OrderResponse> getAllOrdersCanceled();

    @GET("/api/order/orderschecked")
    Single<OrderResponse> getAllOrdersChecked();

    @GET("/api/order/ordersunchecked")
    Single<OrderResponse> getAllOrdersUnchecked();

    @GET("/api/order/getdetail/{id}")
    Single<OrderDetailResponse> getDetail(@Path("id") String id);

    @GET("/api/order/getorder/{id}")
    Single<OrderCtmResponse> getOrder(@Path("id") String id);

    //customers

    @GET("/api/admins/listcustomer")
    Single<CustomerResponse> getAllCustomers();

    @GET("/api/customers/profiles/{id}")
    Single<ProfileResponse> getProfile(@Path("id") String id);
}
