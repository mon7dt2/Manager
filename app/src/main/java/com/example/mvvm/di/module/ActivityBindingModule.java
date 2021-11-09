package com.example.mvvm.di.module;

import com.example.mvvm.ui.category.CategoryActivity;
import com.example.mvvm.ui.customer.CustomerActivity;
import com.example.mvvm.ui.customer.ctmdetail.CustomerDetailActivity;
import com.example.mvvm.ui.dashboard.DashboardActivity;
import com.example.mvvm.ui.login.LoginActivity;
import com.example.mvvm.ui.order.OrderActivity;
import com.example.mvvm.ui.order.orderdetail.OrderDetailActivity;
import com.example.mvvm.ui.product.ListProductActivity;
import com.example.mvvm.ui.product.add.AddProductActivity;
import com.example.mvvm.ui.product.edit.EditProductActivity;
import com.example.mvvm.ui.product.sale.SaleActivity;
import com.example.mvvm.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract DashboardActivity bindDashboardActivity();

    @ContributesAndroidInjector
    abstract CategoryActivity bindCategoryActivity();

    @ContributesAndroidInjector
    abstract OrderActivity bindOrderActivity();

    @ContributesAndroidInjector
    abstract OrderDetailActivity bindOrderDetailActivity();

    @ContributesAndroidInjector
    abstract CustomerActivity bindCustomerActivity();

    @ContributesAndroidInjector
    abstract CustomerDetailActivity bindCustomerDetailActivity();

    @ContributesAndroidInjector
    abstract ListProductActivity bindListProductActivity();

    @ContributesAndroidInjector
    abstract AddProductActivity bindAddProductActivity();

    @ContributesAndroidInjector
    abstract EditProductActivity bindEditProductActivity();

    @ContributesAndroidInjector
    abstract SaleActivity bindSaleActivity();
}
