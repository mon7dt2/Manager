package com.example.mvvm.model.body;

import com.example.mvvm.model.data.OrderPreview;

import java.util.List;

/**
 * Created by Mon7 on 10/22/2021.
 */
public class OrderPreviewBody{
     private List<OrderPreview> results;

    public OrderPreviewBody(List<OrderPreview> results) {
        this.results = results;
    }

    public OrderPreviewBody() {
    }

    public List<OrderPreview> getResults() {
        return results;
    }

    public void setResults(List<OrderPreview> results) {
        this.results = results;
    }
}
