package com.example.mvvm.ui.category;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvm.R;
import com.example.mvvm.adapter.CategoryAdapter;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivityCategoryBinding;
import com.example.mvvm.model.Category;
import com.example.mvvm.model.body.UpdateCatBody;
import com.example.mvvm.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mon7 on 9/30/2021.
 */
public class CategoryActivity extends BaseActivity<CategoryViewModel, ActivityCategoryBinding>{
    private List<Category> listCategories = new ArrayList<>();
    private CategoryAdapter adapter = new CategoryAdapter();;

    @NonNull
    @NotNull
    @Override
    protected CategoryViewModel createViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(CategoryViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    protected void initView() {
        viewModel.getLoading().observe(this, isLoading -> {
            if(isLoading != null){
                if(isLoading){
                    loadingDialog.show();
                }else {
                    loadingDialog.hide();
                }
            }
        });
        viewModel.getCategories().observe(this, categoryResponse -> {
            if (categoryResponse != null){
                listCategories = categoryResponse.getData().getResults();
                updateList();
            }
        });
        viewModel.getDefaultResponse().observe(this, defaultResponse -> {
            viewModel.getAllCategories();
        });
        viewModel.getFailure().observe(this, failureResponse -> {
            Toast.makeText(this, failureResponse,Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void initData() {
        initCategoryData();
    }

    @Override
    protected void initListener() {
        binding.btnBack.setOnClickListener(v->{
            finish();
        });
        binding.btnAdd.setOnClickListener(v->{
            addCategory();
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void updateList(){
        adapter.setCategories(listCategories);
        binding.rcvCategory.setAdapter(adapter);
        adapter.setOnItemClickListener(this::createItemDialog);
    }

    private void initCategoryData() {
        viewModel.getAllCategories();
        adapter.setCategories(listCategories);
        binding.rcvCategory.setAdapter(adapter);
        adapter.setOnItemClickListener(this::createItemDialog);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rcvCategory.getContext(),
                LinearLayoutManager.VERTICAL);
        binding.rcvCategory.addItemDecoration(dividerItemDecoration);
    }

    private void createItemDialog(int position, View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this);
        builder.setTitle("Nhập tên danh mục: ");
        final EditText input = new EditText(CategoryActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        input.setText(listCategories.get(position).getTitle());
        builder.setCancelable(true);

        builder.setPositiveButton("Edit", (dialog, which) -> {
            //update category
            String name = input.getText().toString().trim();
            if(!name.equals("")){
                viewModel.updateCategory(new UpdateCatBody(listCategories.get(position).getId(), name));
            } else {
                Toast.makeText(this, "Vui lòng không bỏ trống tên danh mục", Toast.LENGTH_SHORT).show();
            }

        });
        builder.setNegativeButton("Delete", (dialog, which) -> {
            //delete
            String id = listCategories.get(position).getId();
            showDiaLogDelete(id);
        });
        builder.show();
    }

    private void showDiaLogDelete(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this);
        builder.setTitle("bạn có muốn xóa danh mục này không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Có", (dialog, which) -> {
           viewModel.deleteCategory(id);
        });
        builder.setNegativeButton("Không", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    private void addCategory(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nhập tên danh mục: ");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", (dialog, which) -> {
            String name = input.getText().toString().trim();
            if(!name.equals("")){
                viewModel.addCategory(name);
            } else {
                Toast.makeText(this, "Vui lòng không bỏ trống tên danh mục", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }


}
