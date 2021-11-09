package com.example.mvvm.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.R;
import com.example.mvvm.adapter.ProductAdapter;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivityListProductBinding;
import com.example.mvvm.model.Product;
import com.example.mvvm.model.data.ProductPreview;
import com.example.mvvm.ui.product.add.AddProductActivity;
import com.example.mvvm.ui.product.edit.EditProductActivity;
import com.example.mvvm.ui.product.sale.SaleActivity;
import com.example.mvvm.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mon7 on 10/3/2021.
 */
public class ListProductActivity extends BaseActivity<ListProductViewModel, ActivityListProductBinding>{
    private List<ProductPreview> products = new ArrayList<>();
    private ProductAdapter adapter = new ProductAdapter();

    ItemTouchHelper.SimpleCallback itemTouchHelper
            = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT){
        @Override
        public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
            requestToDelete(viewHolder.getAbsoluteAdapterPosition());
        }
    };

    @NonNull
    @NotNull
    @Override
    protected ListProductViewModel createViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(ListProductViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list_product;
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
        viewModel.getResponse().observe(this, res->{
            viewModel.getProducts();
        });
        viewModel.getFailure().observe(this, msg -> {
            Log.d(Utils.myLog, msg);
            Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
        });
        viewModel.getListProducts().observe(this, response -> {
            products = response.getData().getResults();
            updateList();
        });

        binding.searchview.setQueryHint("Nhập tên sản phẩm");
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

    private void updateList() {
        adapter.setProducts(products);
        binding.rcvProducts.setAdapter(adapter);
        adapter.setOnItemClickListener(this::editItem);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(ListProductActivity.this, AddProductActivity.class);
            startActivity(intent);
        });
        binding.btnBack.setOnClickListener(v->{
            finish();
        });
        binding.btnSale.setOnClickListener(v->{
            Intent intent = new Intent(ListProductActivity.this, SaleActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getListProducts();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void getListProducts() {
        viewModel.getProducts();
        adapter.setProducts(products);
        binding.rcvProducts.setAdapter(adapter);
        adapter.setOnItemClickListener(this::editItem);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rcvProducts);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rcvProducts.getContext(),
                LinearLayoutManager.VERTICAL);
        binding.rcvProducts.addItemDecoration(dividerItemDecoration);
    }

    private void editItem(int i, View view) {
        Intent intent = new Intent(ListProductActivity.this, EditProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("productID", products.get(i).getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void requestToDelete(int position){
        ProductPreview p = products.get(position);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Bạn muốn xóa sản phẩm "+ p.getName()+"?");
        dialog.setCancelable(true);
        dialog.setPositiveButton("Có", (dialog1, id) ->{
            viewModel.deleteProduct(p.getId());
        });
        dialog.setNegativeButton("Không", (dialog1, id) -> {
            updateList();
            dialog1.cancel();
        });
        dialog.show();
    }
}
