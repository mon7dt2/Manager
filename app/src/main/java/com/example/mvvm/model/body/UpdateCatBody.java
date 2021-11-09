package com.example.mvvm.model.body;

/**
 * Created by Mon7 on 10/9/2021.
 */
public class UpdateCatBody{
    private String id;
    private String title;

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

    public UpdateCatBody(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public UpdateCatBody() {
    }
}
