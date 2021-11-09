package com.example.mvvm.model.response;

/**
 * Created by Mon7 on 10/9/2021.
 */
public class DefaultResponse{
    private int code;
    private String msg;
    private String data;

    public DefaultResponse() {
    }

    public DefaultResponse(int code, String msg, String data) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
