package com.example.mvvm.ui.customer.ctmdetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvm.R;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivityCustomerInforBinding;
import com.example.mvvm.model.Customer;
import com.example.mvvm.model.Profile;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Created by Mon7 on 10/3/2021.
 */
public class CustomerDetailActivity extends BaseActivity<CustomerDetailViewModel, ActivityCustomerInforBinding>{

    private Profile customer;
    private boolean isExpanded = true;

    @NonNull
    @NotNull
    @Override
    protected CustomerDetailViewModel createViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(CustomerDetailViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_infor;
    }

    @Override
    protected void initView() {
        getData();
        viewModel.getLoading().observe(this,isLoading->{
            if(isLoading != null){
                if(isLoading){
                    loadingDialog.show();
                }else {
                    loadingDialog.hide();
                }
            }
        });
        viewModel.getResponse().observe(this, res-> {
            customer = res.getData();
            setUpView();
        });
        viewModel.getFailure().observe(this, msg -> {
            Log.d("myLog", msg);
            Toast.makeText(this, "error: "+ msg, Toast.LENGTH_SHORT).show();
        });
        initToolbar();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void getData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String id = bundle.getString("customerID");
        viewModel.getCustomerProfile(id);
    }

    private void setUpView(){
        if(customer != null){
            binding.txtEmail.setText(customer.getEmail());
            binding.txtPhone.setText(customer.getPhone());
            binding.txtUserName.setText(customer.getEmail());

            String gender = "";
            if (customer.getGender() == 1) gender = "Nam";
            else if (customer.getGender() == 0) gender = "Nữ";
            else gender = "Chưa có";
            binding.txtGender.setText(gender);

            String birthday = "";
            if (customer.getBirthday() != null) birthday = customer.getBirthday();
            else birthday = "Chưa có";
            binding.txtBirthday.setText(birthday);

            String address = "";
            if (customer.getAddress() != null) address = customer.getAddress();
            else address = "Chưa có";
            binding.txtLocation.setText(address);

            binding.txtName.setText(customer.getFullName());

            Random r = new Random();
            int token = r.nextInt();
            String url = customer.getAvatarUrl();
            if (url != null) {
                Glide.with(this)
                        .load(url + "?" + token)
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                        .placeholder(R.drawable.logozune)
                        .error(R.drawable.defaultimg)
                        .into(binding.imgAvatar);
            }
        }
    }

    private void initToolbar(){
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Thông tin khách hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.convertimg);
        Palette.from(bitmap).generate(palette -> {
            int myColors = palette.getVibrantColor(getResources().getColor(R.color.color_toolbar));
            binding.toolbarCollap.setContentScrimColor(myColors);
            binding.toolbarCollap.setStatusBarScrimColor(getResources().getColor(R.color.color_toolbar));
        });
        binding.appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            isExpanded = Math.abs(verticalOffset)<= 200;
            invalidateOptionsMenu();
        });
    }
}
