package com.example.mvvm.model.response;

import com.example.mvvm.model.data.ProductData;
import com.example.mvvm.model.data.ProductDetailData;


/**
 * Created by Mon7 on 10/10/2021.
 */
public class ProductDetailResponse{

    private int code;
    private String msg;
    private ProductDetailData data;

    public ProductDetailResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ProductDetailData getData() {
        return data;
    }

    public void setData(ProductDetailData data) {
        this.data = data;
    }

    public ProductDetailResponse(int code, String msg, ProductDetailData data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
