package com.example.mvvm.ui.product.add;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.base.BaseViewModel;
import com.example.mvvm.model.response.CategoryResponse;
import com.example.mvvm.model.response.DefaultResponse;
import com.example.mvvm.repository.Repository;
import com.example.mvvm.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import okhttp3.MultipartBody;

/**
 * Created by Mon7 on 10/8/2021.
 */
public class AddProductViewModel extends BaseViewModel{
    private Repository repository;
    private MutableLiveData<DefaultResponse> response = new MutableLiveData<>();
    private MutableLiveData<String> failure = new MutableLiveData<>();
    private MutableLiveData<CategoryResponse> categories = new MutableLiveData<>();

    public MutableLiveData<CategoryResponse> getListCategories() {
        return categories;
    }
    public MutableLiveData<DefaultResponse> getResponse() {
        return response;
    }

    public MutableLiveData<String> getFailure() {
        return failure;
    }

    @Inject
    public AddProductViewModel(Repository repository) {
        this.repository = repository;
    }

    public void addProduct(String id,
                           String name,
                           String description,
                           double price,
                           String size,
                           int quantity,
                           MultipartBody.Part avatar,
                           MultipartBody.Part cover) {
        compositeDisposable.add(
                repository.addProduct(id, name, description, price,
                                size, quantity, avatar, cover).
                        doOnSubscribe(d -> loading.setValue(true))
                        .doFinally(() -> loading.setValue(false))
                        .subscribe(defaultResponse -> response.setValue(defaultResponse)
                                , throwable -> {
                            failure.setValue(throwable.getMessage());
                            Log.d(Utils.myLog, throwable.getMessage());
                        }));

    }

    public void getCategories(){
        compositeDisposable.add(repository.getCategories()
                .doOnSubscribe(d -> loading.setValue(true))
                .doFinally(() -> loading.setValue(false))
                .subscribe(defaultResponse -> categories.setValue(defaultResponse)
                        , throwable -> {
                    failure.setValue(throwable.getMessage());
                    Log.d(Utils.myLog, throwable.getMessage());
                }));
    }

    public File getImgCache(Context context, String filename, Bitmap bitmap) throws IOException {
        //create a file to write bitmap data
        File f = new File(context.getCacheDir(), filename);
        f.createNewFile();

        //Convert bitmap to byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapData = bos.toByteArray();

        //write the bytes in file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            fos.write(bitmapData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.flush();
            fos.close();
        }
        return f;
    }
}
