package com.example.mvvm.model.response;

import com.example.mvvm.model.data.LoginData;

public class LoginResponse {
    private int code;
    private String msg;
    private LoginData data;

    public LoginResponse() {
    }

    public LoginResponse(int code, String msg, LoginData data) {
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

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
