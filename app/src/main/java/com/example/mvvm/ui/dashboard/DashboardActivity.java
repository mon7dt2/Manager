package com.example.mvvm.ui.dashboard;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm.R;
import com.example.mvvm.adapter.OrderDataAdapter;
import com.example.mvvm.adapter.ProductDataAdapter;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivityDashboardBinding;
import com.example.mvvm.model.data.OrderData;
import com.example.mvvm.model.response.StatisticResponse;
import com.example.mvvm.ui.category.CategoryActivity;
import com.example.mvvm.ui.customer.CustomerActivity;
import com.example.mvvm.ui.order.OrderActivity;
import com.example.mvvm.ui.product.ListProductActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mon7 on 9/30/2021.
 */
public class DashboardActivity extends BaseActivity<DashboardViewModel, ActivityDashboardBinding>{

    private List<OrderData> listOrder = new ArrayList<>();
    private List<OrderData> listProducts= new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    protected DashboardViewModel createViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(DashboardViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void initView() {
        viewModel.getLoading().observe(this,isLoading->{
            if(isLoading != null){
                if(isLoading){
                    loadingDialog.show();
                }else {
                    loadingDialog.hide();
                }
            }
        });
        viewModel.getResponse().observe(this, this::setUpView);
        listOrder.add(new OrderData("Tổng đơn hàng",0,1));
        listOrder.add(new OrderData("Đơn hàng đã xử lý",0,2));
        listOrder.add(new OrderData("Đơn hàng chưa xử lý",0,2));
        listOrder.add(new OrderData("Đơn hàng đã hủy",0,2));
    }

    @Override
    protected void initData() {
       viewModel.getStatistic();
    }

    @Override
    protected void onResume() {
        viewModel.getStatistic();
        super.onResume();
    }

    @Override
    protected void initListener() {
        binding.btnProfile.setOnClickListener( v -> {
        });
        binding.btnCategory.setOnClickListener(v->{
            Intent intent = new Intent(DashboardActivity.this, CategoryActivity.class);
            startActivity(intent);
        });
        binding.btnOrder.setOnClickListener(v ->{
            Intent intent = new Intent(DashboardActivity.this, OrderActivity.class);
            startActivity(intent);
        });
        binding.btnCustomer.setOnClickListener(v->{
            Intent intent = new Intent(DashboardActivity.this, CustomerActivity.class);
            startActivity(intent);
        });
        binding.btnProduct.setOnClickListener(v->{
            Intent intent = new Intent(DashboardActivity.this, ListProductActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        showLogoutDialog();
    }

    private void showLogoutDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Do you want to sign out?");
        dialog.setCancelable(true);
        dialog.setPositiveButton("Yes", (dialog1, id) -> finish());
        dialog.setNegativeButton("No", (dialog1, id) -> dialog1.cancel());
        dialog.show();
    }

    private void setUpView(StatisticResponse res) {
        int totalProduct = res.getData().getTotalProduct();
        binding.txtTotalProduct.setText(String.valueOf(totalProduct));

        int cancel = res.getData().getTotalOrder() - res.getData().getOrderChecked() - res.getData().getOrderUnchecked();

        listOrder.get(0).setNumber(res.getData().getTotalOrder());
        listOrder.get(1).setNumber(res.getData().getOrderChecked());
        listOrder.get(2).setNumber(res.getData().getOrderUnchecked());
        listOrder.get(3).setNumber(cancel);

        OrderDataAdapter adapter1 = new OrderDataAdapter(this, listOrder);
        binding.rcvOrder.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        listProducts.clear();
        Map<String, Integer> map = res.getData().getListSubCate();
        Set<String> set = map.keySet();
        if (!set.isEmpty()) {
            for (String key : set) {
                if (map.get(key) != null) {
                    OrderData data = new OrderData(key, map.get(key));
                    listProducts.add(data);
                }
            }
        }
        ProductDataAdapter adapter2 = new ProductDataAdapter();
        adapter2.setData(listProducts);
        binding.rcvProduct.setAdapter(adapter2);
    }
}
