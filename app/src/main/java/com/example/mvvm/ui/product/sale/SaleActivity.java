package com.example.mvvm.ui.product.sale;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm.R;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivitySaleBinding;
import com.example.mvvm.utils.Utils;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Mon7 on 10/8/2021.
 */
public class SaleActivity extends BaseActivity<SaleViewModel, ActivitySaleBinding>{
    @NonNull
    @NotNull
    @Override
    protected SaleViewModel createViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(SaleViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale;
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
        viewModel.getFailure().observe(this, msg -> {
            Log.d(Utils.myLog, msg);
            Toast.makeText(this,"err: "+  msg,Toast.LENGTH_SHORT).show();
        });
        viewModel.getResponse().observe(this, res->{
            if (res.equals("update")) {
                Toast.makeText(this,"Thêm khuyến mãi thành công!!! ",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Xóa khuyến mãi thành công!!! ",Toast.LENGTH_SHORT).show();
            }
            binding.edtProductId.setText("");
            binding.edtSale.setText("");
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.btnUpdate.setOnClickListener(v->{
            if (binding.edtProductId.getText().length() > 0 && binding.edtSale.length()>0) {
                float sale = Float.parseFloat(binding.edtSale.getText().toString().trim());
                if (sale != 0) {
                    viewModel.updateSale(binding.edtProductId.getText().toString().trim(), sale);
                } else {
                    viewModel.removeSale(binding.edtProductId.getText().toString().trim());
                }
            } else {
                Toast.makeText(this,"Mời bạn nhập đủ thông tin ",Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnBack.setOnClickListener(v->{
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
