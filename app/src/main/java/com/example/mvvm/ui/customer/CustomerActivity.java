package com.example.mvvm.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvm.R;
import com.example.mvvm.adapter.CustomerAdapter;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivityCustomerBinding;
import com.example.mvvm.model.data.CustomerPreview;
import com.example.mvvm.ui.customer.ctmdetail.CustomerDetailActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mon7 on 10/3/2021.
 */
public class CustomerActivity extends BaseActivity<CustomerViewModel, ActivityCustomerBinding>{

    private List<CustomerPreview> customers = new ArrayList<>();
    private CustomerAdapter adapter = new CustomerAdapter();

    @NonNull
    @NotNull
    @Override
    protected CustomerViewModel createViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(CustomerViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer;
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
        viewModel.getResponse().observe(this, res-> {
            customers = res.getData().getResults();
            viewModel.getAllCustomers();
            adapter.setCustomers(customers);
            binding.rcvCustomer.setAdapter(adapter);
        });
        viewModel.getFailure().observe(this, msg -> {
            Log.d("myLog", msg);
            Toast.makeText(this, "error: "+ msg, Toast.LENGTH_SHORT).show();
        });
        binding.searchview.setQueryHint("Nhập tên khách hàng");
        binding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        initCustomerData();
    }

    @Override
    protected void initListener() {
        adapter.setOnItemClickListener(((position, v) -> {
            Intent intent = new Intent(CustomerActivity.this, CustomerDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("customerID", customers.get(position).getId());
            intent.putExtras(bundle);
            startActivity(intent);
        }));
        binding.btnBack.setOnClickListener(v->{
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void initCustomerData() {
        viewModel.getAllCustomers();
        adapter.setCustomers(customers);
        binding.rcvCustomer.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rcvCustomer.getContext(),
                LinearLayoutManager.VERTICAL);
        binding.rcvCustomer.addItemDecoration(dividerItemDecoration);
    }
}
