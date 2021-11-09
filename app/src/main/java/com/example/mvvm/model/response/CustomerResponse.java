package com.example.mvvm.model.response;

import com.example.mvvm.model.body.CustomerPreviewBody;
import com.example.mvvm.model.data.CustomerPreview;

/**
 * Created by Mon7 on 10/22/2021.
 */
public class CustomerResponse{
    private int code;
    private String msg;
    private CustomerPreviewBody data;

    public CustomerResponse(int code, String msg, CustomerPreviewBody data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CustomerResponse() {
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

    public CustomerPreviewBody getData() {
        return data;
    }

    public void setData(CustomerPreviewBody data) {
        this.data = data;
    }
}
