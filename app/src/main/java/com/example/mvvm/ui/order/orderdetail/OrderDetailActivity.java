package com.example.mvvm.ui.order.orderdetail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm.R;
import com.example.mvvm.adapter.OrderDetailAdapter;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivityOrderDetailBinding;
import com.example.mvvm.model.Order;
import com.example.mvvm.model.OrderDetail;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mon7 on 10/1/2021.
 */
public class OrderDetailActivity extends BaseActivity<OrderDetailViewModel, ActivityOrderDetailBinding>{
    private List<OrderDetail> listDetails = new ArrayList<>();
    private String ID;
    private Order ctmInfo = new Order();
    private OrderDetailAdapter adapter = new OrderDetailAdapter();

    @NonNull
    @NotNull
    @Override
    protected OrderDetailViewModel createViewModel() {
        return new ViewModelProvider(this,viewModelFactory).get(OrderDetailViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int state = bundle.getInt("state");
        if ( state != 0 ) disableBtn();
        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading){
                loadingDialog.show();
            } else {
                loadingDialog.hide();
            }
        });
        viewModel.getDetails().observe(this, res ->{
            listDetails = res.getData().getResults();
            adapter.setDetails(listDetails);
            binding.rcvOrderDetail.setAdapter(adapter);
        });
        viewModel.getCtmResponse().observe(this, res -> {
            ctmInfo = res.getData();
            fillData(ctmInfo);
        });
        viewModel.getResponses().observe(this, res -> {
            if(res.equals("update")){
                disableBtn();
                createDialogNotification();
            } else if (res.equals("cancel")){
                disableBtn();
                createDialogDelete();
            }
        });
        viewModel.getFailure().observe(this, msg -> {
            Log.d("myLog", msg);
            Toast.makeText(this, "error: " + msg, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void initData() {
        ID = getOrderID();
        viewModel.getCustomerInfo(ID);
        viewModel.getOrderDetails(ID);
    }

    @Override
    protected void initListener() {
        binding.btnBack.setOnClickListener(v -> finish());
        binding.btnEditOrder.setOnClickListener(v -> {
            viewModel.solveOrder(ID);
        });
        binding.btnDeleteOrder.setOnClickListener(v->{
            viewModel.cancelOrder(ID);
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private String getOrderID(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        return bundle.getString("orderID");
    }

    private void disableBtn(){
        binding.btnEditOrder.setEnabled(false);
        binding.btnDeleteOrder.setEnabled(false);
    }

    private void fillData(Order order){
        adapter.setDetails(listDetails);
        binding.rcvOrderDetail.setAdapter(adapter);

        String id = "Mã đơn hàng: " + order.getId();
        String name = "Người nhận: " + order.getCustomerName();
        String address = "Địa chỉ: " + order.getAddress();
        String phone = "Số điện thoại: " + order.getPhone();

        String info = id + "\n" + name + "\n" + address + "\n" + phone;
        binding.txtInfoCustomer.setText(info);

        String total = order.getTotal() + "đ";
        binding.txtTotalProduct.setText(total);
    }

    private void createDialogNotification(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Đơn hàng giao thành công!");
        dialog.setCancelable(true);
        dialog.setNegativeButton("Đồng ý", (dialog1, id) -> {
            dialog1.cancel();
        });
        dialog.show();
    }

    private void createDialogDelete(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("bạn có đồng ý xóa hóa đơn này không?");
        dialog.setCancelable(true);
        dialog.setPositiveButton("Có", (dialog1, id) ->{
            openDialogClose();
        });
        dialog.setNegativeButton("Không", (dialog1, id) -> {
            dialog1.cancel();
        });
        dialog.show();
    }

    private void openDialogClose(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Xóa đơn hàng thành công!");
        dialog.setCancelable(false);
        dialog.setNegativeButton("Đồng ý", (dialog1, id) -> {
            finish();
        });
        dialog.show();
    }
}
