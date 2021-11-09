package com.example.mvvm.model.response;

import com.example.mvvm.model.data.CategoryData;

/**
 * Created by Mon7 on 10/9/2021.
 */
public class CategoryResponse{
    private int code;
    private String msg;
    private CategoryData data;

    public CategoryResponse() {
    }

    public CategoryResponse(int code, String msg, CategoryData data) {
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

    public CategoryData getData() {
        return data;
    }

    public void setData(CategoryData data) {
        this.data = data;
    }
}
