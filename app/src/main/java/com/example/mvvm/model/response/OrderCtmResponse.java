package com.example.mvvm.model.response;

import com.example.mvvm.model.Order;

/**
 * Created by Mon7 on 10/22/2021.
 */
public class OrderCtmResponse{
    private int code;
    private String msg;
    private Order data;


    public OrderCtmResponse() {
    }

    public OrderCtmResponse(int code, String msg, Order data) {
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

    public Order getData() {
        return data;
    }

    public void setData(Order data) {
        this.data = data;
    }
}
