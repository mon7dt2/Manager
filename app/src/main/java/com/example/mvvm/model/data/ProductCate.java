package com.example.mvvm.model.data;

/**
 * Created by Mon7 on 10/10/2021.
 */
public class ProductCate{
    private String id;
    private String title;

    public ProductCate() {
    }

    public ProductCate(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
