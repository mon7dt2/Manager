package com.example.mvvm.model.body;

import com.example.mvvm.model.data.CustomerPreview;

import java.util.List;

/**
 * Created by Mon7 on 10/22/2021.
 */
public class CustomerPreviewBody{
    private List<CustomerPreview> results;

    public CustomerPreviewBody() {
    }

    public CustomerPreviewBody(List<CustomerPreview> results) {
        this.results = results;
    }

    public List<CustomerPreview> getResults() {
        return results;
    }

    public void setResults(List<CustomerPreview> results) {
        this.results = results;
    }
}
