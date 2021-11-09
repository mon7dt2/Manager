package com.example.mvvm.ui.product.add;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm.R;
import com.example.mvvm.adapter.SpinnerAdapter;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivityAddProductBinding;
import com.example.mvvm.model.Category;
import com.example.mvvm.utils.RealPathUtil;
import com.example.mvvm.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Mon7 on 10/8/2021.
 */
public class AddProductActivity extends BaseActivity<AddProductViewModel, ActivityAddProductBinding>{
    private List<Category> categories = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private SpinnerAdapter adapter;
    private String categoryTitle;
    private String categoryID;
    private String[] permissions = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE};
    private File fileTmp1;
    private File fileTmp2;
    private MultipartBody.Part cover;
    private MultipartBody.Part avatar;
    private boolean haveImageCV;
    private boolean haveImageAV;

    private static final int avtCodeGL= 1333;
    private static final int avtCodeGL_RESULT= 1444;
    private static final int cvCodeGL= 2333;
    private static final int cvCodeGL_RESULT= 2444;

    @NonNull
    @NotNull
    @Override
    protected AddProductViewModel createViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(AddProductViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_product;
    }

    @Override
    protected void initView() {
        adapter = new SpinnerAdapter(this);
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
            if(msg.equals("HTTP 400")){
                String message = "Bạn cần nhập đủ thông tin sản phẩm";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getListCategories().observe(this, result -> {
            categories = result.getData().getResults();
            for (Category category : categories) {
                titles.add(category.getTitle());
            }
            adapter.setList(titles);
            binding.spinnerCategory.setAdapter(adapter);
        });
        viewModel.getResponse().observe(this, res -> {
            Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
            Utils.deleteCache(this);
            requestToAddMore();
        });
        initSpinner();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.imgPrimary.setOnClickListener( v->{
            ActivityCompat.requestPermissions(AddProductActivity.this, permissions, avtCodeGL);
        });
        binding.imgCover.setOnClickListener( v->{
            ActivityCompat.requestPermissions(AddProductActivity.this, permissions, cvCodeGL);
        });
        binding.btnBack.setOnClickListener(v->{
            finish();
        });
        binding.btnAdd.setOnClickListener(v->{
            sendRequestAdd();
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == avtCodeGL){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGal(avtCodeGL_RESULT);
            } 
        }
        if (requestCode == cvCodeGL){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGal(cvCodeGL_RESULT);
            }
        }
    }

    private void openGal(int code) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (requestCode == avtCodeGL_RESULT && resultCode == RESULT_OK) {
            if (data != null) {
                Uri mUri = data.getData();
                binding.imgPrimary.setImageURI(mUri);
                String absolutePath = RealPathUtil.getRealPath(this, mUri);
                Log.d("myLog", absolutePath);
                File file = new File(absolutePath);
                RequestBody body = RequestBody.create(file, MediaType.parse("multipart/form-data"));
                avatar = MultipartBody.Part.createFormData("avatar", file.getName(), body);
                haveImageAV = true;
            }
        } else if (requestCode == cvCodeGL_RESULT && resultCode == RESULT_OK) {
            if (data != null) {
                Uri mUri = data.getData();
                binding.imgCover.setImageURI(mUri);
                String absolutePath = RealPathUtil.getRealPath(this, mUri);
                Log.d("myLog", absolutePath);
                File file = new File(absolutePath);
                RequestBody body = RequestBody.create(file, MediaType.parse("multipart/form-data"));
                cover = MultipartBody.Part.createFormData("cover", file.getName(), body);
                haveImageCV = true;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initSpinner() {
        viewModel.getCategories();
        adapter.setList(titles);
        binding.spinnerCategory.setAdapter(adapter);
        binding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryTitle = titles.get(position);
                categoryID = categories.get(position).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void sendRequestAdd(){
        if (binding.edtName.getText() == null || binding.edtName.getText().toString().equals("")) {
            toastError();
            return;
        }
        if (binding.edtSubCate.getText() == null || binding.edtSubCate.getText().toString().equals("")) {
            toastError();
            return;
        }
        if (binding.edtSize.getText() == null || binding.edtSize.getText().toString().equals("")) {
            toastError();
            return;
        }
        if (binding.edtPrice.getText() == null || binding.edtPrice.getText().toString().equals("")) {
            toastError();
            return;
        }
        if (binding.edtQuantity.getText() == null || binding.edtQuantity.getText().toString().equals("")) {
            toastError();
            return;
        }

        String name = binding.edtName.getText().toString().trim();
        String subCate = binding.edtSubCate.getText().toString().trim();
        String size = binding.edtSize.getText().toString().trim();
        double price = Double.parseDouble(binding.edtPrice.getText().toString().trim());
        int quantity = Integer.parseInt(binding.edtQuantity.getText().toString().trim());
        
        if (!haveImageAV && !haveImageCV) {
            createFileTmp(fileTmp1,1);
            createFileTmp(fileTmp2,2);
            viewModel.addProduct(categoryID,name,subCate,price,size,quantity,avatar,cover);
        } else if (!haveImageAV){
            createFileTmp(fileTmp1,1);
            viewModel.addProduct(categoryID,name,subCate,price,size,quantity,avatar,cover);
        } else if (!haveImageCV){
            createFileTmp(fileTmp2,2);
            viewModel.addProduct(categoryID,name,subCate,price,size,quantity,avatar,cover);
        } else {
            viewModel.addProduct(categoryID,name,subCate,price,size,quantity,avatar,cover);
        }
    }

    private void toastError(){
        Toast.makeText(this, "Vui lòng nhập đu thông tin.", Toast.LENGTH_SHORT).show();
    }

    private void createFileTmp(File fileTmp, int type){
        BitmapDrawable drawable = (BitmapDrawable) AppCompatResources.getDrawable(this,R.drawable.logozune);
        assert drawable != null;
        Bitmap bitmap = drawable.getBitmap();
        int fileName = new Random().nextInt();
        try {
            fileTmp = viewModel.getImgCache(this, String.valueOf(fileName), bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(fileTmp, MediaType.parse("multipart/form-data"));
        if (type == 1) avatar = MultipartBody.Part.createFormData("avatar", fileTmp.getName(), body);
        if (type == 2) cover = MultipartBody.Part.createFormData("avatar", fileTmp.getName(), body);
    }

    private void requestToAddMore(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Bạn có muốn tiếp tục thêm sản phẩm?");
        dialog.setCancelable(false);
        dialog.setPositiveButton("Có", (dialog1, id) ->{
            resetLayout();
        });
        dialog.setNegativeButton("Không", (dialog1, id) -> {
            dialog1.cancel();
            finish();
        });
        dialog.show();
    }

    private void resetLayout(){
        binding.edtName.setText("");
        binding.edtQuantity.setText("");
        binding.edtPrice.setText("");
        binding.edtSize.setText("");
        binding.edtSubCate.setText("");
        binding.imgPrimary.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.defaultimg));
        binding.imgCover.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.defaultimg));
    }
}
