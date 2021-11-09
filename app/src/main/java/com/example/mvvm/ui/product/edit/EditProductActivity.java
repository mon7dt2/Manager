package com.example.mvvm.ui.product.edit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvm.R;
import com.example.mvvm.adapter.SpinnerAdapter;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivityAddProductBinding;
import com.example.mvvm.model.Category;
import com.example.mvvm.model.data.ProductDetailData;
import com.example.mvvm.ui.product.add.AddProductActivity;
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
public class EditProductActivity extends BaseActivity<EditProductViewModel, ActivityAddProductBinding>{
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
    private String productID;

    private static final int avtCodeGL= 1333;
    private static final int avtCodeGL_RESULT= 1444;
    private static final int cvCodeGL= 2333;
    private static final int cvCodeGL_RESULT= 2444;

    @NonNull
    @NotNull
    @Override
    protected EditProductViewModel createViewModel() {
        return new ViewModelProvider(this,viewModelFactory).get(EditProductViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_product;
    }

    @Override
    protected void initView() {
        adapter = new SpinnerAdapter(this);
        binding.btnAdd.setText("UPDATE");
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
        viewModel.getListCategories().observe(this, result -> {
            categories = result.getData().getResults();
            for (Category category : categories) {
                titles.add(category.getTitle());
            }
            adapter.setList(titles);
            binding.spinnerCategory.setAdapter(adapter);
        });
        viewModel.getResponse().observe(this, res -> {
            Utils.deleteCache(this);
            finish();
            Toast.makeText(this, "Cập nhật phẩm thành công", Toast.LENGTH_SHORT).show();
        });
        viewModel.getDetails().observe(this, res -> {
            setUpData(res.getData());
        });
        initSpinner();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                productID = bundle.getString("productID");
                viewModel.getDetail(productID);
            }
        }
    }

    @Override
    protected void initListener() {
        binding.imgPrimary.setOnClickListener( v->{
            ActivityCompat.requestPermissions(EditProductActivity.this, permissions, avtCodeGL);
        });
        binding.imgCover.setOnClickListener( v->{
            ActivityCompat.requestPermissions(EditProductActivity.this, permissions, cvCodeGL);
        });
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.btnAdd.setOnClickListener(v -> {
            sendRequestUpdate();
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void setUpData(ProductDetailData data) {
        binding.edtName.setText(data.getName());
        binding.edtSubCate.setText(data.getDescription());
        binding.edtSize.setText(data.getSize());
        binding.edtPrice.setText(String.valueOf(data.getPrice()));
        binding.edtQuantity.setText(String.valueOf(data.getQuantity()));

        for (int i = 0; i< categories.size(); i++) {
            if (data.getCategoryTitle().equals(categories.get(i).getTitle())){
                binding.spinnerCategory.setSelection(i);
            }
        }
        Random r = new Random();
        int token = r.nextInt();
        Glide.with(this)
                .load(data.getAvatarUrl() + "?" + token)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .placeholder(R.drawable.logozune)
                .error(R.drawable.defaultimg)
                .into(binding.imgPrimary);
        int token1 = r.nextInt();
        Glide.with(this)
                .load(data.getCoverUrl() + "?" + token1)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .placeholder(R.drawable.logozune)
                .error(R.drawable.defaultimg)
                .into(binding.imgCover);
    }

    private void openGal(int code) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, code);
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

    private void sendRequestUpdate(){
        String name = binding.edtName.getText().toString().trim();
        String subCate = binding.edtSubCate.getText().toString().trim();
        String size = binding.edtSize.getText().toString().trim();
        double price = Double.parseDouble(binding.edtPrice.getText().toString().trim());
        int quantity = Integer.parseInt(binding.edtQuantity.getText().toString().trim());

        Log.d(Utils.myLog, categoryID + categoryTitle);
        if (!haveImageAV && !haveImageCV) {
            createFileTmp(fileTmp1,1);
            createFileTmp(fileTmp2,2);
            viewModel.updateProduct(productID,name,subCate,price,size,quantity,avatar,cover);
        } else if (!haveImageAV){
            createFileTmp(fileTmp1,1);
            viewModel.updateProduct(productID,name,subCate,price,size,quantity,avatar,cover);
        } else if (!haveImageCV){
            createFileTmp(fileTmp2,2);
            viewModel.updateProduct(productID,name,subCate,price,size,quantity,avatar,cover);
        } else {
            viewModel.updateProduct(productID,name,subCate,price,size,quantity,avatar,cover);
        }
    }

    private void createFileTmp(File fileTmp, int type){
        if (type == 1) {
            BitmapDrawable drawable = (BitmapDrawable) binding.imgPrimary.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            int fileName = new Random().nextInt();
            try {
                fileTmp = viewModel.getImgCache(this, String.valueOf(fileName), bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(fileTmp, MediaType.parse("multipart/form-data"));
            avatar = MultipartBody.Part.createFormData("avatar", fileTmp.getName(), body);
        }
        if (type == 2) {
            BitmapDrawable drawable = (BitmapDrawable) binding.imgCover.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            int fileName = new Random().nextInt();
            try {
                fileTmp = viewModel.getImgCache(this, String.valueOf(fileName), bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(fileTmp, MediaType.parse("multipart/form-data"));
            cover = MultipartBody.Part.createFormData("cover", fileTmp.getName(), body);
        }
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
}
