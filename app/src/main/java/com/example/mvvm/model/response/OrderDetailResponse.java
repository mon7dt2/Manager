package com.example.mvvm.model.response;

import com.example.mvvm.model.body.OrderDetailBody;

/**
 * Created by Mon7 on 10/22/2021.
 */
public class OrderDetailResponse{
    private int code;
    private String msg;
    private OrderDetailBody data;

    public OrderDetailResponse() {
    }

    public OrderDetailResponse(int code, String msg, OrderDetailBody data) {
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

    public OrderDetailBody getData() {
        return data;
    }

    public void setData(OrderDetailBody data) {
        this.data = data;
    }
}
