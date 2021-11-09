package com.example.mvvm.model.response;

import com.example.mvvm.model.Statistic;

/**
 * Created by Mon7 on 10/27/2021.
 */
public class StatisticResponse{
    private int code;
    private String msg;
    private Statistic data;

    public StatisticResponse() {
    }

    public StatisticResponse(int code, String msg, Statistic data) {
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

    public Statistic getData() {
        return data;
    }

    public void setData(Statistic data) {
        this.data = data;
    }
}
