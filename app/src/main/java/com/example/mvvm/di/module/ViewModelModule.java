package com.example.mvvm.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.example.mvvm.di.ViewModelFactory;
import com.example.mvvm.di.ViewModelKey;
import com.example.mvvm.ui.category.CategoryViewModel;
import com.example.mvvm.ui.customer.CustomerViewModel;
import com.example.mvvm.ui.customer.ctmdetail.CustomerDetailViewModel;
import com.example.mvvm.ui.dashboard.DashboardViewModel;
import com.example.mvvm.ui.login.LoginViewModel;
import com.example.mvvm.ui.order.OrderViewModel;
import com.example.mvvm.ui.order.orderdetail.OrderDetailViewModel;
import com.example.mvvm.ui.product.ListProductViewModel;
import com.example.mvvm.ui.product.add.AddProductViewModel;
import com.example.mvvm.ui.product.edit.EditProductViewModel;
import com.example.mvvm.ui.product.sale.SaleViewModel;
import com.example.mvvm.ui.splash.SplashViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel.class)
    abstract ViewModel bindDashboardViewModel(DashboardViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel.class)
    abstract ViewModel bindCategoryViewModel(CategoryViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel.class)
    abstract ViewModel bindOrderViewModel(OrderViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OrderDetailViewModel.class)
    abstract ViewModel bindOrderDetailViewModel(OrderDetailViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CustomerViewModel.class)
    abstract ViewModel bindCustomerViewModel(CustomerViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CustomerDetailViewModel.class)
    abstract ViewModel bindCustomerDetailViewModel(CustomerDetailViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ListProductViewModel.class)
    abstract ViewModel bindListProductViewModel(ListProductViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddProductViewModel.class)
    abstract ViewModel bindAddProductViewModel(AddProductViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(EditProductViewModel.class)
    abstract ViewModel bindEditProductViewModel(EditProductViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SaleViewModel.class)
    abstract ViewModel bindSaleViewModel(SaleViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
