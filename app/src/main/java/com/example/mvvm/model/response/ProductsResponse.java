package com.example.mvvm.model.response;

import com.example.mvvm.model.data.ProductData;

/**
 * Created by Mon7 on 10/10/2021.
 */
public class ProductsResponse{
    private int code;
    private String msg;
    private ProductData data;

    public ProductsResponse() {
    }

    public ProductsResponse(int code, String msg, ProductData data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public ProductData getData() {
        return data;
    }

    public void setData(ProductData data) {
        this.data = data;
    }
}
