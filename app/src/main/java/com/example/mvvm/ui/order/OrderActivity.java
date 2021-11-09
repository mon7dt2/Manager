package com.example.mvvm.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.mvvm.R;
import com.example.mvvm.adapter.OrderAdapter;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivityOrderBinding;
import com.example.mvvm.model.data.OrderPreview;
import com.example.mvvm.ui.order.orderdetail.OrderDetailActivity;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mon7 on 10/1/2021.
 */
public class OrderActivity extends BaseActivity<OrderViewModel, ActivityOrderBinding>{

    private List<OrderPreview> orders = new ArrayList<>();
    private OrderAdapter adapter = new OrderAdapter();
    @NonNull
    @NotNull
    @Override
    protected OrderViewModel createViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(OrderViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading){
                loadingDialog.show();
            } else {
                loadingDialog.hide();
            }
        });
        viewModel.getResponses().observe(this, res ->{
            orders = res.getData().getResults();
            updateList();
        });
        viewModel.getFailure().observe(this, msg -> {
            Log.d("myLog", msg);
            Toast.makeText(this, "error: " + msg, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void initData() {
        initOrderData();
    }

    @Override
    protected void initListener() {
        binding.btnBack.setOnClickListener(v ->{
            finish();
        });
        binding.btnFilter.setOnClickListener(v->{
            openDialogFilter();
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void updateList(){
        adapter.setOrders(orders);
        binding.rcvOrder.setAdapter(adapter);
        adapter.setOnItemClickListener((this::onItemClick));
    }

    private void initOrderData() {
        viewModel.getOrders();
        updateList();
        binding.rcvOrder.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rcvOrder.getContext(),
                LinearLayoutManager.VERTICAL);
        binding.rcvOrder.addItemDecoration(dividerItemDecoration);
    }

    private void openDialogFilter(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Lọc hóa đơn: ");
        String[] titles = {"Toàn bộ", "Đã xử lý", "Chưa xử lý", "Đã hủy"};
        dialog.setItems(titles, (dialog1, which) -> {
            switch (which){
                case 0:
                    viewModel.getOrders();
                    break;
                case 1:
                    viewModel.getOrdersChecked();
                    break;
                case 2:
                    viewModel.getOrdersUnchecked();
                    break;
                case 3:
                    viewModel.getOrdersCanceled();
                    break;
            }
        });
        dialog.setCancelable(true);
        dialog.setNegativeButton("Cancel", (dialog1, id) -> {
            dialog1.cancel();
        });
        dialog.show();
    }

    private void onItemClick(int position, View v) {
        Intent intent = new Intent(OrderActivity.this, OrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("orderID", orders.get(position).getId());
        bundle.putInt("state", orders.get(position).getIsCheck());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
