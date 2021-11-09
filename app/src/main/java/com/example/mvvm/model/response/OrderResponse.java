package com.example.mvvm.model.response;

import com.example.mvvm.model.body.OrderPreviewBody;

/**
 * Created by Mon7 on 10/22/2021.
 */
public class OrderResponse{
    private int code;
    private String msg;
    private OrderPreviewBody data;

    public OrderResponse() {
    }

    public OrderResponse(int code, String msg, OrderPreviewBody data) {
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

    public OrderPreviewBody getData() {
        return data;
    }

    public void setData(OrderPreviewBody data) {
        this.data = data;
    }
}
