package com.example.mvvm.model.response;

import com.example.mvvm.model.Profile;

/**
 * Created by Mon7 on 10/22/2021.
 */
public class ProfileResponse{
    private int code;
    private String msg;
    private Profile data;

    public ProfileResponse() {
    }

    public ProfileResponse(int code, String msg, Profile data) {
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

    public Profile getData() {
        return data;
    }

    public void setData(Profile data) {
        this.data = data;
    }
}
