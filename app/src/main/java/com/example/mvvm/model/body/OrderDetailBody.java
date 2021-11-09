package com.example.mvvm.model.body;

import com.example.mvvm.model.OrderDetail;

import java.util.List;

/**
 * Created by Mon7 on 10/22/2021.
 */
public class OrderDetailBody{
    private List<OrderDetail> results;

    public OrderDetailBody(List<OrderDetail> results) {
        this.results = results;
    }

    public OrderDetailBody() {
    }

    public List<OrderDetail> getResults() {
        return results;
    }

    public void setResults(List<OrderDetail> results) {
        this.results = results;
    }
}
